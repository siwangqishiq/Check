package com.cloudgy.check.activity;

import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;

import com.cloudgy.check.R;

public class TestActivity extends BaseActivity {

    private Button button1;
    private Button button2;
    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock;
    private int field = 0x00000020;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        try {
            // Yeah, this is hidden field.
            field = PowerManager.class.getField("PROXIMITY_SCREEN_OFF_WAKE_LOCK").getInt(null);
        } catch (Throwable ignored) {
        }

        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(field, getLocalClassName());

        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!wakeLock.isHeld()) {
                    wakeLock.acquire();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(wakeLock.isHeld()) {
                    wakeLock.release();
                }
            }
        });
    }

}//end class
