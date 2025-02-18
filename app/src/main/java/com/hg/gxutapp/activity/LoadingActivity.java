package com.hg.gxutapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hg.gxutapp.R;


public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
            getWindow().setAttributes(lp);
        }else {
            //无title
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //全屏
            getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                    WindowManager.LayoutParams. FLAG_FULLSCREEN);

        }
        setContentView(R.layout.activity_loading);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    SharedPreferences preference=getSharedPreferences("user", MODE_PRIVATE);
                    if (!preference.getString("userName","").equals("")){
                        startActivity(new Intent(LoadingActivity.this,MainActivity.class));
                    }else {
                        startActivity(new Intent(LoadingActivity.this,LoginActivity.class));
                    }
                    LoadingActivity.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
