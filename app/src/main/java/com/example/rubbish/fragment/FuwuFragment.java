package com.example.rubbish.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.eminayar.panter.PanterDialog;
import com.example.rubbish.FeiyongActivity;
import com.example.rubbish.GongyiActivity;
import com.example.rubbish.JiluActivity;
import com.example.rubbish.JitifeiyongActivity;
import com.example.rubbish.R;
import com.example.rubbish.config.URLconfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;


public class FuwuFragment extends Fragment
{
    //个人购买  集体购买  公益报名
    private TextView one,two,three;
    //3个详细
    private TextView one_info,two_info,three_info;
    //修改上门时间
    private TextView xiugai_time;
    //集体记录
    private TextView jiti_jilu;
    //公益条码
    private TextView gongyi_tiaoma;
    private View v;
    //修改时间
    private String time1;
    //存储的登录信息
    private SharedPreferences shaPreferences;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_fuwu, container, false);
        //获取XML中的控件
        one = (TextView) v.findViewById(R.id.goumai1);
        two = (TextView) v.findViewById(R.id.goumai2);
        three = (TextView) v.findViewById(R.id.baoming1);
        one_info = (TextView) v.findViewById(R.id.info1);
        two_info = (TextView) v.findViewById(R.id.info2);
        three_info = (TextView) v.findViewById(R.id.info3);
        xiugai_time = (TextView) v.findViewById(R.id.time);
        jiti_jilu=(TextView) v.findViewById(R.id.jilu);
        gongyi_tiaoma=(TextView) v.findViewById(R.id.tiaoma );

        //定义点击事件
        one.setOnClickListener(l);
        two.setOnClickListener(l);
        three.setOnClickListener(l);
        one_info.setOnClickListener(ll);
        two_info.setOnClickListener(ll);
        three_info.setOnClickListener(ll);
        xiugai_time.setOnClickListener(lll);
        jiti_jilu.setOnClickListener(llll);
        gongyi_tiaoma.setOnClickListener(lllll);


        return v;
    }


    // 处理l的监听
    private View.OnClickListener l = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent i=null;
            switch (v.getId()) {
                case R.id.goumai1:
                    i=new Intent(getActivity(), FeiyongActivity.class);
                    startActivity(i);
                    break;
                case R.id.goumai2:
                    i=new Intent(getActivity(), JitifeiyongActivity.class);
                    startActivity(i);
                    break;
                case R.id.baoming1:
                    i=new Intent(getActivity(), GongyiActivity.class);
                    startActivity(i);
                    break;
                default:
                    break;
            }
        }
    };

    // 处理ll的监听
    private View.OnClickListener ll = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.info1:
                    new PanterDialog(getContext())
                            .setHeaderBackground(R.drawable.pattern_bg_orange)
                            .setPositive("确定")// You can pass also View.OnClickListener as second param
                            .setMessage(R.string.lorem_ipsum)
                            .isCancelable(false)
                            .show();
                    break;
                case R.id.info2:
                    new PanterDialog(getContext())
                            .setHeaderBackground(R.drawable.pattern_bg_orange)
                            .setPositive("确定")// You can pass also View.OnClickListener as second param
                            .setMessage(R.string.lorem_ipsu)
                            .isCancelable(false)
                            .show();
                    break;
                case R.id.info3:
                    new PanterDialog(getContext())
                            .setHeaderBackground(R.drawable.pattern_bg_orange)
                            .setPositive("确定")// You can pass also View.OnClickListener as second param
                            .setMessage(R.string.lorem_ips)
                            .isCancelable(false)
                            .show();
                    break;
                default:
                    break;
            }
        }
    };

    // 处理lll的监听
    private View.OnClickListener lll = new View.OnClickListener() {

        @Override
        public void onClick(View v) {


            DatePickDialog dialog = new DatePickDialog(getContext());
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
                    //Toast.makeText(getActivity(), new SimpleDateFormat("HH:mm").format(date), Toast.LENGTH_SHORT).show();
                    //mBackTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
                    time1=new SimpleDateFormat("HH:mm").format(date);
                    //连接服务器
                    xiugaitime();
                }
            });
            dialog.show();
        }
    };

    // 处理llll的监听
    private View.OnClickListener llll = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent i= new Intent(getActivity(), JiluActivity.class);
            //为便于获取记录数据  添加标记
            i.putExtra("key",15);
            startActivity(i);
        }
    };

    // 处理lllll的监听
    private View.OnClickListener lllll = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            LayoutInflater li = LayoutInflater.from(getActivity());
            View view = li.inflate(R.layout.tiaoma_itma, null);

            //get a builder and set the view
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("请用户扫描条形码或输入随机码获取积分");
            builder.setView(view);
            builder.setPositiveButton("OK",null);
            builder.create();
            builder.show();

        }
    };


    @Override
    public void onResume() {
        super.onResume();

    }


    //与服务器通信 修改上面时间
    private void xiugaitime()
    {
        shaPreferences = getContext().getSharedPreferences("isLogin", 0);
        String name = shaPreferences.getString("num", null);
        String url = String.format(URLconfig.URL_BOOT + URLconfig.URL_XIUGAISHIJIAN);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone_number", name);
        params.put("time", time1);
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
                                Toast.makeText(getActivity(), "上门服务时间修改成功", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "上门服务时间修改失败", Toast.LENGTH_SHORT).show();
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
