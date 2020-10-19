package com.example.rubbish.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.rubbish.GoueucheActivity;
import com.example.rubbish.GuanliguifanActivity;
import com.example.rubbish.JifenjiluActivaty;
import com.example.rubbish.JiluActivity;
import com.example.rubbish.PhotoActivity;
import com.example.rubbish.QingdanActivity;
import com.example.rubbish.R;
import com.example.rubbish.RuzhuActivaty;
import com.example.rubbish.config.URLconfig;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class JifengFragment extends Fragment
{
    //总积分
    private TextView jifen;
    //日期
    private TextView time1,time2,time3,time4,time5,time6;
    //每日积分
    private TextView jifen1,jifen2,jifen3,jifen4,jifen5,jifen6;
    //积分商城  积分记录
    private ImageView jifen_shangcheng,jifen_jilu;
    //我要入驻  管理规范
    private ImageView woyao_ruzhu,guanli_guifan;
    //购物车  消费清单
    private ImageView gouwu_che,xiaofei_qingdan;
    //存储的登录信息
    private SharedPreferences shaPreferences;

    private View v;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_jifen, container, false);
        //从XML中获取控件
        jifen=(TextView) v.findViewById(R.id.zongjifen);
        time1=(TextView) v.findViewById(R.id.hao1);
        time2=(TextView) v.findViewById(R.id.hao2);
        time3=(TextView) v.findViewById(R.id.hao3);
        time4=(TextView) v.findViewById(R.id.hao4);
        time5=(TextView) v.findViewById(R.id.hao5);
        time6=(TextView) v.findViewById(R.id.jingtian);
        jifen1=(TextView) v.findViewById(R.id.hao11);
        jifen2=(TextView) v.findViewById(R.id.hao12);
        jifen3=(TextView) v.findViewById(R.id.hao13);
        jifen4=(TextView) v.findViewById(R.id.hao14);
        jifen5=(TextView) v.findViewById(R.id.hao15);
        jifen6=(TextView) v.findViewById(R.id.jingtian1);

        jifen_shangcheng = (ImageView) v.findViewById(R.id.jifenshangchen);
        jifen_jilu = (ImageView) v.findViewById(R.id.jifenjilu);
        woyao_ruzhu= (ImageView) v.findViewById(R.id.woyaoruzhu);
        guanli_guifan = (ImageView) v.findViewById(R.id.guanliguifan);
        gouwu_che = (ImageView) v.findViewById(R.id.gouwuche);
        xiaofei_qingdan = (ImageView) v.findViewById(R.id.xiaofeiqingdan);

        //事件定义
        jifen_shangcheng.setOnClickListener(l);
        jifen_jilu.setOnClickListener(l);
        woyao_ruzhu.setOnClickListener(l);
        guanli_guifan.setOnClickListener(l);
        gouwu_che.setOnClickListener(l);
        xiaofei_qingdan.setOnClickListener(l);

        //连接服务器 获取积分信息
        init();

        //绑定积分数据
        bangding();

        return v;
    }



    // 点击各个选项的监听
    private View.OnClickListener l = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent i=null;
            switch (v.getId()) {
                case R.id.jifenshangchen:
                    i=new Intent(getActivity(), PhotoActivity.class);
                    startActivity(i);
                    break;
                case R.id.jifenjilu:
                    i= new Intent(getActivity(), JifenjiluActivaty.class);
                    startActivity(i);
                    break;
                case R.id.woyaoruzhu:
                    i=new Intent(getActivity(), RuzhuActivaty.class);
                    startActivity(i);
                    break;
                case R.id.guanliguifan:
                    i=new Intent(getActivity(), GuanliguifanActivity.class);
                    startActivity(i);
                    break;
                case R.id.gouwuche:
                    i=new Intent(getActivity(), GoueucheActivity.class);
                    startActivity(i);
                    break;
                case R.id.xiaofeiqingdan:
                    i=new Intent(getActivity(), QingdanActivity.class);
                    startActivity(i);
                    break;

                default:
                    break;
            }
        }
    };

    //从服务器获取数据
    private void init()
    {
        shaPreferences = this.getActivity().getSharedPreferences("isLogin", 0);
        //从首选项中检索String值 key：要检索的首选项的名称 defValue：如果此首选项不存在，则返回值。这个值可能是null。
        String name = shaPreferences.getString("num", null);
        String url = String.format(URLconfig.URL_BOOT + URLconfig.URL_JIFENHUOQU);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone_number", name);
        JSONObject jsonObject = new JSONObject(params);
        final JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST,url, jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d(TAG, "response -> " + response.toString());
                        //将获取的数据绑定至UI
                        //为便于评审，采用本地模拟数据


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




    //绑定积分数据
    private void bangding()
    {
        //绑定日期
        //获取当前日期
        Calendar now = Calendar.getInstance();
        time6.setText(now.get(Calendar.DAY_OF_MONTH)+"号");
        now.add(now.DATE, -1);
        now.get(Calendar.DAY_OF_MONTH);
        time5.setText(now.get(Calendar.DAY_OF_MONTH)+"号");
        now.add(now.DATE, -1);
        now.get(Calendar.DAY_OF_MONTH);
        time4.setText(now.get(Calendar.DAY_OF_MONTH)+"号");
        now.add(now.DATE, -1);
        now.get(Calendar.DAY_OF_MONTH);
        time3.setText(now.get(Calendar.DAY_OF_MONTH)+"号");
        now.add(now.DATE, -1);
        now.get(Calendar.DAY_OF_MONTH);
        time2.setText(now.get(Calendar.DAY_OF_MONTH)+"号");
        now.add(now.DATE, -1);
        now.get(Calendar.DAY_OF_MONTH);
        time1.setText(now.get(Calendar.DAY_OF_MONTH)+"号");

    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
