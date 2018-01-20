
createTime: 2018年1月19日 11:58:39 by xian

modifyTime1: 2018年1月19日 11:50:55 by xian

#### 分享微信

1. 分享 多图+文本，使用系统方法，调起微信

    ```java
        Intent intent = new Intent();
        // 设置正确的包名
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        // 微信朋友圈编辑界面带有这个 intent-filter
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        // 设置传输类型
        intent.setType("image/*");
        // 放入需要分享的文件列表，列表泛型为 Uri ，且此方式只支持 Uri 类型（用 Uri.fromFile() 生成 Uri）
        intent.putExtra(Intent.EXTRA_STREAM, uris);
        // 分享内容描述，必须以 "Kdescription" 为key，否则调用失败，微信编辑界面会解释此属性
        intent.putExtra("Kdescription", s);
        // 启动微信
        startActivity(intent);
    ```

1. 分享图片到好友
    ```java
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        intent.setComponent(comp);
        intent.setAction("android.intent.action.SEND");
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(intent);
    ```


















































































