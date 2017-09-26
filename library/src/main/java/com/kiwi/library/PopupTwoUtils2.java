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

public class PopupTwoUtils2 extends BottomPushPopupWindow<PopupTwoUtils2.ContentTClickListener>{


    private TextView tvHint;
    private TextView tvCancel;
    private TextView tvSure;
    private TextView tvTitle;
    private LinearLayout llOuter;


    public PopupTwoUtils2(Context context, ContentTClickListener contentTClickListener) {
        super(context, contentTClickListener);
    }

    /**设置圆角
     * @param multiple
     */
    public PopupTwoUtils2 setRadius(int multiple){
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

    //设置提示
    public PopupTwoUtils2 setHint(String hint, int textColor, boolean isBold){
        if (!TextUtils.isEmpty(hint)){
            tvHint.setText(hint);
        }
        //设置是否加粗
        tvHint.setTypeface(isBold? Typeface.defaultFromStyle(Typeface.BOLD):Typeface.defaultFromStyle(Typeface.NORMAL));
        if (textColor!=0){
            tvHint.setTextColor(ContextCompat.getColor(context, textColor));
        }
        return this;
    }
    //设置标题
    public PopupTwoUtils2 setTitle(String title, int textColor, boolean isBold){
        if (!TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
        //设置是否加粗
        tvTitle.setTypeface(isBold? Typeface.defaultFromStyle(Typeface.BOLD):Typeface.defaultFromStyle(Typeface.NORMAL));
        if (textColor!=0){
            tvTitle.setTextColor(ContextCompat.getColor(context, textColor));
        }
        return this;
    }
    public PopupTwoUtils2 setSureTextAndColor(String text, int textColor, int backColor){
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
    public PopupTwoUtils2 setCancelTextAndColor(String text, int textColor, int backColor){
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

//    public PopupTwoUtils(Context context, ContentTClickListener contentClickListener) {
//        super(context,  contentClickListener);
//    }

    @Override
    protected View generateCustomView(final ContentTClickListener contentClickListener) {

        View contentView = View.inflate(context, R.layout.overall_pw_two, null);
        tvHint = (TextView) contentView.findViewById(R.id.tv_pw_twotitle_titleOne);
        tvTitle = (TextView) contentView.findViewById(R.id.tv_pw_twotitle_titleTwo);
        tvSure = (TextView) contentView.findViewById(R.id.tv_pw_twotitle_sure);
        tvCancel = (TextView) contentView.findViewById(R.id.tv_pw_twotitle_cancel);

        llOuter = (LinearLayout) contentView.findViewById(R.id.ll_pw_twotitle_outer);

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
                contentClickListener.sureClickListener(PopupTwoUtils2.this);
            }
        });
        return contentView;
    }


    public interface ContentTClickListener{
        void sureClickListener(PopupTwoUtils2 ptu);
        void cancelClickListener();
    }
}
