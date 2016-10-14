package com.shixia.diudiuma.common_utils;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.shixia.diudiuma.activity.base.BaseActivity;
import com.shixia.diudiuma.presenter.base.BasePresenter;

/**
 * Created by AmosShi on 2016/10/14.
 * Description:Android手机版本 >= 23的运行时权限检查工具类
 */

/**
Dangerous Permissions:

        group:android.permission-group.CONTACTS
        permission:android.permission.WRITE_CONTACTS
        permission:android.permission.GET_ACCOUNTS
        permission:android.permission.READ_CONTACTS

        group:android.permission-group.PHONE
        permission:android.permission.READ_CALL_LOG
        permission:android.permission.READ_PHONE_STATE
        permission:android.permission.CALL_PHONE
        permission:android.permission.WRITE_CALL_LOG
        permission:android.permission.USE_SIP
        permission:android.permission.PROCESS_OUTGOING_CALLS
        permission:com.android.voicemail.permission.ADD_VOICEMAIL

        group:android.permission-group.CALENDAR
        permission:android.permission.READ_CALENDAR
        permission:android.permission.WRITE_CALENDAR

        group:android.permission-group.CAMERA
        permission:android.permission.CAMERA

        group:android.permission-group.SENSORS
        permission:android.permission.BODY_SENSORS

        group:android.permission-group.LOCATION
        permission:android.permission.ACCESS_FINE_LOCATION
        permission:android.permission.ACCESS_COARSE_LOCATION

        group:android.permission-group.STORAGE
        permission:android.permission.READ_EXTERNAL_STORAGE
        permission:android.permission.WRITE_EXTERNAL_STORAGE

        group:android.permission-group.MICROPHONE
        permission:android.permission.RECORD_AUDIO

        group:android.permission-group.SMS
        permission:android.permission.READ_SMS
        permission:android.permission.RECEIVE_WAP_PUSH
        permission:android.permission.RECEIVE_MMS
        permission:android.permission.RECEIVE_SMS
        permission:android.permission.SEND_SMS
        permission:android.permission.READ_CELL_BROADCASTS

 */

public class PermissionUtils {

    private static BaseActivity baseActivity;
    private static BasePresenter basePresenter;

    public static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 0x100;
    public static final int READ_EXTERNAL_STORAGE = 0x200;

    public static PermissionUtils initCurrentActivity(BaseActivity baseActivity, BasePresenter basePresenter){
        PermissionUtils.baseActivity = baseActivity;
        PermissionUtils.basePresenter = basePresenter;
        return new PermissionUtils();
    }

    public static void doBizAfterRequestPermission(String permission,int requestCode){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(baseActivity, permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(baseActivity, new String[]{ permission },
                    requestCode);//自定义的code
        }else {
            basePresenter.doBizWithPermissionRequest(-1,null);
        }
    }


    /**###################### 以下时不需要权限检查的Normal Permission #############################*/

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
