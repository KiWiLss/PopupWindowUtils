package com.kiwi.library;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kiwi on 2017/5/24.
 * Email:2763015920@qq.com
 * 只有一个标题的封装
 */

public class PWOneUtils {

    //确定是哪种展示模式
    public static final int BOTTOM=1;
    public static final int BOTTOM_NO=2;
    public static final int TOP=3;
    public static final int TOP_NO=4;
//    public static final int LEFT_MENU=5;
//    public static final int RIGHT_MENU=6;
//    public static final int TOP_MENU=7;
//    public static final int CUSTOM_MENU=8;
    public static final int CENTER_NO=9;
    private String mTitle="提示";//标题,默认为提示
    private boolean isBold=false;//是否给标题加粗,默认不加
    //private SureAndCancelListener sacl;
    private int titleColor;//标题的颜色,默认可不设置
    private int cancelColor;//取消的颜色,默认可不设置
    private int sureColor;//queding的颜色,默认可不设置
    private int showModel=0;//展示的模式
    private int provityX;
    private int provityY;
    private static PWOneUtils instance;
    private static Context mContext;
    private int cancelBgColor=0;
    private int sureBgColor=0;

    private PWOneUtils(){}
    public static PWOneUtils getInstance(Context context){
        if (instance==null){
            synchronized (PWOneUtils.class){
                if (instance==null){
                    instance=new PWOneUtils();
                }
            }
        }
        mContext=context;
        return instance;
    }

    public  void showPw(View btn, final SureAndCancelListener sacl){
        //默认是点击传入的控件展示,但作为菜单,就是默认在传入的控件的四个方向
        final PopupWindowUtils util = new PopupWindowUtils(btn);
        util.setContentView(R.layout.overall_pw_one);
        TextView tvTitle = (TextView) util.findId(R.id.tv_pw_onetitle_title);
        TextView tvCancel = (TextView) util.findId(R.id.tv_pw_onetitle_cancel);
        TextView tvSure = (TextView) util.findId(R.id.tv_pw_onetitle_sure);
        //设置点击事件,设置标题
        if (!TextUtils.isEmpty(mTitle)){
            tvTitle.setText(mTitle);
        }
        tvTitle.getPaint().setFakeBoldText(isBold);
        //设置标题的颜色
        if (titleColor!=0){
            tvTitle.setTextColor(ContextCompat.getColor(mContext,titleColor));
        }
        if (cancelColor!=0){
            tvCancel.setTextColor(ContextCompat.getColor(mContext,cancelColor));
        }
        if (sureColor!=0){
            tvSure.setTextColor(ContextCompat.getColor(mContext,sureColor));
        }
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sacl.cancelListener(util);//取消的监听
                //util.dismiss();
            }
        });
        //确定点击
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sacl.sureListener(util);
            }
        });
        //设置取消和确定的背景色
        if (cancelBgColor!=0){
            tvCancel.setBackgroundColor(ContextCompat.getColor(mContext,cancelBgColor));
        }
        if (sureBgColor!=0){
            tvSure.setBackgroundColor(ContextCompat.getColor(mContext,sureBgColor));
        }
        //设置展示的模式,默认居中有阴影效果
        if (showModel==0){
            util.showCenterWithAlpha();
        }else {
            switch (showModel) {
                case CENTER_NO://居中无阴影
                    util.showCenter();
                    break;
                case BOTTOM://展示在底部,有背景
                    util.showBottomWithAlpha();
                    break;
                case BOTTOM_NO://展示在底部,没有背景
                    util.showBottom();
                    break;
                case TOP:
                    util.showTopWithAlpha();
                    break;
                case TOP_NO:
                    util.showTop();
                    break;
              /*  case LEFT_MENU://左侧进入菜单
                    util.showLikePopDownLeftMenu();
                    break;
                case RIGHT_MENU://右侧进入菜单
                    util.showLikePopDownRightMenu();
                    break;
                case TOP_MENU://顶部菜单
                    util.showLikeQuickAction();
                    break;
                case CUSTOM_MENU://自定义菜单
                    util.showLikeQuickAction(provityX,provityY);
                    break;*/
            }
        }

    }
//    //设置确定和取消点击监听
//    public void setOnclickListener(SureAndCancelListener sacl){
//        this.sacl=sacl;
//    }
    //设置标题
    public void setTitle(String title){
        this.mTitle=title;
    }
    //设置标题是否加粗
    public void setTitleIsBold(boolean isBold){
        this.isBold=isBold;
    }
    public interface SureAndCancelListener{
        void sureListener(PopupWindowUtils util);
        void cancelListener(PopupWindowUtils util);
    }
    //设置标题颜色
    public void setTitleColor(int color){
        this.titleColor=color;
    }
    //设置取消颜色
    public void setCancelColor(int color){
        this.cancelColor=color;
    }
    //设置确定颜色
    public void setSureColor(int color){
        this.sureColor=color;
    }
    //设置展示模式
    public void setShowModel(int model,int x,int y){
        this.showModel=model;
        this.provityX=x;
        this.provityY=y;
    }

}
