package com.example.bunnys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.tomer.fadingtextview.FadingTextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen<texts> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

final Intent intent = new Intent(this, MainActivity.class);
    TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };

    Timer splash = new Timer();
  splash.schedule(task, 4000);

    }


}
