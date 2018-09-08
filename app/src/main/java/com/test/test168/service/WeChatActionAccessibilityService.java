package com.test.test168.service;

import android.view.accessibility.AccessibilityEvent;

import com.xian.common.utils.XLog;

/**
 * @author xian
 * @date 2018/5/3
 */

class WeChatActionAccessibilityService extends BaseAccessibilityService {


    public static final String WECHAT_PACKAGE_NAME = "com.tencent.wechat";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // 此方法是在主线程中回调过来的，所以消息是阻塞执行的
        // 获取包名
        String pkgName = event.getPackageName().toString();
        int eventType = event.getEventType();
        // AccessibilityOperator封装了辅助功能的界面查找与模拟点击事件等操作
        AccessibilityOperator.getInstance().updateEvent(this, event);
        XLog.i("eventType: " + eventType + " pkgName: " + pkgName);
        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:

                if (pkgName.equals(WECHAT_PACKAGE_NAME)) {

                }

                break;
            default:
                break;
        }
    }


}
