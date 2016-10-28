package study.com.s_sxl.fmeituan.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import butterknife.Bind;
import study.com.s_sxl.carelib.activity.BaseActivity;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.constant.TabConstant;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener {

    @Bind(android.R.id.tabhost)
    FragmentTabHost mTab;
    @Bind(R.id.real_tab_content)
    FrameLayout realTabContent;

    private boolean isFullScreen = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mTab.setup(this,super.getSupportFragmentManager()
                ,R.id.real_tab_content);
        mTab.getTabWidget().setDividerDrawable(null);
        initTab();
        mTab.setOnTabChangedListener(this);

    }

    private void initTab() {
        String tabs[]= TabConstant.getTabsTxt();
        for(int i=0;i<tabs.length;i++){
            TabHost.TabSpec tabSpec =mTab.newTabSpec(tabs[i]).setIndicator(getTabView(i));
            mTab.addTab(tabSpec,TabConstant.getFragments()[i],null);
            mTab.setTag(i);
        }
    }

    private View getTabView(int idx){
        View view= LayoutInflater.from(this).inflate(R.layout.view_tab_content,null);
        ((TextView)view.findViewById(R.id.tv_tab_text)).setText(TabConstant.getTabsTxt()[idx]);
        if(idx==0){
            ((TextView)view.findViewById(R.id.tv_tab_text)).setTextColor(Color.RED);
            ((ImageView)view.findViewById(R.id.iv_tab_icon)).setImageResource(TabConstant.getTabsImgLight()[idx]);
        }else{
            ((ImageView)view.findViewById(R.id.iv_tab_icon)).setImageResource(TabConstant.getTabsImg()[idx]);
        }
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        updateTab();
        updateColor(tabId);
    }

    private void updateTab(){
        TabWidget tabw=mTab.getTabWidget();
        for(int i=0;i<tabw.getChildCount();i++){
            View view=tabw.getChildAt(i);
            ImageView iv=(ImageView)view.findViewById(R.id.iv_tab_icon);
            if(i==mTab.getCurrentTab()){
                ((TextView)view.findViewById(R.id.tv_tab_text)).setTextColor(Color.RED);
                iv.setImageResource(TabConstant.getTabsImgLight()[i]);
            }else{
                ((TextView)view.findViewById(R.id.tv_tab_text)).setTextColor(getResources().getColor(R.color.text_main));
                iv.setImageResource(TabConstant.getTabsImg()[i]);
            }
        }
    }

    public void updateColor(String tabId){
        if(tabId.equals(TabConstant.getTabsTxt()[1])||tabId.equals(TabConstant.getTabsTxt()[2])){
            if(isFullScreen){
                resetFragmentView();
            }
            StatusBarUtil.setColor(this,getResources().getColor(R.color.white),0);
        }else if(tabId.equals(TabConstant.getTabsTxt()[3])){
            isFullScreen = true;
            resetFragmentImageView();
            StatusBarUtil.setTransparentForImageViewInFragment(MainActivity.this, null);
        }else if(tabId.equals(TabConstant.getTabsTxt()[0])){
            if(isFullScreen){
                resetFragmentView();
            }
            StatusBarUtil.setColor(this,getResources().getColor(R.color.red),0);
        }
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
            if (realTabContent!= null) {
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

    @Override
    protected void setStatusBar() {
        isFullScreen = true;
        StatusBarUtil.setColor(this,getResources().getColor(R.color.red),0);
    }

    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

}
