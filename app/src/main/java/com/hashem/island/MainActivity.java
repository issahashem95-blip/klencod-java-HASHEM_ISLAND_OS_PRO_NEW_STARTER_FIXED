package com.hashem.island;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.provider.Settings;
import android.net.Uri;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    LinearLayout root;
    int gold=Color.rgb(214,181,109);
    @Override public void onCreate(Bundle b){super.onCreate(b); build();}
    void build(){
        root=new LinearLayout(this); root.setOrientation(1); root.setPadding(dp(22),dp(26),dp(22),dp(20)); root.setBackgroundColor(Color.rgb(9,11,16));
        ScrollView sc=new ScrollView(this); sc.addView(root); setContentView(sc);
        TextView brand=text("HASHEM ISLAND",12,gold); brand.setLetterSpacing(.18f); root.addView(brand);
        TextView title=text("ISLAND OS PRO",29,Color.WHITE); title.setTypeface(Typeface.create("sans-serif",Typeface.BOLD)); root.addView(title);
        TextView sub=text("Your activity. Beautifully alive.",14,0xff9CA3AF); root.addView(sub);
        space(24);
        LinearLayout preview=new LinearLayout(this); preview.setGravity(Gravity.CENTER); preview.setPadding(dp(18),dp(20),dp(18),dp(20)); preview.setBackground(round(0xff171A22,30));
        TextView pill=text("  ◉   HASHEM ISLAND     •••  ",16,Color.WHITE); pill.setGravity(Gravity.CENTER); pill.setPadding(dp(12),dp(12),dp(12),dp(12)); pill.setBackground(round(Color.BLACK,40)); preview.addView(pill); root.addView(preview,new LinearLayout.LayoutParams(-1,dp(100)));
        space(22); heading("QUICK SETUP");
        addButton("Enable floating island",()->{if(!Settings.canDrawOverlays(this))startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName())));else startService(new Intent(this,IslandService.class));});
        addButton("Enable notification access",()->startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")));
        addButton("Show demo island",()->{Intent i=new Intent(this,IslandService.class); i.putExtra("demo",true);startService(i);});
        space(18); heading("PERSONALIZATION");
        addButton("Island size  ·  Compact / Standard / Wide",()->sizeDialog());
        addButton("Appearance  ·  Obsidian / Gold / Glass",()->themeDialog());
        addButton("Notification duration  ·  3–8 seconds",()->durationDialog());
        space(18); heading("LIVE ACTIVITIES");
        addButton("Screen recording",()->new AlertDialog.Builder(this).setTitle("Screen recording").setMessage("Android requires system screen-capture consent. This starter prepares the island UI; recording integration must be tested on your device.").setPositiveButton("OK",null).show());
        addButton("Voice recording",()->new AlertDialog.Builder(this).setTitle("Voice recording").setMessage("Recording inside this app requires microphone permission and a foreground recording session.").setPositiveButton("OK",null).show());
        TextView foot=text("Built for Android • iOS-inspired, not affiliated with Apple",11,0xff687080); foot.setGravity(Gravity.CENTER); LinearLayout.LayoutParams fp=new LinearLayout.LayoutParams(-1,-2); fp.topMargin=dp(28); root.addView(foot,fp);
    }
    void heading(String s){TextView t=text(s,11,0xffD6B56D); t.setLetterSpacing(.12f); root.addView(t); space(10);}
    void addButton(String s,Runnable r){TextView t=text(s,15,Color.WHITE); t.setPadding(dp(16),dp(17),dp(16),dp(17)); t.setBackground(round(0xff171F2B,18)); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.bottomMargin=dp(9); root.addView(t,p); t.setOnClickListener(v->r.run());}
    void sizeDialog(){new AlertDialog.Builder(this).setTitle("Island size").setItems(new String[]{"Compact","Standard","Wide"},(d,w)->getPreferences(0).edit().putInt("size",w).apply()).show();}
    void themeDialog(){new AlertDialog.Builder(this).setTitle("Appearance").setItems(new String[]{"Obsidian","Gold","Glass"},(d,w)->getPreferences(0).edit().putInt("theme",w).apply()).show();}
    void durationDialog(){String[] a={"3 seconds","4 seconds","5 seconds","6 seconds","8 seconds"};new AlertDialog.Builder(this).setTitle("Notification duration").setItems(a,(d,w)->getPreferences(0).edit().putInt("duration",w+3).apply()).show();}
    TextView text(String s,int size,int color){TextView t=new TextView(this);t.setText(s);t.setTextSize(size);t.setTextColor(color);return t;}
    void space(int h){View v=new View(this);root.addView(v,new LinearLayout.LayoutParams(1,dp(h)));}
    android.graphics.drawable.GradientDrawable round(int c,int r){android.graphics.drawable.GradientDrawable d=new android.graphics.drawable.GradientDrawable();d.setColor(c);d.setCornerRadius(dp(r));return d;}
    int dp(int x){return (int)(x*getResources().getDisplayMetrics().density+.5f);}
}