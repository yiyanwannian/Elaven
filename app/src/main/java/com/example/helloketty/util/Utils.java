package com.example.helloketty.util;

import android.content.Context;

import java.io.File;

public class Utils {
    public static String getAppPath(Context context) {

        File file=context.getFilesDir();
        String path=file.getAbsolutePath();
        return path;
    }
}