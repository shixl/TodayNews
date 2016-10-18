package study.com.s_sxl.fmeituan.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;
import study.com.s_sxl.carelib.activity.BaseActivity;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.view.CustomMediaController;

public class PlayVideoActivity extends BaseActivity implements Runnable{

   private final static String Path = Environment.getExternalStorageDirectory() + "/Test/a.mp4";
    @Bind(R.id.surface_view)
    VideoView mVideoView;
    private CustomMediaController mController;

    private static final int TIME = 0;
    private static final int BATTERY = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TIME:
                    mController.setTime(msg.obj.toString());
                    break;
                case BATTERY:
                    mController.setBattery(msg.obj.toString());
                    break;
            }
        }
    };

    @Override
    protected void init(Bundle savedInstanceState) {

        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = PlayVideoActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //设置视频解码监听
       if (!LibsChecker.checkVitamioLibs(this)) {
            return;
       }

        initVideo();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_play_video;
    }

    private void initVideo() {
        mVideoView.setVideoPath(Path);//设置播放的路径
        mController = new CustomMediaController(this,mVideoView,this);//实例化控制器
        mController.show(5000);
        mVideoView.setMediaController(mController);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
        mVideoView.requestFocus();
        registerBoradcastReceiver();
        new Thread( this).start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        if (mVideoView != null){
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }

    private BroadcastReceiver batteryBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
                //获取当前电量
                int level = intent.getIntExtra("level", 0);
                //电量的总刻度
                int scale = intent.getIntExtra("scale", 100);
                //把它转成百分比
                //tv.setText("电池电量为"+((level*100)/scale)+"%");
                Message msg = new Message();
                msg.obj = (level*100)/scale+"";
                msg.what = BATTERY;
                mHandler.sendMessage(msg);
            }
        }
    };

    public void registerBoradcastReceiver() {
        //注册电量广播监听
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryBroadcastReceiver, intentFilter);
    }

    @Override
    public void run() {
        while (true) {
            //时间读取线程
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String str = sdf.format(new Date());
            Message msg = new Message();
            msg.obj = str;
            msg.what = TIME;
            mHandler.sendMessage(msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void setStatusBar() {
        int color = getResources().getColor(R.color.transparent);
        StatusBarUtil.setColor(this, color, 0);
    }
}
