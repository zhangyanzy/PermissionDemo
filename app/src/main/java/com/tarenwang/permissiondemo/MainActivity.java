package com.tarenwang.permissiondemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.call_phone)
    Button mCallPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.call_phone)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call_phone:
                /**
                 *
                 * 先new出来一个数组 留着放置权限
                 List<String> permissionList = new ArrayList<>();
                 //判断当前程序有没有被用户进行权限授权
                 if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                 //如果没有授权，就放入在permissionList
                 permissionList.add(Manifest.permission.CALL_PHONE);
                 }
                 if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                 permissionList.add(Manifest.permission.WRITE_APN_SETTINGS);
                 }
                 //如果不等于Empty
                 if (!permissionList.isEmpty()) {
                 //如果permissionList不为空,就进行授权，一次性申请多个权限
                 ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
                 } else {
                 //如果permissionList为空，就代表所有权限都授权完毕
                 //所有权限都同意了
                 doSomething();
                 }
                 */
                requestRuntimePermission(new String[]{
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CAMERA}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        Toast.makeText(MainActivity.this, "所有权限都同意了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        for (String permission : deniedPermission) {
                            Toast.makeText(MainActivity.this, "被拒绝权限:" + permission, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        }
    }


    //监听回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getApplicationContext(), "某个权限被拒绝", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            doSomething();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    private void doSomething() {
        Toast.makeText(getApplicationContext(), "所有权限都同意了", Toast.LENGTH_SHORT).show();
    }

}
