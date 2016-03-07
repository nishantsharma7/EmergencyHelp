package com.example.nishu459.helperone;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {
    TextView textView;
    Integer count =0;
    String URL = "http://emergancydialer.xp3.biz/tempo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textview);
        textView.setText("0");
        callAsynchronousTask();
    }

    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            getData();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 50000 ms
    }

    protected void sendSMSMessage(String phoneNo,String message) {
        Log.i("Send SMS", "");
        String phoneno = phoneNo;
        String messagetosend = message;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            count++;
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
            textView.setText(count+"");
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void getData()
    {
        StringRequest stringReq = new StringRequest(Request.Method.POST,
                URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String json) {
                        Log.d("response", json);
                        JSONObject res = null;
                        try {
                            res = new JSONObject(json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        postExecute(res);
                    }
                },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(getApplicationContext(),"Error in Connectiion",1000).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringReq, "Request_fetch");
    }

    public void cancelRequest(){
        AppController.getInstance().getRequestQueue().cancelAll("Request_fetch");
    }

    public void postExecute(JSONObject res){
        try {
            JSONArray request_array = res.getJSONArray("request");
            for(int i=0;i<request_array.length();i++)
            {
                JSONObject obj = request_array.getJSONObject(i);
                String message = obj.getString("name") + " needs help at location "+obj.getString("address")+". Contact him at "+obj.getString("phone")+".";
                String mob = obj.getString("need");
                sendSMSMessage(mob,message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
