package com.example.rubbish.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rubbish.AddressActivity;
import com.example.rubbish.FeiyongActivity;
import com.example.rubbish.GongyiActivity;
import com.example.rubbish.GuanyuActivity;
import com.example.rubbish.QiandaoActivity;
import com.example.rubbish.R;
import com.example.rubbish.TongzhiActivity;
import com.example.rubbish.XinxiActivity;
import com.example.rubbish.XiugaimimaActivity;

public class WodeFragment extends Fragment
{
    private View v;
    //头像
    private ImageView photo;
    //昵称和手机号
    private TextView nickname,phone_number;
    //我的信息 签到 收货地址
    private ImageView my_information,check_in,shipping_address;
    //我的通知 修改密码 关于我们
    private ImageView my_notice,change_password,about_us;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_wode, container, false);
        photo = (ImageView) v.findViewById(R.id.user_photo);
        my_information = (ImageView) v.findViewById(R.id.user_my_information);
        check_in = (ImageView) v.findViewById(R.id.user_check_in);
        shipping_address = (ImageView) v.findViewById(R.id.user_shipping_address);
        my_notice = (ImageView) v.findViewById(R.id.user_my_notice);
        change_password = (ImageView) v.findViewById(R.id.user_change_password);
        about_us = (ImageView) v.findViewById(R.id.user_about_us);
        nickname=(TextView) v.findViewById(R.id.user_name);
        phone_number=(TextView) v.findViewById(R.id.user_id);

        my_information.setOnClickListener(l);
        check_in.setOnClickListener(l);
        shipping_address.setOnClickListener(l);
        my_notice.setOnClickListener(l);
        change_password.setOnClickListener(l);
        about_us.setOnClickListener(l);

        return v;
    }

    // 点击各个选项的监听
    private View.OnClickListener l = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent i=null;
            switch (v.getId()) {
                case R.id.user_my_information:
                    i=new Intent(getActivity(), XinxiActivity.class);
                    startActivity(i);
                    break;
                case R.id.user_check_in:
                    i=new Intent(getActivity(), QiandaoActivity.class);
                    startActivity(i);
                    break;
                case R.id.user_shipping_address:
                    i=new Intent(getActivity(), AddressActivity.class);
                    startActivity(i);
                    break;
                case R.id.user_my_notice:
                    i=new Intent(getActivity(), TongzhiActivity.class);
                    startActivity(i);
                    break;
                case R.id.user_change_password:
                    i=new Intent(getActivity(), XiugaimimaActivity.class);
                    startActivity(i);
                    break;
                case R.id.user_about_us:
                    i=new Intent(getActivity(), GuanyuActivity.class);
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
