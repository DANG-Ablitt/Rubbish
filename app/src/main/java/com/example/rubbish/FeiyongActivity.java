package com.example.rubbish;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.dalong.francyconverflow.FancyCoverFlow;
import com.example.rubbish.adapter.MyFancyCoverFlowAdapter;
import com.example.rubbish.beas.Item;
import com.example.rubbish.config.URLconfig;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class FeiyongActivity extends AppCompatActivity {

    private FancyCoverFlow mfancyCoverFlow;
    private MyFancyCoverFlowAdapter mMyFancyCoverFlowAdapter;
    //套餐
    private String taocan;
    //确定按钮
    private Button ok;
    //时间选择
    private EditText xuanzhetome;
    //联系方式 详细地址
    private EditText iphone,address;
    //存储的登录信息
    private SharedPreferences shaPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ok=(Button)findViewById(R.id.btLogin);
        xuanzhetome=(EditText) findViewById(R.id.ettime);
        iphone=(EditText) findViewById(R.id.etPassword);
        address=(EditText) findViewById(R.id.etUsername);
        ok.setOnClickListener(l);
        xuanzhetome.setOnClickListener(l);
        List<Item> mFancyCoverFlows=new ArrayList<>();
        for(int i=0;i<12;i++){
            Item item=new Item();
            item.setName((i+1)+"个月");
            item.setSelected(false);
            mFancyCoverFlows.add(item);
        }
        mfancyCoverFlow = (FancyCoverFlow) findViewById(R.id.fancyCoverFlow);
        mMyFancyCoverFlowAdapter = new MyFancyCoverFlowAdapter(this, mFancyCoverFlows);
        mfancyCoverFlow.setAdapter(mMyFancyCoverFlowAdapter);
        mMyFancyCoverFlowAdapter.notifyDataSetChanged();
        mfancyCoverFlow.setUnselectedAlpha(0.5f);//通明度
        mfancyCoverFlow.setUnselectedSaturation(0.5f);//设置选中的饱和度
        mfancyCoverFlow.setUnselectedScale(0.3f);//设置选中的规模
        mfancyCoverFlow.setSpacing(0);//设置间距
        mfancyCoverFlow.setMaxRotation(0);//设置最大旋转
        mfancyCoverFlow.setScaleDownGravity(0.5f);
        mfancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
        int num = Integer.MAX_VALUE / 2 % mFancyCoverFlows.size();
        int selectPosition = Integer.MAX_VALUE / 2 - num;
        mfancyCoverFlow.setSelection(selectPosition);
        mfancyCoverFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Item homeFancyCoverFlow = (Item) mfancyCoverFlow.getSelectedItem();
                if (homeFancyCoverFlow != null) {
                    //Toast.makeText(FeiyongActivity.this,homeFancyCoverFlow.getName(),Toast.LENGTH_SHORT).show();
                    taocan=homeFancyCoverFlow.getName();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // 处理l的监听
    private View.OnClickListener l = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ettime:
                    DatePickDialog dialog = new DatePickDialog(FeiyongActivity.this);
                    //设置上下年分限制
                    dialog.setYearLimt(5);
                    //设置标题
                    dialog.setTitle("选择时间");
                    //设置类型
                    dialog.setType(DateType.TYPE_HM);
                    //设置消息体的显示格式，日期格式
                    dialog.setMessageFormat("HH:mm");
                    //设置选择回调
                    dialog.setOnChangeLisener(null);
                    //设置点击确定按钮回调
                    dialog.setOnSureLisener(new OnSureLisener() {
                        @Override
                        public void onSure(Date date) {
                            //Toast.makeText(FeiyongActivity.this,new SimpleDateFormat("HH:mm").format(date),Toast.LENGTH_SHORT).show();
                            //mBackTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
                            xuanzhetome.setText(new SimpleDateFormat("HH:mm").format(date));
                        }
                    });
                    dialog.show();
                    break;
                case R.id.btLogin:
                    //将数据上传服务器
                    Getdata();
                    break;
                default:
                    break;
            }
        }
    };


    private void Getdata()
    {
        shaPreferences = getSharedPreferences("isLogin", 0);
        String name = shaPreferences.getString("num", null);
        String url = String.format(URLconfig.URL_BOOT + URLconfig.URL_GERENGOUMAI);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone_number", name);
        params.put("taocan", taocan);
        params.put("time", xuanzhetome.getText().toString());
        params.put("iphone", iphone.getText().toString());
        params.put("address", address.getText().toString());
        JSONObject jsonObject = new JSONObject(params);
        final JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST,url, jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d(TAG, "response -> " + response.toString());
                        //response.getInt("code");

                        try {
                            if(response.getInt("cade")== 0)
                            {
                                Toast.makeText(FeiyongActivity.this, "购买成功 请您尽快付款", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(FeiyongActivity.this, "购买失败 请您检查网络", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e(TAG, error.getMessage(), error);
                    }
                })
        {
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

