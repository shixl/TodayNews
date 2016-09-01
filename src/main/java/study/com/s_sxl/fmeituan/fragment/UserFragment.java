package study.com.s_sxl.fmeituan.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.OnClick;
import study.com.s_sxl.carelib.fragment.BaseFragment;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.constant.Constant;

public class UserFragment extends BaseFragment {

    @Bind(R.id.iv_header)
    ImageView mIvHeader;

    @Bind(R.id.ll_iv)
    LinearLayout mLlIv;

    @Bind(R.id.tv_login)
    TextView mTvLogin;

    @Bind(R.id.ll_tv)
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

    /**
     * 初始化方法, 类似OnCreate, 仅在此方法中做初始化操作, findView与事件绑定请使用ButterKnife
     *
     * @param savedInstanceState
     */
    @Override
    protected void init(Bundle savedInstanceState) {
        Picasso.with(getContext()).load(Constant.URL_USER).into(mIvHeader);
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


    @OnClick({R.id.rl_msg, R.id.rl_off, R.id.rl_play, R.id.rl_buy, R.id.rl_b_news, R.id.rl_feedBook})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_msg:

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
}
