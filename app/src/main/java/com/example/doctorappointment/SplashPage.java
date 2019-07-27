package com.example.doctorappointment;

import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import java.util.Locale;

public class SplashPage extends AppCompatActivity implements TextToSpeech.OnInitListener

{

    Animation frombottom,fromtop;
TextView care,wel;
ImageView icon;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        textToSpeech= new TextToSpeech(this,this);
        care=findViewById(R.id.care);
        icon=findViewById(R.id.icon);
        wel=findViewById(R.id.wel);


        frombottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop=AnimationUtils.loadAnimation(this,R.anim.fromtop);
        wel.setAnimation(fromtop);
        care.setAnimation(frombottom);
        icon.setAnimation(frombottom);


        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashPage.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);







    }


    @Override
    public void onInit(int i) {


        String msg=("Welcome to Doctor Appointment");
         textToSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
         textToSpeech.setLanguage(Locale.ENGLISH);

    }
}
