package com.kiwi.popupwindowutils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.kiwi.library.PhotoUtils;

/**
 * Created by kiwi on 2017/6/17.
 * Email:2763015920@qq.com
 */

public class PhotoTestActivity extends AppCompatActivity {

    private ImageView imgIcon;
    private PhotoUtils photoUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_test);
        imgIcon = (ImageView) findViewById(R.id.img_photo_icon);

        //1,新建一个对象
        photoUtils = new PhotoUtils(this);
    }
    public void takePhoto(View view){//拍照的方法
    //2,直接调用拍照方法
        photoUtils.takePhoto();
    }
    public void openAlbum(View view){//打开相册的方法
        //
        photoUtils.openAlbum();
    }

    //权限处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //3,添加权限处理
        photoUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //结果处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //4,获取图片的路径
        String imgPath = photoUtils.onActivityResult(requestCode, resultCode, data);
        if (!TextUtils.isEmpty(imgPath)){
            //一般需要对图片压缩处理
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
            imgIcon.setImageBitmap(bitmap);
        }
        //带裁剪的用法类似,只在结果处理上不同,下面是带裁剪的结果处理
//        PhotoUtils2 photoUtils2 = new PhotoUtils2(this);
//        Bitmap bitmap = photoUtils2.onActivityResult(requestCode, resultCode, data);
        //后续根据需要处理
    }
}
