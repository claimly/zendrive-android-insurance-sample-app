package com.zendrive.zendrivesample;

import android.content.Context;
import android.support.annotation.NonNull;

import com.zendrive.sdk.ZendriveNotificationContainer;
import com.zendrive.sdk.ZendriveNotificationProvider;
import com.zendrive.zendrivesample.utils.NotificationUtility;

public class MyZendriveNotificationProvider implements ZendriveNotificationProvider {
    private static final int ZENDRIVE_NOTIFICATION_ID = 495;

    @NonNull
    @Override
    public ZendriveNotificationContainer getMaybeInDriveNotificationContainer(@NonNull Context context) {
        return new ZendriveNotificationContainer(
                ZENDRIVE_NOTIFICATION_ID,
                NotificationUtility.getMaybeInDriveNotification(context));
    }

    @NonNull
    @Override
    public ZendriveNotificationContainer getInDriveNotificationContainer(@NonNull Context context) {
        return new ZendriveNotificationContainer(
                ZENDRIVE_NOTIFICATION_ID,
                NotificationUtility.getInDriveNotification(context));
    }
}
