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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioButton;

import com.example.helloketty.R;
import com.example.helloketty.adapter.FriendsAdapter;
import com.example.helloketty.entity.ElavenUser;
import com.example.helloketty.userinfo.ElavenUserInfoHelper;
import com.example.helloketty.util.FileUtil;
import com.example.helloketty.util.MyApplicatioin;
import com.example.helloketty.util.Synchronizer;
import com.example.helloketty.util.Utils;
import com.google.gson.Gson;

import org.elastos.carrier.AbstractCarrierHandler;
import org.elastos.carrier.Carrier;
import org.elastos.carrier.ConnectionStatus;
import org.elastos.carrier.UserInfo;
import org.elastos.carrier.exceptions.ElastosException;

public class RegisterActivity extends Activity {
    private TextView register_userid;
    private TextView register_address;
    private TextView register_name;
    private RadioButton register_gender;
    private TextView register_phonenumber;
    private TextView register_email;
    private TextView register_region;
    private Button register_save;
    private String userID = "";
    private ElavenUserInfoHelper helper;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            register_userid.setText(helper.getUserid());
            register_address.setText(helper.getAddress());
            Log.e(helper.getUserid(),helper.getUserid());
            Log.e(helper.getAddress(),helper.getAddress());
        }

    };

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.register);
        helper = new ElavenUserInfoHelper(RegisterActivity.this);
        Log.i(Utils.log_page_tag, "Arrive register page");
        initPage();
    }

    private void initPage() {
        initWidgetsStatus();
        register_save = (Button) findViewById(R.id.register_save);
        register_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Utils.log_info_tag, "register button clicked");
                initUserInfo();
                intentToMain();
            }
        });
    }

    private void intentToMain() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void initWidgetsStatus() {
        register_userid = (TextView) findViewById(R.id.register_userid_text);
        register_address = (TextView) findViewById(R.id.register_address_text);
        register_name = (TextView) findViewById(R.id.register_name_text);
        register_phonenumber = (TextView) findViewById(R.id.register_phonenumber_text);
        register_email = (TextView) findViewById(R.id.register_email_text);
        register_region = (TextView) findViewById(R.id.register_region_text);
        handler.sendEmptyMessageDelayed(5,2000);
    }

    private void initUserInfo() {
        register_name = (TextView) findViewById(R.id.register_name_text);
        register_gender = (RadioButton) findViewById(
                ((RadioGroup) findViewById(R.id.register_gender_text))
                        .getCheckedRadioButtonId());
        register_phonenumber = (TextView) findViewById(R.id.register_phonenumber_text);
        register_email = (TextView) findViewById(R.id.register_email_text);
        register_region = (TextView) findViewById(R.id.register_region_text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.carrierInst.kill();

    }
}
