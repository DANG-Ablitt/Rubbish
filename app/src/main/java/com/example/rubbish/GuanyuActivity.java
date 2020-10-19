package com.example.rubbish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GuanyuActivity extends AppCompatActivity {

    ImageView bgapp, clover;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;

    //版本信息  洗耳恭听
    private ImageView banbenxx,xiergt;
    //检查更新  清理缓存
    private ImageView jianchagx,qinglihc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanyu);
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);
        bgapp.animate().translationY(-1900).setDuration(800).setStartDelay(300);
        clover.animate().alpha(0).setDuration(800).setStartDelay(600);
        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);
        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);

        banbenxx = (ImageView) findViewById(R.id.banbenxinxi);
        xiergt = (ImageView) findViewById(R.id.xiergongting);
        jianchagx = (ImageView) findViewById(R.id.jianchagenxin);
        qinglihc = (ImageView) findViewById(R.id.qinglihuancun);

        banbenxx.setOnClickListener(l);
        xiergt.setOnClickListener(l);
        jianchagx.setOnClickListener(l);
        qinglihc.setOnClickListener(l);
    }


    // 点击各个选项的监听
    private View.OnClickListener l = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent i=null;
            switch (v.getId()) {
                case R.id.banbenxinxi:
                    i=new Intent(GuanyuActivity.this, AboutActivity.class);
                    startActivity(i);
                    break;
                case R.id.xiergongting:
                    i=new Intent(GuanyuActivity.this, AdviceActivity.class);
                    startActivity(i);
                    break;
                case R.id.jianchagenxin:
                    Toast.makeText(GuanyuActivity.this, "您已安装最新版本", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.qinglihuancun:
                    Toast.makeText(GuanyuActivity.this, "缓存数据清理完成", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
}
