package lsh.com.hotfixframework.buglyapp;

import android.app.Application;
import android.content.Intent;

import lsh.com.libtinker.BaseTinkerApplicationLike;


public class ApplicationLike extends BaseTinkerApplicationLike {
    public ApplicationLike(Application application, int tinkerFlags,
                                     boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                     long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }
}