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
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.bean.UserBean;
import study.com.s_sxl.fmeituan.db.DbHelper;

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
    private DbHelper mDbHelper;

    public CustomPopupWindow(Context context, DbHelper dbHelper) {
        this.mContext = context;
        this.mDbHelper = dbHelper;
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
                UserBean user = new UserBean();
                user.phoneNumber =  mEtName.getText().toString().trim();
                user.password = mEtPwd.getText().toString().trim();
                user.userName = "止于最好";
                user.headImg = "http://img4.imgtn.bdimg.com/it/u=2894838897,3395831603&fm=21&gp=0.jpg";
                user.nickName ="nidaye";
                mDbHelper.addUser(user,new StringBuffer());

                if(mOnGetUserInfoListener != null){
                    mOnGetUserInfoListener.getUserInfoData(user.userName);
                }

                reSet();
                dismiss();
                break;
        }
    }

    private OnGetUserInfoListener mOnGetUserInfoListener;

    public void setOnGetUserInfoListener(OnGetUserInfoListener onGetUserInfoListener){
        mOnGetUserInfoListener = onGetUserInfoListener;
    }

    /**
     * 用户信息的回调接口
     */
    public interface OnGetUserInfoListener{
        void getUserInfoData(String str);
    }

    public void reSet(){
        mEtName.setText("");
        mEtPwd.setText("");
    }
}
