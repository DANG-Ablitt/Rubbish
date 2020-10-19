package com.example.rubbish;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import com.kannan.glazy.GlazyCard;
import com.kannan.glazy.Utils;
import com.kannan.glazy.pager.GlazyFragmentPagerAdapter;
import com.kannan.glazy.pager.GlazyViewPager;
import com.kannan.glazy.transformers.GlazyPagerTransformer;
import com.kannan.glazy.views.GlazyImageView.ImageCutType;

public class PagerActivity extends AppCompatActivity {

    private GlazyViewPager mPager;
    private GlazyFragmentPagerAdapter mPagerAdapter;
    private int key ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pager);
        setTitle("GlazyViewPager");

        mPager = (GlazyViewPager) findViewById(R.id.pager);
        mPagerAdapter = new GlazyFragmentPagerAdapter(getSupportFragmentManager(), getApplicationContext());

        Resources resources = getApplicationContext().getResources();
        int img_matt = resources.getIdentifier("zynr4", "drawable", getPackageName());
        int img_courteny = resources.getIdentifier("zynr3", "drawable", getPackageName());
        int img_david = resources.getIdentifier("zynr2", "drawable", getPackageName());
        int img_jennifer = resources.getIdentifier("zynr1", "drawable", getPackageName());


        String desc_jenif = "可回收物就是可以再生循环的垃圾。本身或材质可再利用的纸类、硬纸板、玻璃、塑料、金属、塑料包装，与这些材质有关的如：报纸、杂志、广告单及其它干净的纸类等皆可回收。" +
                " 主要包括废纸、塑料、玻璃、金属和纺织物五大类生活垃圾，有害垃圾、电子/电器垃圾和电池三类特殊危害垃圾以及废弃家具类垃圾。" +
                " 废纸主要包括：报纸、杂志、图书、各种包装纸、办公用纸、纸盒等，但是纸巾和卫生用纸由于水溶性太强不可回收；" +
                " 塑料主要包括：各种塑料袋、塑料包装物、一次性塑料餐盒和餐具、牙刷、杯子、矿泉水瓶等；" +
                " 玻璃主要包括：各种玻璃容器，根据回收工艺，玻璃分为无色玻璃，绿色玻璃，棕色玻璃；" +
                "金属主要包括:易拉罐、金属罐头盒、装饰物和铝箔等，按照回收材料分类：铁类，非铁类(一般指有色金属)；"+
                "纺织物主要包括：废弃衣服、毛巾、书包、布鞋等。"+
                "危害垃圾主要包括:油漆、颜料、各类清洗液等化学品，部分地区将电池和电子/电器垃圾也归于此类。"
                ;

        String desc_david = "有害垃圾指废电池、废灯管、废药品、废油漆及其容器等对人体健康或者自然环境造成直接或者潜在危害的生活废弃物。" +
                "常见包括废电池、废荧光灯管、废灯泡、废水银温度计、废油漆桶、过期药品等。有害有毒垃圾需特殊正确的方法安全处理。" +
                "有害垃圾包括废电池、废日光灯管、废水银温度计、过期药品等，这些垃圾需要特殊安全处理。" +
                "废镍镉电池和废氧化汞电池：充电电池、镉镍电池、铅酸电池、蓄电池、纽扣电池" +
                "废药品及其包装物：过期药物、药物胶囊、药片、药品内包装、使用过的医用纱布棉签等"+
                "废油漆和溶剂及其包装物：废油漆桶、染发剂壳、过期的指甲油、洗甲水"+
                "废矿物油及其包装物"+
                "废含汞温度计、废含汞血压计：水银血压计、水银体温计、水银温度计"+
                "废杀虫剂及其包装：老鼠药（毒鼠强）、杀虫喷雾罐"+
                "废胶片及废相纸：x光片等感光胶片、相片底片"
                ;


        String desc_matt = "干垃圾即其它垃圾，指除可回收物、有害垃圾、厨余垃圾（湿垃圾）以外的其它生活废弃物。" +
                " 干垃圾是对垃圾按照可回收垃圾、厨余垃圾、有害垃圾分类后剩余下来的一种垃圾。" +
                " 生活垃圾的具体分类标准可根据经济社会发展水平、生活垃圾特性和处置利用需要予以调整。" +
                " 包括砖瓦陶瓷、渣土、卫生间废纸、瓷器碎片等难以回收的废弃物，" +
                " 采取卫生填埋可有效减少对地下水、地表水、土壤及空气的污染，在当今社会，" +
                "还无有效化解其他垃圾的好方法，所以需尽量少产生。"+
                "餐巾纸、卫生间用纸、尿不湿、猫砂、狗尿垫、污损纸张、烟蒂、干燥剂"+
                "污损塑料、尼龙制品、编织袋、防碎气泡膜"+
                "大骨头、硬贝壳、硬果壳（椰子壳、榴莲壳、核桃壳、玉米衣、甘蔗皮）、硬果实（榴莲核、菠萝蜜核）"+
                "毛发、灰土、炉渣、橡皮泥、太空沙、带胶制品（胶水、胶带）、花盆、毛巾"+
                "一次性餐具、镜子、陶瓷制品、竹制品（竹篮、竹筷、牙签）"+
                "成分复杂的制品（伞、笔、眼镜、打火机）"
                ;

        String desc_corteny = "湿垃圾又称为厨余垃圾、有机垃圾，即易腐垃圾，指食材废料、剩菜剩饭、过期食品、瓜皮果核、花卉绿植、中药药渣等易腐的生物质生活废弃物。" +
                "湿垃圾是居民日常生活及食品加工、饮食服务、单位供餐等活动中产生的垃圾，" +
                "包括丢弃不用的菜叶、剩菜、剩饭、果皮、蛋壳、茶渣、骨头、动物内脏、鱼鳞、树叶、杂草等，其主要来源为家庭厨房、餐厅、饭店、食堂、市场及其他与食品加工有关的行业。" +
                " 有机垃圾是指日常生活垃圾中可分解的有机物质部分。有机垃圾包括食物残渣、菜根、菜叶，动物蹄、角、瓜皮、果屑、蛋壳、鱼鳞、毛发、植物枝干、树叶、杂草、动物尸体、牲畜粪便等。" +
                " 有机垃圾应由专人负责收 集，收集容器必须密封、有盖、防渗漏、防蝇、防鼠。"+
                " 有机垃圾有两大来源： ·园林垃圾和厨余垃圾。"+
                " 1、园林垃圾 ：树枝的收集呈松散状；树叶、杂草和植物放板条箱内。"+
                " 2、厨余垃圾：放塑料袋里或放饭带盖的桶里（2~3L的桶）。有机垃圾，尤其是厨余垃圾，要求比其他混合垃圾收集频次高。"+
                " 有机垃圾应由专人负责收 集，收集容器必须密封、有盖、防渗漏、防蝇、防鼠。"
                ;


        //取得从上一个Activity当中传递过来的Intent对象
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        if (intent != null)
        {
            key = intent.getIntExtra("key",0);
        }

        switch (key) {
            case 1:
                mPagerAdapter.addCardItem(
                        new GlazyCard()
                                .withTitle("干垃圾")
                                .withSubTitle("详解")
                                .withDescription(desc_matt.toUpperCase())
                                .withImageRes(img_matt)
                                .withImageCutType(ImageCutType.WAVE)
                                .withImageCutHeightDP(40)
                );
                break;
            case 2:
                mPagerAdapter.addCardItem(
                        new GlazyCard()
                                .withTitle("湿垃圾")
                                .withSubTitle("详解")
                                .withDescription(desc_corteny.toUpperCase())
                                .withImageRes(img_courteny)
                                .withImageCutType(ImageCutType.LINE_POSITIVE)
                                .withImageCutHeightDP(40)
                );
                break;
            case 3:
                mPagerAdapter.addCardItem(
                        new GlazyCard()
                                .withTitle("有害垃圾")
                                .withSubTitle("详解")
                                .withDescription(desc_david.toUpperCase())
                                .withImageRes(img_david)
                                .withImageCutType(ImageCutType.ARC)
                                .withImageCutHeightDP(40)
                );
                break;
            case 4:
                mPagerAdapter.addCardItem(
                        new GlazyCard()
                                .withTitle("可回收垃圾")
                                .withSubTitle("详解")
                                .withDescription(desc_jenif.toUpperCase())
                                .withImageRes(img_jennifer)
                                .withImageCutType(ImageCutType.LINE_POSITIVE)
                                .withImageCutHeightDP(40)
                );
                break;
            default:
                break;
        }

        mPager.setAdapter(mPagerAdapter);
        mPager.setPageMargin(Utils.dpToPx(getApplicationContext(), 25));
        mPager.setPageTransformer(false, new GlazyPagerTransformer());
    }

}
