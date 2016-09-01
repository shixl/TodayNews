package study.com.s_sxl.fmeituan.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import study.com.s_sxl.carelib.pullRefreshView.layout.FlingLayout;

public class CustomFlingLayout extends FlingLayout {

    public CustomFlingLayout(Context context) {
        super(context);
    }

    public CustomFlingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFlingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean onScroll(float y) {

        View view = getPullView();
        int height = view.getMeasuredHeight();
        if(y > 0){
            ViewCompat.setPivotY(view,0);
            ViewCompat.setScaleY(view,(height + y)/height);
        }else {
            ViewCompat.setPivotY(view, height);
            ViewCompat.setScaleY(view, (height - y) / height);
        }
        return true;
    }
}
