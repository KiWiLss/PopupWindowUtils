package com.kiwi.library;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by kiwi on 2017/6/5.
 * Email:2763015920@qq.com
 */

public class PopupOneUtils2 extends BottomPushPopupWindow<PopupOneUtils2.ContentClickListener> {

    private LinearLayout llOuter;

    public PopupOneUtils2(Context context, ContentClickListener contentClickListener) {
        super(context, contentClickListener);
    }
    TextView tvTitle;
    TextView tvSure;
    TextView tvCancel;


    /**设置圆角弧度
     * @param multiple 倍数
     */
    public PopupOneUtils2 setRadius(int multiple){
       if (llOuter!=null){
           switch (multiple) {
               case 1://圆角为最小
                   llOuter.setBackgroundResource(R.drawable.bg_white_small_radiu2);
                   break;
               case 2://
                   llOuter.setBackgroundResource(R.drawable.bg_white_small_radiu);
                   break;
               case 3://圆角为最大
                   llOuter.setBackgroundResource(R.drawable.bg_white_small_radiu3);
                   break;
               default:
                   //llOuter.setBackgroundResource(R.drawable.bg_white_small_radiu);
           }
       }
        return this;
    }
    /**设置标题内容,字体颜色和是否加粗
     * @param title
     * @param color
     * @param isBold
     */
    public PopupOneUtils2 setTitleAndColor(String title, int color, boolean isBold){
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
    public PopupOneUtils2 setSureTextAndColor(String text, int textColor, int backColor){
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
    public PopupOneUtils2 setCancelTextAndColor(String text, int textColor, int backColor){
        if (!TextUtils.isEmpty(text)){
            tvCancel.setText(text);
        }
        if (textColor!=0){
            tvCancel.setTextColor(ContextCompat.getColor(context, textColor));
        }
        if (backColor!=0){
            tvCancel.setBackgroundColor(ContextCompat.getColor(context, backColor));
        }
        return this;
    }

    @Override
    protected View generateCustomView(final ContentClickListener contentClickListener) {
        View contentView = View.inflate(context, R.layout.overall_pw_one, null);
         tvTitle = (TextView) contentView.findViewById(R.id.tv_pw_onetitle_title);
         tvSure = (TextView) contentView.findViewById(R.id.tv_pw_onetitle_sure);
         tvCancel = (TextView) contentView.findViewById(R.id.tv_pw_onetitle_cancel);

        llOuter = (LinearLayout) contentView.findViewById(R.id.ll_pw_onetitle_outer);

        //点击监听
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentClickListener.cancelClickListener();
                dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentClickListener.sureClickListener(PopupOneUtils2.this);
            }
        });
        return contentView;
    }



    public interface ContentClickListener{
        void sureClickListener(PopupOneUtils2 pou);
        void cancelClickListener();
    }
}
