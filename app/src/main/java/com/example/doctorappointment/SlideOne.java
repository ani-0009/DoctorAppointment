package com.example.doctorappointment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SlideOne extends AppCompatActivity implements View.OnClickListener {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        next =findViewById(R.id.slideone);
        next.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent =new Intent(SlideOne.this,SlideTwo.class);
        startActivity(intent);

    }
}