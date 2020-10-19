package com.example.rubbish;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.zhouwei.library.CustomPopWindow;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.bezierlayout.BezierLayout;
import com.example.rubbish.adapter.PhotoAdapter;
import com.example.rubbish.beas.Photo;
import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {
    private PhotoAdapter photoAdapter;
    private CustomPopWindow mCustomPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);


        setupRecyclerView((RecyclerView) findViewById(R.id.recyclerview));

        findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void setupRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        photoAdapter = new PhotoAdapter();
        rv.setAdapter(photoAdapter);

        final TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
//        ProgressLayout headerView = new ProgressLayout(this);
        BezierLayout headerView = new BezierLayout(this);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setMaxHeadHeight(140);
//        refreshLayout.setFloatRefresh(true);
//        refreshLayout.setPureScrollModeOn(true);
        refreshLayout.setOverScrollBottomShow(false);
//        refreshLayout.setAutoLoadMore(true);

//        addHeader();
        refreshCard();
        findViewById(R.id.ib_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //refreshLayout.startRefresh();
                showPopMenu();

            }
        });

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshCard();
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreCard();
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);
            }
        });

//        refreshLayout.startRefresh();
    }

    void refreshCard() {
        List<Photo> photos = new ArrayList<>();
//        foods.add(new Food("Preparing Salmon Steak Close Up","BY VIKTOR HANACEK",R.drawable.food1,R.drawable.avatar0));
//        foods.add(new Food("Fresh & Healthy Fitness Broccoli Pie with Basil","BY VIKTOR HANACEK",R.drawable.food2,R.drawable.avatar1));
//        foods.add(new Food("Enjoying a Tasty Burger","BY VIKTOR HANACEK",R.drawable.food3,R.drawable.avatar2));
//        foods.add(new Food("Fresh Strawberries and Blackberries in Little Bowl","BY VIKTOR HANACEK",R.drawable.food4,R.drawable.avatar3));
//        foods.add(new Food("Baked Healthy Fitness Broccoli Pie with Basil","BY VIKTOR HANACEK",R.drawable.food5,R.drawable.avatar4));
        photos.add(new Photo("猫耳朵", R.drawable.ls11,1011));
        photos.add(new Photo("玩具汽车", R.drawable.wj5,1002));
        photos.add(new Photo("面包", R.drawable.ls12,1079));
        photos.add(new Photo("小火车", R.drawable.wj4,1230));
        photos.add(new Photo("小火车", R.drawable.wj4,1066));
        photos.add(new Photo("长袖", R.drawable.yf11,1555));
        photos.add(new Photo("短裤", R.drawable.yf12,1000));
        photos.add(new Photo("生菜", R.drawable.sc13,1111));
        photos.add(new Photo("卡片", R.drawable.wj10,1223));
        photoAdapter.setDataList(photos);
    }

    void loadMoreCard() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo("chest nut", R.drawable.photo1,1));
        photos.add(new Photo("fish", R.drawable.photo2,1));
        photos.add(new Photo("cat", R.drawable.photo10,1));
        photos.add(new Photo("guitar", R.drawable.photo3,1));
        photos.add(new Photo("common-hazel", R.drawable.photo4,1));
        photos.add(new Photo("cherry", R.drawable.photo5,1));
        photos.add(new Photo("flower details", R.drawable.photo6,1));
        photos.add(new Photo("tree", R.drawable.photo7,1));
        photos.add(new Photo("blue berries", R.drawable.photo8,1));
        photos.add(new Photo("snow man", R.drawable.photo9,1));
        //chest nut   cat and fish  guitar   common-hazel  cherry   flower details   tree
        //blue berries   snow man
//        foods.add(new Food(R.drawable.food3));
//        foods.add(new Food(R.drawable.food2));
//        foods.add(new Food(R.drawable.food1));
        photoAdapter.addItems(photos);
    }



    /**
     * 处理弹出显示内容、点击事件等逻辑
     * @param contentView
     */
    private void handleLogic(View contentView){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCustomPopWindow!=null){
                    mCustomPopWindow.dissmiss();
                }
                String showContent = "";
                switch (v.getId()){
                    case R.id.menu1:
                        showContent = "加载店铺列表";
                        break;
                }
                Toast.makeText(PhotoActivity.this,showContent,Toast.LENGTH_SHORT).show();
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(l);
    }



    private void showPopMenu(){
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_menu,null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .create()
                .showAsDropDown(findViewById(R.id.ib_refresh),0,20);


    }



    private View.OnClickListener l = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent i=null;
            i=new Intent(PhotoActivity.this, MusicActivity.class);
            startActivity(i);
        }
    };







}
