package com.sunny.bsdproject1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginForm extends AppCompatActivity {

    private EditText name, Email, Phone, Password, Conpassword;
    Button vbtnsignup;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        this.setTitle("Sign Up Page");

        progressBar = (ProgressBar) findViewById(R.id.signupprogressBar);
        databaseReference = FirebaseDatabase.getInstance().getReference("user_details");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.editText1);
        Email = (EditText) findViewById(R.id.editText2);
        Phone = (EditText) findViewById(R.id.editText3);
        Password = (EditText) findViewById(R.id.editText4);
        Conpassword = (EditText) findViewById(R.id.editText5);
        vbtnsignup = (Button) findViewById(R.id.signupbtn);

        vbtnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegister();
            }
        });
    }

    private void userRegister() {
        String sname = name.getText().toString().trim();
        String semail = Email.getText().toString().trim();
        String sphone = Phone.getText().toString().trim();
        String spassword = Password.getText().toString().trim();
        String sconpass = Conpassword.getText().toString().trim();

        //checking the validity of the email
        if(semail.isEmpty())
        {
            Email.setError("Enter an email address");
            Email.requestFocus();
            return;
        }
        else if (sphone.isEmpty()){
            Phone.setError("Enter phone number");
            Phone.requestFocus();
        }
        else if (spassword.isEmpty()){
            Password.setError("Enter Password");
            Password.requestFocus();

        }else if (sconpass.isEmpty()){
            Conpassword.setError("Enter Confirm Password");
            Conpassword.requestFocus();
        }
//        else if (sname.isEmpty()){
//            firstname.setError("Enter the Name");
//            firstname.requestFocus();
//        }
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(semail).matches())
        {
            Email.setError("Enter a valid email address");
            Email.requestFocus();
        }
        //checking the validity of the password
        else if(semail.isEmpty())
        {
            Email.setError("Enter a password");
            Email.requestFocus();
        }
        else if (spassword.length()<6){
            Password.setError("Enter Minimum six digits");
            Password.requestFocus();
        }
        else {
            //Write data
            if (semail.equals("") && spassword.equals("") && sconpass.equals("")) {
                Toast.makeText(getApplicationContext(), "Please enter the data", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit(); // edot method dea data write korbo
                editor.putString("usernameKey",sname);
                editor.putString("emailKey",semail);
                editor.putString("phoneKey",sphone);
                editor.putString("passwoedKey",spassword);
                editor.putString("conpassKey",sconpass);
                editor.commit();
                Email.setText("");
                Password.setText("");
                Conpassword.setText("");
                name.setText("");
                Phone.setText("");
//                Toast.makeText(getApplicationContext(), "Data is stored", Toast.LENGTH_SHORT).show();

                //Realtime Database
                // for realtime data base
                String key = databaseReference.push().getKey();
                RegisterDataModel registerDataModel = new RegisterDataModel(sname,semail,sphone,spassword,sconpass); // RegisterDataNodek ar object create if access data from modle class
                databaseReference.child(key).setValue(registerDataModel); // Database e register info rekhe dlam
//                Toast.makeText(getApplicationContext(),"Realtime Data store successfully",Toast.LENGTH_SHORT).show();
                name.setText("");
                Email.setText("");
                Phone.setText("");
                Password.setText("");
                Conpassword.setText("");
                // end Realtime data store //end

            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(semail, spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        finish();
                        Toast.makeText(getApplicationContext(),"Register is successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginForm.this,ProfileActivity.class);
                        startActivity(intent);
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException){
                            Toast.makeText(getApplicationContext(),"User is already Registered",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Error :"  + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
  }
}