package com.example.p4paysecurepayment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class Login_page extends AppCompatActivity {
    EditText userame,password;
    Button login_button;
    TextView singup_button;
    RequestQueue requestQueue;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    TextView msg;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String regexStr = "^[0-9]*$";
    Button close;
    String url ="https://mysterious-eyrie-12968.herokuapp.com/api/auth/signin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        sharedPreferences = getSharedPreferences("p4pay",0);
        editor = sharedPreferences.edit();
        userame =(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        singup_button=(TextView)findViewById(R.id.signup_button);
        login_button =(Button)findViewById(R.id.login_button);
        singup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),signup.class));
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userame.getText().length() == 0 && userame.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Enter your email/moblie number",Toast.LENGTH_SHORT).show();
                }else if(userame.getText().length() < 10 ){
                    if(!userame.getText().toString().trim().matches(regexStr)){
                        Toast.makeText(getApplicationContext(),"Enter Mobile number is not valied ",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Please check the moblie number",Toast.LENGTH_SHORT).show();
                    }

                }else if(userame.getText().toString().trim().matches(emailPattern)){
                    Toast.makeText(getApplicationContext(),"Please check the email address",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.getText().length() == 0  && password.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Enter your password",Toast.LENGTH_SHORT).show();
                    }else if(password.getText().length() < 6 ){
                        Toast.makeText(getApplicationContext(),"password length must be at least 6 character",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Map<String, String> postParam= new HashMap<String, String>();
                        postParam.put("usernameOrEmail", userame.getText().toString());
                        postParam.put("password", password.getText().toString());
                        requestQueue = Volley.newRequestQueue(getApplicationContext());
                        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam),
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            dialog.dismiss();
                                            JSONObject jsonObject = new JSONObject(response.toString());
                                            if (jsonObject.get("success").toString().equals("true")){
                                                editor.putString("FirstTime","launch");
                                                editor.commit();
                                                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                                                //Toast.makeText(getApplicationContext(),jsonObject.get("accessToken").toString(),Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(getApplicationContext(),jsonObject.get("message").toString(),Toast.LENGTH_SHORT).show();
                                            }
                                            //editor.putString("FirstTime","launch");
                                            //editor.commit();
                                           // startActivity(new Intent(getApplicationContext(),Dashboard.class));
                                            //Toast.makeText(getApplicationContext(),jsonObject.get("accessToken").toString(),Toast.LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
                        requestQueue.add(jsonObjectRequest);
                        alert_box("Please wait...");
                    }
                }

            }
        });
    }
    private void alert_box(String massage) {
         builder = new AlertDialog.Builder(Login_page.this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_login_page,null);
        builder.setView(dialogView);
        msg = (TextView) dialogView.findViewById(R.id.msg);
        //close= (Button) dialogView.findViewById(R.id.close_box);
        msg.setText(massage);
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        /*android.os.Process.killProcess(android.os.Process.myPid());
        Login_page.this.finish();
        System.exit(0);*/
    }
}
