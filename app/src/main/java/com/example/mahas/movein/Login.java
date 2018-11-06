package com.example.mahas.movein;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends Fragment implements View.OnClickListener {

    SharedPreferences sharedpreferences;
    ImageView imageView;
    Button log;
    EditText enem,enpas;
    ProgressDialog progressDialog;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedpreferences= getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        log=(Button)rootView.findViewById(R.id.btlogin);
        enem=(EditText)rootView.findViewById(R.id.enEmail);
        enpas=(EditText)rootView.findViewById(R.id.enPass);
        log.setOnClickListener(this);
        progressDialog=new ProgressDialog(rootView.getContext());
        imageView=rootView.findViewById(R.id.imageView2);
        return rootView;
    }

    public void logUser()
    {
        final SharedPreferences.Editor editor= sharedpreferences.edit();
        progressDialog.setMessage("Logging You In");
        progressDialog.show();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    progressDialog.dismiss();
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            Toast.makeText(getActivity(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            if(jsonObject.getString("error")=="false")
                            {
                                editor.putString("email",enem.getText().toString());
                                editor.putString("name",jsonObject.getJSONObject("idnamear").getString("FirstName"));
                                editor.putString("id",jsonObject.getJSONObject("idnamear").getString("id"));
                                editor.apply();
                                getActivity().finish();
                                startActivity(new Intent(getContext(),MainActivity.class));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),"Error Occured",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("Emailid",enem.getText().toString().trim());
                params.put("Password",enpas.getText().toString().trim());
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
    @Override
    public void onClick(View v) {
        logUser();
    }
}

