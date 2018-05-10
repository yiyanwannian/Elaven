package com.example.helloketty.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.helloketty.R;
import com.example.helloketty.userinfo.ElavenUserInfoHelper;
import com.example.helloketty.util.Utils;

/**
 * Created by Houfa.Zhou on 2018/5/10.
 */

public class WelcomeActivity extends Activity{

    private Button login_button;
    private Button register_button;

    private String log_tag = "Test Info";

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            //Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            Intent intent=new Intent(WelcomeActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }

    };

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.welcome);

        Log.i(Utils.log_page_tag,"Arrive welcome page");
        initPage();
    }

    private void initPage() {
        initLogin();
        initRegister();
    }

    private void initLogin() {
        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To do
                Log.i(log_tag,"Clicked login button");
            }
        });

    }

    private void initRegister() {
        register_button = (Button) findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserIdAndAddress();
                handler.sendEmptyMessageDelayed(0x01, 2000);
            }
        });
    }

    private void createUserIdAndAddress (){
        ElavenUserInfoHelper helper = new ElavenUserInfoHelper(WelcomeActivity.this);
        String userid = helper.getUserid();
        String address = helper.getAddress();
    }


}
