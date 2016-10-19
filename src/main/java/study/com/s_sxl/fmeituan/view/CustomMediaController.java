package study.com.s_sxl.fmeituan.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import study.com.s_sxl.fmeituan.R;

public class CustomMediaController extends MediaController {

    private Context mContext;
    private VideoView mVideoView;
    private Activity mActivity;
    private int controllerWidth = 0;//设置mediaController高度为了使横屏时top显示在屏幕顶端
    private GestureDetector mGestureDetector;
    private ImageButton mTopBack;
    private ImageView mBattery;
    private TextView mTime;
    private TextView mBatteryName;


    public CustomMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMediaController(Context context, VideoView videoView) {
        super(context);
        this.mContext = context;
        this.mVideoView = videoView;
    }

    public CustomMediaController(Context context, VideoView videoView, Activity activity) {
        super(context);
        this.mContext = context;
        this.mVideoView = videoView;
        this.mActivity = activity;

        WindowManager win = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        controllerWidth = win.getDefaultDisplay().getWidth();

        mGestureDetector = new GestureDetector(context, new MyGestureListener());
    }

    @Override
    protected View makeControllerView() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.video_view, null);
        view.setMinimumWidth(controllerWidth);
        mTopBack = (ImageButton) view.findViewById(R.id.mediacontroller_top_back);
        mBattery = (ImageView) view.findViewById(R.id.mediacontroller_imgBattery);
        mTopBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity != null) {
                    mActivity.finish();
                }
            }
        });
        mTime = (TextView) view.findViewById(R.id.mediacontroller_time);
        mBatteryName = (TextView) view.findViewById(R.id.mediacontroller_Battery);

        return view;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event))
            return true;
        // 处理手势结束
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            //当手势结束，并且是单击结束时，控制器隐藏/显示
            toggleMediaControlsVisiblity();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        //双击暂停或开始
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            playOrPause();
            return true;
        }
    }

    //设置视频播放时长
    public void setTime(String time) {
        if (mTime != null)
            mTime.setText(time);
    }

    //显示电量
    public void setBattery(String stringBattery){
        if(mTime != null && mBattery != null){
            mBatteryName.setText( stringBattery + "%");
//            int battery = Integer.valueOf(stringBattery);
//            if(battery < 15)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_15));
//            if(battery < 30 && battery >= 15)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_15));
//            if(battery < 45 && battery >= 30)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_30));
//            if(battery < 60 && battery >= 45)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_45));
//            if(battery < 75 && battery >= 60)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_60));
//            if(battery < 90 && battery >= 75)img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_75));
//            if(battery > 90 )img_Battery.setImageDrawable(getResources().getDrawable(R.drawable.battery_90));
        }
    }

    //隐藏/显示
    private void toggleMediaControlsVisiblity() {
        if (isShowing()) {
            hide();
        } else {
            show();
        }
    }

    //播放与暂停
    private void playOrPause() {
        if (mVideoView != null)
            if (mVideoView.isPlaying()) {
                mVideoView.pause();
            } else {
                mVideoView.start();
            }
    }
}
