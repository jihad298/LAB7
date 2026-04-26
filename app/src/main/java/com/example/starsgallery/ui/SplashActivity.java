package com.example.starsgallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.starsgallery.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo    = findViewById(R.id.logo);
        TextView  tagline = findViewById(R.id.tagline);
        logo.setImageResource(R.mipmap.snow_logo);


        logo.animate()
                .scaleX(1.3f)
                .scaleY(1.3f)
                .setDuration(1500)
                .start();

        tagline.setAlpha(0f);
        tagline.animate()
                .alpha(1f)
                .setStartDelay(800)
                .setDuration(1500)
                .start();

        logo.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, ListActivity.class));
            finish();
        }, 4000);
    }
}