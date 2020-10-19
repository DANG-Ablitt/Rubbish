package com.example.rubbish.beas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShangchengParser {

    public static List<ShangpinEntity> getGouwucheParser(JSONObject response){
        List<ShangpinEntity> data=new ArrayList<ShangpinEntity>();
        try {
            JSONArray array=response.getJSONArray("datas");
            for (int i = 0; i < array.length(); i++) {
                JSONObject j=array.getJSONObject(i);
                ShangpinEntity entity=new ShangpinEntity();
                entity.setUrl(j.getString("goods_image"));
                entity.setPrice(j.getString("goods_price"));
                entity.setName(j.getString("goods_name"));
                entity.setIntro(j.getString("goods_title"));
                entity.setId(j.getString("goods_id"));
                entity.setCount(j.getString("num"));
                data.add(entity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}

