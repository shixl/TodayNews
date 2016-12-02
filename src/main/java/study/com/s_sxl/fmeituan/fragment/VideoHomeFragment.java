package study.com.s_sxl.fmeituan.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;

import butterknife.Bind;
import study.com.s_sxl.carelib.fragment.BaseFragment;
import study.com.s_sxl.carelib.viewUtils.NavBar;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.bean.VideoData;

public class VideoHomeFragment extends BaseFragment {

    @Bind(R.id.nvBar)
    NavBar mBavBar;

    @Bind(R.id.irc)
    IRecyclerView mRecycler;

    /**
     * 初始化方法, 类似OnCreate, 仅在此方法中做初始化操作, findView与事件绑定请使用ButterKnife
     *
     * @param savedInstanceState
     */
    @Override
    protected void init(Bundle savedInstanceState) {
        initNav();
    }

    /**
     * 指定Fragment需加载的布局ID
     *
     * @return 需加载的布局ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    private void initNav() {
        mBavBar.setBgColor(R.color.white);
        mBavBar.hideBack();
        mBavBar.setTitle("视频");
        mBavBar.showRightIcon(R.mipmap.seach_right, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastMessage("视频搜索");
            }
        });
        initView();
    }

    private void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        new CommonRecycleViewAdapter<VideoData>(getContext(), R.layout.item_video_list) {
            @Override
            public void convert(ViewHolderHelper helper, VideoData videoData) {
                helper.setImageRoundUrl(R.id.iv_logo,videoData.getTopicImg());
                helper.setText(R.id.tv_from,videoData.getTopicName());
                helper.setText(R.id.tv_play_time,String.format(getResources().getString(R.string.video_play_times),
                        String.valueOf(videoData.getPlayCount())));

            }
        };
    }

}
