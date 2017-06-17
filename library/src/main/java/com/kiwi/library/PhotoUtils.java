package com.kiwi.library;

/**
 * Created by kiwi on 2017/6/17.
 * Email:2763015920@qq.com
 */

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by kiwi on 2017/6/16.
 * Email:2763015920@qq.com
 */

public class PhotoUtils {

    private String[] PERMISSIONS_CAMERA_AND_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    //检查是否有打开相机的权限

    public static final int OPEN_ALBUM=2;//打开相册是标识
    public static final int TAKE_PHOTO = 1;//拍照的标记
    public String mImgPath; //拍照或是相册选中图片的地址
    private Activity activity;



    public PhotoUtils(Activity activity) {
        this.activity = activity;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
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
    //判断相机是否可用
    public boolean cameraIsCanUse(Context context){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(context.getPackageManager()) == null) {
            return false;
        }
        return true;
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
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setType("image/*");
            activity.startActivityForResult(intent, OPEN_ALBUM);
        }

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

    public String onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //LogUtils.i("data==null"+data);
            switch (requestCode) {
                case TAKE_PHOTO://拍照
                    if (!TextUtils.isEmpty(mImgPath)){
                        return mImgPath;
                    }
                case OPEN_ALBUM://打开相册
                    //判断手机系统的版本信息
                    if (Build.VERSION.SDK_INT>=19) {
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
                    }
                    if (!TextUtils.isEmpty(mImgPath)){
                        return mImgPath;
                    }
            }
        }
        return null;
    }
    private void handleImageBeforeKitKat(Intent data) {//5.0以下的系统
        Uri uri = data.getData();
        String imagePath = getimagePath(uri, null);
        if (!TextUtils.isEmpty(imagePath)){
            mImgPath=imagePath;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data) {//5.0以上的系统
        String imgPath=null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(activity,uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imgPath=getimagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imgPath=getimagePath(contentUri,null);

            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            imgPath=getimagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imgPath=uri.getPath();
        }
        if (!TextUtils.isEmpty(imgPath)){
            mImgPath=imgPath;
        }
        //displayImage(imgPath);
    }
    private String getimagePath(Uri uri, String seletion) {//获取图片路径的方法
        String path=null;
        Cursor cursor = activity.getContentResolver().query(uri, null, seletion, null, null);
        if (cursor!=null) {
            if (cursor.moveToFirst()) {
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
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
