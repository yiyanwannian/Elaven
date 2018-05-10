package com.example.helloketty.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by HelloKetty on 2018/5/4.
 */

public class JsonFileLoader {
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    public static String getResearchListJson(Context context) {
        return  getJson(context, "mainResearchResults.json");
    }
    public static String getResearchItemListJson(Context context) {
        return  getJson(context, "researchItemList.json");
    }

}
