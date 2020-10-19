package com.example.rubbish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.example.rubbish.adapter.AddresslistAdapter;
import com.example.rubbish.application.ExitApplication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressActivity extends AppCompatActivity
{

    private ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ExitApplication.getInstance().addActivity(this);
        listView=(ListView)findViewById(R.id.address_list_address);
        List<Map<String, Object>> list=getData();
        listView.setAdapter(new AddresslistAdapter(this, list));
    }

    //具体数据从服务器获取
    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

            Map<String, Object> map=new HashMap<String, Object>();
            map.put("name", "李先生");
            map.put("iphone", "15030335996");
            map.put("info", "河北省石家庄市正定县县一中");
            Map<String, Object> map1=new HashMap<String, Object>();
            map1.put("name", "刘女士");
            map1.put("iphone", "15030333333");
            map1.put("info", "河北省石家庄市正定县县一中");
            Map<String, Object> map2=new HashMap<String, Object>();
            map2.put("name", "王先生");
            map2.put("iphone", "15030333322");
            map2.put("info", "河北省石家庄市正定县县一中");
            list.add(map);
            list.add(map1);
            list.add(map2);

        return list;
    }

}
