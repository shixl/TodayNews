package study.com.s_sxl.fmeituan.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import butterknife.Bind;
import study.com.s_sxl.carelib.activity.BaseActivity;
import study.com.s_sxl.carelib.viewUtils.tab.InterceptedFragmentTabHost;
import study.com.s_sxl.carelib.viewUtils.tab.TabNavigator;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.fragment.FollowFragment;
import study.com.s_sxl.fmeituan.fragment.HomeFragment;
import study.com.s_sxl.fmeituan.fragment.UserFragment;
import study.com.s_sxl.fmeituan.fragment.VideoHomeFragment;

public class MainActivity extends BaseActivity implements TabNavigator.TabNavigatorContent {

    @Bind(android.R.id.tabhost)
    InterceptedFragmentTabHost mTab;
    @Bind(R.id.real_tab_content)
    FrameLayout realTabContent;

    private TabNavigator mNavigator = new TabNavigator();
    private int[] mBgRecourse = {R.drawable.tab_1_bg, R.drawable.tab_2_bg, R.drawable.tab_3_bg, R.drawable.tab_4_bg};
    private String[] mTabs;
    private boolean isFullScreen = false;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mTabs = new String[]{getString(R.string.tab_home), getString(R.string.tab_business),
                getString(R.string.tab_mine), getString(R.string.tab_more)};
        mNavigator.setup(this, mTab, this, getSupportFragmentManager(), R.id.real_tab_content);
        //把tab间的分隔线置为null
        mTab.getTabWidget().setDividerDrawable(null);

        //处理不同Fragment的状态栏颜色
        mTab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals(mTabs[3])) {
                    isFullScreen = true;
                    resetFragmentImageView();
                    StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0, null);
                } else if (tabId.equals(mTabs[1]) || tabId.equals(mTabs[2])) {
                    if(isFullScreen){
                        resetFragmentView();
                    }
                    StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.white), 0);
                } else if (tabId.equals(mTabs[0])) {
                    if(isFullScreen){
                        resetFragmentView();
                    }
                    setStatusBar();
                }
            }
        });
    }

    /**
     * 根据position获取每个tab标签视图
     *
     * @param position tab标签位置
     * @return tab标签视图
     */
    @Override
    public View getTabView(int position) {
        View view = getLayoutInflater().inflate(R.layout.view_tab_content, null);

        ImageView ivTab = (ImageView) view.findViewById(R.id.iv_tab_icon);
        TextView tvTab = (TextView) view.findViewById(R.id.tv_tab_text);

        ivTab.setImageResource(mBgRecourse[position]);
        tvTab.setText(mTabs[position]);

        return view;
    }

    /**
     * 根据position获取切换至目标Fragment要传递的数据Bundle
     *
     * @param position 目标Fragment位置
     * @return 数据Bundle
     */
    @Override
    public Bundle getArgs(int position) {
        return null;
    }

    /**
     * 获取Fragment的类对象数组
     *
     * @return Fragment的类对象数组
     */
    @Override
    public Class[] getFragmentClasses() {
        return new Class[]{HomeFragment.class, VideoHomeFragment.class, FollowFragment.class,
                UserFragment.class};
    }

    /**
     * 获取每个Fragment的tag
     *
     * @return Fragment的tag数组
     */
    @Override
    public String[] getTabTags() {
        return mTabs;
    }

    @Override
    protected void setStatusBar() {
        //isFullScreen = true;
        int color = getResources().getColor(R.color.red);
        StatusBarUtil.setColor(this, color, 0);
    }

    public void resetFragmentView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View contentView = findViewById(android.R.id.content);
            if (contentView != null) {
                ViewGroup rootView;
                rootView = (ViewGroup) ((ViewGroup) contentView).getChildAt(0);
                if (rootView.getPaddingTop() != 0) {
                    rootView.setPadding(0, 0, 0, 0);
                }
            }
            if (realTabContent != null){
                realTabContent.setPadding(0, getStatusBarHeight(this), 0, 0);
            }
        }
    }

    public void resetFragmentImageView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View contentView = findViewById(android.R.id.content);
            if (contentView != null) {
                ViewGroup rootView;
                rootView = (ViewGroup) ((ViewGroup) contentView).getChildAt(0);
                if (rootView.getPaddingTop() != 0) {
                    rootView.setPadding(0, 0, 0, 0);
                }
            }
            if (realTabContent != null){
                realTabContent.setPadding(0, 0, 0, 0);
            }
        }
    }
    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

}
