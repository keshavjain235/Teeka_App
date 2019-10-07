package com.example.teekaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    TextView text, quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
        text = findViewById(R.id.qu);
        quote = findViewById(R.id.quote);

        YoYo.with(Techniques.ZoomIn).duration(2000).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                YoYo.with(Techniques.FadeOut).duration(1700).playOn(text);
                YoYo.with(Techniques.FadeOut).duration(1).playOn(quote);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                YoYo.with(Techniques.FadeIn).duration(1500).playOn(text);
                YoYo.with(Techniques.FadeIn).duration(1700).playOn(quote);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        }).playOn(image);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);

                    startActivity(new Intent(MainActivity.this,SplashActivity.class));

                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
