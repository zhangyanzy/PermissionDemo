package com.tarenwang.permissiondemo;

import java.util.List;

/**
 * Created by zhangyan on 2016/12/28.
 */

public interface PermissionListener {
    //授权
    void onGranted();
    //拒绝授权
    void onDenied(List<String>deniedPermission) ;
}
