package com.example.helloketty.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helloketty.R;
import com.example.helloketty.adapter.ResearchItemListAdapter;
import com.example.helloketty.entity.Quesition;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by HelloKetty on 2018/5/6.
 */

public class CreatResearchActivity extends Activity {
    private TextView buttonCreatItem;
    private EditText ed_Title;
    private ListView question_list;
    public static int MESSAGECODE = 10;
    private ArrayList<Quesition> questions = new ArrayList<>();
    private Gson gson = new Gson();
    private ResearchItemListAdapter adapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MESSAGECODE) {
            if(data != null){
                String result = data.getExtras().getString("data");
                Quesition questionItem =  gson.fromJson(result, Quesition.class);
                questions.add(questionItem);
                adapter.notifyDataSetChanged();
            }
        }


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_research);
        initView();
    }

    public void initView() {
        buttonCreatItem = (TextView) findViewById(R.id.txt_creatItem);
        buttonCreatItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreatQuestionActivity.class);
                startActivityForResult(intent, MESSAGECODE);
            }
        });
        ed_Title = (EditText) findViewById(R.id.edit_title);
        question_list = (ListView) findViewById(R.id.question_list);
        adapter =  new ResearchItemListAdapter(this.getBaseContext(), questions);
        question_list.setAdapter(adapter);
    }
}
