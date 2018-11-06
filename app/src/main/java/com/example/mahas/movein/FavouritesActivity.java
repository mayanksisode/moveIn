package com.example.mahas.movein;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavouritesActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItems> listItems;
    private String userid;
    public StringRequest[] reqArr= new StringRequest[6];
    public String[] tnames={
            "fandh",
            "furn",
            "groceries",
            "medical",
            "stationary",
            "wifip",

    };
    NukeSSLCerts nukeSSLCerts=new NukeSSLCerts();
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        nukeSSLCerts.nuke();
        sharedPreferences=getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        listItems = new ArrayList<>();
        userid=sharedPreferences.getString("id",null);
        recyclerView = (RecyclerView)findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = Volley.newRequestQueue(this);
        for(int i=0;i<reqArr.length;i++)
        {
            final int k=i;
            reqArr[i] = new StringRequest(com.android.volley.Request.Method.POST, Constants.URL_GET,
                    new Response.Listener<String>()
                    {
                    @Override
                        public void onResponse(String response)
                        {
                            try
                            {

                                JSONObject jsonObject= new JSONObject(response);
                                JSONArray array=jsonObject.getJSONArray("idliked");
                                for(int i=0;!array.isNull(i);i++) {
                                    ListItems ls = new ListItems(array.getJSONObject(i).getString("head"),array.getJSONObject(i).getString("url"));
                                    listItems.add(ls);
                                    adapter = new RV2Adapater(listItems, FavouritesActivity.this);
                                    recyclerView.setAdapter(adapter);
                                }
                                Log.w("kgkh",listItems.toString());

                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                    @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Log.d("erere","inside");
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params= new HashMap<>();
                    params.put("userid",userid);
                    params.put("tablename",tnames[k]);
                    return params;
                }
            };
            requestQueue.add(reqArr[i]);
        }

    }


}
