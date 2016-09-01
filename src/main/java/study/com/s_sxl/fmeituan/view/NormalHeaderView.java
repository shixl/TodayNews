package study.com.s_sxl.fmeituan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

import study.com.s_sxl.carelib.pullRefreshView.layout.BaseHeaderView;
import study.com.s_sxl.carelib.pullRefreshView.support.type.LayoutType;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.utils.AnimUtil;

public class NormalHeaderView extends BaseHeaderView {

    private TextView mTv;
    private ImageView mTag;
    private View mPro;
    private View mState;

    public NormalHeaderView(Context context) {
        this(context,null);
    }

    public NormalHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NormalHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.header_view,this,true);
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
        ViewHelper.setTranslationY(mState,0);
        ViewHelper.setTranslationY(mPro,0);

        switch (state){
            case NONE:
                break;

            case PULLING:
                mTv.setText("下拉推荐");
                AnimUtil.startRotation(mTag,0);
                break;
            case LOOSENT_O_REFRESH:
                mTv.setText("松开推荐");
                AnimUtil.startRotation(mTag,180);
                break;
            case REFRESHING:
                mTv.setText("正在推荐中...");
                AnimUtil.startShow(mPro, 0.1f, 400, 200);
                //AnimUtil.startHide(mTv);
                AnimUtil.startHide(mTag);
                break;
            case REFRESH_CLONE:
                AnimUtil.startFromY(mTag, -2 * mTag.getHeight());
                AnimUtil.startToY(mPro, 2 * mPro.getHeight());
                mState.setVisibility(View.VISIBLE);
                mPro.setVisibility(View.VISIBLE);
                mTv.setVisibility(View.INVISIBLE);
                mTag.setVisibility(View.INVISIBLE);
                mTv.setText("推荐完成");

                break;
        }
    }

    @Override
    public int getLayoutType() {
        return LayoutType.LAYOUT_NORMAL;
    }
}
