package com.example.rubbish;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import com.example.rubbish.application.ExitApplication;
import com.example.rubbish.config.URLconfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class XiugaimimaActivity extends AppCompatActivity
{
    //旧密码  新密码  确认新密码
    private EditText jiupassword,xinpassword,quepassword;
    //确定按钮
    private Button ok;
    //存储的登录信息
    private SharedPreferences shaPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugaimima);
        ExitApplication.getInstance().addActivity(this);
        //获取XML中的控件
        jiupassword=(EditText)findViewById(R.id.etname);
        xinpassword=(EditText)findViewById(R.id.etUsername);
        quepassword=(EditText)findViewById(R.id.etPassword);
        ok=(Button) findViewById(R.id.btLogin);

        ok.setOnClickListener(ll);
    }

    //处理ll的事件监听
    private View.OnClickListener ll = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //验证两次输入的密码是否相同
            if(xinpassword.getText().toString().equals(quepassword.getText().toString()))
            {
                //连接服务器
                shaPreferences = getSharedPreferences("isLogin", 0);
                String name = shaPreferences.getString("num", null);
                String url = String.format(URLconfig.URL_BOOT + URLconfig.URL_XIUGAIMIMA);
                RequestQueue requestQueue = Volley.newRequestQueue(XiugaimimaActivity.this);
                Map<String, String> params = new HashMap<String, String>();
                params.put("iphone_number", name);
                params.put("jiupassword", jiupassword.getText().toString());
                params.put("xinpassword", xinpassword.getText().toString());
                params.put("quepassword", quepassword.getText().toString());
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
                                        //提示密码修改成功
                                        Toast.makeText(XiugaimimaActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                                        //进入登录界面
                                        Intent i=new Intent(XiugaimimaActivity.this,LoginActivity.class);
                                        startActivity(i);

                                    }
                                    else
                                    {
                                        Toast.makeText(XiugaimimaActivity.this, "修改密码失败，请稍后重试", Toast.LENGTH_SHORT).show();
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
            else
            {
                Toast.makeText(XiugaimimaActivity.this, "您输入的两次密码不相等", Toast.LENGTH_SHORT).show();
            }
        }
    };



}
