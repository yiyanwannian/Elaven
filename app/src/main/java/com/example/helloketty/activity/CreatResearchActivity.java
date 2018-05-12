package com.example.helloketty.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helloketty.R;
import com.example.helloketty.adapter.FriendsAdapter;
import com.example.helloketty.adapter.ResearchItemListAdapter;
import com.example.helloketty.entity.Quesition;
import com.example.helloketty.entity.QuestionListBean;
import com.example.helloketty.entity.ResearchList;
import com.example.helloketty.observer.IChatObserver;
import com.example.helloketty.userinfo.ElavenUserInfoHelper;
import com.example.helloketty.util.MyApplicatioin;
import com.google.gson.Gson;


import org.elastos.carrier.Carrier;
import org.elastos.carrier.UserInfo;
import org.elastos.carrier.exceptions.ElastosException;

import java.util.ArrayList;

/**
 * Created by HelloKetty on 2018/5/6.
 */

public class CreatResearchActivity extends Activity {
    public final static int MESSAGECODE = 0x01;
    private TextView buttonCreatItem;
    private TextView txt_sendtoFriend;
    private EditText ed_Title;
    private ListView question_list;
    private ResearchList researchList = new ResearchList();
    private ArrayList<Quesition> questions;
    private Gson gson = new Gson();
    private ResearchItemListAdapter adapter;
    public Carrier carrierInst;

    private IChatObserver chatActivityObserver;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Gson gson = new Gson();
            String result = data.getExtras().getString("dataquestion");
            Quesition question = gson.fromJson(result, Quesition.class);
            questions.add(question);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_research);
        String flags = getIntent().getStringExtra("dataquestion");
        questions = new ArrayList<>();
        if (flags != null) {
            Quesition questionItem = gson.fromJson(flags, Quesition.class);
            ed_Title.setText(flags);
        }
        carrierInst = Carrier.getInstance();

        initView();
    }

    public void initView() {

        MyApplicatioin.getInstance().addChatObserver(chatActivityObserver);

        buttonCreatItem = (TextView) findViewById(R.id.txt_creatItem);
        txt_sendtoFriend = (TextView) findViewById(R.id.txt_sendtoFriend);
        ed_Title = (EditText) findViewById(R.id.edit_title);
        question_list = (ListView) findViewById(R.id.question_list);
        adapter = new ResearchItemListAdapter(this.getBaseContext(), questions);
        question_list.setAdapter(adapter);
        buttonCreatItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreatQuestionActivity.class);
                startActivityForResult(intent, MESSAGECODE);
            }
        });
        txt_sendtoFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                Intent intent = new Intent();
                researchList.setQuesitions(questions);
                researchList.setTitle(ed_Title.getText().toString());
                researchList.setSummary(ed_Title.getText().toString());
                intent.putExtra("data_research", gson.toJson(researchList));
                setResult(MainActivity.SEND_FRIEND_MESSAGE, intent);
                questions.clear();
                adapter.notifyDataSetChanged();
                try {
                    carrierInst.sendFriendMessage("6dJmbVF4p7xejtBjiwfPvBDb5xRYTjthixxAkTxk3TKE", "Hello!");
                } catch (ElastosException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class ChatActivityObserver implements IChatObserver {
        @Override
        public void receiveFriendMessage(String fromId, String message) {

        }

        @Override
        public void receiveFriendRequest(String fromId, UserInfo info, String hello) {

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplicatioin.getInstance().removeChatObserver(chatActivityObserver);

    }

}
