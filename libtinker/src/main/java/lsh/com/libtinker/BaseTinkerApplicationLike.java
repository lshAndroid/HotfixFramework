package lsh.com.libtinker;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.entry.DefaultApplicationLike;



public class BaseTinkerApplicationLike extends DefaultApplicationLike {
    private static Application mContext;
    public BaseTinkerApplicationLike(Application application, int tinkerFlags,
                                     boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                     long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplication();

        String buglyId=BuildConfig.BUGLYIDBUILD;
        if (buglyId==null||"".equals(buglyId)){
            Toast.makeText(mContext,"未从Gradle中获得buglyid",Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(mContext,"buglyId:"+buglyId,Toast.LENGTH_SHORT).show();
            //热修复功能--匹配buglyid
            TinkerUtil.initBugly(getApplication(), buglyId, BuildConfig.DEBUG);
            onTinkerApplicationCreate();
        }

    }

    /**
     * 子类使用此方法进行其他初始化
     */
    protected void onTinkerApplicationCreate() {

    }

    /**
     * 获取上下文
     * @return
     */
    public static Application getContext(){
        return mContext;
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        Beta.unInit();
    }
}

