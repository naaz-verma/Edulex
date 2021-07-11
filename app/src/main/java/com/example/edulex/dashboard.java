package com.example.edulex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {

    ImageView hb_img,ss_img;
    TextView hb_text,ss_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_dashboard);


       hb_img=(ImageView) findViewById(R.id.hb_img);

        ss_img=(ImageView) findViewById(R.id.ss_img);

        hb_text = (TextView) findViewById(R.id.hb_text);

        ss_text = (TextView) findViewById(R.id.ss_text);
    }
    public void hb_img(View v)
    {
        hb_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),humanbody_system.class);
                startActivity(intent);
            }
        });
    }
    public void hb_text(View v)
    {
        hb_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),humanbody_system.class);
                startActivity(intent);
            }
        });
    }

    public void ss_img(View v)
    {
        ss_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),solar_system.class);
                startActivity(intent);
            }
        });
    }
    public void ss_text(View v)
    {
        ss_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),solar_system.class);
                startActivity(intent);
            }
        });
    }




}




