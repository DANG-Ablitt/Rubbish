package com.example.rubbish;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;


public class GongyiActivity extends AppCompatActivity {
    private ExpandingList mExpandingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongyi);
        mExpandingList = findViewById(R.id.expanding_list_main);
        createItems();
    }

    private void createItems()
    {
        //从服务器下载数据并绑定
        addItem("2019-10-1", new String[]{"6:00--8:00     平安街", "11:00--12:00     市政府", "12:00--1:00     市政府", "5:00--6:00     平安街" }, R.color.pink, R.drawable.ic_ghost);
        addItem("2019-10-2", new String[]{"6:00--8:00     平安街",  "5:00--6:00     市政府" }, R.color.blue, R.drawable.ic_ghost);
        addItem("2019-10-3", new String[]{"6:00--8:00     平安街",  "5:00--6:00     市政府" }, R.color.purple, R.drawable.ic_ghost);
        addItem("2019-10-4", new String[]{"6:00--8:00     平安街", "11:00--12:00     市政府", "12:00--1:00     市政府", "5:00--6:00     平安街" }, R.color.yellow, R.drawable.ic_ghost);
        addItem("2019-10-5", new String[]{"6:00--8:00     市政府" }, R.color.orange, R.drawable.ic_ghost);
        addItem("2019-10-6", new String[]{"6:00--8:00     平安街", "11:00--12:00     市政府", "12:00--1:00     市政府", "5:00--6:00     平安街" }, R.color.green, R.drawable.ic_ghost);
        addItem("2019-10-7", new String[]{"12:00--1:00     平安街", "5:00--6:00     平安街" }, R.color.blue, R.drawable.ic_ghost);
    }

    private void addItem(String title, String[] subItems, int colorRes, int iconRes) {
        //Let's create an item with R.layout.expanding_layout
        final ExpandingItem item = mExpandingList.createNewItem(R.layout.expanding_layout);

        //If item creation is successful, let's configure it
        if (item != null) {
            item.setIndicatorColorRes(colorRes);
            item.setIndicatorIconRes(iconRes);
            //It is possible to get any view inside the inflated layout. Let's set the text in the item
            ((TextView) item.findViewById(R.id.title)).setText(title);

            //We can create items in batch.
            item.createSubItems(subItems.length);
            for (int i = 0; i < item.getSubItemsCount(); i++) {
                //Let's get the created sub item by its index
                final View view = item.getSubItemView(i);

                //Let's set some values in
                configureSubItem(item, view, subItems[i]);
            }
           /*  item.findViewById(R.id.add_more_sub_items).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInsertDialog(new OnItemCreated() {
                        @Override
                        public void itemCreated(String title) {
                            View newSubItem = item.createSubItem();
                            configureSubItem(item, newSubItem, title);
                        }
                    });
                }
            });

           item.findViewById(R.id.remove_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mExpandingList.removeItem(item);
                }
            });*/
        }
    }

    private void configureSubItem(final ExpandingItem item, final View view, String subTitle) {
        ((TextView) view.findViewById(R.id.sub_title)).setText(subTitle);
        view.findViewById(R.id.remove_sub_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item.removeSubItem(view);
                AlertDialog.Builder builder = new AlertDialog.Builder(GongyiActivity.this);
                builder.setTitle("确定报名参加");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //与服务器通信 上传数据
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();

            }
        });
    }

    private void showInsertDialog(final OnItemCreated positive) {
        final EditText text = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(text);
        builder.setTitle(R.string.enter_title);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                positive.itemCreated(text.getText().toString());
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.show();
    }

    interface OnItemCreated {
        void itemCreated(String title);
    }
}

