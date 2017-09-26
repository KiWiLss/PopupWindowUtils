package com.kiwi.library;

import android.content.Context;
import android.view.View;

/**
 * Created by kiwi on 2017/6/7.
 * Email:2763015920@qq.com
 * 通用的各种类型的popupwidnow,暂时没写关于菜单显示的
 */

public class PopupUtils extends BottomPushPopupWindow<Integer>{

    private View contentView;

    public PopupUtils(Context context, Integer pwLayoutId) {
        super(context, pwLayoutId);
    }

    @Override
    protected View generateCustomView(Integer integer) {
        //引入布局
        contentView = View.inflate(context, integer, null);
        return contentView;
    }
    //获取内容的单个控件
    public <T extends View>T getItemView(int itemId){
        View viewById = contentView.findViewById(itemId);
        return (T)viewById;
    }
}
