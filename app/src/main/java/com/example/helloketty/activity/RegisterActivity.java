package com.example.helloketty.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioButton;

import com.example.helloketty.R;
import com.example.helloketty.entity.ElavenUser;
import com.example.helloketty.util.FileUtil;
import com.example.helloketty.util.Utils;
import com.google.gson.Gson;

public class RegisterActivity extends Activity {

    private  ElavenUser elaven_user;

    private TextView register_name;
    private RadioButton register_gender;
    private TextView register_phonenumber;
    private TextView register_email;
    private TextView register_region;

    private Button register_save;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.register);

        Log.i(Utils.log_page_tag,"Arrive register page");

        Gson gson = new Gson();
        Bundle bundle = this.getIntent().getExtras();
        String userJson = bundle.getString("userJson");
        elaven_user = gson.fromJson(userJson, ElavenUser.class);

        initPage();
    }

    private void initPage() {
        if (elaven_user.getName() == null || elaven_user.getName().equals("")) {
            initRegisterSaveButton();
        }

        intentToWelcome(elaven_user);
    }

    private void intentToWelcome(ElavenUser elaven_user) {
        Bundle bundle = new Bundle();
        bundle.putString("userJson", elaven_user.toString());
        Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void initRegisterSaveButton() {
        Log.i(Utils.log_info_tag,"before register button clicked");
        register_save = (Button) findViewById(R.id.register_save);
        register_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Utils.log_info_tag,"register button clicked");
                initUserInfo();
            }
        });
    }

    private void initUserInfo() {
        register_name = (TextView) findViewById(R.id.register_name_text);
        register_gender = (RadioButton)findViewById(
                                ((RadioGroup) findViewById(R.id.register_gender_text))
                                .getCheckedRadioButtonId());
        register_phonenumber = (TextView) findViewById(R.id.register_phonenumber_text);
        register_email = (TextView) findViewById(R.id.register_email_text);
        register_region = (TextView) findViewById(R.id.register_region_text);


        elaven_user.setName(register_name.getText().toString());
        elaven_user.setGender(register_gender.getText().toString());
        elaven_user.setPhone_number(register_phonenumber.getText().toString());
        elaven_user.setEmail(register_email.getText().toString());
        elaven_user.setRegion(register_region.getText().toString());

        FileUtil.saveFile(elaven_user.toString(), Utils.userinfo_file_name);
    }


}
