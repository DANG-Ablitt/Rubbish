package com.example.rubbish;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import com.example.rubbish.fragment.FuwuFragment;
import com.example.rubbish.fragment.JifengFragment;
import com.example.rubbish.fragment.ShouyeFragment;
import com.example.rubbish.fragment.WodeFragment;
import com.luseen.luseenbottomnavigation.BottomNavigation.*;

public class HomeActivity extends AppCompatActivity
{
    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        transaction = getSupportFragmentManager().beginTransaction();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        int[] image = {R.drawable.ic_home_black_24dp, R.drawable.ic_dashboard_black_24dp,
                R.drawable.ic_book_black_24dp, R.drawable.ic_account_circle_white_24dp};
        int[] color = {ContextCompat.getColor(this, R.color.firstColor), ContextCompat.getColor(this, R.color.secondColor),
                ContextCompat.getColor(this, R.color.thirdColor), ContextCompat.getColor(this, R.color.fourthColor)};

        if (bottomNavigationView != null)
        {
            bottomNavigationView.isWithText(false);
            bottomNavigationView.isColoredBackground(false);
            bottomNavigationView.setTextActiveSize(getResources().getDimension(R.dimen.text_active));
            bottomNavigationView.setTextInactiveSize(getResources().getDimension(R.dimen.text_inactive));
            bottomNavigationView.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(this, R.color.firstColor));
            bottomNavigationView.setFont(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Noh_normal.ttf"));
        }
        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("首页", color[0], image[0]);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("积分", color[1], image[1]);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("服务", color[2], image[2]);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("我的", color[3], image[3]);
        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
        bottomNavigationView.addTab(bottomNavigationItem3);
        XuanZ(0);

        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                switch (index) {
                    case 0:
                       XuanZ(0);
                        break;
                    case 1:
                        XuanZ(1);
                        break;
                    case 2:
                        XuanZ(2);
                        break;
                    case 3:
                        XuanZ(3);
                        break;
                }
            }
        });
    }

    private void XuanZ(int i)
    {
        ShouyeFragment fi3=new ShouyeFragment();
        JifengFragment fi2=new JifengFragment();
        FuwuFragment fi1=new FuwuFragment();
        WodeFragment fi0=new WodeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.myfragment_1,fi0);
        transaction.add(R.id.myfragment_1,fi1);
        transaction.add(R.id.myfragment_1,fi2);
        transaction.add(R.id.myfragment_1,fi3);
        switch(i)
        {
            case 0:
                transaction.replace(R.id.myfragment_1,fi3);
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.myfragment_1,fi2);
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.myfragment_1,fi1);
                transaction.commit();
                break;
            case 3:
                transaction.replace(R.id.myfragment_1,fi0);
                transaction.commit();
                break;
        }
    }
}
