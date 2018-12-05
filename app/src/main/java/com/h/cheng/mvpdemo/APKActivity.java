package com.h.cheng.mvpdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.h.cheng.mvpdemo.utils.FileUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class APKActivity extends AppCompatActivity {

    @BindView(R.id.btn_down)
    Button btnDown;

    private String dir;
    private String authority = "com.h.cheng.mvpdemo.fileprovider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_down)
    public void onViewClicked() {
        if (AndPermission.hasPermission(APKActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )) {
            // 有权限，直接do anything.
            copyFile();

        } else {
            // 申请权限。
            AndPermission.with(APKActivity.this)
                    .requestCode(101)
                    .permission(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .send();
        }
    }


    private void copyFile() {

        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = getAssets().open("app-debug.apk");

            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {// 检查是否有存储卡

                dir = Environment.getExternalStorageDirectory() + "/ceshi/";
                File dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                File apk = new File(dir + "app-debug.apk");
                if (!apk.exists()) {
                    apk.createNewFile();
                }

                outputStream = new FileOutputStream(apk);

                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = inputStream.read(buffer)) != -1) {// 循环从输入流读取
                    // buffer字节
                    outputStream.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
                }
                outputStream.flush();// 刷新缓冲区
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (getPackageManager().canRequestPackageInstalls()) {
                FileUtil.openFile(APKActivity.this, dir + "app-debug.apk", authority);
            } else {
                // 申请权限。
                startInstallPermissionSettingActivity();
            }
        } else {

            FileUtil.openFile(APKActivity.this, dir + "app-debug.apk", authority);
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        startActivityForResult(intent, 10086);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086) {
            FileUtil.openFile(APKActivity.this, dir + "app-debug.apk", authority);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        // 只需要调用这一句，其它的交给AndPermission吧，最后一个参数是PermissionListener。
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, listener);
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == 10086) {
                FileUtil.openFile(APKActivity.this, dir + "app-debug.apk", authority);
            } else if (requestCode == 101) {
                copyFile();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(APKActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(APKActivity.this, 300).show();

            }
        }
    };
}
