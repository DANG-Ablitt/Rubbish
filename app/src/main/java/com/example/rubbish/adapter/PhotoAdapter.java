package com.example.rubbish.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rubbish.PhotoActivity;
import com.example.rubbish.R;
import com.example.rubbish.XiangQinActivity;
import com.example.rubbish.base.*;
import com.example.rubbish.beas.Photo;
import butterknife.BindView;


public class PhotoAdapter extends BaseRecyclerAdapter<Photo> {
    @Override
    public CommonHolder<Photo> setViewHolder(ViewGroup parent) {
        return new CardHolder(parent.getContext(), parent);
    }

    class CardHolder extends CommonHolder<Photo> {

        @BindView(R.id.tv_info)
        TextView tv_info;

        @BindView(R.id.iv_pic)
        ImageView iv_pic;

        @BindView(R.id.tv_id)
        TextView tv_id;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_photo);
        }

        @Override
        public void bindData(Photo photo) {
            iv_pic.setImageResource(photo.imgSrc);
            tv_info.setText(photo.name);
            tv_id.setText("商品ID："+photo.id+"");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = null;
                    //Toast.makeText(getContext(),tv_info.getText(),Toast.LENGTH_SHORT).show();
                    intent = new Intent(getContext(),XiangQinActivity.class);
                    intent.putExtra("type",2);
                    getContext().startActivity(intent);
                }
            });
        }
    }
}