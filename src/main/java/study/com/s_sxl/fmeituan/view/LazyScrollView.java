package study.com.s_sxl.fmeituan.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by zengjz on 2016/1/22.
 * scrollview回弹效果
 */
public class LazyScrollView<T extends View> extends ScrollView {
    private View inner;// 孩子View

    private float y;// 点击时y坐标
    T mRefreshableView;
    private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否�?��动画.)

    private boolean isCount = false;// 是否�?��计算

    private boolean mShowViewWhileRefreshing;

    private boolean mScrollingWhileRefreshingEnabled;

    private Interpolator mScrollAnimationInterpolator;


    private ScrollViewListener mScrollViewListener;

    //滑动隐藏控件回调监听
    private OnScrolledShowandHideListener mScrolledShowandHideListener;
    private int HIDE_THRESHOLD = 30;//显示或隐藏触发的滚动阀值
    private int scrolledDistance = 0;
    private boolean controlsVisible = true;

    /**
     * 加了两个构造方法
     */
    public LazyScrollView(Context context) {
        super(context);
    }

    public LazyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LazyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /***
     * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之�? 即使子类覆盖�?onFinishInflate
     * 方法，也应该调用父类的方法，使该方法得以执行.
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
    }

    /***
     * 监听touch
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner != null) {
            commOnTouchEvent(ev);
        }

        return super.onTouchEvent(ev);
    }

    /***
     * 触摸事件
     *
     * @param ev
     */
    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                // 手指松开.
                if (isNeedAnimation()) {
                    animation();
                    isCount = false;
                }
                break;
            /***
             * 排除出第�?��移动计算，因为第�?��无法得知y坐标�?在MotionEvent.ACTION_DOWN中获取不到，
             * 因为此时是MyScrollView的touch事件传�?到到了LIstView的孩子item上面.�?��从第二次计算�?��.
             * 然�?我们也要进行初始化，就是第一次移动的时�?让滑动距离归0. 之后记录准确了就正常执行.
             */
            case MotionEvent.ACTION_MOVE:
                final float preY = y;// 按下时的y坐标
                float nowY = ev.getY();// 时时y坐标
                int deltaY = (int) (preY - nowY);// 滑动距离
                if (!isCount) {
                    deltaY = 0; // 在这里要�?.
                }

                y = nowY;
                // 当滚动到�?��或�?�?��时就不会再滚动，这时移动布局
                if (isNeedMove()) {
                    // 初始化头部矩�?
                    if (normal.isEmpty()) {
                        // 保存正常的布�?���?
                        normal.set(inner.getLeft(), inner.getTop(),
                                inner.getRight(), inner.getBottom());
                    }
                    // Log.e("jj", "矩形�? + inner.getLeft() + "," + inner.getTop()
                    // + "," + inner.getRight() + "," + inner.getBottom());
                    // 移动布局
                    inner.layout(inner.getLeft(), inner.getTop() - deltaY / 2,
                            inner.getRight(), inner.getBottom() - deltaY / 2);
                }
                isCount = true;
                break;

            default:
                break;
        }
    }

    /***
     * 回缩动画
     */
    public void animation() {
        // �?��移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
                normal.top);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // 设置回到正常的布�?���?
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);

        // Log.e("jj", "回归�? + normal.left + "," + normal.top + "," +
        // normal.right
        // + "," + normal.bottom);

        normal.setEmpty();

    }

    // 是否�?���?��动画
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    /***
     * 是否�?��移动布局 inner.getMeasuredHeight():获取的是控件的�?高度
     * <p/>
     * getHeight()：获取的是屏幕的高度
     *
     * @return
     */
    public boolean isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // Log.e("jj", "scrolly=" + scrollY);
        // 0是顶部，后面那个是底�?
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollViewListener != null)
            mScrollViewListener.onScrollChanged(l, t, oldl, oldt);

        if (mScrolledShowandHideListener != null) {
            //根据上下滑动距离显示隐藏
            //Log.i("Tag","Y-"+y+"   OldY-"+oldy+"   ABS-"+Math.abs(y-oldy));
            int dy = t - oldt;
            if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
                mScrolledShowandHideListener.onHide();
                controlsVisible = false;
                scrolledDistance = 0;
            } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
                mScrolledShowandHideListener.onShow();
                controlsVisible = true;
                scrolledDistance = 0;
            }
            if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
                scrolledDistance += dy;
            }

        }


    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.mScrollViewListener = scrollViewListener;
    }

    public interface ScrollViewListener {

        void onScrollChanged(int x, int y, int oldx, int oldy);

    }

    public void setOnScrolledShowandHideListener(OnScrolledShowandHideListener scrolledShowandHideListener) {
        this.mScrolledShowandHideListener = scrolledShowandHideListener;
    }

    public interface OnScrolledShowandHideListener {
        void onHide();

        void onShow();
    }
}
