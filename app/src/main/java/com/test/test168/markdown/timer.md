
## 自定义的定时器
未编辑完。。。

### Rxjava
这个Subscription是Rxjava的
```java
// 定义方法
Subscription subscribe;
// 启动轮询
void startLoop() {
    if (null == subscribe || subscribe.isUnsubscribed()) {
        subscribe = Observable.interval(0, interval, TimeUnit.SECONDS)
            //延时0 ，每间隔interval，时间单位 SECONDS
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<Long>() {
                @Override
                public void call(Long aLong) {
                    // todo something
                }
        });
    }
}

// 停止轮询
void stopLoop() {
    if (null != subscribe && !subscribe.isUnsubscribed()) {
        subscribe.unsubscribe();
    }
}
```

###  TimerTask

```java
// 定义代码
TimerTask task = new TimerTask(){
    public void run() {
    Message message = new Message();
    message.what = 1;
    handler.sendMessage(message);
 }
};

Handler handler = new Handler(){
 public void handleMessage(Message msg) {
     switch (msg.what) {
         case 1:
             // todo something
             break;
         }
         super.handleMessage(msg);
     }
 };

// 使用代码：
timer = new Timer(true);
timer.schedule(task, 1000, 1000); //延时1000ms后执行，1000ms执行一次
//timer.cancel(); //退出计时器
```


### handler
```java
// 定义简单的定时器
private Handler handler = new Handler();
private Runnable runnable = new Runnable() {
  public void run() {
      // TODO: todo update UI or other something
      handler.postDelayed(this, 1000);
      //postDelayed(this,1000)方法安排一个Runnable对象到主线程队列中
  }
};

//使用代码
handler.postDelayed(runnable,1000); // 开始Timer
handler.removeCallbacks(runnable); //停止Timer
```




