package study.com.s_sxl.fmeituan.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import study.com.s_sxl.carelib.utils.ToastMgr;
import study.com.s_sxl.fmeituan.R;

public class CustomPopupWindow extends PopupWindow {

    @Bind(R.id.iv_close)
    ImageView mIvClose;

    @Bind(R.id.et_name)
    EditText mEtName;

    @Bind(R.id.et_psd)
    EditText mEtPwd;

    @Bind(R.id.tv_login)
    TextView mTvLogin;

    private Context mContext;

    public CustomPopupWindow(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_login, null);
        ButterKnife.bind(this, view);
        initPop(view);
    }

    private void initPop(View view) {
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(1000);
        setAnimationStyle(R.style.PopupWindow_animation);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);
        update();
    }

    @OnClick({R.id.iv_close, R.id.tv_login})
    public void close(View view) {
        switch (view.getId()) {
            case R.id.iv_close:

                dismiss();

                break;
            case R.id.tv_login:
                ToastMgr.show(mEtName.getText().toString()+ mEtPwd.getText().toString());
                dismiss();
                break;

        }

    }
}
