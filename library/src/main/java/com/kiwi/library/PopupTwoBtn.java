package com.kiwi.library;

import android.content.Context;
import android.view.View;

/**
 * Created by 刘少帅 on 2017/10/25
 */

public class PopupTwoBtn extends BottomPushPopupWindow <PopupOneBtn.BtnClick>{


    public PopupTwoBtn(Context context, PopupOneBtn.BtnClick btnClick) {
        super(context, btnClick);
    }

    @Override
    protected View generateCustomView(PopupOneBtn.BtnClick btnClick) {
        View contentView = View.inflate(context, R.layout.overall_pw_two_btn, null);


        return null;
    }

    public interface BtnClick{
        void sureClickListener(PopupTwoBtn ptb);
    }
}
