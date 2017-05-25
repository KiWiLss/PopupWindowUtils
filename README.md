# PopupWindowUtils
简单的PopupWindow对话框的封装
------
#  展示
       ![image](https://github.com/KiWiLss/PopupWindowUtils/blob/master/screenshots/device-2017-05-25-231531.png)
# 1,引入
        <1>allprojects {
        repositories {
       			...
       			maven { url 'https://www.jitpack.io' }
       		}
       	}
       	<2>dependencies {
           	        compile 'com.github.KiWiLss:PopupWindowUtils:1.0.0'
           	}
# 2,简单使用
         //1,初始化对象
                       PopupWindowUtils utils1 = new PopupWindowUtils(btnCenter);
                       //2,设置内容
                       utils1.setContentView(R.layout.test_pw);
                       //3,展示
                       //utils1.showCenter();//没有阴影效果
                       utils1.showCenterWithAlpha();//有阴影效果
 # 3,其它方法
                <1>
                //获取布局控件的方法
                 TextView tv2 = (TextView) utils2.findId(R.id.tv_pw_text);
                 tv2.setText("底部对话框");
                 utils2.showBottomWithAlpha();
                 //utils2.showTop();//展示在底部
                  <2>左侧进入菜单对话框
                    PopupWindowUtils utils3 = new PopupWindowUtils(btnLeft);
                                  utils3.setContentView(R.layout.test_pw);
                                  utils3.showLikePopDownLeftMenu();
                    <3>自定义距离菜单
                       PopupWindowUtils utils6 = new PopupWindowUtils(btnCustom);
                                    utils6.setContentView(R.layout.test_pw);
                                    utils6.showLikeQuickAction(100,100);