package com.vchmgi.myappointment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.king.methods.HandlingCalender;

/**
 * Created by Arvindo on 19-01-2017.
 * Company KinG
 * email at support@towardtheinfinity.com
 */

public class CalendarMainClass extends AppCompatActivity {

//    ListView lv;
    Toolbar toolbar;

    private HandlingCalender calender;
    private int currentYear, currentMonth;
    private Button submit;
    private String dateChosen = "", timeChosen;
    private int row_selected, col_selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
//        lv = (ListView)findViewById(R.id.list);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(null);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        calender = new HandlingCalender();
        currentYear = calender.getCurrentYear();
        currentMonth = calender.getCurrentMonth();
        setCurrentMonth(calender.currentMonth());

        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dateChosen.equals("")) {
                    Intent i = new Intent(CalendarMainClass.this, UploadClass.class);
                    i.putExtra("DATE", dateChosen);
                    i.putExtra("TIME", timeChosen);
                    startActivity(i);
                }
                else{
                    Toast.makeText(CalendarMainClass.this, "first select date",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.Login_menu:
                Intent loginClass = new Intent(CalendarMainClass.this,Login.class);
                startActivity(loginClass);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        String s = "row_" + row_selected + "_" + col_selected;
        int resID = getResources().getIdentifier(s, "id", getPackageName());
        Button bt = (Button) findViewById(resID);
        bt.setBackgroundColor(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String s = "row_" + row_selected + "_" + col_selected;
        int resID = getResources().getIdentifier(s, "id", getPackageName());
        Button bt = (Button) findViewById(resID);
        bt.setBackgroundColor(0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        row_selected = -1;
//        col_selected = -1;
        currentYear = calender.getCurrentYear();
        currentMonth = calender.getCurrentMonth();
        setCurrentMonth(calender.currentMonth());
    }

    private void setCurrentMonth(final int[][] monthArray){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = (TextView) findViewById(R.id.month_name);
                tv.setText(calender.getMonthName(currentMonth) + " " + currentYear);
                for(int i = 0; i < monthArray.length -1; i++){
                    for(int j =0; j < monthArray[i].length; j++){
                        String s = "row_" + i + "_" + j;
                        int resID = getResources().getIdentifier(s, "id", getPackageName());
                        Button bt = (Button) findViewById(resID);

                        if(monthArray[i][j] != 0){
                            bt.setText(String.valueOf(monthArray[i][j]));
    //                        bt.setTextColor(Color.parseColor("#040404"));
                        }
                        else{
                            bt.setText("");
                        }
                    }
                }
            }
        });
    }

    private void onArrowClick(boolean arrow) {
        if(arrow){
            String s = "row_" + row_selected + "_" + col_selected;
            int resID = getResources().getIdentifier(s, "id", getPackageName());
            Button bt = (Button) findViewById(resID);
            bt.setBackgroundColor(0);
            if(currentMonth == 0){
                currentMonth = 11;
                currentYear--;
            }
            else{
                currentMonth--;
            }
            setCurrentMonth(calender.thisMonth(currentYear, currentMonth));
        }
        else {
            String s = "row_" + row_selected + "_" + col_selected;
            int resID = getResources().getIdentifier(s, "id", getPackageName());
            Button bt = (Button) findViewById(resID);
            bt.setBackgroundColor(0);

            if(currentMonth == 11){
                currentMonth = 0;
                currentYear++;
            }
            else{
                currentMonth++;
            }
            setCurrentMonth(calender.thisMonth(currentYear, currentMonth));
        }

    }

    private void dateClicked(final int i, final int j){
        final int[][] monthArray = calender.thisMonth(currentYear, currentMonth);
        dateChosen = monthArray[i][j] + "/" + (currentMonth + 1) + "/" + currentYear;
        timeChosen = calender.getCurrentTime();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(monthArray[i][j] != 0) {
                    if(row_selected > -1 && col_selected > -1) {
                        String s = "row_" + row_selected + "_" + col_selected;
                        int resID = getResources().getIdentifier(s, "id", getPackageName());
                        Button bt = (Button) findViewById(resID);
                        bt.setBackgroundColor(0);
                    }
                    String s = "row_" + i + "_" + j;
                    int resID = getResources().getIdentifier(s, "id", getPackageName());
                    Button bt = (Button) findViewById(resID);
                    //bt.setBackgroundColor(Color.parseColor("4DD0E1"));
                    bt.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                    row_selected = i;
                    col_selected = j;

                    Toast.makeText(CalendarMainClass.this, "Date:" + monthArray[i][j] + "/" +
                            (currentMonth + 1) + "/" + currentYear + "\nTime:" + calender.getCurrentTime(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()){

            case R.id.row_0_0:
                dateClicked(0, 0);
                break;
            case R.id.row_0_1:
                dateClicked(0, 1);
                break;
            case R.id.row_0_2:
                dateClicked(0, 2);
                break;
            case R.id.row_0_3:
                dateClicked(0, 3);
                break;
            case R.id.row_0_4:
                dateClicked(0, 4);
                break;
            case R.id.row_0_5:
                dateClicked(0, 5);
                break;
            case R.id.row_0_6:
                dateClicked(0, 6);
                break;

            case R.id.row_1_0:
                dateClicked(1, 0);
                break;
            case R.id.row_1_1:
                dateClicked(1, 1);
                break;
            case R.id.row_1_2:
                dateClicked(1, 2);
                break;
            case R.id.row_1_3:
                dateClicked(1, 3);
                break;
            case R.id.row_1_4:
                dateClicked(1, 4);
                break;
            case R.id.row_1_5:
                dateClicked(1, 5);
                break;
            case R.id.row_1_6:
                dateClicked(1, 6);
                break;


            case R.id.row_2_0:
                dateClicked(2, 0);
                break;
            case R.id.row_2_1:
                dateClicked(2, 1);
                break;
            case R.id.row_2_2:
                dateClicked(2, 2);
                break;
            case R.id.row_2_3:
                dateClicked(2, 3);
                break;
            case R.id.row_2_4:
                dateClicked(2, 4);
                break;
            case R.id.row_2_5:
                dateClicked(2, 5);
                break;
            case R.id.row_2_6:
                dateClicked(2, 6);
                break;

            case R.id.row_3_0:
                dateClicked(3, 0);
                break;
            case R.id.row_3_1:
                dateClicked(3, 1);
                break;
            case R.id.row_3_2:
                dateClicked(3, 2);
                break;
            case R.id.row_3_3:
                dateClicked(3, 3);
                break;
            case R.id.row_3_4:
                dateClicked(3, 4);
                break;
            case R.id.row_3_5:
                dateClicked(3, 5);
                break;
            case R.id.row_3_6:
                dateClicked(3, 6);
                break;


            case R.id.row_4_0:
                dateClicked(4, 0);
                break;
            case R.id.row_4_1:
                dateClicked(4, 1);
                break;
            case R.id.row_4_2:
                dateClicked(4, 2);
                break;
            case R.id.row_4_3:
                dateClicked(4, 3);
                break;
            case R.id.row_4_4:
                dateClicked(4, 4);
                break;
            case R.id.row_4_5:
                dateClicked(4, 5);
                break;
            case R.id.row_4_6:
                dateClicked(4, 6);
                break;

            case R.id.row_5_0:
                dateClicked(5, 0);
                break;
            case R.id.row_5_1:
                dateClicked(5, 1);
                break;
            case R.id.row_5_2:
                dateClicked(5, 2);
                break;
            case R.id.row_5_3:
                dateClicked(5, 3);
                break;
            case R.id.row_5_4:
                dateClicked(5, 4);
                break;
            case R.id.row_5_5:
                dateClicked(5, 5);
                break;
            case R.id.row_5_6:
                dateClicked(5, 6);
                break;

            case R.id.weak_day_1:
                break;
            case R.id.weak_day_2:
                break;
            case R.id.weak_day_3:
                break;
            case R.id.weak_day_4:
                break;
            case R.id.weak_day_5:
                break;
            case R.id.weak_day_6:
                break;
            case R.id.weak_day_7:
                break;

            case R.id.arrow_left:
                onArrowClick(true);
                break;
            case R.id.arrow_right:
                onArrowClick(false);
                break;
        }
    }


}
