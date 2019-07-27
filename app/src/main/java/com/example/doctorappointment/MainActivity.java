package com.example.doctorappointment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class MainActivity extends AppCompatActivity
{
    Button btnreg,btnmore;

    ProSwipeButton btnlogin;


    EditText txtloginemail,txtloginpass;
    //Intent intent;

    String loginemail;
    String loginpass;


private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* intent=getIntent();
         final String registeredID= intent.getStringExtra("id");
         final String registeredPass= intent.getStringExtra("pass");*/

txtloginemail=findViewById(R.id.txtloginemail);

txtloginpass=findViewById(R.id.txtloginpass);
        btnlogin=findViewById(R.id.btnlogin);
        btnmore=findViewById(R.id.btnmore);
        btnreg=findViewById(R.id.btnreg);
firebaseAuth=FirebaseAuth.getInstance();





        btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent in=new Intent(MainActivity.this,Infoweb.class);
                startActivity(in);


            }


        });


        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(MainActivity.this,Pop.class);
                startActivity(intent);
            }
        });


       btnlogin.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
           @Override
           public void onSwipeConfirm() {

               loginemail=txtloginemail.getText().toString().trim();
               loginpass=txtloginpass.getText().toString().trim();

              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {

                      if(loginemail.equals("")){


                          txtloginemail.setError("Email should not be empty");
                          btnlogin.showResultIcon(false);
                      }
                      else if(loginpass.equals(""))
                      {
                          txtloginpass.setError("Name Should not be Empty");
                          btnlogin.showResultIcon(false);
                      }

                      else if(loginpass.length()<6)
                      {
                          Toast.makeText(MainActivity.this, "Password Length Too Small", Toast.LENGTH_SHORT).show();
                          btnlogin.showResultIcon(false);
                      }
                      else
                      {


                          firebaseAuth.signInWithEmailAndPassword(loginemail , loginpass)
                                  .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                      @Override
                                      public void onComplete(@NonNull Task<AuthResult> task) {
                                          if (task.isSuccessful()) {

                                              startActivity(new Intent(getApplicationContext(),ClerkHome.class));
                                              btnlogin.showResultIcon(true);

                                          } else {
                                              Toast.makeText(MainActivity.this, "Login Failed or User Not Available", Toast.LENGTH_SHORT).show();

                                              btnlogin.showResultIcon(false);
                                              txtloginemail.setText("");
                                              txtloginpass.setText("");
                                          }


                                      }
                                  });
                      }
                  }
              }, 2000);
           }
       });




    }


}
