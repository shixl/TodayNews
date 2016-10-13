package study.com.s_sxl.fmeituan.activity;

import android.os.Bundle;
import android.os.Environment;

import butterknife.Bind;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import study.com.s_sxl.carelib.activity.BaseActivity;
import study.com.s_sxl.fmeituan.R;

public class PlayVideoActivity extends BaseActivity {

    private final static String Path = Environment.getExternalStorageDirectory() + "/Test/a.mp4";
    @Bind(R.id.surface_view)
    VideoView mSurfaceView;
    private MediaController mController;

    @Override
    protected void init(Bundle savedInstanceState) {
        //设置视频解码监听
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        initVideo();
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_item;
    }

    private void initVideo() {
        // mSurfaceView = (VideoView) findViewById(R.id.surface_view);
        mSurfaceView.setVideoPath(Path);//设置播放的路径
        mController = new MediaController(this);//实例化控制器
        mController.show(5000);
        mSurfaceView.setMediaController(mController);
        mSurfaceView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
        mSurfaceView.requestFocus();
    }


}
