package com.example.helloketty.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.helloketty.R;
import com.example.helloketty.entity.ResearchList;
import com.example.helloketty.entity.ResearchListBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HelloKetty on 2018/5/4.
 */

public class ResearchResultAdapter extends BaseAdapter {
    /**
     * x新的消息适配器
     */
    private ArrayList<ResearchList> items;
    private Context context;

    public ResearchResultAdapter(Context context, ArrayList<ResearchList> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (items == null) {
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
        openHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.research_itme, parent, false);
            holder = new openHolder();
            assert convertView != null;
            holder.name = (TextView) convertView.findViewById(R.id.title);
            holder.text = (TextView) convertView.findViewById(R.id.content);
            holder.id = (TextView) convertView.findViewById(R.id.item_id);

            convertView.setTag(holder);
        } else {
            holder = (openHolder) convertView.getTag();
        }
        holder.name.setText(this.items.get(position).getTitle());
        holder.text.setText(this.items.get(position).getSummary());
        holder.id.setText(this.items.get(position).getResearchId());
        return convertView;
    }

    class openHolder {
        TextView name;
        TextView text;
        TextView id;
    }
}
