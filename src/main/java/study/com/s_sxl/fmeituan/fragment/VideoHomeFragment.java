package study.com.s_sxl.fmeituan.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import study.com.s_sxl.carelib.base.BaseFragment;
import study.com.s_sxl.carelib.viewUtils.NavBar;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.bean.VideoData;
import study.com.s_sxl.fmeituan.contract.VideosListContract;
import study.com.s_sxl.fmeituan.model.VideosListModel;
import study.com.s_sxl.fmeituan.presenter.VideoListPresenter;

public class VideoHomeFragment extends BaseFragment<VideoListPresenter,VideosListModel> implements VideosListContract.View,OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.nvBar)
    NavBar mBavBar;

    @Bind(R.id.irc)
    IRecyclerView mRecycler;
    private CommonRecycleViewAdapter<VideoData> mAdapter;

    private String mVideoType ="VIDEO_TYPE";
    private int mStartPage=0;

    /**
     * 指定Fragment需加载的布局ID
     *
     * @return 需加载的布局ID
     */
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video;
    }

    private void initNav() {
        mBavBar.setBgColor(R.color.white);
        mBavBar.hideBack();
        mBavBar.setTitle("视频");
        mBavBar.showRightIcon(R.mipmap.seach_right, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLongToast("视频搜索");
            }
        });
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    /**
     * 初始化方法, 类似OnCreate, 仅在此方法中做初始化操作, findView与事件绑定请使用ButterKnife
     */
    @Override
    protected void initView() {
        initNav();
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CommonRecycleViewAdapter<VideoData>(getContext(), R.layout.item_video_list) {
            @Override
            public void convert(ViewHolderHelper helper, VideoData videoData) {
                helper.setImageRoundUrl(R.id.iv_logo, videoData.getTopicImg());
                helper.setText(R.id.tv_from, videoData.getTopicName());
                helper.setText(R.id.tv_play_time, String.format(getResources().getString(R.string.video_play_times),
                        String.valueOf(videoData.getPlayCount())));
                JCVideoPlayerStandard jcvideoPlayerStandard = helper.getView(R.id.videoplayer);
                Boolean setUp = jcvideoPlayerStandard.setUp(videoData.getMp4_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                        TextUtils.isEmpty(videoData.getDescription()) ? videoData.getTitle() + "" : videoData.getDescription());
                if (setUp) {
                    Glide.with(mContext).load(videoData.getCover())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .error(R.drawable.ic_empty_picture)
                            .crossFade().into(jcvideoPlayerStandard.thumbImageView);
                }
            }
        };

        mRecycler.setAdapter(mAdapter);
        mRecycler.setOnRefreshListener(this);
        mRecycler.setOnLoadMoreListener(this);
        mRecycler.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                if(JCVideoPlayerManager.listener() != null){
                    JCVideoPlayer videoPlayer = (JCVideoPlayer) JCVideoPlayerManager.listener();
                    if(((ViewGroup)view).indexOfChild(videoPlayer)!=-1&&videoPlayer.currentScreen==JCVideoPlayer.CURRENT_STATE_PLAYING){
                        JCVideoPlayer.releaseAllVideos();
                    }
                }
            }
        });

        initData();
    }

    private void initData() {
        if(mAdapter.getSize() <=0){
            mStartPage = 0;
            mPresenter.getVideosListDataRequest(mVideoType,mStartPage);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onRefresh() {
        mAdapter.getPageBean().setRefresh(true);
        mStartPage = 0;
        mRecycler.setRefreshing(true);
        mPresenter.getVideosListDataRequest(mVideoType,mStartPage);
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        mAdapter.getPageBean().setRefresh(false);
        //发起请求
        mRecycler.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        mPresenter.getVideosListDataRequest(mVideoType, mStartPage);
    }

    @Override
    public void returnVideosListData(List<VideoData> newsSummaries) {
        if (newsSummaries != null) {
            mStartPage += 1;
            if (mAdapter.getPageBean().isRefresh()) {
                mRecycler.setRefreshing(false);
                mAdapter.replaceAll(newsSummaries);
            } else {
                if (newsSummaries.size() > 0) {
                    mRecycler.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    mAdapter.addAll(newsSummaries);
                } else {
                    mRecycler.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        if( mAdapter.getPageBean().isRefresh()) {
            /*loadedTip.setLoadingTip(LoadingTip.LoadStatus.error);
            loadedTip.setTips(msg);*/
            mRecycler.setRefreshing(false);
        }else{
            mRecycler.setLoadMoreStatus(LoadMoreFooterView.Status.ERROR);
        }
    }
}
