
createTime: 2018年1月19日 11:50:55 by xian

#### 简化 OnActivityResult 的使用方法

原理是调用了一个无界面的 Fragment ,使用这个 Fragment 作为中介，返回数据到调用位置

调用了 ``` com.test.test168.utils.avoidonresult.AvoidOnResult ```

使用方式，有两种

1. 响应式

    ```kotlin
    AvoidOnResult(this)
        .startForResult(TakePhotoActivity::class.java, RequestGetImageUtils.REQUEST_CODE)
        .filter { it.resultCode == Activity.RESULT_OK }
        .subscribe({
            XLog.i(" result : " + it)
            // do something by result (it)
        }, {
            XLog.e("onError : " + it.message)
        }, {
            XLog.i(" onComplete ! ")
        })
    ```


1. 回调
    ```kotlin
    AvoidOnResult(this)
        .startForResult(TakePhotoActivity::class.java, RequestGetImageUtils.REQUEST_CODE,
            object : AvoidOnResult.Callback {
                override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                // do something by result
            }
        })
    ```





