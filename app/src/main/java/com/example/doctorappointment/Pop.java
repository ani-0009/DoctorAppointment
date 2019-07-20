package com.example.doctorappointment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.doctorappointment.com.Clerk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Pop extends AppCompatActivity implements View.OnClickListener {

Button btn_register;
EditText txtid,txtname,txtpass,txtemail,txtphone,txtadd;
private  FirebaseAuth mAuth;
String id;
    String password;


 private  ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        txtid=findViewById(R.id.txtid);
        txtname=findViewById(R.id.txtname);
        txtemail=findViewById(R.id.txtemail);
        txtpass=findViewById(R.id.txtpass);
        txtadd=findViewById(R.id.txtadd);
        txtphone=findViewById(R.id.txtphone);
        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        progressBar=new ProgressBar(this);



        mAuth=FirebaseAuth.getInstance();



        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width=displayMetrics.widthPixels;
        int height=displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.8));
    }


    @Override
    protected void onStart() {
        super.onStart();
         if(mAuth.getCurrentUser() !=null)
         {


         }
    }

    public void Clerkfunction()
    {
         id=txtid.getText().toString().trim();
        final String name=txtname.getText().toString().trim();
        final String email=txtemail.getText().toString().trim();
         password=txtpass.getText().toString().trim();
        final String phone=txtphone.getText().toString().trim();
        final String address=txtadd.getText().toString().trim();

        if(id.equals("")){

            txtid.setError("ID should not be empty");}
        else if(name.equals(""))
        {
            txtname.setError("Name Should not be Empty");
        }
        else if(email.equals(""))
        {
            txtemail.setError("Email Should not be Empty");
        }
        else if(phone.equals(""))
        {
            txtphone.setError("Phone Should not be Empty");
        }
        else if(address.equals(""))
        {
            txtadd.setError("Address Should not be Empty");
        }
        else {


            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {

                                Clerk clerk = new Clerk(
                                        id,
                                        name,
                                        email,
                                        password,
                                        phone,
                                        address
                                );

                                FirebaseDatabase.getInstance().getReference("Clerks")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(clerk).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Pop.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Pop.this, MainActivity.class);
                                            intent.putExtra("id", id);
                                            intent.putExtra("pass", password);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(Pop.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            } else {
                                Toast.makeText(Pop.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }





    }

    @Override
    public void onClick(View view) {
        switch (view.getId())

        {
            case R.id.btn_register:
                Clerkfunction();


                break;
        }




    }
}
