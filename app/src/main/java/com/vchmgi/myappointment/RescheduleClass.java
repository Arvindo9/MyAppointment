package com.vchmgi.myappointment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Arvindo on 19-01-2017.
 * Company KinG
 * email at support@towardtheinfinity.com
 */

public class RescheduleClass extends AppCompatActivity implements View.OnClickListener{

    private int mYear, mMonth, mDay;
    private TextView date_et, time_et;
    private int mHour, mMinute;
    private String emailAdd, phoneNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reschedule_layout);

        emailAdd = getIntent().getStringExtra("email_a");
        phoneNo = getIntent().getStringExtra("phone_a");

        date_et = (TextView) findViewById(R.id.date_et);
        time_et = (TextView) findViewById(R.id.time_set_ev);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.date_re:
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date_et.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;

            case R.id.time_re:
                // Get Current Time
                final Calendar c1 = Calendar.getInstance();
                mHour = c1.get(Calendar.HOUR_OF_DAY);
                mMinute = c1.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String AM_PM ;
                                if(hourOfDay < 12) {
                                    AM_PM = "AM";
                                } else {
                                    if(hourOfDay != 12){
                                        hourOfDay -= 12;
                                    }
                                    AM_PM = "PM";
                                }

                                time_et.setText(hourOfDay + ":" + minute + " " + AM_PM);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;

            case R.id.email_re:
                String msg = "  Dear sir/madam,\n\n" + "Date : " + mDay + "/" + mMonth + "/" + mYear + "\n" +
                        "Time : " + mHour + ":" + mMinute + "\n";

                Log.i("Send email", "");
                String[] TO = {emailAdd};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "re-scheduling date");
                emailIntent.putExtra(Intent.EXTRA_TEXT, msg);

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Finished sending..", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.sms_re:
                String msg1 = "Dear sir/madam,\n" + "Date : " + mDay + "/" + mMonth + "/" + mYear + "\n" +
                        "Time : " + mHour + ":" + mMinute + "\n";

                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                i.putExtra("address", phoneNo);
                i.putExtra("sms_body", msg1);
                i.setType("vnd.android-dir/mms-sms");
                startActivity(i);
                break;
        }
    }
}
