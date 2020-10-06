package com.sunny.bsdproject1;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    CardView vChromeCard,  vFbCard,vYoutubeCard, vMessengerCard, vWhatsappCard, vImoCard, vInstaCard,vSkypeCard, vLinkedCard ,vTwitterCard, vSnapCard,
            vTikTokCard , vPinterestCard, vMoreCard ;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    FirebaseAuth mAuth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        // for hide Actionbar
//        getSupportActionBar().hide(); // end
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

        vChromeCard =(CardView) findViewById(R.id.idCardViewChrome);
        vFbCard = (CardView) findViewById(R.id.idCardViewFb);
        vYoutubeCard = (CardView) findViewById(R.id.idCardViewYouTube);
        vMessengerCard = (CardView) findViewById(R.id.idCardViewMessenger);
        vWhatsappCard = (CardView) findViewById(R.id.idCardViewWhatsApp);
        vImoCard = (CardView) findViewById(R.id.idCardViewImo);
        vInstaCard = (CardView) findViewById(R.id.idCardViewInsta);
        vSkypeCard = (CardView) findViewById(R.id.idCardViewSkype);
        vLinkedCard = (CardView) findViewById(R.id.idCardViewLinkedin);
        vTwitterCard = (CardView) findViewById(R.id.idCardViewTwiter);
        vSnapCard = (CardView) findViewById(R.id.idCardViewSnap);
        vTikTokCard = (CardView) findViewById(R.id.idCardViewTikTok);
        vPinterestCard = (CardView) findViewById(R.id.idCardViewPinterest);
        vMoreCard = (CardView) findViewById(R.id.idCardViewMore);



        vChromeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,ChromeActivity.class);
                startActivity(intent);
            }
        });
        vFbCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,FacebookActivity.class);
                startActivity(intent);
            }
        });
        vMessengerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,MessengerActivity.class);
                startActivity(intent);
            }
        });
        vYoutubeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,YoutubeActivity.class);
                startActivity(intent);
            }
        });

        vWhatsappCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,WhatsappActivity.class);
                startActivity(intent);
            }
        });
        vImoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,ImoActivity.class);
                startActivity(intent);
            }
        });
        vInstaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,InstaActivity.class);
                startActivity(intent);
            }
        });
        vSkypeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,SkypeActivity.class);
                startActivity(intent);
            }
        });
        vLinkedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,LinkedinActivity.class);
                startActivity(intent);
            }
        });
        vTwitterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,TwitterActivity.class);
                startActivity(intent);
            }
        });

        vSnapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,SnapchatActivity.class);
                startActivity(intent);
            }
        });
        vTikTokCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,TikTokActivity.class);
                startActivity(intent);
            }
        });
        vPinterestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,PinterestActivity.class);
                startActivity(intent);
            }
        });
        vTikTokCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,TikTokActivity.class);
                startActivity(intent);
            }
        });

    }

    // working Menu bar
//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== R.id.idsignout){
//            FirebaseAuth.getInstance().signOut();
//            finish();
            sharedPreferences = getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
            editor = sharedPreferences.edit();
            sharedPreferences.edit().clear().commit();
//                editor.putString("EMAIL_ID",lemail);
//                editor.putString("PASSWORD",lpassword);
            editor.commit(); // end sharedpreference
            moveTaskToBack(true);
            Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.idchangepassword){
            finish();
            Intent i = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.idDeactivatemenu){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                dialog.setMessage("Deactivating..., please wait !!!");
                dialog.show();
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Account Deactivated", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Account couldn't be Deactivated", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });
            }
        }
        return super.onOptionsItemSelected(item);
    } // end menu work

    // For App Exit or Not Dialog

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder; // create variable
        alertDialogBuilder = new AlertDialog.Builder(ProfileActivity.this); // create Object

        // set properties
        alertDialogBuilder.setIcon(R.drawable.warning);
        alertDialogBuilder.setTitle(R.string.exit_title);
        alertDialogBuilder.setMessage(R.string.exit_text);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    } // end exit Dialog

}
