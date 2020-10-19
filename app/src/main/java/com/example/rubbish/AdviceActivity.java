package com.example.rubbish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

public class AdviceActivity extends AppCompatActivity {
    private MultiAutoCompleteTextView advice_content;
    private EditText phone_num;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        String si = "建议反馈";

        advice_content = (MultiAutoCompleteTextView) findViewById(R.id.advice_multiAutoCompleteTextView1);
        phone_num = (EditText) findViewById(R.id.advice_editText_phoneNum);
        btn = (Button) findViewById(R.id.advice_btn_tijiao);
        btn.setOnClickListener(l);

    }

    private View.OnClickListener l = new View.OnClickListener() {

        @Override
        public void onClick(View v)
        {
            weitingjubao();
        }
    };


    private void weitingjubao()
    {
        //向服务器发送建议信息





    }
}




