package com.betx.matka;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.betx.matka.helper.Session;
import com.kiprotich.japheth.TextAnim;

public class SplashActivity extends AppCompatActivity {
    TextAnim textWriter;
    Session session;
    Activity activity;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activity = SplashActivity.this;
        session = new Session(activity);
        textWriter = findViewById(R.id.textWriter);

        textWriter
                .setWidth(12)
                .setDelay(30)
                .setColor(getColor(R.color.appdesignblue))
                .setConfig(TextAnim.Configuration.INTERMEDIATE)
                .setSizeFactor(30f)
                .setLetterSpacing(25f)
                .setText("MATKA")
                .setListener(new TextAnim.Listener() {
                    @Override
                    public void WritingFinished() {
                        if (session.getBoolean("is_logged_in")){
                            Intent intent = new Intent(activity,HomeActivity.class);
                            startActivity(intent);
                            finish();

                        }
                        else {
                            Intent intent = new Intent(activity,LoginActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    }
                })
                .startAnimation();
    }
}