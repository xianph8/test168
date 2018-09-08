package com.xian.common.module;

import java.io.IOException;

/**
 * @author xian
 * @date 2018/6/2
 */

public class YLApiError extends IOException {


    public boolean isApiError() {
        return false;
    }
}
