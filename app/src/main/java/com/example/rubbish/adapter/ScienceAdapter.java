package com.example.rubbish.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rubbish.R;
import com.example.rubbish.beas.Card;
import java.util.ArrayList;
import java.util.List;

public class ScienceAdapter extends BaseAdapter {

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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_science, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(cards.get(position).title);
        holder.iv_cover.setImageResource(cards.get(position).imageSrc);
        holder.tv_id.setText("商品ID："+cards.get(position).id+"");

        return convertView;
    }

    class ViewHolder {
        final ImageView iv_cover;
        final TextView tv_name;
        final TextView tv_id;

        ViewHolder(View view) {
            iv_cover = (ImageView) view.findViewById(R.id.iv_cover);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_id = (TextView) view.findViewById(R.id.tv_id);
        }
    }

    public void refreshCard() {
        cards.clear();
        cards.add(new Card("短袖黑", "", R.drawable.yf0,000));
        cards.add(new Card("长袖绿", "", R.drawable.yf1,001));
        cards.add(new Card("短袖红", "", R.drawable.yf2,002));
        cards.add(new Card("长裤白", "", R.drawable.yf3,003));
        cards.add(new Card("风衣黑", "", R.drawable.yf4,004));
        cards.add(new Card("短袖黄", "", R.drawable.yf5,005));
        cards.add(new Card("高帮帆布鞋", "", R.drawable.yf6,006));
        cards.add(new Card("长袖灰", "", R.drawable.yf7,007));
        cards.add(new Card("长袖黄", "", R.drawable.yf8,010));
        cards.add(new Card("长袖蓝", "", R.drawable.yf9,011));
        notifyDataSetChanged();
    }

    public void refreshCard1() {
        cards.clear();
        cards.add(new Card("饮料", "", R.drawable.js1,100));
        cards.add(new Card("饮料", "", R.drawable.js2,101));
        cards.add(new Card("饮料", "", R.drawable.js3,102));
        cards.add(new Card("饮料", "", R.drawable.js4,103));
        cards.add(new Card("饮料", "", R.drawable.js5,104));
        cards.add(new Card("饮料", "", R.drawable.js6,105));
        cards.add(new Card("饮料", "", R.drawable.js7,106));
        cards.add(new Card("饮料", "", R.drawable.js8,107));
        cards.add(new Card("饮料", "", R.drawable.js9,108));
        cards.add(new Card("饮料", "", R.drawable.js10,109));
        notifyDataSetChanged();
    }

    public void refreshCard2() {
        cards.clear();
        cards.add(new Card("小吃", "", R.drawable.ls1,200));
        cards.add(new Card("小吃", "", R.drawable.ls2,201));
        cards.add(new Card("小吃", "", R.drawable.ls3,202));
        cards.add(new Card("小吃", "", R.drawable.ls4,203));
        cards.add(new Card("小吃", "", R.drawable.ls5,204));
        cards.add(new Card("小吃", "", R.drawable.ls6,205));
        cards.add(new Card("小吃", "", R.drawable.ls7,206));
        cards.add(new Card("小吃", "", R.drawable.ls8,207));
        cards.add(new Card("小吃", "", R.drawable.ls9,208));
        cards.add(new Card("小吃", "", R.drawable.ls10,209));
        notifyDataSetChanged();
    }

    public void refreshCard3() {
        cards.clear();
        cards.add(new Card("瓜果蔬菜", "", R.drawable.sc1,300));
        cards.add(new Card("瓜果蔬菜", "", R.drawable.sc2,301));
        cards.add(new Card("瓜果蔬菜", "", R.drawable.sc3,302));
        cards.add(new Card("瓜果蔬菜", "", R.drawable.sc4,303));
        cards.add(new Card("瓜果蔬菜", "", R.drawable.sc5,304));
        cards.add(new Card("瓜果蔬菜", "", R.drawable.sc6,305));
        cards.add(new Card("瓜果蔬菜", "", R.drawable.sc7,306));
        cards.add(new Card("瓜果蔬菜", "", R.drawable.sc0,307));
        cards.add(new Card("瓜果蔬菜", "", R.drawable.sc9,308));
        cards.add(new Card("瓜果蔬菜", "", R.drawable.sc10,309));
        notifyDataSetChanged();
    }

    public void refreshCard4() {
        cards.clear();
        cards.add(new Card("玩具", "", R.drawable.wj1,400));
        cards.add(new Card("玩具", "", R.drawable.wj2,401));
        cards.add(new Card("玩具", "", R.drawable.wj3,402));
        cards.add(new Card("玩具", "", R.drawable.wj4,403));
        cards.add(new Card("玩具", "", R.drawable.wj5,404));
        cards.add(new Card("玩具", "", R.drawable.wj6,405));
        cards.add(new Card("玩具", "", R.drawable.wj7,407));
        cards.add(new Card("玩具", "", R.drawable.wj8,408));
        cards.add(new Card("玩具", "", R.drawable.wj9,409));
        cards.add(new Card("玩具", "", R.drawable.wj10,406));
        notifyDataSetChanged();
    }

    public void loadMoreCard() {
        cards.add(new Card("genetics", "", R.drawable.science1,1));
        cards.add(new Card("globe", "", R.drawable.science2,2));
        cards.add(new Card("lab-flask-leaf", "", R.drawable.science3,3));
        cards.add(new Card("magnet", "", R.drawable.science4,0));
        cards.add(new Card("microscope", "", R.drawable.science5,2));
        cards.add(new Card("moon", "", R.drawable.science6,5));
        cards.add(new Card("telescope", "", R.drawable.science7,9));
        cards.add(new Card("satellite", "", R.drawable.science8,9));
        cards.add(new Card("Newtons-cradle", "", R.drawable.science9,4));
        cards.add(new Card("nuclear-symbol", "", R.drawable.science10,0));
        notifyDataSetChanged();
    }
}
