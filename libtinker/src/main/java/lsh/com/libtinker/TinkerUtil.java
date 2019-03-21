package lsh.com.libtinker;

import android.content.Context;
import android.widget.Toast;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;

import java.util.Locale;


public class TinkerUtil {

    /**
     * 检查补丁及用bugly检查应用更新-放在最前的地方，防止其他意外
     */
    public static void checkTinkerPatch() {
        Beta.checkUpgrade(false, false);//
    }

    /**
     * 初始化腾讯bugly
     */
    public static void initBugly(final Context context, String buglyId, boolean isDebug) {
        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true;
        // 设置是否自动合成补丁，默认为true
        Beta.canAutoPatch = true;
        // 设置是否提示用户重启，默认为false
        Beta.canNotifyUserRestart = true;
        // 补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                Toast.makeText(context, "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Toast.makeText(context,
                        String.format(Locale.getDefault(), "%s %d%%",
                                Beta.strNotificationDownloading,
                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String msg) {
                Toast.makeText(context, "补丁下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Toast.makeText(context, "补丁下载失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onApplySuccess(String msg) {
                Toast.makeText(context, "补丁应用成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                Toast.makeText(context, "补丁应用失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {

            }
        };

        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        Bugly.setIsDevelopmentDevice(context, true);
        // 多渠道需求塞入
        // String channel = WalleChannelReader.getChannel(getApplication());
        // Bugly.setAppChannel(getApplication(), channel);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        Bugly.init(context, buglyId, isDebug);
    }

    /**
     * 设置热更新的测试设备
     * @param contxt
     */
    /*public static void setBuglyDebugDevice(Context contxt) {
        String imei=SystemUtils.getIMEI(contxt);
        if ("860286032384590".equals(imei) || "860286032384594".equals(imei)
                || "865774020485326".equals(imei) || "865774020485327".equals(imei)
                || "866032026315123".equals(imei) || "868029027630169".equals(imei)) {
            LogUtils.i("此为测试设备：IMEI==" + imei);
            Bugly.setIsDevelopmentDevice(UIUtils.getContext(), true);//标记测试设备
        }
    }*/
}
