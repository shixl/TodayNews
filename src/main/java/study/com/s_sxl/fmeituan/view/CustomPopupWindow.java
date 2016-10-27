package study.com.s_sxl.fmeituan.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import study.com.s_sxl.fmeituan.R;

public class CustomPopupWindow extends PopupWindow{

    private Context mContext;

    public CustomPopupWindow(Context context){
        this.mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_login, null);
        initPop(view);
    }

    private void initPop(View view) {
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(1100);
        setAnimationStyle(R.style.PopupWindow_animation);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);
        update();
    }

}
