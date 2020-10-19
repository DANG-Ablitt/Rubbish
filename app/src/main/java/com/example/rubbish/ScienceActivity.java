package com.example.rubbish;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.rubbish.adapter.ScienceAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

public class ScienceActivity extends AppCompatActivity {

    private ScienceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);
        setupGridView((GridView) findViewById(R.id.gridView));

        findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupGridView(GridView gridView) {
        final TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
        SinaRefreshView headerView = new SinaRefreshView(this);
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
//        TextHeaderView headerView = (TextHeaderView) View.inflate(this,R.layout.header_tv,null);
        refreshLayout.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(this);
        refreshLayout.setBottomView(loadingView);

        adapter = new ScienceAdapter();
        gridView.setAdapter(adapter);
        //adapter.refreshCard();
        Intent intent = getIntent();
        if (intent != null)
        {
            String value = intent.getStringExtra("key");
            System.out.println(value);
            if(value.equals("店铺ID：1000"))
            {
                adapter.refreshCard();
            }
            if(value.equals("店铺ID：1001"))
            {
                adapter.refreshCard1();
            }
            if(value.equals("店铺ID：1002"))
            {
                adapter.refreshCard2();
            }
            if(value.equals("店铺ID：1003"))
            {
                adapter.refreshCard3();
            }
            if(value.equals("店铺ID：1004"))
            {
                adapter.refreshCard4();
            }
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                //Toast.makeText(getContext(),tv_info.getText(),Toast.LENGTH_SHORT).show();
                intent = new Intent(ScienceActivity.this,XiangQinActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                //Toast.makeText(ScienceActivity.this, "" + i, Toast.LENGTH_SHORT).show();
            }
        });

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //adapter.refreshCard();
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //adapter.loadMoreCard();
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);
            }
        });
    }
}

