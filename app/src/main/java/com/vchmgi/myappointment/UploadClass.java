package com.vchmgi.myappointment;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Arvindo on 17-01-2017.
 * Company KinG
 * email at support@towardtheinfinity.com
 */

public class UploadClass extends AppCompatActivity implements View.OnClickListener{
    EditText name,mail,phone,sub,msg;
    Button choose,upload,submit;
    private static final int SELECT_FILE = 1;
    private String fileName;
    TextView text;
    FileBody picBody;
    private InputStream inputStream;
    public static final String UPLOAD_URL = "http://vchmgi.com/appointment.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";
    private String Name,Mail,Phone,Sub,Msg, Date;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private File fileForUpload;

    //    private Uri filePath;
    private Uri filePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        name = (EditText)findViewById(R.id.fullname);
        mail = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phNo);
        sub = (EditText)findViewById(R.id.subject);
        msg = (EditText)findViewById(R.id.message);
        text = (TextView)findViewById(R.id.text);
        choose = (Button)findViewById(R.id.chooseFile);

        submit = (Button)findViewById(R.id.bookingSubmit);
        submit.setOnClickListener(this);

        choose.setOnClickListener(this);


//        time =  getIntent().getStringExtra("TIME");

//                Intent intent = new Intent();
////                String extStore = System.getenv("EXTERNAL_STORAGE");
////                String secStore = System.getenv("SECONDARY_STORAGE");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
////                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("*/*");
////                intent.addCategory(Intent.CATEGORY_OPENABLE);
////                Log.e("internal: " + extStore, "external: " + secStore);
////        startActivityForResult(Intent.createChooser(intent, "Select File"), 2);
//        startActivityForResult(intent, SELECT_FILE);
////                startActivityForResult(intent, MULTIPLE_SELECT);

    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_FILE);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            onSelectFromGalleryResult(data);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void onSelectFromGalleryResult(Intent data) {
        if (data != null) {
            Uri u = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(u);
                String s = String.valueOf(u.getPath());
                int l = s.length() - 1, i = 0;
                while ((':' != s.charAt(l) && '/' != s.charAt(l) && l > 0)) {
                    l--;
                }

                l++;
                char[] ch = new char[s.length() - l];

                while (l < s.length()) {
                    ch[i++] = s.charAt(l++);
                }
                fileName = String.valueOf(ch);
                final String outFileName;
                if(!fileName.contains(".")){
                    ContentResolver cR = getContentResolver();
                    MimeTypeMap mime = MimeTypeMap.getSingleton();
                    String type = mime.getExtensionFromMimeType(cR.getType(u));
                    outFileName = fileName + "." + type;
                }
                else{
                    outFileName = fileName;
                }

                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File mainDirectory = cw.getDir("temp_folder", Context.MODE_PRIVATE);

                File fileOutput = new File(mainDirectory, outFileName);

                assert inputStream != null;
                try (
                        // Create an input stream
                        BufferedInputStream input = new BufferedInputStream(inputStream);
                        // Create an output stream
                        BufferedOutputStream output = new BufferedOutputStream(new
                                FileOutputStream(fileOutput));
                ) {
                    // Continuously read a byte from input and write it to output
                    int r;
                    while ((r = input.read()) != -1) {
                        output.write((byte) r);
                    }
                    Log.e(String.valueOf(input), " " + String.valueOf(output));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                fileForUpload = new File(mainDirectory, outFileName);
                if(fileForUpload.exists()){
                    Log.e("file exist", String.valueOf(fileForUpload));
                    picBody = new FileBody(fileForUpload);

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView tv = (TextView) findViewById(R.id.file_upload);
                        tv.setText(outFileName);
                    }
                });


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chooseFile:
                showFileChooser();
                break;

//           case R.id.uploadFile:
//               new  UploadClass();
//               break;
            case R.id.bookingSubmit:
                new UploadImage().execute();
                break;
        }

    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//                filePath = data.getData();
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//    }}
//        public String getStringImage(Bitmap bmp){
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            byte[] imageBytes = baos.toByteArray();
//            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//            return encodedImage;
//        }

    //    public class myUpload extends AsyncTask<String,String,String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            /*
//            OkHttpClient client  = new OkHttpClient();
//            String url = "";
//            RequestBody body = new MultipartBody.Builder()
//                    .setType(MultipartBody.FORM)
//                    .addFormDataPart("","")
//                    .addFormDataPart("","")
//                    .addFormDataPart("","").addFormDataPart("","")
//                    .addFormDataPart("","")
//                    .addFormDataPart("file ",  RequestBody.create(MediaType.parse("* /*"),));
//            Request request = new Request.Builder().url(url).post(body).build();
//            try {
//                Response response = client.newCall(request).execute();
//                String res = response.body().string();
//                Log.e("RESPONSE FROM SERVER IS",""+res);
//            } catch (IOException e) {
//                Log.e("EXCEPTION",""+e);
//                e.printStackTrace();
//            }
//*/
//
//            return null;
//        }
//        @Override
//        protected void onPostExecute(String s) {
//            Log.e("ON POSTEXECUTE",""+s);
//            Toast.makeText(getApplicationContext(),"Request Submited",Toast.LENGTH_SHORT).show();
//            super.onPostExecute(s);
//        }
//    }
    class UploadImage extends AsyncTask<String,Void,String>{
        private String reqUrl = "http://vchmgi.com/appointment.php";
        private ProgressDialog dialog = new ProgressDialog(UploadClass.this);
        String response;
        ProgressDialog loading;
//        RequestHandler rh = new RequestHandler();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Name =  name.getText().toString();
            Mail = mail.getText().toString();
            Phone = phone.getText().toString();
            Sub = sub.getText().toString();
            Msg = msg.getText().toString();
            Date =  getIntent().getStringExtra("DATE");
//            Date = date.getText().toString();
            UploadClass.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                   loading = ProgressDialog.show(UploadClass.this, "Uploading Image", "Please wait...",true,true);
                }
            });
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (response!=null) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Files Uploaded..", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getApplicationContext(), "Server Failed...", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected String doInBackground(String... params) {
////            Bitmap bitmap = params[0];
////            String uploadImage = getStringImage(bitmap);
//            HashMap<String,String> data = new HashMap<>();
//////            data.put(UPLOAD_KEY, uploadImage);
////            data.put("fname",Name);
////            data.put("lname",Mail);
////            data.put("phoneno",Phone);
////            data.put("email",Sub);
////            data.put("address",Msg);
////            data.put("",Date);
//            data.put("fullname",Name);
//            data.put("email",Mail);
//            data.put("phoneno",Phone);
//            data.put("subj",Sub);
//            data.put("message",Msg);
//            data.put("Calendar",Date);
//
////            data.put("",fileForUpload);
//
//            return rh.sendPostRequest(UPLOAD_URL,data);
//        }
//    }
//
//    UploadImage ui = new UploadImage();
//    ui.execute();
            try {

                URL url = new URL(reqUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //connection.connect();

                String reqHead = "Accept:application/json";
                connection.setRequestMethod("POST");
                connection.setRequestProperty("connection","Keep-Alive"+reqHead);
                connection.setDoOutput(true);
                //Header header = new Header();

                MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
//                byte[] data = baos.toByteArray();
//                bab = new ByteArrayBody(data, "abc.jpg");
                entity.addPart("fullname",new StringBody(Name));
                entity.addPart("email",new StringBody(Mail));
                entity.addPart("calendar",new StringBody(Date));
                entity.addPart("phoneno",new StringBody(Phone));
                entity.addPart("subj",new StringBody(Sub));
                entity.addPart("message",new StringBody(Msg));
                entity.addPart("document",picBody);
                connection.addRequestProperty("content-length",entity.getContentLength()+"");
                connection.addRequestProperty(entity.getContentType().getName(),entity.getContentType().getValue());


                OutputStream os = new BufferedOutputStream(connection.getOutputStream());

//                OutputStream os = connection.getOutputStream();
                entity.writeTo(connection.getOutputStream());
//                os.close();
                connection.connect();

                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    return readStream(connection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private String readStream(InputStream inputStream) {

            BufferedReader reader;
            StringBuilder builder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            try {
                while ((line = reader.readLine())!=null){
                    builder.append(line);
                }
                response = builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }
}
