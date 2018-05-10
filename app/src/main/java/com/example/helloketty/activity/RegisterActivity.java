package com.example.helloketty.activity;

import android.app.Activity;
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

public class RegisterActivity extends Activity {

    private TextView register_name;
    private RadioButton register_gender;
    private TextView register_phonenumber;
    private TextView register_email;
    private TextView register_region;
    private TextView register_address;

    private Button register_save;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.register);

        Log.i(Utils.log_page_tag,"Arrive register page");
        initPage();
    }

    private void initPage() {
        initRegisterButton();
    }

    private void initRegisterButton() {
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
        register_address = (TextView) findViewById(R.id.register_address_text);

        ElavenUser user = new ElavenUser();
        user.setName(register_name.getText().toString());
        user.setGender(register_gender.getText().toString());
        user.setPhone_number(register_phonenumber.getText().toString());
        user.setEmail(register_email.getText().toString());
        user.setRegion(register_region.getText().toString());
        user.setAddress(register_address.getText().toString());

        FileUtil.saveFile(user.toString(),"ElavenUser.txt");
    }


}
