
### Ripple 水波纹效果，也就是涟漪效果。
未编辑完。。。

波纹效果有两种：

　　1，波纹有边界：波纹涟漪效果只是显示在控件内部　　

        android:background="?android:attr/selectableItemBackground"
　　2，波纹超出边界：波纹涟漪效果不会被限制在控件内部

        android:background="?android:attr/selectableItemBackgroundBorderless"


通过给布局文件里面添加如上代码，就可以比较灵活的控制涟漪效果的显示区域。

    设置波纹的颜色：
        android:colorControlHighlight：设置波纹颜色

    设置checkbox等控件的选中颜色：
        android:colorAccent：设置checkbox等控件的选中颜色

要在v21以上才能做这个drawable

res/drawable/ripple_round.xml

    <?xml version="1.0" encoding="utf-8"?>
    <ripple xmlns:android="http://schemas.android.com/apk/res/android"
        android:color="?android:colorPrimary">

        <item>
            <shape android:shape="oval">
                <solid android:color="?android:colorPrimary" />
            </shape>
        </item>
    </ripple>

res/drawable/oval.xml

    <?xml version="1.0" encoding="utf-8"?>
    <ripple xmlns:android="http://schemas.android.com/apk/res/android"
        android:color="?android:colorControlHighlight">
        <item>
            <shape android:shape="oval">
                <solid android:color="#738ffe" />
            </shape>
        </item>
    </ripple>


布局文件中使用：

    <Button
        android:id="@+id/fab_button"
        android:layout_width="56dp"
        android:transitionName="fab"
        android:layout_height="56dp"
        android:background="@drawable/ripple_round"
        android:elevation="5dp"/>