package com.example.rubbish;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.rubbish.beas.CardItem;
import com.example.rubbish.fragment.CardPagerAdapter;

public class HuafeiActivity extends AppCompatActivity
{
    private ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huafei);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // arg0是当前选中的页面的Position
                //Log.e(TAG, "onPageSelected------>"+arg0);
                View view=mViewPager.getChildAt(arg0);
                //View view= LayoutInflater.from(HuafeiActivity.this).inflate(R.layout.fragment_adapter,null);
                Button button= (Button) view.findViewById(R.id.goumai);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //写上点击后要执行的事件
                        //将用户账号和金额上传服务器
                        Toast.makeText(HuafeiActivity.this, "236", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // arg0 :当前页面，及你点击滑动的页面；arg1:当前页面偏移的百分比；arg2:当前页面偏移的像素位置
                // 把当前显示的position传递出去
                // = arg0;
                //Log.e(TAG, "onPageScrolled------>arg0："+arg0+"\nonPageScrolled------>arg1:"+arg1+"\nonPageScrolled------>arg2:"+arg2);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                //arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做。
                //if(arg0 == 0){
                //    Log.e(TAG, "onPageScrollStateChanged------>0");
               // }else if(arg0 == 1){
                //    Log.e(TAG, "onPageScrollStateChanged------>1");
                //}else if(arg0 == 2){
                 //   Log.e(TAG, "onPageScrollStateChanged------>2");
               // }

            }
        });
    }
}
