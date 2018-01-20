

type | time | author | what
--- | --- | --- | ---
createTime| 2017年3月19日 13:55:55 | xian
modifyTime1 | 2017年3月20日 10:43:55 | xian
modifyTime2 | 2018年1月19日 11:50:55 | xian

###开发中遇到的问题，解决方法的简记
1. 更改包名的时候，如果修改不成功，那就执行 Gradle 的 clean 的任务，然后再修改包名

1. 通常，新建的文件(activity , fragment 等) 建好后，运行了之后发现新建的位置出错了
往往这时候移动文件到另一个包，会出现移动不成功的问题。这时 运行 clean project （menu -> Build -> Clean Project）就好了

1. 当更换框架的时候，如果框架接口有改变，那就使用 AS 的全局替换功能: Ctrl + Shift + R


1. 当retrofit 请求返回错误（retrofit java.net.ProtocolException: Too many follow-up requests: 21）的时候
说明，是服务端出问题，也有可能是自己访问的URL错误了，而我遇到的是，自己访问错地址

1. 使用 okhttp log 拦截器，当访问接口，无论是成功还是失败，都走 onError 方法，报 java.lang.IllegalStateException: closed
    可能是自行添加的拦截器，把 response 消费了（也就是调用了 response.body().string() 方法，注意是 string() 方法，而不是 toString() 方法）
，返回到回调的地方已经为关闭了，所以，一直会提示 "closed"，这是 okhttp 避免内在泄露的机制





















