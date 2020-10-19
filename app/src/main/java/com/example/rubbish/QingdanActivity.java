package com.example.rubbish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.rubbish.adapter.AddresslistAdapter;
import com.example.rubbish.adapter.QingdanlistAdapter;
import com.example.rubbish.application.ExitApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QingdanActivity extends AppCompatActivity
{
    private ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qingdan);
        ExitApplication.getInstance().addActivity(this);
        listView=(ListView)findViewById(R.id.address_list_address);
        List<Map<String, Object>> list=getData();
            listView.setAdapter(new QingdanlistAdapter(this, list));
    }

    //具体数据从服务器获取
    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("name", "商品：上衣");
            map.put("image", "file:///android_asset/avatar0.jpg");
            map.put("num", "数量：1");
            map.put("time", "时间：2019-3-26");
            map.put("jiage", "价格：800积分");
            map.put("shangdian_name", "1加1女装店");
            Map<String, Object> map1=new HashMap<String, Object>();
            map1.put("name", "商品：小汽车");
            map1.put("image", "file:///android_asset/wj4.jpg");
            map1.put("num", "数量：1");
            map1.put("time", "时间：2019-9-27");
            map1.put("jiage", "价格：200积分");
            map1.put("shangdian_name", "LEGO");
            Map<String, Object> map2=new HashMap<String, Object>();
            map2.put("name", "商品：强美饮料");
            map2.put("image", "file:///android_asset/js3.jpg");
            map2.put("num", "数量：1");
            map2.put("time", "时间：2019-10-1");
            map2.put("jiage", "价格：500积分");
            map2.put("shangdian_name", "启德酒水");
            list.add(map);
            list.add(map1);
            list.add(map2);




        return list;
    }
}
