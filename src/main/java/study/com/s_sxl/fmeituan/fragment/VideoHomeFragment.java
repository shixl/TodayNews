package study.com.s_sxl.fmeituan.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import study.com.s_sxl.carelib.fragment.BaseFragment;
import study.com.s_sxl.carelib.viewUtils.NavBar;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.adapter.VideoAdapter;
import study.com.s_sxl.fmeituan.bean.VideoBean;
import study.com.s_sxl.fmeituan.constant.DataSimulation;

public class VideoHomeFragment extends BaseFragment {

    @Bind(R.id.nvBar)
    NavBar mBavBar;
    @Bind(R.id.recycler)
    RecyclerView mRecycler;
    private List<VideoBean> mVideos;

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
        mVideos = DataSimulation.getInstance().getVideos(10);
        initView();
    }

    private void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        VideoAdapter mAdapter = new VideoAdapter(getContext(),mVideos);
        mRecycler.setAdapter(mAdapter);
    }

}
