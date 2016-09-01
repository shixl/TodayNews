package study.com.s_sxl.fmeituan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

import study.com.s_sxl.carelib.pullRefreshView.layout.BaseFooterView;
import study.com.s_sxl.carelib.pullRefreshView.support.type.LayoutType;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.utils.AnimUtil;

public class NormalFooterView extends BaseFooterView {

    private TextView mTv;
    private ImageView mTag;
    private View mPro;
    private View mState;

    public NormalFooterView(Context context) {
        this(context,null);
    }

    public NormalFooterView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NormalFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.footer_view,this,true);
        mTv = (TextView) findViewById(R.id.text);
        mTag = (ImageView) findViewById(R.id.tag);
        mPro = findViewById(R.id.progress);
        mState = findViewById(R.id.state);
    }

    @Override
    public float getSpanHeight() {
        return getHeight();
    }

    @Override
    protected void onStateChange(int state) {
        if(mTv == null || mTag == null || mPro == null || mState == null){
            return ;
        }

        mState.setVisibility(INVISIBLE);
        mPro.setVisibility(INVISIBLE);
        mTv.setVisibility(VISIBLE);
        mTag.setVisibility(VISIBLE);

        ViewHelper.setAlpha(mTv,1);
        ViewHelper.setAlpha(mTag,1);

        switch (state){
            case NONE:
                break;

            case PULLING:
                mTv.setText("加载更多");
                AnimUtil.startRotation(mTag,0);
                break;
            case LOOSENT_O_LOAD:
                mTv.setText("松开加载");
                AnimUtil.startRotation(mTag,180);
                break;
            case LOADING:
                mTv.setText("正在加载中...");
                AnimUtil.startShow(mPro, 0.1f, 400, 200);
                //AnimUtil.startHide(mTv);
                AnimUtil.startHide(mTag);
                break;
            case LOAD_CLONE:
                AnimUtil.startScale(mState, 0.3f, 1f, 500, 50, new OvershootInterpolator());
                AnimUtil.startShow(mState, 0.1f, 300, 150);
                AnimUtil.startHide(mPro, 150, 0);
                mTv.setVisibility(View.INVISIBLE);
                mTag.setVisibility(View.INVISIBLE);
                mTv.setText("加载完成");
                break;
        }
    }

    @Override
    public int getLayoutType() {
        return LayoutType.LAYOUT_NORMAL;
    }
}
