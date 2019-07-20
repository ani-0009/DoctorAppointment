package com.example.doctorappointment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SlideTwo extends AppCompatActivity {


    Button prev,finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prev=findViewById(R.id.prev);
        finish=findViewById(R.id.finish);


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SlideTwo.this,SlideOne.class);
                startActivity(intent);
            }
        });


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SlideTwo.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
