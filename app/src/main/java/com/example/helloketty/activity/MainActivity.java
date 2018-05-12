package com.example.helloketty.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloketty.R;
import com.example.helloketty.adapter.FriendsAdapter;
import com.example.helloketty.adapter.ResearchResultAdapter;
import com.example.helloketty.entity.ElavenUser;
import com.example.helloketty.entity.ResearchList;
import com.example.helloketty.entity.ResearchListBean;
import com.example.helloketty.observer.IChatObserver;
import com.example.helloketty.userinfo.ElavenUserInfoHelper;
import com.example.helloketty.userinfo.TestOptions;
import com.example.helloketty.util.JsonFileLoader;
import com.example.helloketty.util.MyApplicatioin;
import com.example.helloketty.util.Utils;
import com.google.gson.Gson;

import org.elastos.carrier.AbstractCarrierHandler;
import org.elastos.carrier.Carrier;
import org.elastos.carrier.ConnectionStatus;
import org.elastos.carrier.FriendInfo;
import org.elastos.carrier.PresenceStatus;
import org.elastos.carrier.UserInfo;
import org.elastos.carrier.exceptions.ElastosException;

import com.example.helloketty.util.Synchronizer;

import java.util.List;

public class MainActivity extends Activity {
    public static int SEND_FRIEND_MESSAGE = 0x02;
    public final static int MY_MESSAGE = 0X11;
    private ListView listView_searchresult;
    private ResearchListBean the_research_list;
    private FriendsAdapter messageAdapter;
    private EditText editTeext;
    private List<FriendInfo> friends;
    private String resultMessage = "";

    Carrier carrierInst = null;
    String carrierAddr = null;
    String carrierUserID = null;
    Gson gson = new Gson();

    private MainChatObserver mainChatObserver;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MY_MESSAGE:
                    messageAdapter = new FriendsAdapter(getBaseContext(), friends);
                    listView_searchresult.setAdapter(messageAdapter);
                    listView_searchresult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getBaseContext(), ResearchResultActivity.class);
                            intent.putExtra("itemInfo", gson.toJson(the_research_list.getLists().get(position)));
                            startActivity(intent);
                        }
                    });
                    if (friends.size() > 0) {
                        editTeext.setText(friends.get(0).getUserId());
                    }
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(Utils.log_page_tag, "Arrive main page");
        try {
            carrierInst = Carrier.getInstance();
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainChatObserver = new MainChatObserver();
        MyApplicatioin.getInstance().addChatObserver(mainChatObserver);
        initDate();
    }

    private void initDate() {
        String jsonResult = JsonFileLoader.getResearchListJson(this.getBaseContext());
        the_research_list = gson.fromJson(jsonResult, ResearchListBean.class);
        // 加载布局
        initView();
    }

    private void initView() {
        listView_searchresult = (ListView) findViewById(R.id.listView_searchresult);
        // 提交按钮
        ImageView button = (ImageView) findViewById(R.id.tv_commit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreatResearchActivity.class);
                startActivityForResult(intent, SEND_FRIEND_MESSAGE);
            }
        });
        editTeext = (EditText) findViewById(R.id.user_add);
        try {
            friends = carrierInst.getFriends();
            handler.sendEmptyMessage(MY_MESSAGE);
        } catch (ElastosException e) {
            e.printStackTrace();
        }
        TextView textView = (TextView) findViewById(R.id.tv_addfriend);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    carrierInst.addFriend("3XRrjAMXF7ga66bZSKWRUVmJm73hE4ZLA89Tg5L3socKRUxipofQ", "auto-accepted!");
                } catch (ElastosException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class MainChatObserver implements IChatObserver {

        @Override
        public void receiveMessage(String message) {
            resultMessage = message;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, resultMessage, Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplicatioin.getInstance().removeChatObserver(mainChatObserver);
    }

}
