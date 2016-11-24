package study.com.s_sxl.fmeituan.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.OnClick;
import study.com.s_sxl.carelib.fragment.BaseFragment;
import study.com.s_sxl.carelib.viewUtils.PullScrollView;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.constant.Constant;
import study.com.s_sxl.fmeituan.view.CustomPopupWindow;

public class UserFragment extends BaseFragment implements PullScrollView.OnTurnListener {

    @Bind(R.id.iv_header)
    ImageView mIvHeader;

    @Bind(R.id.scroll_view)
    PullScrollView mScrollView;

    @Bind(R.id.tv_login)
    TextView mTvLogin;

    @Bind(R.id.iv_login)
    ImageView mIvLogin;

    @Bind(R.id.ll_tv_set)
    LinearLayout mLlTv;
    @Bind(R.id.rl_msg)
    RelativeLayout rlMsg;
    @Bind(R.id.rl_off)
    RelativeLayout rlOff;
    @Bind(R.id.rl_play)
    RelativeLayout rlPlay;
    @Bind(R.id.rl_buy)
    RelativeLayout rlBuy;
    @Bind(R.id.rl_b_news)
    RelativeLayout rlBNews;
    @Bind(R.id.rl_feedBook)
    RelativeLayout rlFeedBook;
    private CustomPopupWindow mCustomPopupWindow;

    /**
     * 初始化方法, 类似OnCreate, 仅在此方法中做初始化操作, findView与事件绑定请使用ButterKnife
     *
     * @param savedInstanceState
     */
    @Override
    protected void init(Bundle savedInstanceState) {
        Picasso.with(getContext()).load(Constant.URL_USER).into(mIvHeader);
        mScrollView.setHeader(mIvHeader);
        mScrollView.setOnTurnListener(this);
    }

    /**
     * 指定Fragment需加载的布局ID
     *
     * @return 需加载的布局ID
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    /**
     * 更多登录方式
     */
    @OnClick(R.id.tv_login)
    public void onClick() {

    }

    @OnClick(R.id.iv_login)
    public void onIvClick(){
        controlPop(mIvLogin);
    }

    @OnClick({R.id.rl_msg, R.id.rl_off, R.id.rl_play, R.id.rl_buy, R.id.rl_b_news, R.id.rl_feedBook})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_msg:
                showToastMessage("测试");
                break;
            case R.id.rl_off:

                break;
            case R.id.rl_play:

                break;
            case R.id.rl_buy:

                break;
            case R.id.rl_b_news:

                break;
            case R.id.rl_feedBook:

                break;
        }
    }

    /**
     * 操作窗体
     * @param mIvLogin
     */
    private void controlPop(ImageView mIvLogin) {
        if (mCustomPopupWindow == null) {
            initPopupWindow();
        }

        if (mCustomPopupWindow.isShowing()) {
            mCustomPopupWindow.dismiss();
        } else {
            mCustomPopupWindow.showAtLocation(mIvLogin, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
            lp.alpha = 0.3f;
            getActivity().getWindow().setAttributes(lp);
        }
    }

    /**
     * 泡泡窗体
     */
    private void initPopupWindow() {
        mCustomPopupWindow = new CustomPopupWindow(getContext());
        mCustomPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow()
                        .getAttributes();// 1.设置完全透明主题
                lp.alpha = 1.0f;// 2. 设置window的alpha值 (0.0 - 1.0)
                getActivity().getWindow().setAttributes(lp);
            }
        });
   }

    @Override
    public void onTurn() {

    }
}
