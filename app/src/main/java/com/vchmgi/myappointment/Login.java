package com.vchmgi.myappointment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Naresh on 17-01-2017.
 */

public class Login extends AppCompatActivity {
    EditText mailId,password;
    Button loginB;
    private static final String TAG = Login.class.getSimpleName();
    String mail,pass;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mailId = (EditText)findViewById(R.id.loginMail);
        password = (EditText)findViewById(R.id.loginPass);
        loginB = (Button)findViewById(R.id.loginButton);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = mailId.getText().toString();
                pass = password.getText().toString();
                if (mailId.length()==0 ||password.length()==0){
                    mailId.setError("Please Enter Your Email");
                    password.setError("Plase Enter Password");
                }else if (!Patterns.EMAIL_ADDRESS.matcher(mailId.getText().toString()).matches()){
                    mailId.setError("Please Enter Valid Email");
                }else{
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    editor = preferences.edit();
                    editor.putString("EMAILID",mail);
                    editor.putString("PASSWORD",pass);
                    editor.commit();
                    String str = preferences.getString("EMAILID","");
                    Log.d(TAG,"Preferences email"+str);
                    Intent userClass = new Intent(Login.this,UserClass.class);
                    startActivity(userClass);
                    mailId.setText("");
                    password.setText("");
                }

            }
        });

    }

}
