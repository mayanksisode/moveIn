package com.example.mahas.movein;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class desc2 extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    FloatingActionButton fab, fab1, fab2;
    public TextView add1;
    public TextView sqft1;
    public TextView rcview1;
    public TextView overv11;
    public TextView overv21;
    public Button call;
    public String likedid, userid, tname;
    public boolean isLiked;
    public int pos;
    // public TextView overv3;
    //public TextView overv4;
    public Target target;
    String lang, lot, storename;

    public TextView contact1;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    boolean isFABOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc2);
        add1 = (TextView) findViewById(R.id.address1);
        sqft1 = (TextView) findViewById(R.id.sqft1);
        rcview1 = (TextView) findViewById(R.id.textView101);
        overv11 = (TextView) findViewById(R.id.textView61);
        overv21 = (TextView) findViewById(R.id.textView51);
        contact1 = (TextView) findViewById(R.id.textView81);
        call = (Button) findViewById(R.id.button5);
        sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        userid = sharedpreferences.getString("id", "Null");
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout1);
        final Bundle b1 = getIntent().getExtras();
        lot = b1.getString("maplat");
        lang = b1.getString("maplong");
        tname = b1.getString("tname");
        likedid = b1.getString("id");
        storename = b1.getString("storename");
        add1.setText(b1.getString("name"));
        sqft1.setText(b1.getString("rcview"));
        overv11.setText(b1.getString("overv1"));
        overv21.setText(b1.getString("overv2"));
        //overv3.setText(b1.getString("overv3"));
        //overv4.setText(b1.getString("overv4"));
        contact1.setText(b1.getString("contact"));
        pos = b1.getInt("pos");
        isLiked = b1.getBoolean("liked");
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+contact1.getText().toString().trim()));
                startActivity(callIntent);
            }
        });
        target=new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                collapsingToolbarLayout.setBackground(new BitmapDrawable(getApplicationContext().getResources(),bitmap));
                collapsingToolbarLayout.invalidate();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Picasso.get().load(Constants.URL+   b1.getString("url")).into(target);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle(storename);
        //toolbar.setBackground(getDrawable(R.drawable.building));
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab01);
        fab1=(FloatingActionButton)findViewById(R.id.fab11);
        fab2=(FloatingActionButton)findViewById(R.id.fab21);
        if(isLiked)
        {
            fab2.setImageResource(R.drawable.hefilled);
        }
        else
        {
            fab2.setImageResource(R.drawable.hea);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
    }
    private void showFABMenu(){
        isFABOpen=true;
        fab1.animate().translationY(-175);
        fab2.animate().translationY(-350);
        sharedpreferences= getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedpreferences.edit();
        final String userid= sharedpreferences.getString("id","Null");

       // final Bundle b1=getIntent().getExtras();

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(desc2.this,MapsActivity.class);

                i.putExtra("maplat",lot);
                i.putExtra("maplong",lang);
                i.putExtra("name",storename);
                Log.d("desc2", lot);
                startActivity(i);
            }
        });

      fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest= new StringRequest(Request.Method.POST, Constants.URL_LIKED, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("error").equals("false"))
                             {
                                 Toast.makeText(getApplicationContext(),jsonObject.getString("message").trim(),Toast.LENGTH_SHORT).show();

                                 if(jsonObject.getString("message").trim().contains("Dis"))
                                 {
                                     fab2.setImageResource(R.drawable.hea);

                                 }
                                 else
                                 {
                                     fab2.setImageResource(R.drawable.hefilled);

                                 }


                             }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        })


                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map=new HashMap<>();
                        map.put("userid",userid);
                        map.put("likedid",likedid);
                        map.put("tablename",tname);
                        return map;

                    }
                };

                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

            }


            });
        }



    private void closeFABMenu(){
        isFABOpen=false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
    }
}
