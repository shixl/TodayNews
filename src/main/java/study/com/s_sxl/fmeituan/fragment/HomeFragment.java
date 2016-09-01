package study.com.s_sxl.fmeituan.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.List;

import butterknife.Bind;
import study.com.s_sxl.carelib.fragment.BaseFragment;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.adapter.TodayFragmentPagerAdapter;
import study.com.s_sxl.fmeituan.constant.DataSimulation;

public class HomeFragment extends BaseFragment{


    @Bind(R.id.vp)
    ViewPager mVp;

    @Bind(android.R.id.tabs)
    TabLayout mTabs;

    /**
     * 初始化方法, 类似OnCreate, 仅在此方法中做初始化操作, findView与事件绑定请使用ButterKnife
     *
     * @param savedInstanceState
     */
    @Override
    protected void init(Bundle savedInstanceState) {
        initFragment();
    }

    /**
     * 指定Fragment需加载的布局ID
     *
     * @return 需加载的布局ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    private void initFragment() {
        List<Fragment> fragments = DataSimulation.getInstance().getFragment();
        mVp.setAdapter(new TodayFragmentPagerAdapter(fragments,getFragmentManager()));
        mTabs.setupWithViewPager(mVp);
    }

}
