package study.com.s_sxl.fmeituan;

import android.content.Context;
import android.content.res.Resources;

import study.com.s_sxl.carelib.BaseApplication;
import study.com.s_sxl.carelib.utils.PreferencesHelper;
import study.com.s_sxl.carelib.utils.ToastMgr;

public class App extends BaseApplication {

    public static App baseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    public static Context getAppContext(){
        return baseApp;
    }

    public static Resources getAppResources() {
        return baseApp.getResources();
    }

    private void init() {
        baseApp = this;
        //Vitamio.initialize(this);
        ToastMgr.init(getApplicationContext());
        PreferencesHelper.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
