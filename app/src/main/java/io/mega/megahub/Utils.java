package io.mega.megahub;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class Utils {
    public static String getVersion(Context context) {
        String versionName = "1.0.0";
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void printLog() {
        String error = "hello good";
//        if (error != null) {
//            Log.e("xxx", error);
//        }
        Log.e("xxx", error);
    }
}
