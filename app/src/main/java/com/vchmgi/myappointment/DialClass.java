package com.vchmgi.myappointment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Arvindo on 14-02-2017.
 * Company KinG
 * email at support@towardtheinfinity.com
 */

public class DialClass extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dial_layout);

        /** Defining an onclick listener */
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /** Get Telephone number Object*/
                EditText telNo = (EditText) findViewById(R.id.telNo);

                /** Get Telephone number String **/
                String strTelNo = getIntent().getStringExtra("PHONE_NUMBER");

                /** Creating an intent which invokes an activity whose action name is ACTION_CALL */
                Intent intent = new Intent("android.intent.action.CALL");
                /** Creating a uri object to store the telephone number */
                Uri data = Uri.parse("tel:"+ strTelNo );

                /** Setting intent data */
                intent.setData(data);

                /** Starting the caller activity by the implicit intent */
                startActivity(intent);

            }
        };

        /** Getting reference to button of main.xml */
        Button btn = (Button) findViewById(R.id.btnCall);

        /** Setting the event listener for the button */
        btn.setOnClickListener(listener);


    }


    @Override
    public void onClick(View v) {

    }
}
