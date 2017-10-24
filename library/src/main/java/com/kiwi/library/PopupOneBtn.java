package com.kiwi.library;

import android.content.Context;
import android.view.View;

/**
 * Created by 刘少帅 on 2017/10/24
 */

public class PopupOneBtn extends BottomPushPopupWindow<PopupOneBtn.BtnClick> {


    public PopupOneBtn(Context context, BtnClick btnClick) {
        super(context, btnClick);
    }

    @Override
    protected View generateCustomView(BtnClick btnClick) {
        View contentView = View.inflate(context, R.layout.over_pw_one_btn, null);


        return null;
    }

    public interface BtnClick{

    }
}
