package com.example.helloketty.util;

import android.content.Context;

import java.io.File;

public class Utils {

    public static  String log_page_tag = "Page Info";
    public static  String log_info_tag = "Debug Info";

    public static String getAppPath(Context context) {

        File file=context.getFilesDir();
        String path=file.getAbsolutePath();
        return path;
    }
}