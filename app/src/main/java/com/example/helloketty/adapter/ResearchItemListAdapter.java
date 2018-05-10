package com.example.helloketty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.helloketty.R;
import com.example.helloketty.entity.Answer;
import com.example.helloketty.entity.Quesition;

import java.util.ArrayList;

/**
 * Created by HelloKetty on 2018/5/6.
 */

public class ResearchItemListAdapter extends BaseAdapter {

    /**
     * x新的消息适配器
     */
    private ArrayList<Quesition> items;
    private Context context;

    public ResearchItemListAdapter(Context context, ArrayList<Quesition> items) {
        this.items = items;
        this.context = context;
    }

    public void setCheckBox(LinearLayout container, ArrayList<Answer> answers) {
        container.removeAllViews();
        for (int i = 0; i < answers.size(); i++){
            View item = LayoutInflater.from(context).inflate(R.layout.answer_item, container, false);
            CheckBox checkBox = (CheckBox) item.findViewById(R.id.ch1);
            TextView tx = (TextView) item.findViewById(R.id.answer_textView);
            checkBox.setChecked(answers.get(i).isCheck());
            container.addView(item);
        }
    }

    public void disableRadioGroup(RadioGroup testRadioGroup) {
        for (int i = 0; i < testRadioGroup.getChildCount(); i++) {
            testRadioGroup.getChildAt(i).setEnabled(false);
        }
    }

    public void enableRadioGroup(RadioGroup testRadioGroup) {
        for (int i = 0; i < testRadioGroup.getChildCount(); i++) {
            testRadioGroup.getChildAt(i).setEnabled(true);
        }
    }

    @Override
    public int getCount() {
        if(items == null){
            return 0;
        }
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ResearchItemListAdapter.openHolder holder;
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.question_item, parent, false);
            holder = new openHolder();
            assert view != null;
            holder.title = (TextView) view.findViewById(R.id.txt_title);
            holder.answerContainer = (LinearLayout) view.findViewById(R.id.answer_container);
            view.setTag(holder);
        } else {
            holder = (ResearchItemListAdapter.openHolder) view.getTag();
        }

        holder.title.setText(items.get(position).getTitle());
        setCheckBox(holder.answerContainer, items.get(position).getAnswers());
        return view;
    }

    class openHolder {
        TextView title;
        LinearLayout answerContainer;
        TextView text;
    }
}
