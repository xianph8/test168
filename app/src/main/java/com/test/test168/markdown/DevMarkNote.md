
###开发中遇到的问题，解决方法的简记
1. 更改包名的时候，如果修改不成功，那就执行 Gradle 的 clean 的任务，然后再修改包名

1. 通常，新建的文件(activity , fragment 等) 建好后，运行了之后发现新建的位置出错了
往往这时候移动文件到另一个包，会出现移动不成功的问题。这时 运行 clean project （menu -> Build -> Clean Project）就好了

1. 当更换框架的时候，如果框架接口有改变，那就使用 AS 的全局替换功能: Ctrl + Shift + R


1. 当retrofit 请求返回错误（retrofit java.net.ProtocolException: Too many follow-up requests: 21）的时候
说明，是服务端出问题，也有可能是自己访问的URL错误了



















