package com.example.reapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register_Activity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = db.getReference();
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth =  FirebaseAuth.getInstance();

        final EditText fullname = findViewById(R.id.editTextTextPersonName);
        final EditText emailreg = findViewById(R.id.emailreg);
        final EditText passreg = findViewById(R.id.passreg);
        final Button register = findViewById(R.id.buttonreg);
        final EditText conpass = findViewById(R.id.passreg2);
        final TextView login = findViewById(R.id.textView5);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullnametxt = fullname.getText().toString();
                final String email = emailreg.getText().toString();
                final String pass = passreg.getText().toString();
                final String conpassword = conpass.getText().toString();


                if (fullnametxt.isEmpty() || email.isEmpty() || pass.isEmpty() || conpassword.isEmpty()) {

                    Toast.makeText(Register_Activity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(conpassword)) {

                    Toast.makeText(Register_Activity.this, "password are not a match", Toast.LENGTH_SHORT).show();

                } else {


                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){


                                Toast.makeText(Register_Activity.this, "Reg success", Toast.LENGTH_SHORT).show();

                            }

                            else{

                                Toast.makeText(Register_Activity.this, "Reg failed", Toast.LENGTH_SHORT).show();


                            }

                        }



                    });


                }


            }


        });


    }
}