package com.zhihaofans.texttext;

/**
 * Created by zhihaofans on 2016/12/18.
 */

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CloseActivityClass{


    public static List<Activity> activityList = new ArrayList<Activity>();

    public static void exitClient(Context context)
    {
        Log.d("sdfas", "----- exitClient -----");
        // 关闭所有Activity
        for (int i = 0; i < activityList.size(); i++)
        {
            if (null != activityList.get(i))
            {
                activityList.get(i).finish();
            }
        }

        ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE );
        activityMgr.restartPackage(context.getPackageName());

        System.exit(0);
    }
}