package com.example.rubbish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import com.example.rubbish.fragment.PayFragment;
import com.example.rubbish.view.PayPwdView;

public class RegisteredActivity extends AppCompatActivity implements PayPwdView.InputCallBack
{

    //昵称  手机号  密码  确认密码
    private EditText name,phone_number,password,determine_password;
    //下一步
    private Button last;
    //昵称  手机号  密码  确认密码的输入值
    private String name_input,phone_number_input,password_input,determine_password_input;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //获取XML文件中的控件
        name=(EditText)findViewById(R.id.etname);
        phone_number=(EditText)findViewById(R.id.etUsername);
        password=(EditText)findViewById(R.id.etPassword);
        determine_password=(EditText)findViewById(R.id.etRePassword);
        last=(Button) findViewById(R.id.btLogin);
        //按钮下一步的监听事件
        last.setOnClickListener(last_listener);
    }

    //处理按钮下一步的监听事件
    private View.OnClickListener last_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            //提取值
            name.setText(name_input);
            phone_number.setText(phone_number_input);
            password.setText(password_input);
            determine_password.setText(determine_password_input);
            //上传手机号
            phone_number();
            //弹出输入验证码界面
            Bundle bundle = new Bundle();
            bundle.putString(PayFragment.EXTRA_CONTENT, "我们已将验证码发送到您的手机");
            PayFragment fragment = new PayFragment();
            fragment.setArguments(bundle);
            fragment.setPaySuccessCallBack(RegisteredActivity.this);
            fragment.show(getSupportFragmentManager(), "Pay");
        }
    };

    //处理手机号上传过程的函数
    private void phone_number()
    {

    }

    @Override
    public void onInputFinish(String result)
    {
        //上传所有信息，判断是否注册成功
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }



}
