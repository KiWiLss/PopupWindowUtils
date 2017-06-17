package com.kiwi.library;

/**
 * Created by kiwi on 2017/6/17.
 * Email:2763015920@qq.com
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by kiwi on 2017/6/16.
 * Email:2763015920@qq.com
 * 极简封装,权限处理,带裁剪
 */

public class PhotoUtils2 {

    private String[] PERMISSIONS_CAMERA_AND_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    //检查是否有打开相机的权限

    public  final int OPEN_ALBUM=2;//打开相册是标识
    public  final int TAKE_PHOTO = 1;//拍照的标记
    public  final int PHOTO_RESULT=3;//结果标识
    public String mImgPath; //拍照或是相册选中图片的地址
    private Activity activity;



    public PhotoUtils2(Activity activity) {
        this.activity = activity;
        //针对7.0的处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }
    public File getTakePath(){
        return new File(activity.getExternalCacheDir(), "output_image.jpg");
    }
    //打开相机拍照的方法
    public void takePhoto() {
        //创建用于存放拍摄照片的文件
        File outputImage = new File(activity.getExternalCacheDir(), "output_image.jpg");
        //如果文件存在,就删除重新创建
        if (outputImage.exists()) {
            outputImage.delete();
        }
        try {
            outputImage.createNewFile();
            mImgPath = outputImage.toString();//记录存储拍照图片的路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri imgUri = Uri.fromFile(outputImage);
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        //判断系统相机是否可用
        if (intent.resolveActivity(activity.getPackageManager()) == null) {
            Toast.makeText(activity, "存储卡不可用", Toast.LENGTH_SHORT).show();
        }else {
            //判断权限
            boolean permission = isCameraPermission(activity, TAKE_PHOTO);
            if (permission){//有权限直接打开相机,没有自动申请
                activity.startActivityForResult(intent,TAKE_PHOTO);
            }
        }
    }

    public Bitmap onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //LogUtils.i("data==null"+data);
            switch (requestCode) {
                case TAKE_PHOTO://拍照
                    startPhotoZoom(Uri.fromFile(new File(activity.getExternalCacheDir(), "output_image.jpg")));
                    break;
                case OPEN_ALBUM://打开相册
                    if (data!=null){
                        startPhotoZoom(data.getData());
                    }
                    break;
                case PHOTO_RESULT:
                    Bundle extras = data.getExtras();
                    if (extras!=null){
                        Bitmap photo = extras.getParcelable("data");
                        return photo;
                    }
                    break;
            }
        }
        return null;
    }
    public Bitmap onActivityResult2(int requestCode, int resultCode, Intent data){
        if (resultCode==RESULT_OK){
            if (requestCode==PHOTO_RESULT){
                Bundle extras = data.getExtras();
                if (extras!=null){
                    Bitmap photo = extras.getParcelable("data");
                    return photo;
                }
            }
        }
        return null;
    }

    //打开相册的方法
    public void openAlbum(){
        //先判断存储卡是否可用
        boolean b = cameraIsCanUse(activity);
        if (!b){
            Toast.makeText(activity, "存储卡不可用", Toast.LENGTH_SHORT).show();
            return;
        }
        //先判断是否有权限
        boolean permission = isCameraPermission(activity, OPEN_ALBUM);
        if (permission){
            //Intent intent = new Intent("android.intent.action.GET_CONTENT");
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setType("image/*");
            activity.startActivityForResult(intent, OPEN_ALBUM);
        }
    }
    //判断相机是否可用
    public boolean cameraIsCanUse(Context context){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(context.getPackageManager()) == null) {
            return false;
        }
        return true;
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == TAKE_PHOTO)
        {
            if (grantResults.length>0){
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    takePhoto();
                } else
                {
                    // Permission Denied
                    Toast.makeText(activity, "缺少必要权限,请在设置中授予", Toast.LENGTH_SHORT).show();
                }
            }

        }


        if (requestCode == OPEN_ALBUM)
        {
            if (grantResults.length>0){

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    openAlbum();
                } else
                {
                    // Permission Denied
                    Toast.makeText(activity, "缺少必要权限,请在设置中授予", Toast.LENGTH_SHORT).show();
                }
            }
        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    /**
     * 收缩图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Log.i("MMM", "startPhotoZoom: start");
        Intent intent = new Intent("com.android.camera.action.CROP");//调用Android系统自带的一个图片剪裁页面,

        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");//进行修剪
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, PHOTO_RESULT);
        Log.i("MMM", "startPhotoZoom: end");
    }

    //检查是否有打开相机的权限,读取相册的权限
    public boolean isCameraPermission(Activity context, int requestCode){
        if (Build.VERSION.SDK_INT >= 23) {
            int storagePermission = ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
            if (storagePermission != PackageManager.PERMISSION_GRANTED || cameraPermission!= PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(context, PERMISSIONS_CAMERA_AND_STORAGE,
                        requestCode);
                return false;
            }
        }
        return true;
    }
}
