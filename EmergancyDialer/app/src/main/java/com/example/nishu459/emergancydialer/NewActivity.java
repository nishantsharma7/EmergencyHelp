package com.example.nishu459.emergancydialer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by nishu459 on 3/4/2016.
 */
public class NewActivity extends Activity {
    private String URL_REGISTER="http://emergancydialer.xp3.biz/create_user.php";
    private ProgressDialog pDialog;
    private Button submit;
    private EditText name,mobile;
    private GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        submit = (Button)findViewById(R.id.submit);
        name=(EditText)findViewById(R.id.name);
        mobile=(EditText)findViewById(R.id.phone);
        final Bundle extras = getIntent().getExtras();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(NewActivity.this);
                if (gps.canGetLocation()) {
                    String na, mob, need = null,ne;
                    na = name.getText().toString();
                    mob = mobile.getText().toString();
                    ne = extras.getString("need");
                    switch(ne){
                        case 1+"":
                            need="9097839606";
                            break;
                        case 2+"":
                            need="9097839606";
                            break;
                        case 3+"":
                            need="9097839606";
                            break;
                        case 4+"":
                            need="9097839606";
                            break;
                        case 5+"":
                            need="9097839606";
                            break;
                        case 6+"":
                            need="9097839606";
                            break;
                        case 7+"":
                            need="9097839606";
                            break;
                        case 8+"":
                            need="9097839606";
                            break;
                        case 9+"":
                            need="9097839606";
                            break;
                        case 10+"":
                            need="9097839606";
                            break;
                        case 11+"":
                            need="9097839606";
                            break;
                        case 12+"":
                            need="9097839606";
                            break;
                        default :
                    }
                    if (!na.isEmpty() && !mob.isEmpty() && !need.isEmpty()) {
                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongtitude();
                        String Add = "";
                        Geocoder geocoder;
                        List<Address> addresses = null;
                        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                        try {
                            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            String city = addresses.get(0).getLocality();
                            String state = addresses.get(0).getAdminArea();
                            String country = addresses.get(0).getCountryName();
                            Add = address + ", " + city + ", " + state + ", " + country;

                            Toast.makeText(getApplicationContext(), "Your Location: " + Add, Toast.LENGTH_LONG).show();
                            senddata(na, mob, Add,need);
                            // Toast.makeText(getApplicationContext(), "Your Location: " + latitude + " " + longitude, Toast.LENGTH_LONG).show();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter Your Credentials...!!!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    gps.showSettingAlert();
                }
            }
        });
    }

    private void senddata(final String name, final String phone,final String Add,final String need) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setTitle("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                //Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();
                Log.d("Volley erro2", "error1");
                try {

                    JSONObject jObj = new JSONObject(response);
                        boolean suc = jObj.getBoolean("success");

                        Log.d("Volley erro1", suc+"");

                        if (suc) {

                            Toast.makeText(getApplicationContext(), "Your Request is successfully submitted...!!!", Toast.LENGTH_LONG).show();


                        } else {

                            // Error occurred in registration. Get the error
                            // message
                            String errorMsg = jObj.getString("message");
                            Toast.makeText(getApplicationContext(),
                                    errorMsg, Toast.LENGTH_LONG).show();
                        }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley erro","error");
               // Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("mobile", phone);
                params.put("address", Add);
                params.put("need", need);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
