package com.example.rubbish;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rubbish.application.ExitApplication;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RuzhuActivaty extends Activity
{
    //基本信息：姓名 手机 身份证号
    private EditText name, iphone, sfzhao;
    private String name1, iphone1, sfzhao1;
    //基本信息：店名 手机 身份证号
    private EditText dianming, xiaoshoufw;
    private String dianming1, xiaoshoufw1;
    //身份证正面
    private ImageView sfz;
    //营业执照正面
    private ImageView yyzz;
    //提交照片
    private PopupWindow zmPopupwindow;
    //下一步  提交
    private Button userXYB, user_TJ;
    private TextView album, camera, cancle;
    private ViewGroup group;
    private PopupWindow popupWindow;

    int ppp=0;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    private String fileName1,fileName2;

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruzhu);
        ExitApplication.getInstance().addActivity(this);
        //获取XML文件中的控件
        name = (EditText) findViewById(R.id.etname);
        iphone = (EditText) findViewById(R.id.etUsername);
        sfzhao = (EditText) findViewById(R.id.etPassword);
        dianming = (EditText) findViewById(R.id.etnamev);
        xiaoshoufw = (EditText) findViewById(R.id.etUsernamev);
        userXYB = (Button) findViewById(R.id.btLogin);
        userXYB.setOnClickListener(listener);
    }

    //对事件listener的监听
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //记录用户输入的数据
            name1=name.getText().toString();
            iphone1=iphone.getText().toString();
            sfzhao1=sfzhao.getText().toString();
            dianming1=dianming.getText().toString();
            xiaoshoufw1=xiaoshoufw.getText().toString();
            //如果每一个输入项都不为空，进入图片上传页
            if("".equals(name1)||"".equals(iphone1)||"".equals(sfzhao1)||"".equals(dianming1)||"".equals(xiaoshoufw1))
            {
                Toast.makeText(RuzhuActivaty.this, "信息填写不完整", Toast.LENGTH_SHORT).show();
            }
            else
            {
                //进入图片上传页
                setPwdWindow();
                zmPopupwindow.showAtLocation(findViewById(R.id.login_activity_layout), Gravity.CENTER, 0, 0);
            }
        }
    };

    //图片上传页PopupWindow
    private void setPwdWindow() {
        ViewGroup group1 = (ViewGroup) LayoutInflater.from(this)
                .inflate(R.layout.popu_zhengjian, null);
        zmPopupwindow = new PopupWindow(group1,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true);
        Display display = getWindowManager().getDefaultDisplay();
        //设置高度   必须代码设置
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        zmPopupwindow.setHeight(height);
        sfz = (ImageView) group1.findViewById(R.id.fragment_fabu_pic1);
        yyzz = (ImageView) group1.findViewById(R.id.fragment_fabu_pic1v);
        user_TJ = (Button) group1.findViewById(R.id.btLoginv);
        zmPopupwindow.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.white)));
        zmPopupwindow.setFocusable(true);
        zmPopupwindow.setOutsideTouchable(false);
        zmPopupwindow.setClippingEnabled(false);
        sfz.setOnClickListener(d_listener);
        yyzz.setOnClickListener(d_listener);
        user_TJ.setOnClickListener(d_listener);
    }

    //处理d_listener的事件监听
    private View.OnClickListener d_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            view=v;
            switch (v.getId()) {
                case R.id.fragment_fabu_pic1:
                    //绑定身份证照片正面
                    ppp=1;
                    setUploadPhoto(v);
                    popupWindow.showAtLocation(findViewById(R.id.login_activity_layout), Gravity.BOTTOM, 0, 0);
                    backgroundAlpha(0.5f);
                    break;
                case R.id.fragment_fabu_pic1v:
                    //绑定营业执照照片正面
                    ppp=2;
                    setUploadPhoto(v);
                    popupWindow.showAtLocation(findViewById(R.id.btLoginv), Gravity.BOTTOM, 0, 0);
                    backgroundAlpha(0.5f);
                    break;
                case R.id.btLoginv:
                    //将数据上传服务器
                    break;
                default:
                    break;
            }
        }
    };


    //设置头像上传
    private void setUploadPhoto(View v){

        group = (ViewGroup) LayoutInflater.from(this)
                .inflate(R.layout.upload_photo, null);
        album = (TextView) group.findViewById(R.id.upload_photo_album);
        camera = (TextView) group.findViewById(R.id.upload_photo_camera);
        cancle = (TextView) group.findViewById(R.id.upload_photo_cancle);
        //设置上传头像弹出的菜单
        popupWindow = new PopupWindow(group,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        backgroundAlpha(1f);
        //添加pop窗口关闭事件
        popupWindow.setOnDismissListener(new poponDismissListener());
        // 给上传头像的选择设置监听器
        album.setOnClickListener(uploadPhoto_listener);
        camera.setOnClickListener(uploadPhoto_listener);
        cancle.setOnClickListener(uploadPhoto_listener);

    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }


    private View.OnClickListener uploadPhoto_listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 相册选择照片
                case R.id.upload_photo_album:
                    albumPhoto();
                    break;
                // 拍照
                case R.id.upload_photo_camera:
                    takePhoto();
                    break;
                // 取消上传头像
                case R.id.upload_photo_cancle:
                    popupWindow.dismiss();
                    break;

                default:
                    break;
            }
        }
    };

    // 从本地相册选取图片作为头像
    private void albumPhoto() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void takePhoto() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == 0) {
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(this, "没有SDCard!", Toast.LENGTH_LONG).show();
                }
                break;
            case CODE_RESULT_REQUEST:
                if (intent != null) {

                    setImageToHeadView(intent);
                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }


    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    @SuppressLint("SdCardPath")
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            ((ImageView)view).setImageBitmap(photo);
            if(ppp==1)
            {
                fileName1="";
                //fileName1=bitmapToString(photo);
            }
            if(ppp==2)
            {
                fileName2="";
                //fileName2=bitmapToString(photo);
            }

        }
    }


    /**
     　　* 加载本地图片
     　　* @param url
     　　* @return
     　　*/
    public Bitmap getLoacalBitmap(String url) {
        try{
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }
    /**
     * 获取当前时间作为名字
     */
    private String getPhoneName(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate=new Date(System.currentTimeMillis());//获取当前时间
        String str=formatter.format(curDate)+".jpg";
        return str;
    }

    //将位图转换为字符串
    public String bitmapToString(Bitmap bitmap){
        //将Bitmap转换成字符串
        String string=null;
        ByteArrayOutputStream bStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bStream);
        byte[]bytes=bStream.toByteArray();
        //BASE64Encoder encoder = new BASE64Encoder();
        //return encoder.encode(bytes);//返回Base64编码过的字节数组字符串
        //string=Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }
}
