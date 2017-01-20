
## 学习使用 Gradle 多打包


### 控制程序变量的方法

#### 方法一：

##### 1. 先在 project build.gradle 里面设置好需要控制的变量

```gradle
android {
    productFlavors {
        relase {
            manifestPlaceholders = [
                BASE_API_ROOT     : "http://192.168.1.3:8033/book"
            ]
        }
    }
}
```

##### 2. 在 AndroidManifest.xml 里面设置相同名字的变量

```xml
<meta-data
    android:name="BASE_API_ROOT"
    android:value="${BASE_API_ROOT}" />
```

##### 3. 在初始化的 activity 的页面里面读取出变量值，然后赋值给需要的变量，读取方法如下

```java
public void initConstants() {
    try {
        ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
        String baseApiRoot = appInfo.metaData.getString("BASE_API_ROOT");
        String baseApiImagePath = appInfo.metaData.getString("BASE_API_IMAGE_PATH");
        if (baseApiRoot != null) {
            Constants.BASE_API_ROOT = baseApiRoot;
        }
        if (baseApiImagePath != null) {
            Constants.BASE_API_IMAGE_PATH = baseApiImagePath;
        }
    } catch (PackageManager.NameNotFoundException e) {
        e.printStackTrace();
    }
}
```

#### 方法二

##### 1. 先在 project build.gradle 里面设置好需要控制的变量

```gradle
android {
    productFlavors {
        normal {//正常
            buildConfigField "Boolean", "IS_DEBUG", "false"
            buildConfigField "String", "SERVER_ADDRESS", "\"http://192.168.1.179:8090/\""
        }
        ceshi {//测试
             buildConfigField "Boolean", "IS_DEBUG", "true"
             buildConfigField "String", "SERVER_ADDRESS", "\"http://192.168.1.31:89/\""
        }
    }
}
```

##### 2. Rebuild Project ，然后会在项目的 build->source->buildConfig->normal 下自动生成如下文件

```java
public final class BuildConfig {
    public static final boolean DEBUG = Boolean.parseBoolean("true");
    public static final String APPLICATION_ID = "com.example.test";
    public static final String BUILD_TYPE = "debug";
    public static final String FLAVOR = "normal";
    public static final int VERSION_CODE = 100;
    public static final String VERSION_NAME = "1.0.0";
    // Fields from build type: normal
    public static final Boolean IS_DEBUG = true;
    public static final String SERVER_ADDRESS = "http://192.168.1.179:8090/";
}
```

对应的 productFlavors 会生成对应的 BuildConfig

##### 3. 在初始化的 activity 的页面里面读取出变量值，然后赋值给需要的变量，读取方法如下

```java
public void initConstants() {
    Constants.IS_DEBUG = BuildConfig.IS_DEBUG;
    Constants.SERVER_ADDRESS = BuildConfig.SERVER_ADDRESS;
}
```

---

好了，两种方法已经介绍完了，可以看出第二种方法较于第一种比较简单，所以推荐使用第二种方法。

目前，已经学会了如何在 Gradle 控制程序的变量了

方法通常用于设置程序是否打印 Log ，因为在正式版本下，是不需要打印 Log 的，所以，这样可以在打包的之前设置好这些变量，就可以不用手动修改了

还有服务器地址，因为通常测试地址和正式地址是分开的，以免数据混乱，通过先设置好地址，打包的时候就会自动的赋值了

## 接下来，我们继续学习！！！升级版本 Gradle 多打包

#### 使用 Gradle 多打包，控制应用的名字

有时候，我会需要在不同的需求下，要生成应用的名字也不同，所以，我们需要打包的时候，就要修改应用名，然后再打包
想想，如果有需要打十几个不同应用名的包呢？
呵呵~~ 现在学会了 Gradle 那就用好它！

##### 1. project build.gradle

1. 打开项目的 build.gradle 文件
   找到  android->productFlavors

1. 在对应的 productFlavors 添加设置每个包的应用名 如：

    ```gradle
    manifestPlaceholders = [APP_NAME: "test360"]
    ```

    这里的 APP_NAME 变量名可以随便设置，但是之后用到这个变量名，就必须是一样的。
    内容也是可以变的，如

    ```gradle
    manifestPlaceholders = [APP_NAME_A: "test应用宝"]
    ```

1. 最终结果应如下：

    ```gradle
    android {
        productFlavors {
            replese360 {
                 manifestPlaceholders = [APP_NAME: "test360"]
            }
            repleseyinyongbao {
                 manifestPlaceholders = [APP_NAME: "test应用宝"]
            }
        }
    }
    ```

##### 2. AndroidManifest.xml

1. 在 application 标签下设置应用名为一个变量
将 android:label 标签的内容改成 "${APP_NAME}" 注意这个 APP_NAME 要与 build.gradle 里面设置的一样，如下：

    ```xml
    android:label="${APP_NAME}"
    ```

1. 在 application 标签下添加声明要替换的变量：

    ```xml
    tools:replace="android:label"
    ```

1. 注意要在最顶级标签添加命名空间

    ```xml
    xmlns:tools="http://schemas.android.com/tools"
    ```

1. 最终结果应如下

    ```xml
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="${APP_NAME}"
        android:supportsRtl="true"
        android:theme="@style/mAppTheme"
        tools:replace="android:label">
        <activity android:name=".activity.MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
    </application>
    ```



##### 3. 然后，打包  ~~

对应每个 productFlavors 打包，就可以完成 Gradle 控制应用名的功能了

这样多打包控制应用名就搞掂了！

##### 4. 注意事项：
    1. 打包定义应用名，在 启动 activity 中不能有 label , 否则不能有效果












