package com.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.vchmgi.myappointment.R;
import com.vchmgi.myappointment.ReplyClass;

/**
 * Created by Arvindo on 11-02-2017.
 * Company KinG
 * email at support@towardtheinfinity.com
 */

public class ReplyMsg extends DialogFragment {


    private ReplyClass activity;
    onSubmitListener mListener;
    ImageView email_btn, sms_btn;

    public void setActivity(ReplyClass activity) {
        this.activity = activity;
    }

    public interface onSubmitListener {
        void setOnSubmitListener(String arg);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);











        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.reply_layout);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        email_btn = (ImageView) dialog.findViewById(R.id.send_email);
        sms_btn = (ImageView) dialog.findViewById(R.id.send_sms);

        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Toast.makeText(getContext(), "email", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });

        sms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Toast.makeText(getContext(), "ses", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });

        return dialog;
    }
    }

