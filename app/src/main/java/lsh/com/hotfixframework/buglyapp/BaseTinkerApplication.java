package lsh.com.hotfixframework.buglyapp;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;


public class BaseTinkerApplication extends TinkerApplication {
    public BaseTinkerApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "lsh.com.hotfixframework.buglyapp.ApplicationLike");
    }
}