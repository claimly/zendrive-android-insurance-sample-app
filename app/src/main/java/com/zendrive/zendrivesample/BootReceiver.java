package com.zendrive.zendrivesample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zendrive.zendrivesample.manager.SharedPrefsManager;
import com.zendrive.zendrivesample.manager.ZendriveManager;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            String driverId = SharedPrefsManager.sharedInstance(context).getDriverId();
            if (driverId != null) {
                ZendriveManager.sharedInstance().initializeZendriveSDK(context, driverId);
            }
        }
    }
}
