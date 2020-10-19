package com.example.rubbish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rubbish.R;
import java.util.List;
import java.util.Map;

public class QingdanlistAdapter extends BaseAdapter
{
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public QingdanlistAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }

    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class addersslist{
        public TextView user_shangdian_name;
        public TextView user_name;
        public TextView user_shuliang;
        public TextView user_jiage;
        public TextView user_time;
        public ImageView image;
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
        QingdanlistAdapter.addersslist addersslist1=null;
        if(convertView==null){
            addersslist1=new QingdanlistAdapter.addersslist();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.item_card_jilu, null);
            addersslist1.image=(ImageView)convertView.findViewById(R.id.avatar);
            addersslist1.user_name=(TextView)convertView.findViewById(R.id.tv_song);
            addersslist1.user_shuliang=(TextView)convertView.findViewById(R.id.tv_singer);
            addersslist1.user_jiage=(TextView)convertView.findViewById(R.id.tv_singerv);
            addersslist1.user_shangdian_name=(TextView)convertView.findViewById(R.id.tv_source_name);
            addersslist1.user_time=(TextView)convertView.findViewById(R.id.tv_singervv);
            convertView.setTag(addersslist1);
        }else{
            addersslist1=(QingdanlistAdapter.addersslist)convertView.getTag();
        }
        //绑定数据
        Glide.with(parent.getContext())
                .load(data.get(position).get("image"))
                .into(addersslist1.image);
        //addersslist1.image.setBackgroundResource((int)data.get(position).get("image"));
        addersslist1.user_name.setText((String)data.get(position).get("name"));
        addersslist1.user_shuliang.setText((String)data.get(position).get("num"));
        addersslist1.user_time.setText((String)data.get(position).get("time"));
        addersslist1.user_jiage.setText((String)data.get(position).get("jiage"));
        addersslist1.user_shangdian_name.setText((String)data.get(position).get("shangdian_name"));
        return convertView;
    }

}
