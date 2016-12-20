package com.zhihaofans.texttext;

import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by zhihaofans on 2016/12/20.
 */

public class SysAct {
    public static void Tcopy(String content, Context context)
    {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }
    public static String Tpaste(Context context)
    {
        try {
            ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
            return cmb.getText().toString().trim();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "";
    }
}
