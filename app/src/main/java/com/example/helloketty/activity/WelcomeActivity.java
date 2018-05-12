package com.example.helloketty.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.helloketty.R;
import com.example.helloketty.adapter.ResearchItemListAdapter;
import com.example.helloketty.entity.ElavenUser;
import com.example.helloketty.userinfo.ElavenUserInfoHelper;
import com.example.helloketty.util.FileUtil;
import com.example.helloketty.util.Utils;
import com.google.gson.Gson;

import org.elastos.carrier.Carrier;
import org.json.JSONObject;

import java.io.File;

import static com.example.helloketty.util.Utils.default_file_directory;
import static com.example.helloketty.util.Utils.log_info_tag;

/**
 * Created by Houfa.Zhou on 2018/5/10.
 */

public class WelcomeActivity extends Activity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.welcome);
        Log.i(Utils.log_page_tag, "Arrive welcome page");
        initLogin();
    }

    private void initLogin() {
        handler.sendEmptyMessageDelayed(2, 3000);
    }
}
