package com.example.rubbish;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.rubbish.application.ExitApplication;
import com.example.rubbish.beas.Photo;
import com.example.rubbish.config.URLconfig;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class LoginActivity extends Activity
{

    //手机号和密码
    private EditText phone_number,password;
    //登录按钮
    private Button login;
    //忘记密码
    private TextView forget_password;
    //注册按钮
    private ImageButton registered;
    //存储的登录信息
    private SharedPreferences shaPreferences;
    //存储的手机号和密码
    private String name, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //在每个在Activity的onCreate()方法中调用ExitApplication.getInstance().
        // addActivity(this)方法,在退出时调用ExitApplication.getInstance().exit()方法，就可以完全退出应用程序了
        ExitApplication.getInstance().addActivity(this);
        //获取XML文件中的控件
        phone_number=(EditText)findViewById(R.id.etUsername);
        password=(EditText)findViewById(R.id.etPassword);
        login=(Button) findViewById(R.id.btLogin);
        forget_password=(TextView) findViewById(R.id.tvForgot);
        registered=(ImageButton) findViewById(R.id.btRegister);
        //注册的事件监听
        registered.setOnClickListener(registered_listener);
        //忘记密码的事件监听
        forget_password.setOnClickListener(forget_password_listener);
        //登录的事件监听
        login.setOnClickListener(login_listener);
        //判断是否登录过
        shaPreferences = getSharedPreferences("isLogin", 0);
        //从首选项中检索String值 key：要检索的首选项的名称 defValue：如果此首选项不存在，则返回值。这个值可能是null。
        name = shaPreferences.getString("num", null);
        pwd = shaPreferences.getString("pwd", null);
        if (name != null && pwd != null)
        {
            //进入主页
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);

        }
        else
        {
            phone_number.setText(name);
            password.setText(pwd);
        }
    }

    //处理注册的事件监听
    private View.OnClickListener registered_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //进入注册界面
            Intent i = new Intent(LoginActivity.this, RegisteredActivity.class);
            startActivity(i);
        }
    };

    //处理忘记密码的事件监听
    private View.OnClickListener forget_password_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //进入修改密码界面
            Intent i = new Intent(LoginActivity.this, PasswordActivity.class);
            startActivity(i);
        }
    };

    //处理登录的事件监听
    private View.OnClickListener login_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login();
        }
    };

    //登录处理函数
    private void login()
    {


        /*String url = String.format(URLconfig.URL_BOOT + URLconfig.URL_LOGIN);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone_number", phone_number.getText().toString());
        params.put("password", password.getText().toString());
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
                            {*/
        if(phone_number.getText().toString().equals("13303343396")&&password.getText().toString().equals("666")) {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
        }
                                //保存用户登录信息
                                /*SharedPreferences.Editor editor = shaPreferences.edit();
                                editor.putString("num", phone_number.getText().toString());
                                editor.putString("pwd", password.getText().toString());
                                editor.commit();


                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
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
                headers.put("Accept", "application/json, text/javascript;" q=0.01");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        requestQueue.add(jsonRequest);*/

    }

}
