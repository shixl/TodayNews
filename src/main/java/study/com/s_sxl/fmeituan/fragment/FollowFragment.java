package study.com.s_sxl.fmeituan.fragment;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import study.com.s_sxl.carelib.fragment.BaseFragment;
import study.com.s_sxl.fmeituan.R;

public class FollowFragment extends BaseFragment {

    @Bind(R.id.tv_up)
    TextView tvUp;

    /**
     * 初始化方法, 类似OnCreate, 仅在此方法中做初始化操作, findView与事件绑定请使用ButterKnife
     *
     * @param savedInstanceState
     */
    @Override
    protected void init(Bundle savedInstanceState) {

    }

    /**
     * 指定Fragment需加载的布局ID
     *
     * @return 需加载的布局ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_follow;
    }


    @OnClick(R.id.tv_up)
    public void onClick() {

    }
}
