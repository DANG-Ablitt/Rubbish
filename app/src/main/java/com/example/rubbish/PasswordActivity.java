package com.example.rubbish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rubbish.fragment.PayFragment;
import com.example.rubbish.view.PayPwdView;

public class PasswordActivity extends AppCompatActivity implements PayPwdView.InputCallBack
{
    //手机号  新密码  确认新密码
    private EditText phone_number,new_password,new_determine_password;
    //下一步
    private Button last;
    //手机号  新密码  确认新密码的输入值
    private String phone_number_input,new_password_input,new_determine_password_input;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        //获取XML文件中的控件
        phone_number=(EditText)findViewById(R.id.etname);
        new_password=(EditText)findViewById(R.id.etPassword);
        new_determine_password=(EditText)findViewById(R.id.etRePassword);
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
            phone_number.setText(phone_number_input);
            new_password.setText(new_password_input);
            new_determine_password.setText(new_determine_password_input);
            //上传手机号
            phone_number();
            //弹出输入验证码界面
            Bundle bundle = new Bundle();
            bundle.putString(PayFragment.EXTRA_CONTENT, "我们已将验证码发送到您的手机");
            PayFragment fragment = new PayFragment();
            fragment.setArguments(bundle);
            fragment.setPaySuccessCallBack(PasswordActivity.this);
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
        //上传所有信息，判断密码是否修改成功
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

}
