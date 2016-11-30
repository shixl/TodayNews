package study.com.s_sxl.fmeituan.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
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
    private String phoneNumber;
    private String psd;
    private OnGetUserInfoListener mOnGetUserInfoListener;

    public CustomPopupWindow(Context context, DbHelper dbHelper) {
        this.mContext = context;
        this.mDbHelper = dbHelper;
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_login, null);
        ButterKnife.bind(this, view);
        initPop(view);
        mDbHelper.initSQLite(context);
    }

    /**
     * 初始化窗体
     * @param view
     */
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

                reSet();

                break;
            case R.id.tv_login:
                login();

                break;
        }
    }

    /**
     * 登录相关操作
     */
    private void login() {
        phoneNumber = mEtName.getText().toString().trim();
        psd = mEtPwd.getText().toString().trim();

        if (phoneNumber.equals("") || psd.equals("")) {
            ToastMgr.show("用户名或密码不能为空");
            return;
        }

        UserBean user = new UserBean();
        mDbHelper.getUserInfo(user, new StringBuffer());

        if(TextUtils.isEmpty(user.phoneNumber) && TextUtils.isEmpty(user.password)){
            saveUser(user);
            if (mOnGetUserInfoListener != null) {
                mOnGetUserInfoListener.getUserInfoData(user);
           }
            reSet();
        }else if(user.phoneNumber.equals(phoneNumber)&& user.password.equals(psd)){
            if (mOnGetUserInfoListener != null) {
                mOnGetUserInfoListener.getUserInfoData(user);
            }
            reSet();
        }else {
            ToastMgr.show("用户名或密码错误");
            return;
        }
    }

    /**
     * 保存用户信息
     * @param user
     */
    private void saveUser(UserBean user) {
        user.phoneNumber = phoneNumber;
        user.password = psd;
        user.userName = "止于最好";
        user.headImg = "http://img4.imgtn.bdimg.com/it/u=2894838897,3395831603&fm=21&gp=0.jpg";
        user.nickName = "nidaye";
        mDbHelper.addUser(user, new StringBuffer());
    }

    public void setOnGetUserInfoListener(OnGetUserInfoListener onGetUserInfoListener) {
        mOnGetUserInfoListener = onGetUserInfoListener;
    }

    /**
     * 重置窗体各类信息
     */
    private void reSet() {
        mEtName.setText("");
        mEtPwd.setText("");
        dismiss();
    }

    /**
     * 用户信息的回调接口
     */
    public interface OnGetUserInfoListener {
        void getUserInfoData(UserBean data);
    }
}
