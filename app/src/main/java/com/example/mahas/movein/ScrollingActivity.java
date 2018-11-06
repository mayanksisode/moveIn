package com.example.mahas.movein;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

public class ScrollingActivity extends AppCompatActivity {
     SharedPreferences sharedPreferences;
    FloatingActionButton fab,fab1,fab2;
    public TextView add;
    public TextView sqft;
    public TextView rcview;
    public TextView overv1;
    public TextView overv2;
    public TextView overv3;
    public TextView overv4;
    public TextView contact;
    public Button call;
    public Target target;
    public boolean isLiked;
    String lang,lot,storename,userid,likedid,tname;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    public int pos;
    boolean isFABOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        add=(TextView)findViewById(R.id.address);
        sqft=(TextView)findViewById(R.id.sqft);
        rcview=(TextView)findViewById(R.id.textView10);
        overv1=(TextView)findViewById(R.id.textView6);
        overv2=(TextView)findViewById(R.id.textView5);
        overv3=(TextView)findViewById(R.id.textView9);
        overv4=(TextView)findViewById(R.id.textView7);
        contact=(TextView)findViewById(R.id.textView8);
        call=(Button)findViewById(R.id.button2);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        final Bundle b1=getIntent().getExtras();

        lot=b1.getString("maplat");
        lang=b1.getString("maplong");
        storename=b1.getString("storename");
        likedid=b1.getString("id");
        sharedPreferences= getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        userid= sharedPreferences.getString("id","Null");
        add.setText(b1.getString("name"));
        sqft.setText(b1.getString("rcview"));
        overv1.setText(b1.getString("overv1"));
        overv2.setText(b1.getString("overv2"));
        overv3.setText(b1.getString("overv3"));
        overv4.setText(b1.getString("overv4"));
        contact.setText(b1.getString("contact"));
        tname=b1.getString("tname");
        pos=b1.getInt("pos");
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+contact.getText().toString().trim()));
                startActivity(callIntent);
            }
        });
        isLiked=b1.getBoolean("liked");
        Log.d("like",isLiked+"");
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
        Picasso.get().load(Constants.URL+b1.getString("url")).into(target);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(storename);
        //toolbar.setBackground(getDrawable(R.drawable.building));
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1=(FloatingActionButton)findViewById(R.id.fab1);
        fab2=(FloatingActionButton)findViewById(R.id.fab2);
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
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ScrollingActivity.this,MapsActivity.class);

                i.putExtra("maplat",lot);
                i.putExtra("maplong",lang);
                i.putExtra("name",storename);
                Log.d("desc2", lot);
                startActivity(i);
                startActivity(new Intent(ScrollingActivity.this,MapsActivity.class));
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
                                SharedPreferences.Editor editor= sharedPreferences.edit();
                                if(jsonObject.getString("message").trim().contains("Dis"))
                                {
                                    fab2.setImageResource(R.drawable.hea);
                                   // editor.putInt("poslike",pos);
                                   // editor.putBoolean("statuslike",true);
                                }
                                else
                                {
                                    fab2.setImageResource(R.drawable.hefilled);
                                   // editor.putInt("poslike",pos);
                                   // editor.putBoolean("statuslike",false);
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
