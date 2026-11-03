package com.hashem.island;

import android.app.*;
import android.content.*;
import android.graphics.PixelFormat;
import android.os.*;
import android.provider.Settings;
import android.view.*;
import android.widget.*;

public class IslandService extends Service {
    WindowManager wm; View island; Handler handler=new Handler(Looper.getMainLooper()); Runnable hide;
    @Override public void onCreate(){super.onCreate();wm=(WindowManager)getSystemService(WINDOW_SERVICE);}
    @Override public int onStartCommand(Intent intent,int flags,int id){
        boolean demo=intent!=null&&intent.getBooleanExtra("demo",false);
        show(demo?"HASHEM ISLAND  •  Demo":"Activity detected");
        if(hide!=null)handler.removeCallbacks(hide);
        hide=()->remove(); handler.postDelayed(hide,5000);
        return START_NOT_STICKY;
    }
    void show(String message){
        if(!Settings.canDrawOverlays(this))return;
        remove();
        TextView t=new TextView(this);t.setText("  ◉   "+message+"   ");t.setTextColor(0xffffffff);t.setTextSize(13);t.setGravity(Gravity.CENTER);
        android.graphics.drawable.GradientDrawable bg=new android.graphics.drawable.GradientDrawable();bg.setColor(0xff050505);bg.setCornerRadius(100);t.setBackground(bg);
        int type=Build.VERSION.SDK_INT>=26?WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY:WindowManager.LayoutParams.TYPE_PHONE;
        WindowManager.LayoutParams p=new WindowManager.LayoutParams(-2,dp(42),type,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,PixelFormat.TRANSLUCENT);
        p.gravity=Gravity.TOP|Gravity.CENTER_HORIZONTAL;p.y=dp(10);
        try{island=t;wm.addView(island,p);}catch(Exception ignored){}
    }
    void remove(){try{if(island!=null)wm.removeView(island);}catch(Exception ignored){}island=null;}
    int dp(int n){return (int)(n*getResources().getDisplayMetrics().density+.5f);}
    @Override public void onDestroy(){remove();super.onDestroy();}
    @Override public android.os.IBinder onBind(Intent i){return null;}
}
