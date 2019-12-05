package com.vchmgi.myappointment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class MainActivity extends AppCompatActivity {
    Button submit;
    MaterialCalendarView cv;
    ListView lv;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        lv = (ListView)findViewById(R.id.list);
       toolbar = (Toolbar)findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        cv = (MaterialCalendarView)findViewById(R.id.calendarView);
        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,UploadClass.class);
                startActivity(i);
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
                Intent loginClass = new Intent(MainActivity.this,Login.class);
                startActivity(loginClass);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
