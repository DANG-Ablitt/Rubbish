package com.example.rubbish.view;

import com.example.rubbish.R;
import com.stephentuso.welcome.*;
import static com.stephentuso.welcome.WelcomeConfiguration.BottomLayout.INDICATOR_ONLY;

public class welcomeview extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .bottomLayout(INDICATOR_ONLY)
                .page(new BasicPage(R.drawable.ic_front_desk_white,
                        "欢迎",
                        "感谢您首次安装我们的网约代收垃圾-“大妈”再也不烦你APP")
                        .background(R.color.orange_background)
                )

                .page(new BasicPage(R.drawable.ic_thumb_up_white,
                        "商城",
                        "我们的积分商城有大量的宝贝等待您的光临哦")
                        .background(R.color.red_background)
                )

                .page(new ParallaxPage(R.layout.parallax_example,
                        "服务",
                        "购买我们的服务帮助您摆脱不会仍垃圾的痛苦")
                        .lastParallaxFactor(2f)
                        .background(R.color.purple_background)
                )

                .page(new BasicPage(R.drawable.ic_edit_white,
                        "GO IT...",
                        "等不及了吧，您快登陆吧")
                        .background(R.color.blue_background)
                )
                .canSkip(false)
                .swipeToDismiss(true)
                .build();
    }
}

