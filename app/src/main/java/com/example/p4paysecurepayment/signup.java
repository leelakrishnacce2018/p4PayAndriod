package com.example.p4paysecurepayment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    LinearLayout scr1,scr2;
    Button signup,verification;
    CheckBox termsconditions;
    EditText username,email,mobile,passw,confrim_password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    RequestQueue requestQueue;
    //String url ="https://mysterious-eyrie-12968.herokuapp.com/api/auth/signup";
    String url =Connection.URL+"auth/signup";

    AlertDialog.Builder builder;
    AlertDialog dialog;
    TextView msg,msg2;
    String statues;
    Button close;
    String regexStr = "^[0-9]*$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.moblie);
        passw = (EditText) findViewById(R.id.passwords);
        confrim_password=(EditText)findViewById(R.id.confrim_passwords);
        termsconditions =(CheckBox)findViewById(R.id.terms_conditions);
        scr1 = (LinearLayout) findViewById(R.id.scr1);
        scr2 = (LinearLayout) findViewById(R.id.scr2);
        signup = (Button) findViewById(R.id.bsingup);
        verification = (Button) findViewById(R.id.verified);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(termsconditions.isChecked()){

                    if(username.getText().length() == 0 && username.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Enter your full name",Toast.LENGTH_SHORT).show();
                    }else if(username.getText().length() < 6){
                        Toast.makeText(getApplicationContext(),"Full name length must be at least 6 character",Toast.LENGTH_SHORT).show();
                    }else{
                        if(email.getText().length() == 0 && email.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(),"Enter your email address",Toast.LENGTH_SHORT).show();
                        }else if(email.getText().toString().trim().matches(emailPattern)){
                            if(mobile.getText().length() ==0 && mobile.getText().toString().equals("")){
                                Toast.makeText(getApplicationContext(),"Enter your Mobile Number",Toast.LENGTH_SHORT).show();
                            }else if(mobile.getText().length() <10){
                                if(!mobile.getText().toString().trim().matches(regexStr)){
                                    Toast.makeText(getApplicationContext(),"Enter only Number",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Enter vaild Moblie Number",Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                if(passw.getText().length() == 0 && passw.getText().toString().equals("")){
                                    Toast.makeText(getApplicationContext(),"Enter your password",Toast.LENGTH_SHORT).show();
                                }else if(passw.getText().length() < 6){
                                    Toast.makeText(getApplicationContext(),"Password length must be at least 6 character",Toast.LENGTH_SHORT).show();
                                }else {
                                    if (confrim_password.getText().length() == 0 && confrim_password.getText().toString().equals("")){
                                        Toast.makeText(getApplicationContext(),"Enter your confrim Password",Toast.LENGTH_SHORT).show();
                                    }else if(confrim_password.getText().length()< 6){
                                        Toast.makeText(getApplicationContext(),"Password length must be least 6 character",Toast.LENGTH_SHORT).show();
                                    }else{
                                        if(passw.getText().toString().matches(confrim_password.getText().toString())){
                                            Map<String, String> postpara = new HashMap<String, String>();
                                            String uname = username.getText().toString().trim();
                                            String emm = email.getText().toString().trim();
                                            String pm = mobile.getText().toString().trim();
                                            String pss = passw.getText().toString().trim();
                                            postpara.put("name", uname);
                                            postpara.put("email", emm);
                                            postpara.put("mobileNumber", pm);
                                            postpara.put("password", pss);
                                            Log.i("sss", postpara.toString());
                                            requestQueue = Volley.newRequestQueue(getApplicationContext());
                                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postpara),
                                                    new Response.Listener<JSONObject>() {
                                                        @Override
                                                        public void onResponse(JSONObject response) {
                                                            try {
                                                                final String ss ;
                                                                JSONObject jsonObject = new JSONObject(response.toString());
                                                                ss = jsonObject.get("message").toString();
                                                                dialog.dismiss();
                                                                statues = jsonObject.get("success").toString();
                                                                if (statues.equals("true")){
                                                                    dialog.dismiss();
                                                                    new Handler().postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            alert_box2(ss);
                                                                        }
                                                                    },100);
                                                                }else{
                                                                    dialog.dismiss();
                                                                    new Handler().postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            alert_box2(ss);
                                                                        }
                                                                    },100);

                                                                }
                                                                //alert_box2(jsonObject.get("message").toString());
                                                                //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Log.i("volley error",error.toString());
                                                    Toast.makeText(getApplicationContext(), "Server Communication  Problem", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                }
                                            }){
                                                @Override
                                                public Map<String, String> getHeaders() throws AuthFailureError {
                                                    HashMap<String, String> headers = new HashMap<String, String>();
                                                    headers.put("Content-Type", "application/json; charset=utf-8");
                                                    return headers;
                                                }

                                            };
                                            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(8000,
                                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                            requestQueue.add(jsonObjectRequest);
                                            alert_box("Please Wait .....");
                                        }else{
                                            Toast.makeText(getApplicationContext(),"Password and Confrim password not matched",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"Enter valid Email",Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Please check the terms &ams; Conditions",Toast.LENGTH_SHORT).show();
                }
               /**/
            }
        });
    }

    private void alert_box(String massage) {
        builder = new AlertDialog.Builder(signup.this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_login_page,null);
        builder.setView(dialogView);
        msg = (TextView) dialogView.findViewById(R.id.msg);
        //close= (Button) dialogView.findViewById(R.id.close_box);
        msg.setText(massage);
        dialog = builder.create();
        dialog.show();
    }

    private void alert_box2(String massage) {
        builder = new AlertDialog.Builder(signup.this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.display_alertbox,null);
        builder.setView(dialogView);
        msg2 = (TextView) dialogView.findViewById(R.id.msg2);
        msg2.setText(massage);
        close= (Button) dialogView.findViewById(R.id.close_box);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(statues.equals("true")){
                    startActivity(new Intent(getApplicationContext(),Login_page.class));
                }else{

                }

            }
        });
        msg.setText(massage);
        dialog = builder.create();
        dialog.show();
    }

}
