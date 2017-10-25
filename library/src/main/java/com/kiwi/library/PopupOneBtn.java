package com.kiwi.library;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 刘少帅 on 2017/10/24
 */

public class PopupOneBtn extends BottomPushPopupWindow<PopupOneBtn.BtnClick> {


    private TextView tvTitle;
    private CardView cvCv;
    private TextView tvSure;


    /**设置圆角弧度
     * @param multiple 倍数
     */
    public PopupOneBtn setRadius(int multiple){
        float dimension1 = context.getResources().getDimension(R.dimen.m5);
        if (cvCv!=null){
            cvCv.setRadius(dimension1*multiple);
        }
        return this;
    }
    /**设置标题内容,字体颜色和是否加粗
     * @param title
     * @param color
     * @param isBold
     */
    public PopupOneBtn setTitleAndColor(String title,int color,boolean isBold){
        if (!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
        //设置是否加粗
        tvTitle.setTypeface(isBold? Typeface.defaultFromStyle(Typeface.BOLD):Typeface.defaultFromStyle(Typeface.NORMAL));
        if (color!=0){
            tvTitle.setTextColor(ContextCompat.getColor(context, color));
        }
        return this;
    }
    public PopupOneBtn setSureTextAndColor(String text,int textColor,int backColor){
        if (!TextUtils.isEmpty(text)){
            tvSure.setText(text);
        }
        if (textColor!=0){
            tvSure.setTextColor(ContextCompat.getColor(context, textColor));
        }
        if (backColor!=0){
            tvSure.setBackgroundColor(ContextCompat.getColor(context, backColor));
        }
        return this;
    }




    public PopupOneBtn(Context context, BtnClick btnClick) {
        super(context, btnClick);
    }

    @Override
    protected View generateCustomView(final BtnClick btnClick) {
        View contentView = View.inflate(context, R.layout.over_pw_one_btn, null);
        tvTitle = (TextView) contentView.findViewById(R.id.tv_pw_onebtn_title);
        cvCv = (CardView) contentView.findViewById(R.id.cv_pw_onebtn_cv);
        tvSure = (TextView) contentView.findViewById(R.id.tv_pw_onebtn_sure);
        //点击监听
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClick.sureClickListener(PopupOneBtn.this);
            }
        });
        return contentView;
    }

    public interface BtnClick{
        void sureClickListener(PopupOneBtn pob);
    }
}
