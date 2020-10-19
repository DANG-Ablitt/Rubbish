package com.example.rubbish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.example.rubbish.adapter.GuanliguifanAdapter;
import com.example.rubbish.application.ExitApplication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuanliguifanActivity extends AppCompatActivity
{

    private ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanliguifan);
        ExitApplication.getInstance().addActivity(this);
        listView=(ListView)findViewById(R.id.address_list_address);
        List<Map<String, Object>> list=getData();
        listView.setAdapter(new GuanliguifanAdapter(this, list));
    }

    //具体数据从服务器获取
    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

        Map<String, Object> map=new HashMap<String, Object>();
        map.put("date", "目的和依据");
        map.put("title", "为维护交易服务的正常运营秩序，保障交易用户的合法权益，根据《服务协议》、《服务协议补充确认函》规定，制定本规范。");
        list.add(map);
        Map<String, Object> map1=new HashMap<String, Object>();
        map1.put("date", "适用范围");
        map1.put("title", "本规范适用于所有使用该商店服务的用户。");
        list.add(map1);
        Map<String, Object> map2=new HashMap<String, Object>();
        map2.put("date", "入驻资质细则");
        map2.put("title", "商家需提供企业营业执照，部分行业还需提供特殊经营资质，并上传本人身份证件。");
        list.add(map2);
        Map<String, Object> map3=new HashMap<String, Object>();
        map3.put("date", "经营");
        map3.put("title", "用户须遵守《禁售商品管理规范》、《交互信息规则》及《规则》中关于信息发布的相关规定。根据交易特性，同品牌的不同门店可以展示相同的商品或服务。");
        list.add(map3);
        Map<String, Object> map4=new HashMap<String, Object>();
        map4.put("date", "曝光处理");
        map4.put("title", "针对经新闻媒体曝光、国家质监部门等行政管理部门通报，系质量不合格的线下某一品牌、品类、批次的产品，将依照其情形严重程度，采取商品下架、商品删除、店铺监管的临时性市场管控措施，情形特别严重，做清退处理。");
        list.add(map4);
        Map<String, Object> map5=new HashMap<String, Object>();
        map5.put("date", "其他情形");
        map5.put("title", "在某些特殊情形下(如短期内大量引发或持续引发消费者维权等)，平台有权依照其情形严重程度，采取下架产品、删除产品等处理措施进行临时性的市场管控，情形特别严重，做清退处理。");
        list.add(map5);

        return list;
    }

}
