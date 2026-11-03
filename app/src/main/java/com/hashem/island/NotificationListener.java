package com.hashem.island;

import android.service.notification.*;
import android.content.*;
import android.app.*;

public class NotificationListener extends NotificationListenerService {
    @Override public void onNotificationPosted(StatusBarNotification sbn){
        if(sbn==null||sbn.getPackageName().equals(getPackageName()))return;
        Intent i=new Intent(this,IslandService.class);
        i.putExtra("demo",false);
        i.putExtra("app",sbn.getPackageName());
        CharSequence title=sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TITLE);
        CharSequence text=sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT);
        i.putExtra("message",(title==null?"Notification":title.toString())+(text==null?"":" · "+text));
        try{startService(i);}catch(Exception ignored){}
    }
}
