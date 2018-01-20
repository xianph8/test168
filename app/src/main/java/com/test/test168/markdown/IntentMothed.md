#### 调用系统的一些功能
1.从google搜索内容

    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_WEB_SEARCH);
    intent.putExtra(SearchManager.QUERY,"搜索内容")
    startActivity(intent);
2.浏览网页

    Uri uri =Uri.parse("http://www.google.com");
    Intent it = new Intent(Intent.ACTION_VIEW,uri);
    startActivity(it);
3.显示地图

    Uri uri = Uri.parse("geo:38.899533,-77.036476");
    Intent it = newIntent(Intent.Action_VIEW,uri);
    startActivity(it);
4.路径规划

    Uri uri =Uri.parse("http://maps.google.com/maps?f=dsaddr=startLat%20startLng&daddr=endLat%20endLng&hl=en");
    Intent it = newIntent(Intent.ACTION_VIEW,URI);
    startActivity(it);
5.拨打电话

    Uri uri =Uri.parse("tel:xxxxxx");
    Intent it = new Intent(Intent.ACTION_DIAL,uri);
    startActivity(it);
这个用法有一定的局限性，就是startActivity()一定要在主线程/UI线程，下面介绍另一种方法：
在Android M 即android 6.0上新增一个打电话的接口 placeCall(Uri address, Bundle extras)，下面是说明，格式我就不整理了

    * Requires permission: {@link android.Manifest.permission#CALL_PHONE}//申请权限
    *
    * Usage example:
    * <pre>
    * Uri uri = Uri.fromParts("tel", "12345", null);//Uri 主要是号码
    * Bundle extras = new Bundle();
    * extras.putBoolean(TelecomManager.EXTRA_START_CALL_WITH_SPEAKERPHONE, true);//默认开扬声器
    * telecomManager.placeCall(uri, extras);
6.发短信

    //方法1：
    Intent it = newIntent(Intent.ACTION_VIEW);
    it.putExtra("sms_body", "TheSMS text");
    it.setType("vnd.android-dir/mms-sms");
    startActivity(it);

    //方法2：
    Uri uri =Uri.parse("smsto:0800000123");
    Intent it = newIntent(Intent.ACTION_SENDTO, uri);
    it.putExtra("sms_body", "TheSMS text");
    startActivity(it);

    //方法三：
    String body="this is sms demo";
    Intent mmsintent = newIntent(Intent.ACTION_SENDTO, Uri.fromParts("smsto", number, null));
    mmsintent.putExtra(Messaging.KEY_ACTION_SENDTO_MESSAGE_BODY,body);
    mmsintent.putExtra(Messaging.KEY_ACTION_SENDTO_COMPOSE_MODE,true);
    mmsintent.putExtra(Messaging.KEY_ACTION_SENDTO_EXIT_ON_SENT,true);
    startActivity(mmsintent);
7.发送彩信

    Uri uri =Uri.parse("content://media/external/images/media/23");
    Intent it = newIntent(Intent.ACTION_SEND);
    it.putExtra("sms_body","some text");
    it.putExtra(Intent.EXTRA_STREAM, uri);
    it.setType("image/png");
    startActivity(it);
    StringBuilder sb = new StringBuilder();
    sb.append("file://");
    sb.append(fd.getAbsoluteFile());
    Intent intent = newIntent(Intent.ACTION_SENDTO, Uri.fromParts("mmsto", number, null));
    // Below extra datas are all optional.
    intent.putExtra(Messaging.KEY_ACTION_SENDTO_MESSAGE_SUBJECT,subject);
    intent.putExtra(Messaging.KEY_ACTION_SENDTO_MESSAGE_BODY,body);
    intent.putExtra(Messaging.KEY_ACTION_SENDTO_CONTENT_URI,sb.toString());
    intent.putExtra(Messaging.KEY_ACTION_SENDTO_COMPOSE_MODE,composeMode);
    intent.putExtra(Messaging.KEY_ACTION_SENDTO_EXIT_ON_SENT,exitOnSent);
    startActivity(intent);
8.发送Email

    Uri uri =Uri.parse("mailto:xxx@abc.com");
    Intent it = newIntent(Intent.ACTION_SENDTO, uri);
    startActivity(it);

    Intent it = new Intent(Intent.ACTION_SEND);
    it.putExtra(Intent.EXTRA_EMAIL,"me@abc.com");
    it.putExtra(Intent.EXTRA_TEXT, "Theemail body text");
    it.setType("text/plain");
    startActivity(Intent.createChooser(it,"Choose Email Client"));

    Intent it=new Intent(Intent.ACTION_SEND);
    String[] tos={"me@abc.com"};
    String[]ccs={"you@abc.com"};
    it.putExtra(Intent.EXTRA_EMAIL, tos);
    it.putExtra(Intent.EXTRA_CC, ccs);
    it.putExtra(Intent.EXTRA_TEXT, "Theemail body text");
    it.putExtra(Intent.EXTRA_SUBJECT, "Theemail subject text");
    it.setType("message/rfc822");
    startActivity(Intent.createChooser(it,"Choose Email Client"));

    Intent it = newIntent(Intent.ACTION_SEND);
    it.putExtra(Intent.EXTRA_SUBJECT, "Theemail subject text");
    it.putExtra(Intent.EXTRA_STREAM,"file:///sdcard/mysong.mp3");
    sendIntent.setType("audio/mp3");
    startActivity(Intent.createChooser(it,"Choose Email Client"));
9.播放多媒体

    Intent it = new Intent(Intent.ACTION_VIEW);
    Uri uri =Uri.parse("file:///sdcard/song.mp3");
    it.setDataAndType(uri,"audio/mp3");
    startActivity(it);
    Uri uri =Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,"1");
    Intent it = new Intent(Intent.ACTION_VIEW,uri);
    startActivity(it);
10.卸载 apk

    Uri uri =Uri.fromParts("package", strPackageName, null);
    Intent it = newIntent(Intent.ACTION_DELETE, uri);
    startActivity(it);
11.安装 apk

    Uri installUri = Uri.fromParts("package","xxx", null);
    returnIt = newIntent(Intent.ACTION_PACKAGE_ADDED, installUri);

    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setDataAndType(Uri.parse("file://" + filepath),"application/vnd.android.package-archive");
    startActivity(intent);// 安装
12.打开照相机

    //1
    Intent intent = new Intent("android.media.action.STILL_IMAGE_CAMERA"); //调用照相机
    startActivity(intent);
    //2
    Intent i = new Intent(Intent.ACTION_CAMERA_BUTTON, null);
    this.sendBroadcast(i);
    //3
    long dateTaken = System.currentTimeMillis();
    String name = createName(dateTaken) + ".jpg";
    fileName = folder + name;
    ContentValues values = new ContentValues();
    values.put(Images.Media.TITLE, fileName);
    values.put("_data", fileName);
    values.put(Images.Media.PICASA_ID, fileName);
    values.put(Images.Media.DISPLAY_NAME, fileName);
    values.put(Images.Media.DESCRIPTION, fileName);
    values.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, fileName);
    Uri photoUri = getContentResolver().insert(
    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

    Intent inttPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    inttPhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
    startActivityForResult(inttPhoto, 10);
13.从gallery选取图片

    Intent i = new Intent();
    i.setType("image/*");
    i.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(i, 11);
14.打开录音机

    Intent mi = new Intent(Media.RECORD_SOUND_ACTION);
    startActivity(mi);
15.显示应用详细列表

    Uri uri =Uri.parse("market://details?id=app_id");
    Intent it = new Intent(Intent.ACTION_VIEW,uri);
    startActivity(it);
    //where app_id is the application ID, findthe ID
    //by clicking on your application on Markethome
    //page, and notice the ID from the addressbar
    //发现用package name也可以
    //Uri uri =Uri.parse("market://details?id=<packagename>");
16.寻找应用

    Uri uri =Uri.parse("market://search?q=pname:pkg_name");
    Intent it = new Intent(Intent.ACTION_VIEW,uri);
    startActivity(it);
    //where pkg_name is the full package pathfor an application
17.打开联系人列表

    //1
    Intent i = new Intent();
    i.setAction(Intent.ACTION_GET_CONTENT);
    i.setType("vnd.android.cursor.item/phone");
    startActivityForResult(i, REQUEST_TEXT);
    //2
    Uri uri = Uri.parse("content://contacts/people");
    Intent it = new Intent(Intent.ACTION_PICK, uri);
    startActivityForResult(it, REQUEST_TEXT);
18.调用系统编辑添加联系人

    Intent intent = newIntent(Intent.ACTION_INSERT_OR_EDIT);
    intent.setType(People.CONTENT_ITEM_TYPE);
    intent.putExtra(Contacts.Intents.Insert.NAME, "My Name");
    intent.putExtra(Contacts.Intents.Insert.PHONE, "+1234567890");
    intent.putExtra(Contacts.Intents.Insert.PHONE_TYPE,Contacts.PhonesColumns.TYPE_MOBILE);
    intent.putExtra(Contacts.Intents.Insert.EMAIL, "com@com.com");
    intent.putExtra(Contacts.Intents.Insert.EMAIL_TYPE, Contacts.ContactMethodsColumns.TYPE_WORK);
    startActivity(intent);



打开第三方应用
方法一
    Intent intent=new Intent();
    //包名 包名+类名（全路径）
    intent.setClassName("com.linxcool", "com.linxcool.PlaneActivity");
    startActivity(intent);
方法二
    Intent intent = new Intent();
    ComponentName comp = new ComponentName("com.linxcool","com.linxcool.PlaneActivity");
    intent.setComponent(comp);
    intent.setAction("android.intent.action.MAIN");
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
方法三
出自storm的博客。
这段代码会检测app是否安装，没安装的话会指向商店下载。

    public static final String APP_PACKAGE_NAME = "com.*.*";//包名

    /**
     * 启动薄荷App
     * @param context
     */
    public static void launchapp(Context context) {
        // 判断是否安装过App，否则去市场下载
        if (isAppInstalled(context, APP_PACKAGE_NAME)) {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(APP_PACKAGE_NAME));
        } else {
            goToMarket(context, APP_PACKAGE_NAME);
        }
    }

    /**
     * 检测某个应用是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 去市场下载页面
     */
    public static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
        }
    }
































