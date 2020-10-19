package com.example.rubbish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rubbish.R;

import java.util.List;
import java.util.Map;

public class AddresslistAdapter extends BaseAdapter
{
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public AddresslistAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class addersslist{
        public ImageView image;
        public TextView user_name;
        public TextView address_info;
        public TextView user_iphone;
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
        addersslist addersslist1=null;
        if(convertView==null){
            addersslist1=new addersslist();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.address_list, null);
            addersslist1.image=(ImageView)convertView.findViewById(R.id.user_image);
            addersslist1.user_name=(TextView)convertView.findViewById(R.id.uesr_name);
            addersslist1.address_info=(TextView)convertView.findViewById(R.id.user_info);
            addersslist1.user_iphone=(TextView)convertView.findViewById(R.id.user_iphone);
            convertView.setTag(addersslist1);
        }else{
            addersslist1=(addersslist)convertView.getTag();
        }
        //绑定数据
        //addersslist1.image.setBackgroundResource((Integer)data.get(position).get("image"));
        addersslist1.user_name.setText((String)data.get(position).get("name"));
        addersslist1.address_info.setText((String)data.get(position).get("info"));
        addersslist1.user_iphone.setText((String)data.get(position).get("iphone"));
        return convertView;
    }
}
