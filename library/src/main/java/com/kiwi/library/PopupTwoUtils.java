package com.kiwi.library;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kiwi on 2017/6/5.
 * Email:2763015920@qq.com
 */

public class PopupTwoUtils extends BottomPushPopupWindow<PopupOneUtils.ContentClickListener>{


    private TextView tvHint;
    private TextView tvCancel;
    private TextView tvSure;
    private TextView tvTitle;
    //设置提示
    public void setHint(String hint,int textColor,boolean isBold){
        if (!TextUtils.isEmpty(hint)){
            tvHint.setText(hint);
        }
        //设置是否加粗
        tvHint.setTypeface(isBold? Typeface.defaultFromStyle(Typeface.BOLD):Typeface.defaultFromStyle(Typeface.NORMAL));
        if (textColor!=0){
            tvHint.setTextColor(ContextCompat.getColor(context, textColor));
        }
    }
    //设置标题
    public void setTitle(String title,int textColor,boolean isBold){
        if (!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
        //设置是否加粗
        tvTitle.setTypeface(isBold? Typeface.defaultFromStyle(Typeface.BOLD):Typeface.defaultFromStyle(Typeface.NORMAL));
        if (textColor!=0){
            tvTitle.setTextColor(ContextCompat.getColor(context, textColor));
        }
    }
    public void setSureTextAndColor(String text,int textColor,int backColor){
        if (!TextUtils.isEmpty(text)){
            tvSure.setText(text);
        }
        if (textColor!=0){
            tvSure.setTextColor(ContextCompat.getColor(context, textColor));
        }
        if (backColor!=0){
            tvSure.setBackgroundColor(ContextCompat.getColor(context, backColor));
        }
    }
    public void setCancelTextAndColor(String text,int textColor,int backColor){
        if (!TextUtils.isEmpty(text)){
            tvCancel.setText(text);
        }
        if (textColor!=0){
            tvCancel.setTextColor(ContextCompat.getColor(context, textColor));
        }
        if (backColor!=0){
            tvCancel.setBackgroundColor(ContextCompat.getColor(context, backColor));
        }
    }

    public PopupTwoUtils(Context context, PopupOneUtils.ContentClickListener contentClickListener) {
        super(context, contentClickListener);
    }

    @Override
    protected View generateCustomView(final PopupOneUtils.ContentClickListener contentClickListener) {

        View contentView = View.inflate(context, R.layout.overall_pw_two2, null);
        tvHint = (TextView) contentView.findViewById(R.id.tv_pw_twotitle_titleOne);
        tvTitle = (TextView) contentView.findViewById(R.id.tv_pw_twotitle_titleTwo);
        tvSure = (TextView) contentView.findViewById(R.id.tv_pw_twotitle_sure);
        tvCancel = (TextView) contentView.findViewById(R.id.tv_pw_twotitle_cancel);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                contentClickListener.cancelClickListener();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentClickListener.sureClickListener();
            }
        });
        return contentView;
    }

    public interface ContentClickListener{
        void sureClickListener();
        void cancelClickListener();
    }
}
