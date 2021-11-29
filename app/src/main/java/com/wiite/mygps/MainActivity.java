package com.wiite.mygps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton wazeIb, mapsIb;
    long lastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvents();
    }

    private void initViews() {
        wazeIb = findViewById(R.id.wazeIb);
        mapsIb = findViewById(R.id.mapsIb);
    }

    private void initEvents() {
        wazeIb.setOnClickListener(this);
        mapsIb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mapsIb:
                System.out.println("@pressed: wazeIb = ");
                launchPackage("com.waze");
                break;
            case R.id.wazeIb:
                launchPackage("com.google.android.apps.maps");
                break;
        }
    }

    private void launchPackage(String packageName) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - 5000 > lastClickTime) {
            lastClickTime = currentTime;
            try {
                PackageManager packageManager = getPackageManager();
                Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
//            launchIntentForPackage.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(launchIntentForPackage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}