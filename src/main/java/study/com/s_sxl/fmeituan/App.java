package study.com.s_sxl.fmeituan;

import android.app.Application;

import io.vov.vitamio.Vitamio;
import study.com.s_sxl.carelib.utils.PreferencesHelper;
import study.com.s_sxl.carelib.utils.ToastMgr;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        Vitamio.initialize(this);
        ToastMgr.init(getApplicationContext());
        PreferencesHelper.init(getApplicationContext());
    }
}
