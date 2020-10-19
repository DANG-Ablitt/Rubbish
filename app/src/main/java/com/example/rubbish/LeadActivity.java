package com.example.rubbish;

import android.content.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.rubbish.view.welcomeview;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.ParallaxPage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;
import com.stephentuso.welcome.WelcomeHelper;


public class LeadActivity extends AppCompatActivity
{
    //存储的手机号和密码
    private String name, pwd;
    private WelcomeHelper welcomeScreen;
    //存储的登录信息
    private SharedPreferences shaPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是不是第一次安装本软件   如果是第一次就有引导页   如果不是就直接进入Login界面
        SharedPreferences preferences = getSharedPreferences("lead", 0);
        boolean flag = preferences.getBoolean("isLead", false);
        if (flag == true) {
            Intent i = new Intent(LeadActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
            return;
        }
        else
        {
            preferences=getSharedPreferences("lead", 0);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putBoolean("isLead", true);
            editor.commit();
        }

        welcomeScreen = new WelcomeHelper(LeadActivity.this, welcomeview.class);
        welcomeScreen.show(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WelcomeHelper.DEFAULT_WELCOME_SCREEN_REQUEST)
        {
            if (resultCode == RESULT_OK)
            {
                shaPreferences = getSharedPreferences("isLogin", 0);
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
                    //进入登录页
                    Intent i = new Intent(LeadActivity.this, LoginActivity.class);
                    startActivity(i);
                }

            }
            else
                {
                // Code here will run if the welcome screen was canceled
                // In most cases you'll want to call finish() here
                }
        }
    }



}








