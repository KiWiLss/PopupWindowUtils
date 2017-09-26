package com.kiwi.library;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;



/**
 * Created by kiwi on 2017/5/25.
 * Email:2763015920@qq.com
 * 带两个标题的popupwindon封装
 */

public class PWTwoUtils {
    public static final int CENTER_NO = 11;
    private static PWTwoUtils instance;
    private String hintTitle;//提示标题
    private int hintColor=0;//提示文字颜色
    private boolean hintIsBold=false;//提示标题是否加粗

    private int cancelColor=0;//取消颜色
    private String cancelText;//取消内容

    private int sureColor=0;//确定颜色
    private String sureText;//确定内容

    private String title;//标题文字
    private int titleColor=0;//标题颜色

    private int showModal=0;//展示模式

    private int cancelBgColor=0;//取消的背景
    private int sureBgColor=0;//确定的背景

    private static Context mContext;
    private PWTwoUtils(){}
    public static PWTwoUtils getInstance(Context context){
        if (instance==null) {
            synchronized (PWTwoUtils.class){
                if (instance==null) {
                    instance=new PWTwoUtils();
                }
            }
        }
        mContext=context;
        return instance;
    }

    public void showPw(View btn, final SureAndCancelListener sacl){
        //默认是点击传入的控件展示,但作为菜单,就是默认在传入的控件的四个方向
        final PopupWindowUtils util = new PopupWindowUtils(btn);
        util.setContentView(R.layout.overall_pw_two);
        //获取子控件
        TextView tvHint = (TextView) util.findId(R.id.tv_pw_twotitle_titleOne);
        TextView tvTitle = (TextView) util.findId(R.id.tv_pw_twotitle_titleTwo);
        TextView tvCancel = (TextView) util.findId(R.id.tv_pw_twotitle_cancel);
        TextView tvSure = (TextView) util.findId(R.id.tv_pw_twotitle_sure);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sacl.cancelListener(util);
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sacl.sureListener(util);
            }
        });
        //设置提示标题的文字和颜色
        if (!TextUtils.isEmpty(hintTitle)){
            tvHint.setText(hintTitle);
        }
        if (hintColor!=0){
            tvHint.setTextColor(ContextCompat.getColor(mContext,hintColor));
        }
        tvHint.getPaint().setFakeBoldText(hintIsBold);
        //设置标题和标题颜色
        if (!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
        if (titleColor!=0){
            tvTitle.setTextColor(ContextCompat.getColor(mContext,titleColor));
        }
        //取消和确定的颜色
        if (!TextUtils.isEmpty(cancelText)){
            tvCancel.setText(cancelText);
        }
        if (cancelColor!=0){
            tvCancel.setTextColor(ContextCompat.getColor(mContext,cancelColor));
        }
        if (!TextUtils.isEmpty(sureText)){
            tvSure.setText(sureText);
        }
        if (sureColor!=0){
            tvSure.setTextColor(ContextCompat.getColor(mContext,sureColor));
        }
        //设置取消和确定的背景
        if (sureBgColor!=0){
            tvSure.setBackgroundColor(ContextCompat.getColor(mContext,sureBgColor));
        }
        if (cancelBgColor!=0){
            tvCancel.setBackgroundColor(ContextCompat.getColor(mContext,cancelBgColor));
        }
        //设置展示模式,默认居中有阴影
        switch (showModal) {
            case 0:
                util.showCenterWithAlpha();
                break;
           case CENTER_NO://居中无阴影
                util.showCenter();
                break;
        }
    }
    //设置标题和标题颜色
    public PWTwoUtils setTitleAndColor(String title,int color){
        this.title=title;
        this.titleColor=color;
        return this;
    }
    //设置提示的内容和颜色,是否加粗
    public void setHint(String text,int hintColor,boolean isBold){
        this.hintTitle=text;
        this.hintColor=hintColor;
        this.hintIsBold=isBold;
    }
    //设置取消内容和颜色
    public void setCancelTextColor(String text,int color){
        this.cancelText=text;
        this.cancelColor=color;
    }
    //设置确定内容和颜色
    public void setSureTextColor(String text,int color){
        this.sureText=text;
        this.sureColor=color;
    }
    //设置取消的背景
    public void setCancelBg(int color){
        this.cancelBgColor=color;
    }
    //设置确定的背景
    public void setSureBg(int color){
        this.sureBgColor=color;
    }
    //设置展示模式
    public void setShowModal(int showModal){
        this.showModal=showModal;
    }

    public interface SureAndCancelListener{
        void sureListener(PopupWindowUtils util);
        void cancelListener(PopupWindowUtils util);
    }
}
