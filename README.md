# PopupWindowUtils
简单的PopupWindow对话框的封装
尽量用最新版popupUtils
------
#  展示





#  1,引入
        <1>allprojects {
        repositories {
       			...
       			maven { url 'https://jitpack.io' }
       		}
       	}
       	<2>dependencies {
           	        compile 'com.github.KiWiLss:PopupWindowUtils:最新版'
           	}
#  2,简单使用
    <1>PopupUtils的使用
    1, PopupUtils popupUtils = new PopupUtils(this, R.layout.overall_pw_one);
                      popupUtils.setAnimationStyle(R.style.PushInBottom);//设置动画效果,默认渐变动画
                      popupUtils.showCenter(this);//设置展示位置
                      popupUtils.getItemView(R.id.....);//获取对话框里的控件
     2,一个标题的对话框使用
       PopupOneUtils popupOneUtils = new PopupOneUtils(this, new PopupOneUtils.ContentClickListener() {
                         @Override
                         public void sureClickListener() {
                             Toast.makeText(MainActivity.this, "sure", Toast.LENGTH_SHORT).show();
                         }

                         @Override
                         public void cancelClickListener() {

                         }
                     });
                     popupOneUtils.setTitleAndColor("你好啊",0,false);//1,文字2,文字颜色,3,文字是否加粗
                     popupOneUtils.setSureTextAndColor("一定",R.color.colorAccent,R.color.black);//1,文字2,文字颜色3,按钮背景
                    // popupOneUtils.setAnimationStyle(R.style.PushInBottom);
                     popupOneUtils.showCenter(this);//通常都是居中展示
        3,两个标题对话框使用
         PopupTwoUtils popupTwoUtils = new PopupTwoUtils(this, new PopupOneUtils.ContentClickListener() {
                            @Override
                            public void sureClickListener() {//

                            }

                            @Override
                            public void cancelClickListener() {
                                    //默认点击对话框消失,无别的要求可不写
                            }
                        });
                        popupTwoUtils.setHint("",0,false);//设置提示标题
                        方法与一个标题对话框类似
          <2>PopupWindowUtils的使用,动画效果不好,比较适合用来处理菜单对话框
         //1,初始化对象
                       PopupWindowUtils utils1 = new PopupWindowUtils(btnCenter);
                       //2,设置内容
                       utils1.setContentView(R.layout.test_pw);
                       //3,展示
                       //utils1.showCenter();//没有阴影效果
                       utils1.showCenterWithAlpha();//有阴影效果
                4,其他一些方法
                //获取布局控件的方法
                 TextView tv2 = (TextView) utils2.findId(R.id.tv_pw_text);
                 tv2.setText("底部对话框");
                 utils2.showBottomWithAlpha();
                 //utils2.showTop();//展示在底部
                 5左侧进入菜单对话框
                    PopupWindowUtils utils3 = new PopupWindowUtils(btnLeft);
                                  utils3.setContentView(R.layout.test_pw);
                                  utils3.showLikePopDownLeftMenu();
                6自定义距离菜单
                       PopupWindowUtils utils6 = new PopupWindowUtils(btnCustom);
                                    utils6.setContentView(R.layout.test_pw);
                                    utils6.showLikeQuickAction(100,100);
#  3,拍照,打开相册的使用

        //1,新建一个对象
        photoUtils = new PhotoUtils(this);
        //2,直接调用拍照方法
                photoUtils.takePhoto();
          //打开相册的方法
                        photoUtils.openAlbum();
       //权限处理
         @Override
       public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
          //3,添加权限处理
               photoUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
               }
          //结果处理
              @Override
              protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                  super.onActivityResult(requestCode, resultCode, data);
                  //4,获取图片的路径
                  String imgPath = photoUtils.onActivityResult(requestCode, resultCode, data);
                  if (!TextUtils.isEmpty(imgPath)){
                      //一般需要对图片压缩处理
                      Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
                      imgIcon.setImageBitmap(bitmap);
                  }
                  //带裁剪的用法类似,只在结果处理上不同,下面是带裁剪的结果处理
          //        PhotoUtils2 photoUtils2 = new PhotoUtils2(this);
          //        Bitmap bitmap = photoUtils2.onActivityResult(requestCode, resultCode, data);
                  //后续根据需要处理
              }
