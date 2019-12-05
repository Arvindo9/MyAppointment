package com.vchmgi.myappointment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Arvindo on 19-01-2017.
 * Company KinG
 * email at support@towardtheinfinity.com
 */

public class ReplyClass extends AppCompatActivity implements View.OnClickListener {
    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2, fab3;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    private TextView tv3, tv2, tv4;
    private String msg_all, msgSub, senderEmail, senderPhone;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reply_layout);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        tv3 = (TextView) findViewById(R.id.tv_3);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv4 = (TextView) findViewById(R.id.tv_4);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);

        TextView date_tv = (TextView) findViewById(R.id.appointmentDate);
        TextView fullName_tv = (TextView) findViewById(R.id.fullnameText);
        TextView email_tv = (TextView) findViewById(R.id.emailText);
        TextView phone_tv = (TextView) findViewById(R.id.phoneText);
        TextView sub_tv = (TextView) findViewById(R.id.subText);
        TextView msg_tv = (TextView) findViewById(R.id.msgText);
        TextView fileName_tv = (TextView) findViewById(R.id.filename);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setOverflowIcon(getDrawable(R.drawable.dial_icon));
        setSupportActionBar(toolbar);

        String fn = getIntent().getStringExtra("fName");
        senderEmail = getIntent().getStringExtra("email");
        senderPhone = getIntent().getStringExtra("phone");
        msgSub = getIntent().getStringExtra("sub");
        String msg = getIntent().getStringExtra("msg");

//        date_tv.setText(getIntent().getStringExtra("date"));
        fullName_tv.setText(fn);
        email_tv.setText(senderEmail);
        phone_tv.setText(senderPhone);
        sub_tv.setText(msgSub);
        msg_tv.setText(msg);
//        fullName_tv.setText(getIntent().getStringExtra("fileName"));

        msg_all = "Name : " + fn + "\n" + "Email : " + senderEmail + "\n" + "Phone no. : " + senderPhone + "\n"
                + "Subject : " + msgSub + "\n" + "Message : " + msg;



        /*

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFabOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }

            }
        });
        */
    }
//        sp = (Spinner)findViewById(R.id.replySp);
//        List<String> list = new ArrayList<String>();
//        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
//                String selectedItem = parent.getItemAtPosition(i).toString();
//                if (selectedItem.equals("Email")){
//                    sendEmail();
//                }else if (selectedItem.equals("Sms")){
//                     Intent sending_message = new Intent(ReplyClass.this,SmsSendingClass.class);
//                    startActivity(sending_message);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//   }

    protected void sendEmail(String type) {
        Log.i("Send email", "");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        if(type.equals("reply")) {
            String[] TO = {senderEmail};
            String[] CC = {""};

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, senderEmail);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "on response to your mail");
//            emailIntent.putExtra(Intent.EXTRA_TEXT, msg_all);
        }
        else {
            String[] TO = {""};
            String[] CC = {""};

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, msgSub);
            emailIntent.putExtra(Intent.EXTRA_TEXT, msg_all);
        }

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending..", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    private void showFABMenu(){
        isFabOpen=true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
    }

    private void closeFABMenu(){
        isFabOpen=false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:

                animateFAB();
                break;
            case R.id.fab1:// onClick reply button
                try {
                    dialogMethodReply();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.fab2:// re-schedule
                Intent i = new Intent(ReplyClass.this, RescheduleClass.class);
                i.putExtra("email_a", senderEmail);
                i.putExtra("phone_a", senderPhone);
                startActivity(i);
                break;

            case R.id.fab3: //on forward button
                dialogMethodForward();
                break;
        }
    }



    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            tv2.startAnimation(fab_close);
            tv3.startAnimation(fab_close);
            tv4.startAnimation(fab_close);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            tv2.startAnimation(fab_open);
            tv3.startAnimation(fab_open);
            tv4.startAnimation(fab_open);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }

    void dialogMethodReply() throws Exception{
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setTitle(getResources().getString(R.string.dialog_t1));
        dialog.setContentView(R.layout.dialog_reply_btn);

        ImageView email = (ImageView) dialog.findViewById(R.id.send_email);
        ImageView sms = (ImageView) dialog.findViewById(R.id.send_sms);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail("reply");
                Toast.makeText(ReplyClass.this, "email", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                i.putExtra("address", senderPhone + ";");
//                i.putExtra("sms_body", msg_all);
                i.setType("vnd.android-dir/mms-sms");
                startActivity(i);
                Toast.makeText(ReplyClass.this, "sms", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void dialogMethodForward() {
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setTitle(getResources().getString(R.string.dialog_t1));
        dialog.setContentView(R.layout.dialog_reply_btn);

        ImageView email = (ImageView) dialog.findViewById(R.id.send_email);
        ImageView sms = (ImageView) dialog.findViewById(R.id.send_sms);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail("forward");
                Toast.makeText(ReplyClass.this, "email", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                i.putExtra("address", "5556; 5558; 5560");
                i.putExtra("sms_body", msg_all);
                i.setType("vnd.android-dir/mms-sms");
                startActivity(i);
                Toast.makeText(ReplyClass.this, "sms", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.calling_menu:
                Intent intent = new Intent("android.intent.action.CALL");
                Uri data = Uri.parse("tel:"+ senderPhone);
                intent.setData(data);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
