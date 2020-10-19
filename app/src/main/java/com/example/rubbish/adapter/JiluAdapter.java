package com.example.rubbish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rubbish.JiluActivity;
import com.example.rubbish.R;

import java.util.List;
import java.util.Map;

public class JiluAdapter extends BaseAdapter {
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public JiluAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 组件集合，对应list.xml中的控件
     *
     * @author Administrator
     */
    public final class jilulist{
        public TextView image;
        public TextView user_name;

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
        JiluAdapter.jilulist jilulist1 = null;
        if (convertView == null) {
            jilulist1 = new JiluAdapter.jilulist();
            //获得组件，实例化组件
            convertView = layoutInflater.inflate(R.layout.recycler_item, null);
            jilulist1.image = (TextView) convertView.findViewById(R.id.item_name_tv);
            jilulist1.user_name = (TextView) convertView.findViewById(R.id.item_current_price);
            convertView.setTag(jilulist1);
        } else {
            jilulist1 = (JiluAdapter.jilulist) convertView.getTag();
        }
        //绑定数据
        jilulist1.image.setText((String) data.get(position).get("name"));
        jilulist1.user_name.setText((String) data.get(position).get("price"));
        return convertView;
    }
}
