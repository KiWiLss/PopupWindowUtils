package com.kiwi.popupwindowutils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.kiwi.library.PWOneUtils;
import com.kiwi.library.PWTwoUtils;
import com.kiwi.library.PopupOneBtn;
import com.kiwi.library.PopupOneUtils;
import com.kiwi.library.PopupTwoBtn;
import com.kiwi.library.PopupUtils;
import com.kiwi.library.PopupWindowUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCenter;
    private Button btnBottom;
    private Button btnLeft;
    private Button btnRight;
    private Button btnTop;
    private Button btnCustom;
    private Button btnCustom2;
    private Button btnCustom3;
    private Button btnPwCenter;
    private Button btnPwDrop;
    private Button btnPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用实例
        initView();
        initEvent();




    }

    private void initEvent() {
        btnBottom.setOnClickListener(this);
        btnCenter.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnTop.setOnClickListener(this);
        btnCustom.setOnClickListener(this);
        btnCustom2.setOnClickListener(this);
        btnCustom3.setOnClickListener(this);
        btnPwCenter.setOnClickListener(this);
        btnPwDrop.setOnClickListener(this);
        btnPhoto.setOnClickListener(this);
    }

    private void initView() {
        btnCenter = (Button) findViewById(R.id.btn_main_center);
        btnBottom = (Button) findViewById(R.id.btn_main_bottom);
        btnLeft = (Button) findViewById(R.id.btn_main_left);
        btnRight = (Button) findViewById(R.id.btn_main_right);
        btnTop = (Button) findViewById(R.id.btn_main_top);
        btnCustom = (Button) findViewById(R.id.btn_main_custom);
        btnCustom2 = (Button) findViewById(R.id.btn_main_custom2);
        btnCustom3 = (Button) findViewById(R.id.btn_main_custom3);
        btnPwCenter = (Button) findViewById(R.id.btn_main_pwCenter);
        btnPwDrop = (Button) findViewById(R.id.btn_main_pwDrop);
        btnPhoto = (Button) findViewById(R.id.btn_main_photo);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_center:
                //1,初始化对象
                PopupWindowUtils utils1 = new PopupWindowUtils(btnCenter);
                //2,设置内容
                utils1.setContentView(R.layout.test_pw);
                //3,展示
                //utils1.showCenter();//没有阴影效果
                utils1.showCenterWithAlpha();
                break;
            case R.id.btn_main_bottom:
                PopupWindowUtils utils2 = new PopupWindowUtils(btnCenter);
                utils2.setContentView(R.layout.test_pw);
                //获取布局控件的方法
                TextView tv2 = (TextView) utils2.findId(R.id.tv_pw_text);
                tv2.setText("底部对话框");
                utils2.showBottomWithAlpha();
                //utils2.showTop();//展示在底部
                break;
            case R.id.btn_main_left://左侧进入菜单
                PopupWindowUtils utils3 = new PopupWindowUtils(btnLeft);
                utils3.setContentView(R.layout.test_pw);
                utils3.showLikePopDownLeftMenu();
                break;
            case R.id.btn_main_right://右侧进入菜单
                PopupWindowUtils utils4 = new PopupWindowUtils(btnRight);
                utils4.setContentView(R.layout.test_pw);
                utils4.showLikePopDownRightMenu();
                break;
            case R.id.btn_main_top:
                PopupWindowUtils utils5 = new PopupWindowUtils(btnTop);
                utils5.setContentView(R.layout.test_pw);
                utils5.showLikeQuickAction();
                break;
            case R.id.btn_main_custom://自定义距离
//                PopupWindowUtils utils6 = new PopupWindowUtils(btnCustom);
//                utils6.setContentView(R.layout.test_pw);
//                utils6.showLikeQuickAction(100,100);
                PWOneUtils instance = PWOneUtils.getInstance(this);
                //可以通过instance设置标题,颜色,是否加粗等
                PWOneUtils.getInstance(this).showPw(btnCustom, new PWOneUtils.SureAndCancelListener() {
                    @Override
                    public void sureListener(PopupWindowUtils util) {
                        Toast.makeText(MainActivity.this, "sure", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void cancelListener(PopupWindowUtils util) {
                        Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_main_custom2:
                PWTwoUtils twoUtils = PWTwoUtils.getInstance(this);
               // twoUtils.setHint("确定要吃饭吗?",0,false);//设置提示标题
                twoUtils.setTitleAndColor("一定要去吃饭?",0);//设置内容标题
                twoUtils.setSureBg(R.color.colorAccent);//右侧按钮背景
                twoUtils.setSureTextColor("",0);//右侧按钮文字和颜色
                twoUtils.showPw(btnCustom2, new PWTwoUtils.SureAndCancelListener() {
                    @Override
                    public void sureListener(PopupWindowUtils util) {
                        Toast.makeText(MainActivity.this, "sure", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void cancelListener(PopupWindowUtils util) {
                        Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_main_custom3:
                PopupOneUtils popupOneUtils = new PopupOneUtils(this, new PopupOneUtils.ContentClickListener() {

                    @Override
                    public void sureClickListener(PopupOneUtils pou) {
                        Toast.makeText(MainActivity.this, "sure", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void cancelClickListener() {

                    }
                });
//                popupOneUtils.setTitleAndColor("hellokitty",0,false)
//                        .setSureTextAndColor("sure",0,0);//链式结构

                popupOneUtils.setTitleAndColor("你好啊",0,false);//1,文字2,文字颜色,3,文字是否加粗
                popupOneUtils.setSureTextAndColor("一定",R.color.colorAccent,R.color.black);//1,文字2,文字颜色3,按钮背景
               // popupOneUtils.setAnimationStyle(R.style.PushInBottom);
                popupOneUtils.setRadius(2);//设置圆角,参数是倍数
                popupOneUtils.showCenter(this);//通常都是居中展示
                //popupOneUtils.showBottom(this);
                break;
            case R.id.btn_main_pwCenter://可随意更改动画效果,
                PopupUtils popupUtils = new PopupUtils(this, R.layout.overall_pw_one);
                popupUtils.setAnimationStyle(R.style.PushInBottom);//设置动画效果
                popupUtils.showCenter(this);//设置展示位置
                //popupUtils.getItemView(R.id.....);//获取对话框里的控件
                break;
            case R.id.btn_main_pwDrop:
                PopupUtils popupUtils2 = new PopupUtils(this, R.layout.overall_pw_one);
                popupUtils2.setAnimationStyle(R.style.PopDownRightMenu);//设置动画
                //popupUtils2.showAsDropDown(btnPwDrop);
                popupUtils2.showDrop(btnPwDrop,0,0);
                break;
            case R.id.btn_main_photo:
                startActivity(new Intent(this,PhotoTestActivity.class));
                break;
        }
    }

    private void showPw() {
        screenDarken(this);
        View contentVeiw
                = LayoutInflater.from(this).inflate(R.layout.test_pw, null);
        PopupWindow popupWindow = new PopupWindow(contentVeiw,
                WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.AnimFadeCenter);
        View rootVeiw
                = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(rootVeiw, Gravity.CENTER,0,0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                screenLight(MainActivity.this);
            }
        });
    }
    /*使整个屏幕界面变暗*/
    public void screenDarken(Activity activity) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.6f;//设置整个屏幕的透明度为0.5
        window.setAttributes(attributes);
    }
    public void screenLight(Activity activity) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 1f;//设置整个屏幕的透明度为1
        window.setAttributes(attributes);
    }

    /**一个标题,一个按钮
     * @param view
     */
    public void oneOneListener(View view) {
        new PopupOneBtn(this, new PopupOneBtn.BtnClick() {
            @Override
            public void sureClickListener(PopupOneBtn pob) {
                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        }).setRadius(2)
                .setTitleAndColor("一定以及肯定",0,true)
                .setSureTextAndColor("",R.color.black,R.color.colorAccent)
                .showCenter(this);
    }

    public void twoOneListener(View view) {
        new PopupTwoBtn(this, new PopupTwoBtn.BtnClick() {
            @Override
            public void sureClickListener(PopupTwoBtn ptb) {

            }
        })
                //.setRadius(2)
                .setHint("警告",0,false)
                //.setSureTextAndColor("",R.color.colorPrimaryDark,R.color.grayBF)
                .setTitle("天地覅放得开地方降低肌肤等下开房间诶都挺好ID发生的纠纷ID积分价格",0,false)
                .showCenter(this);
    }
}
