package com.tarenwang.permissiondemo;

import java.util.List;

/**
 * Created by zhangyan on 2016/12/28.
 */

public class PermissionUtil {

    public void test(){
        BaseActivity.requestRuntimePermission(new String[]{}, new PermissionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onDenied(List<String> deniedPermission) {

            }
        });
    }
}
