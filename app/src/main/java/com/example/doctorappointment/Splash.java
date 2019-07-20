package com.example.doctorappointment;

import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

public class Splash extends AppCompatActivity implements TextToSpeech.OnInitListener {
TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textToSpeech=new TextToSpeech(this,this);


        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent intent=new Intent(Splash.this,SlideOne.class);
                startActivity(intent);
                finish();
            }
        }, 4000);


    }

    @Override
    public void onInit(int i)
    {

        String msg = "Welcome to Doctor Appointmment";
        textToSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
        textToSpeech.setLanguage(Locale.ENGLISH);

    }
}
