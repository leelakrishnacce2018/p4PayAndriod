package com.example.p4paysecurepayment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class dashboard extends AppCompatActivity {
    DrawerLayout drawer;
    NavigationView navigationView;
    FrameLayout frameLayout;
    ActionBarDrawerToggle toggle;
    ImageView imageView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Toolbar toolbar;
    String username="";
    View header;
    RequestQueue requestQueue;
    String url = "https://mysterious-eyrie-12968.herokuapp.com/api/dashboard";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        sharedPreferences = getSharedPreferences("p4pay",0);
        editor = sharedPreferences.edit();
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        frameLayout = (FrameLayout) findViewById(R.id.frame);
        Toast.makeText(getApplicationContext(),sharedPreferences.getString("accessToken",""),Toast.LENGTH_SHORT).show();
        header = navigationView.getHeaderView(0);
        imageView = (ImageView) findViewById(R.id.user_profile_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if(!drawer.isDrawerOpen(GravityCompat.START)) drawer.openDrawer(Gravity.START);
                else drawer.closeDrawer(Gravity.END);
              //  drawer.closeDrawer(GravityCompat.START);
            }
        });
       // getdate();
        //this code we will eanble the icon to open and close the side navigation bar
        /*toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

        //set default fragment
        loadFragment(new homeFragment());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.nav_item_home) {
                    loadFragment(new homeFragment());
                } else if(id == R.id.nav_item_kyc) {
                    loadFragment(new Profile());
                } else if(id == R.id.nav_item_aboutus) {
                    loadFragment(new Fragment());
                }else if(id == R.id.nav_item_update_account) {
                    loadFragment(new Fragment());
                }else if(id == R.id.nav_item_coupons) {
                    loadFragment(new Fragment());
                }else if(id == R.id.nav_item_upaccount) {
                    loadFragment(new Fragment());
                }else if(id == R.id.nav_item_logout) {
                    loadFragment(new Fragment());
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }



    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

    public void getdate(){
        final String va = sharedPreferences.getString("tokenType","")+" "+sharedPreferences.getString("accessToken","");
        Log.i("sssssss",va);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            username = jsonObject.getString("name");
                            JSONArray jsonArray = jsonObject.getJSONArray("transactions");
                            for (int j=0;j<=jsonArray.length();j++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> parames = new HashMap<String,String>();
                parames.put("Authorization",va);
                return parames;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}
