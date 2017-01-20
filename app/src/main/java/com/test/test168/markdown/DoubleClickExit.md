
## 双击退出

未编辑完。。。

```java
// 定义
private boolean isExit = false;
private int intervalTime = 2000;

Handler mHandler = new Handler() {
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		isExit = false;
	}
};

public void exit() {
	if (!isExit) {
		isExit = true;
		Toast.makeText(MainActivity.this, "再按一次退出", 0).show();
		mHandler.sendEmptyMessageDelayed(0, intervalTime);
	} else {
		//AppManager.getInstance().AppExit(this);// todo exit something
		Log.d("program exit success", "程序成功退出");
	}
}
```


// 使用代码

//点击返回键或者指定的按钮调用：
```java
exit();
```
//两秒内再点击一遍即可退出。
