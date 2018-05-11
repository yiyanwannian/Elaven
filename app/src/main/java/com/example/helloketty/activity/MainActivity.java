package com.example.helloketty.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.helloketty.R;
import com.example.helloketty.adapter.ResearchResultAdapter;
import com.example.helloketty.entity.ResearchListBean;
import com.example.helloketty.util.JsonFileLoader;
import com.example.helloketty.util.Utils;
import com.google.gson.Gson;

public class MainActivity extends Activity {
    private ListView listView_searchresult;
    private ResearchListBean the_research_list;
    private final static int MY_MESSAGE = 0X01;
    Gson gson = new Gson();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MY_MESSAGE:
                    ResearchResultAdapter messageAdapter = new ResearchResultAdapter(getBaseContext(), the_research_list.getLists());
                    listView_searchresult.setAdapter(messageAdapter);
                    listView_searchresult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getBaseContext(), ResearchResultActivity.class);
                            intent.putExtra("itemInfo", gson.toJson(the_research_list.getLists().get(position)));
                            startActivity(intent);
                        }
                    });
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

        Log.i(Utils.log_page_tag,"Arrive main page");

        // 初始化数据
        initDate();
        // 提交按钮
        TextView button = (TextView) findViewById(R.id.tv_commit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreatResearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDate() {
        String jsonResult = JsonFileLoader.getResearchListJson(this.getBaseContext());
        the_research_list = gson.fromJson(jsonResult, ResearchListBean.class);
       // 加载布局
        initView();
    }

    private void initView( ) {
        listView_searchresult = (ListView) findViewById(R.id.listView_searchresult);
        handler.sendEmptyMessage(MY_MESSAGE);
    }
}
