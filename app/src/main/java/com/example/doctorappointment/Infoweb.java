package com.example.doctorappointment;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class Infoweb extends AppCompatActivity {
WebView web;


private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoweb);






       web=findViewById(R.id.web);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().getDisplayZoomControls();


        String url="https://www.goodreads.com/quotes/tag/doctors";
        web.loadUrl(url);



    }
}
