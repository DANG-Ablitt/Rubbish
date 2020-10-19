package com.example.rubbish;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.rubbish.adapter.JifenjiluAdapter;
import com.example.rubbish.adapter.JiluAdapter;
import com.example.rubbish.application.ExitApplication;
import com.example.rubbish.config.URLconfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.android.volley.VolleyLog.TAG;

public class JifenjiluActivaty extends AppCompatActivity
{

    private ListView listView=null;

    //存储的登录信息
    private SharedPreferences shaPreferences;

    List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jilu);
        ExitApplication.getInstance().addActivity(this);
        listView=(ListView)findViewById(R.id.address_list_address);
        Data();
        SystemClock.sleep(50);
        listView.setAdapter(new JifenjiluAdapter(this, list));
    }

    //具体数据从服务器获取
    private void Data() {
        shaPreferences = getSharedPreferences("isLogin", 0);
        String name = shaPreferences.getString("num", null);
        String url = String.format(URLconfig.URL_BOOT + URLconfig.URL_JIFENJILU);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone_number", name);
        JSONObject jsonObject = new JSONObject(params);
        final JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                        //response.getInt("code");

                        try {
                            if (response.getInt("cade") == 0) {
                                //解析数据
                                JSONArray array = response.getJSONArray("datas");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject j = array.getJSONObject(i);
                                    Map<String, Object> map = new HashMap<String, Object>();
                                    map.put("name", j.getString("name"));
                                    map.put("price", j.getString("price"));
                                    map.put("price11", j.getString("price11"));
                                    list.add(map);
                                }

                            } else {
                                Toast.makeText(JifenjiluActivaty.this, "数据请求失败 请您检查网络", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage(), error);
                    }
                }) {
            //重写头文件，根据需要设置
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        requestQueue.add(jsonRequest);
    }

}
