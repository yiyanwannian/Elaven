package com.example.helloketty.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloketty.R;
import com.example.helloketty.entity.Answer;
import com.example.helloketty.entity.Quesition;
import com.example.helloketty.entityutil.QuestionType;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.datatype.Duration;

/**
 * Created by HelloKetty on 2018/5/6.
 */

public class CreatQuestionActivity extends Activity {
    private TextView buttonCreatAnswerItem;
    private TextView save;
    private EditText ed_Title;
    private LinearLayout answer_list;
    private Button back;
    private Button del;
    private ArrayList<Answer> answers = new ArrayList<>();
    private static List<String> ANSWERLIST = Arrays.asList("A", "B", "C", "D");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_question);
        initView();
    }

    private void initView() {
        ed_Title = (EditText) findViewById(R.id.answer_title);
        save = (TextView) findViewById(R.id.save);
        buttonCreatAnswerItem = (TextView) findViewById(R.id.create_answer);
        answer_list = (LinearLayout) findViewById(R.id.answer_listView);
        back = (Button) findViewById(R.id.but_back);
        del = (Button) findViewById(R.id.but_delete);
        buttonCreatAnswerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnswerItem(answer_list);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleName =  ed_Title.getText().toString();
                if(titleName == null || titleName == "" || titleName == "null"){
                    return;
                }
                if(answers.size() == 0){
                    return;
                }
                Quesition quesition = new Quesition("aaaa", titleName, QuestionType.SINGLEANSWER, titleName, answers);
                Gson gson = new Gson();
                Intent intent = new Intent();
                intent.putExtra("data", gson.toJson(quesition));
                setResult(CreatResearchActivity.MESSAGECODE, intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_Title.setText("");
                answer_list.removeAllViews();
                answer_list.refreshDrawableState();
            }
        });
    }

    private void addAnswerItem(LinearLayout container) {
        View item = LayoutInflater.from(this.getBaseContext()).inflate(R.layout.answer_item, container, false);
        CheckBox checkBox = (CheckBox) item.findViewById(R.id.ch1);
        TextView tx = (TextView) item.findViewById(R.id.answer_textView);
        int count = answer_list.getChildCount();
        if (count < ANSWERLIST.size()) {
            tx.setText(ANSWERLIST.get(count));
            answer_list.addView(item);
            answer_list.refreshDrawableState();
            Answer answerItem = new Answer();
            answerItem.setAnswer_content(ANSWERLIST.get(count));
            answers.add(answerItem);
        }
    }
}
