package com.example.dudl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
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

        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES_NAME,MODE_PRIVATE);
        String savedText = preferences.getString(Constants.KEY, "");
        textView.setText("Лучший результат: " + savedText);
        textView.setTextSize(height*TEXT_SIZE);
        close.setImageBitmap(Bitmap.createScaledBitmap( BitmapFactory.decodeResource(getResources(),R.drawable.close),(int)(width*CLOSE_SIZE),(int)(width*CLOSE_SIZE),false));


        layout.addView(textView);
        layout.addView(close);

        textView.setX(width*MARGIN_VAL);
        textView.setY(width*MARGIN_VAL);
        close.setX(width - width*CLOSE_SIZE- width*MARGIN_VAL);
        close.setY(width*MARGIN_VAL);

        textView.setWidth(width);
        textView.setHeight((int)(height*HEIGHT_TEXT_SIZE));

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}