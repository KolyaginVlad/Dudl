package com.example.dudl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final float MARGIN_VAL = 0.025f;
    private static final float WIDTH_TEXT_SIZE = 0.5f;
    private static final float HEIGHT_TEXT_SIZE = 0.05f;
    private static final float TEXT_SIZE = 0.008f;
    private static final float CLOSE_SIZE = 0.1f;
    private static final float BUTTON_X_SIZE = 0.45f;
    private static final float BUTTON_Y_SIZE = 0.1f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        ConstraintLayout layout = findViewById(R.id.mainLay);

        TextView textView = new TextView(this);
        ImageView close = new ImageView(this);
        Button play = new Button(this);

        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES_NAME,MODE_PRIVATE);
        String savedText = preferences.getString(Constants.KEY, "");
        textView.setText("Лучший результат: " + savedText);
        textView.setTextSize(height*TEXT_SIZE);
        close.setImageBitmap(Bitmap.createScaledBitmap( BitmapFactory.decodeResource(getResources(),R.drawable.close),(int)(width*CLOSE_SIZE),(int)(width*CLOSE_SIZE),false));
        play.setText("Играть");
        play.setTextSize(height*TEXT_SIZE*2);
        play.setBackgroundColor(Color.GREEN);
        play.setShadowLayer(3,2,2,Color.GRAY);


        layout.addView(textView);
        layout.addView(close);
        layout.addView(play);

        textView.setX(width*MARGIN_VAL);
        textView.setY(width*MARGIN_VAL);
        close.setX(width - width*CLOSE_SIZE- width*MARGIN_VAL);
        close.setY(width*MARGIN_VAL);
        play.setX(width/2-width*BUTTON_X_SIZE/2);
        play.setY(height*7/10);

        textView.setWidth(width);
        textView.setHeight((int)(height*HEIGHT_TEXT_SIZE));
        play.setWidth((int) (width*BUTTON_X_SIZE));
        play.setHeight((int) (height*BUTTON_Y_SIZE));

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}