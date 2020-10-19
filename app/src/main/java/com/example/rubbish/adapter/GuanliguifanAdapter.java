package com.example.rubbish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rubbish.R;

import java.util.List;
import java.util.Map;

public class GuanliguifanAdapter extends BaseAdapter
{
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public GuanliguifanAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class addersslist{
        public TextView date;
        public TextView title;

    }
    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GuanliguifanAdapter.addersslist addersslist1=null;
        if(convertView==null){
            addersslist1=new GuanliguifanAdapter.addersslist();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.item_base, null);
            addersslist1.date=(TextView)convertView.findViewById(R.id.tv_date);
            addersslist1.title=(TextView)convertView.findViewById(R.id.tv_title);
            convertView.setTag(addersslist1);
        }else{
            addersslist1=(GuanliguifanAdapter.addersslist)convertView.getTag();
        }
        //绑定数据
        //addersslist1.image.setBackgroundResource((Integer)data.get(position).get("image"));
        addersslist1.date.setText((String)data.get(position).get("date"));
        addersslist1.title.setText((String)data.get(position).get("title"));
        return convertView;
    }
}

