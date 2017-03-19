package com.test.test168.bean;

import android.app.Activity;
import android.graphics.drawable.Drawable;

/**
 * Created by Test on 2017/1/7.
 */

public class ItemHomeMenu {

    private Drawable menuIcon;
    private String menuName;
    private Class activity;

    public ItemHomeMenu(Drawable menuIcon, String menuName, Class<? extends Activity> aActivity) {
        this.menuIcon = menuIcon;
        this.menuName = menuName;
        activity = aActivity;
    }

    public Drawable getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(Drawable menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }
}
