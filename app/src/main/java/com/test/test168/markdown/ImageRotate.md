
createTime: 2017年1月20日 18:38:41 by xian

modifyTime1: 2018年1月19日 11:50:55 by xian

## 图片旋转
未编辑完。。。

```java
    private Handler handler;
    private Runnable runnable;
    private int rotateTime = 30;// 毫秒

    private void startRotate() {
        if (handler == null && runnable == null) {
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    float rotation = iv.getRotation() + 10;//iv.getRotation() 是目前的角度
                    iv.setRotation(rotation == 360 ? 0 : rotation);
                    handler.postDelayed(runnable, rotateTime);
                }
            };
            handler.postDelayed(runnable, rotateTime);
        }
    }

    private void stopRotate() {
        if (runnable != null && handler != null) {
            handler.removeCallbacks(runnable);
            handler = null;
            runnable = null;
        }
    }

 ```

```java
// 使用方法
开始转动：startRotate();
停止转动：stopRotate();
```
