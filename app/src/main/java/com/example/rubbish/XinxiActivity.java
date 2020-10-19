package com.example.rubbish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.rubbish.application.ExitApplication;

public class XinxiActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinxi);
        ExitApplication.getInstance().addActivity(this);
    }
}
