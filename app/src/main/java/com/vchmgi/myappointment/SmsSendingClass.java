package com.vchmgi.myappointment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Naresh on 19-01-2017.
 */

public class SmsSendingClass extends AppCompatActivity {
    EditText text,number;
    Button send;
    String message,phoneNumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);
        text = (EditText)findViewById(R.id.Text);
        number = (EditText)findViewById(R.id.phnno);
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 message = text.getText().toString();
                 phoneNumber = number.getText().toString();
                if (phoneNumber.length()>0&&message.length()>0){
                    sendMessage(phoneNumber,message);
                }else{
                    Toast.makeText(getApplicationContext(),"Please fill both fields",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void sendMessage(String phoneNumber,String message ){
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber,null,message,null,null);
            Toast.makeText(getApplication(),"SMS SENT",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Log.d("Exception","exception is"+e);
            Toast.makeText(getApplicationContext(),"Failed,Please Try Again",Toast.LENGTH_LONG).show();
        }

    }


}

