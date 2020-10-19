package com.example.rubbish.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.rubbish.PagerActivity;
import com.example.rubbish.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


import java.util.ArrayList;

public class ShouyeFragment extends Fragment
{
    private View v;
    CarouselView carouselView;
    private ImageView gan,shi,youhai,wuhai;
    int[] sampleImages = {R.drawable.dhl1, R.drawable.dh00, R.drawable.dhl3, R.drawable.dhl4, R.drawable.dh11};

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_shouye, container, false);
        carouselView = (CarouselView) v.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        gan = (ImageView) v.findViewById(R.id.user_gan);
        shi = (ImageView) v.findViewById(R.id.user_shi);
        youhai= (ImageView) v.findViewById(R.id.user_youhai);
        wuhai= (ImageView) v.findViewById(R.id.user_wuhai);
        gan.setOnClickListener(l);
        shi.setOnClickListener(l);
        youhai.setOnClickListener(l);
        wuhai.setOnClickListener(l);
        return v;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    // 点击各个选项的监听
    private View.OnClickListener l = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent i=new Intent(getActivity(), PagerActivity.class);;
            switch (v.getId()) {
                case R.id.user_gan:
                    i.putExtra("key",1);
                    startActivity(i);
                    break;
                case R.id.user_shi:
                    i.putExtra("key",2);
                    startActivity(i);
                    break;
                case R.id.user_youhai:
                    i.putExtra("key",3);
                    startActivity(i);
                    break;
                case R.id.user_wuhai:
                    i.putExtra("key",4);
                    startActivity(i);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onResume() {
        super.onResume();

    }




}
