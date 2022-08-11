package com.example.reapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        if(mAuth.getCurrentUser() != null)
            startActivity(new Intent(LoginActivity.this, fragment_home.class));
        final EditText email = findViewById(R.id.emailreg);
        final EditText numpass = findViewById(R.id.passreg);
        final Button login = findViewById(R.id.buttonreg);
        final TextView register = findViewById(R.id.textView5);

             login.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     final String emailadd = email.getText().toString();
                     final String password = numpass .getText().toString();

                     if(emailadd.isEmpty() || password.isEmpty()){
                         Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                     }
                     else{
                         mAuth.signInWithEmailAndPassword( emailadd, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {

                                 if(task.isSuccessful()){

                                     Toast.makeText(LoginActivity.this, "successfull", Toast.LENGTH_SHORT).show();

                                 }

                                 else{
                                     Toast.makeText(LoginActivity.this, "try Again", Toast.LENGTH_SHORT).show();
                                 }

                             }
                         });

                     }
                 }
             });

             register.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                     startActivity(new Intent(getApplicationContext(), Register_Activity.class));
                 }
             });
    }

}