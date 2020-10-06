package com.sunny.bsdproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText editText;
    Button savechange;
    FirebaseAuth mAuth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        this.setTitle("Password change");

        editText = findViewById(R.id.idChangePasswordLayout);
        savechange = findViewById(R.id.idChangeBtn);
        dialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


    }

    // for Password change
    public void change(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            dialog.setMessage("Changing password, please wait !!!");
            dialog.show();

            user.updatePassword(editText.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Your password has been changed", Toast.LENGTH_LONG).show();
                                mAuth.signOut();
                                finish();
                                Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Password could not be changed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    //For Deactivate
//    public void deactivate(View view) {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            dialog.setMessage("Deactivating..., please wait !!!");
//            dialog.show();
//            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(getApplicationContext(), "Account Deactivated", Toast.LENGTH_LONG).show();
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "Account couldn't be Deactivated", Toast.LENGTH_LONG).show();
//                    }
//                }
//            });
//        }
//    }


}
