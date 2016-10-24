package com.shixia.diudiuma.common_utils;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.shixia.diudiuma.mvp.activity.base.BaseActivity;
import com.shixia.diudiuma.mvp.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/10/14.
 * Description:Android手机版本 >= 23的运行时权限检查工具类
 */

/**
 * Dangerous Permissions:
 * <p>
 * group:android.permission-group.CONTACTS
 * permission:android.permission.WRITE_CONTACTS
 * permission:android.permission.GET_ACCOUNTS
 * permission:android.permission.READ_CONTACTS
 * <p>
 * group:android.permission-group.PHONE
 * permission:android.permission.READ_CALL_LOG
 * permission:android.permission.READ_PHONE_STATE
 * permission:android.permission.CALL_PHONE
 * permission:android.permission.WRITE_CALL_LOG
 * permission:android.permission.USE_SIP
 * permission:android.permission.PROCESS_OUTGOING_CALLS
 * permission:com.android.voicemail.permission.ADD_VOICEMAIL
 * <p>
 * group:android.permission-group.CALENDAR
 * permission:android.permission.READ_CALENDAR
 * permission:android.permission.WRITE_CALENDAR
 * <p>
 * group:android.permission-group.CAMERA
 * permission:android.permission.CAMERA
 * <p>
 * group:android.permission-group.SENSORS
 * permission:android.permission.BODY_SENSORS
 * <p>
 * group:android.permission-group.LOCATION
 * permission:android.permission.ACCESS_FINE_LOCATION
 * permission:android.permission.ACCESS_COARSE_LOCATION
 * <p>
 * group:android.permission-group.STORAGE
 * permission:android.permission.READ_EXTERNAL_STORAGE
 * permission:android.permission.WRITE_EXTERNAL_STORAGE
 * <p>
 * group:android.permission-group.MICROPHONE
 * permission:android.permission.RECORD_AUDIO
 * <p>
 * group:android.permission-group.SMS
 * permission:android.permission.READ_SMS
 * permission:android.permission.RECEIVE_WAP_PUSH
 * permission:android.permission.RECEIVE_MMS
 * permission:android.permission.RECEIVE_SMS
 * permission:android.permission.SEND_SMS
 * permission:android.permission.READ_CELL_BROADCASTS
 */

public class PermissionUtils {

    private static BaseActivity baseActivity;
    private static BasePresenter basePresenter;

    private static final String[] ARR_DO_NOT_NEED_REQUEST_PERMISSION = {"do_not_need_permission"};  //在权限已被授予的回调中使用
    private static final String DO_NOT_NEED_REQUEST_PERMISSION = "do_not_need_permission";  //在权限已被授予的回调中使用

    public static final int PERMISSION_ALREADY_GRANTED = 0x001;     //已被授予权限
    public static final int PERMISSION_DENIED = 0x002;  //权限被拒绝

    public static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 0x100; //定位请求码
    public static final int READ_EXTERNAL_STORAGE = 0x200;  //读写文件请求码
    public static final int CAMERA = 0x300; //照相机请求码

    public static PermissionUtils initCurrentActivity(BaseActivity baseActivity, BasePresenter basePresenter) {
        PermissionUtils.baseActivity = baseActivity;
        PermissionUtils.basePresenter = basePresenter;
        return new PermissionUtils();
    }

    public static void doBizAfterRequestPermission(String permission, int requestCode) {
        L.i("doBizAfterRequestPermission", "requestCode:" + requestCode);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(baseActivity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(baseActivity, new String[]{permission},
                    requestCode);//自定义的code
        } else {
            basePresenter.doBizWithPermissionRequest(PERMISSION_ALREADY_GRANTED, ARR_DO_NOT_NEED_REQUEST_PERMISSION);
        }
    }

    /**
     * 判断请求的权限是否授予
     *
     * @param returnResultCode  请求结果返回的请求码
     * @param yourRequestCode   你的请求码，同于同返回请求码做对比，判断是否是该次请求的返回结果
     * @param returnPermissions       返回的请求权限列表
     * @param yourRequestPermission   你请求的权限
     * @return  本次请求权限是否被授予
     */
    public static boolean isPermissionGranted(int returnResultCode,int yourRequestCode,String[] returnPermissions,String yourRequestPermission){
        return (returnResultCode == PERMISSION_ALREADY_GRANTED && TextUtils.equals(returnPermissions[0],DO_NOT_NEED_REQUEST_PERMISSION))    //该情况表示上一次已经取得了授权
                //以下情况表示未授予过权限
                || (returnResultCode == yourRequestCode
                && !TextUtils.equals(returnPermissions[0],DO_NOT_NEED_REQUEST_PERMISSION)
                && returnPermissions.length > 0
                && returnPermissions[0].equals(yourRequestPermission));
    }

    /**###################### 以下时不需要权限检查的 Normal Permission #############################*/

    /**
     ACCESS_LOCATION_EXTRA_COMMANDS
     ACCESS_NETWORK_STATE
     ACCESS_NOTIFICATION_POLICY
     ACCESS_WIFI_STATE
     BLUETOOTH
     BLUETOOTH_ADMIN
     BROADCAST_STICKY
     CHANGE_NETWORK_STATE
     CHANGE_WIFI_MULTICAST_STATE
     CHANGE_WIFI_STATE
     DISABLE_KEYGUARD
     EXPAND_STATUS_BAR
     GET_PACKAGE_SIZE
     INSTALL_SHORTCUT
     INTERNET
     KILL_BACKGROUND_PROCESSES
     MODIFY_AUDIO_SETTINGS
     NFC
     READ_SYNC_SETTINGS
     READ_SYNC_STATS
     RECEIVE_BOOT_COMPLETED
     REORDER_TASKS
     REQUEST_INSTALL_PACKAGES
     SET_ALARM
     SET_TIME_ZONE
     SET_WALLPAPER
     SET_WALLPAPER_HINTS
     TRANSMIT_IR
     UNINSTALL_SHORTCUT
     USE_FINGERPRINT
     VIBRATE
     WAKE_LOCK
     WRITE_SYNC_SETTINGS

     */
}
