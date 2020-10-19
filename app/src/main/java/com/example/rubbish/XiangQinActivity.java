package com.example.rubbish;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class XiangQinActivity extends AppCompatActivity
{
    //价格
    private TextView jiage;
    //数量
    private EditText num;
    //图片
    private ImageView photo;
    CardView cv;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        jiage = (TextView) findViewById(R.id.et_password);
        num = (EditText) findViewById(R.id.et_password);
        photo=(ImageView)findViewById(R.id.user_photo);
        cv = (CardView) findViewById(R.id.cv);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        //连接服务器获取数据
        intni();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_go:
                //立即购买商品

                break;
            case R.id.bt_go1:
                //将商品加入到购物车
                break;
        }
    }

    private void intni()
    {
        //连接数据库获取图片和价格
    }


}
