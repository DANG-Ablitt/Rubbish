package com.example.rubbish.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rubbish.R;
import com.example.rubbish.beas.Card;
import com.example.rubbish.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends BaseAdapter {

    private List<Card> cards = new ArrayList<>();

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Card getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MusicAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_music, null);
            holder = new MusicAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MusicAdapter.ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(cards.get(position).title);
        holder.tv_subTitle.setText(cards.get(position).info);
        holder.mImageView.setImageResource(cards.get(position).imageSrc);
        holder.tv_id.setText("店铺ID："+cards.get(position).id+"");


        return convertView;
    }

    class ViewHolder {
        final CircleImageView mImageView;
        final TextView tv_title;
        final TextView tv_subTitle;
        final TextView tv_id;

        ViewHolder(View view) {
            mImageView = (CircleImageView) view.findViewById(R.id.avatar);
            tv_title = (TextView) view.findViewById(R.id.tv_song);
            tv_subTitle = (TextView) view.findViewById(R.id.tv_singer);
            tv_id = (TextView) view.findViewById(R.id.tv_id);
        }
    }

    public void refreshCard(){
        cards.clear();
        cards.add(new Card("1加1女装店", "为每一位顾客提供优质的产品", R.drawable.fzdp,1000));
        cards.add(new Card("启德酒水", "想喝一杯吗 进来看看", R.drawable.jsdp,1001));
        cards.add(new Card("三只松鼠","好可爱呀",R.drawable.lsdp,1002));
        cards.add(new Card("老一生鲜","购买水果蔬菜的朋友请进店",R.drawable.scdp,1003));
        cards.add(new Card("LEGO","让您的孩子玩的快乐",R.drawable.wjdp,1004));
        notifyDataSetChanged();
    }

    public void loadMoreCard(){
        cards.add(new Card("You Raise Me Up","Westlife",R.drawable.avatar6,0));
        cards.add(new Card("See You Again","Charlie Puth",R.drawable.avatar7,1));
        cards.add(new Card("Love Story","Taylor Swift",R.drawable.avatar0,2));
        cards.add(new Card("Let It Go","Demi Lovato",R.drawable.avatar8,3));
        cards.add(new Card("Secret Garden", "Song From A Secret Garden", R.drawable.avatar2,4));
        cards.add(new Card("Call Me Maybe","Carly Rae Jepsen",R.drawable.avatar9,5));
        notifyDataSetChanged();
    }

    public void addCard() {
        cards.add(new Card("What Do You Mean?", "Justin Bieber", R.drawable.avatar1,0));
        cards.add(new Card("Secret Garden", "Song From A Secret Garden", R.drawable.avatar2,1));
        cards.add(new Card("Moves Like Jagger","Maroon 5",R.drawable.avatar3,2));
        cards.add(new Card("Work Hard, Play Hard","Wiz Khalifa",R.drawable.avatar4,3));
        cards.add(new Card("Love The Way You Lie (Part Ii)","Rihanna",R.drawable.avatar5,4));
        cards.add(new Card("You Raise Me Up","Westlife",R.drawable.avatar6,5));
        cards.add(new Card("See You Again","Charlie Puth",R.drawable.avatar7,6));
        cards.add(new Card("Let It Go","Demi Lovato",R.drawable.avatar8,7));
        cards.add(new Card("Call Me Maybe","Carly Rae Jepsen",R.drawable.avatar9,8));
        //Love Story   Taylor Swift
    }
}
