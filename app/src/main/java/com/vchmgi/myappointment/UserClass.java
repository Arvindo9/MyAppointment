package com.vchmgi.myappointment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Naresh on 18-01-2017.
 */

public class UserClass extends AppCompatActivity {

    String  myJSON;
    String url = "http://vchmgi.com/json.php";
    private static final String TAG_RESULTS="requests";
    private static final String TAG_DATE="calendar";
    private static final String TAG_FULL = "fullname";
    private static final String TAG_EMAIL = "subj";
    private static final String TAG_PHONE ="phoneno";
    private static final String TAG_SUB ="email";
    private static final String TAG_MSG ="message";
    String dateId,fullNameId,emailId,phoneNoId,subId,messageId;
    JSONArray peoples = null;

    private ArrayList<RowItem> dataList;

    //    ArrayList<String> items = new ArrayList<>();
//    ArrayList<String> sub_items = new ArrayList<>();
//    ArrayList<Integer> cross_image = new ArrayList<>();
//    ArrayList<Integer> next_image = new ArrayList<>();
//    ArrayList<RowItem> pfDatas = new ArrayList<>();
    Toolbar toolbar1;
    ListView lv;
    //    String[] items = {"Suchi","Padma","Laxmi","krishnaveni","Vasanthi","Divya","Manisha","Naresh","Adithya","Areef","Aravindh","Sushanth"};
//    String[] sub_items =  {"Su","Pad","Lax","krishna","Vasanth","Div","Manish","Nare","Adith","Are","Aravi","Sucha"};
//    Integer[]  cross_image = {R.drawable.ignore,R.drawable.ignore,R.drawable.ignore,R.drawable.ignore,R.drawable.ignore,R.drawable.ignore,
//    R.drawable.ignore,R.drawable.ignore,R.drawable.ignore,R.drawable.ignore,R.drawable.ignore,R.drawable.ignore};
//    Integer[]  next_image = {R.drawable.transfer,R.drawable.transfer,R.drawable.transfer,R.drawable.transfer,R.drawable.transfer,R.drawable.transfer,
//            R.drawable.transfer,R.drawable.transfer,R.drawable.transfer,R.drawable.transfer,R.drawable.transfer,R.drawable.transfer};
    SimpleListViewAdapter simpleListViewAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);
//        intialised();
        toolbar1 = (Toolbar)findViewById(R.id.logouttoolbar);
        setSupportActionBar(toolbar1);
        lv = (ListView)findViewById(R.id.list);
//        for (int i =0;  i<items.length;i++){
////            RowItem row = new RowItem(next_image[i],items[i],cross_image[i]);
//            data.add(row);
//        }

        dataList = new ArrayList<>();
        simpleListViewAdapter = new SimpleListViewAdapter(this, R.layout.listview_items, dataList, this);
        lv.setAdapter(simpleListViewAdapter);



//        simpleListViewAdapter.notifyDataSetChanged();
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent replyClass = new Intent(UserClass.this,ReplyClass.class);
//                startActivity(replyClass);
//            }
//        });

        getJSON(url);

    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                dateId = c.getString(TAG_DATE);
                fullNameId= c.getString(TAG_FULL);
                emailId = c.getString(TAG_EMAIL);
                phoneNoId = c.getString(TAG_PHONE);
                subId = c.getString(TAG_SUB);
                messageId = c.getString(TAG_MSG);

                dataList.add(new RowItem(dateId,fullNameId, emailId, phoneNoId, subId, messageId));

//                HashMap<String,String> persons = new HashMap<String,String>();
//
//                persons.put(TAG_FULL,fullname);
//                persons.put(TAG_EMAIL,email);
//                persons.put(TAG_PHONE,phoneno);
//                persons.put(TAG_SUB,subject);
//                persons.put(TAG_MSG,message);
//
//                personList.add(persons);
            }

            simpleListViewAdapter.notifyDataSetChanged();


//            ListAdapter adapter = new SimpleAdapter(
//                    MainActivity.this, personList, R.layout.list_item,
//                    new String[]{TAG_ID,TAG_NAME,TAG_ADD},
//                    new int[]{R.id.id, R.id.name, R.id.address}
//            );
//            SimpleListViewAdapter adapter = new SimpleListViewAdapter(this,R.layout.listview_items);
//            lv.setAdapter(adapter);
//            RowItem item = new RowItem(fullNameId,emailId,phoneNoId,subId,messageId);
//            simpleListViewAdapter.add(item);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Logout_menu:
                SharedPreferences preferences = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent login = new Intent(UserClass.this,MainActivity.class);
                startActivity(login);
//              case R.id.Reschedule_menu:
//                  Intent rescheduleClass = new Intent(UserClass.this,RescheduleClass.class);
//                  startActivity(rescheduleClass);
        }

        return super.onOptionsItemSelected(item);
    }
    /*
    void intialised(){
        pfDatas.add(new RowItem(R.drawable.ignore,"Suchi" ,R.drawable.transfer,"Su"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Chinnu" ,R.drawable.transfer,"Ch"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Naresh" ,R.drawable.transfer,"Nar"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Padma" ,R.drawable.transfer,"Pad"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Adilaxmi" ,R.drawable.transfer,"Adi"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Vasanthi" ,R.drawable.transfer,"Vas"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Aravindh" ,R.drawable.transfer,"Ara"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Sushanth" ,R.drawable.transfer,"Sush"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Manisha" ,R.drawable.transfer,"Mani"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Krshnaveni" ,R.drawable.transfer,"Krish"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Priyanka" ,R.drawable.transfer,"Priy"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Alekya" ,R.drawable.transfer,"Ale"));
        pfDatas.add(new RowItem(R.drawable.ignore,"Malli" ,R.drawable.transfer,"Mal"));
    }
    */
    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UserClass.this, "Please Wait...",null,true,true);
            }

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];
                String finalJson = null;

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    finalJson = sb.toString().trim();
                    Log.d("Finaljson",finalJson);

                }catch(Exception e){
                }
                finally {

                }
                return finalJson;
            }

            @Override
            protected void onPostExecute(String finalJson) {
                super.onPostExecute(finalJson);
                loading.dismiss();
                myJSON = finalJson;
                showList();
//            textViewJSON.setText(s);
            }
        }

        GetJSON gj = new GetJSON();
        gj.execute(url);
    }




}
