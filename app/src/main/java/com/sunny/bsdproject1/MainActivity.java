package com.sunny.bsdproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {


    //    for Sharedpreference
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    EditText loginEmail, loginPassword;
    ImageButton loginbtn;
    TextView vsignupbtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // for hide Actionbar
        getSupportActionBar().hide(); // end
        setContentView(R.layout.activity_main);
        this.setTitle("Log in Page");

        final SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        final Boolean isloggedin=sharedPreferences.getBoolean("ISLOGGEDIN",false);
        if(isloggedin)
        {
            Intent main = new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(main);
        }

        progressBar =findViewById(R.id.loginprogressBar);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        loginEmail =(EditText) findViewById(R.id.UserId);
        loginPassword =(EditText) findViewById(R.id.PasswordId);
        vsignupbtn=(TextView) findViewById(R.id.signupbtn);
        loginbtn =(ImageButton)findViewById(R.id.btnlogin);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        vsignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginForm.class);
                startActivity(intent);
            }
        });
    }


    private void userLogin() {
        // mail , password check already in or not
        String lemail = loginEmail.getText().toString().trim();
        String lpassword = loginPassword.getText().toString().trim();

        sharedPreferences = getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("EMAIL_ID",lemail);
        editor.putString("PASSWORD",lpassword);

        editor.putBoolean("ISLOGGEDIN",true);
        editor.commit(); // end sharedpreference

        //checking the validity of the email
        if(lemail.isEmpty())
        {
            loginEmail.setError("Enter an email address");
            loginEmail.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(lemail).matches())
        {
            loginEmail.setError("Enter a valid email address");
            loginEmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(lpassword.isEmpty())
        {
            loginPassword.setError("Enter a password");
            loginPassword.requestFocus();
            return;
        }
        if (lpassword.length()<6){
            loginPassword.setError("Enter Minimum six digits");
            loginPassword.requestFocus();
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(lemail, lpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
               if (task.isSuccessful()){
                   Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                   startActivity(intent);
               }
               else {
                   Toast.makeText(getApplicationContext()," 'Log in is unsuccessful' ",Toast.LENGTH_LONG).show();
               }
            }
        });
        loginEmail.setText("");
        loginPassword.setText("");
    }
//// For App Exit or Not Dialog
//
//    @Override
//    public void onBackPressed() {
//        AlertDialog.Builder alertDialogBuilder; // create variable
//        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this); // create Object
//
//        // set properties
//        alertDialogBuilder.setIcon(R.drawable.warning);
//        alertDialogBuilder.setTitle(R.string.exit_title);
//        alertDialogBuilder.setMessage(R.string.exit_text);
//        alertDialogBuilder.setCancelable(false);
//
//        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                finish();
//            }
//        });
//        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.cancel();
//            }
//        });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    } // end exit Dialog

}
