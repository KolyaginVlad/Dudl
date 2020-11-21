package com.example.dudl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
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

        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCES_NAME,MODE_PRIVATE);
        String savedText = preferences.getString(Constants.KEY, "");
        textView.setText("Лучший результат: " + savedText);
        textView.setTextSize(height*TEXT_SIZE);


        layout.addView(textView);

        textView.setX(width*MARGIN_VAL);
        textView.setY(width*MARGIN_VAL);

        textView.setWidth(width);
        textView.setHeight((int)(height*HEIGHT_TEXT_SIZE));
    }
}