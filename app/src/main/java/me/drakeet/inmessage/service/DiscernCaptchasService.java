package me.drakeet.inmessage.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import me.drakeet.inmessage.R;
import me.drakeet.inmessage.model.Message;
import me.drakeet.inmessage.utils.ClipboardUtils;
import me.drakeet.inmessage.utils.NotificationUtils;
import me.drakeet.inmessage.utils.ToastUtils;

/**
 * Created by shengkun on 15/6/11.
 */
public class DiscernCaptchasService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getBundleExtra("bundle");
        Message message = (Message)bundle.getSerializable("message");

        if(message.getCaptchas() != null) {
            ClipboardUtils.putTextIntoClipboard(DiscernCaptchasService.this, message.getCaptchas());
            // 弹两遍，加长时间。
            ToastUtils.showLong(String.format(getResources().getString(R.string.tip), message.getCaptchas()));
            ToastUtils.showLong(String.format(getResources().getString(R.string.tip), message.getCaptchas()));
        }
        NotificationUtils.showMessageInNotificationBar(DiscernCaptchasService.this, message);


        return START_STICKY;
    }
}
