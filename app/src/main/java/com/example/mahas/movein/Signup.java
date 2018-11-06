package com.example.mahas.movein;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
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


public class Signup extends Fragment implements View.OnClickListener{
    public EditText fname,lname,emailid,pass,repass;
    public ProgressDialog progressDialog;
    public Button btsignup;
    ImageView imageView;
    public Signup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  rootView=inflater.inflate(R.layout.fragment_signup, container, false);
        btsignup= (Button)rootView.findViewById(R.id.button);
        fname=(EditText)rootView.findViewById(R.id.sufname);
        lname=(EditText)rootView.findViewById(R.id.sulname);
        emailid=(EditText)rootView.findViewById(R.id.suemail);
        pass=(EditText)rootView.findViewById(R.id.supass);
        repass=(EditText)rootView.findViewById(R.id.surepass);
        progressDialog=new ProgressDialog(rootView.getContext());
        btsignup.setOnClickListener(this);
        imageView = rootView.findViewById(R.id.imageView3);
        return rootView;
    }


    public void registerUser()
    {
        if(fname.getText().toString().equals(""))
        {
            Snackbar.make(getView(),"First Name Empty",Snackbar.LENGTH_SHORT).show();
        }
        else if(lname.getText().toString().equals(""))
        {
            Snackbar.make(getView(),"Last Name Empty",Snackbar.LENGTH_SHORT).show();
        }
        else if(!emailid.getText().toString().contains("@") || !emailid.getText().toString().contains("."))
        {
            Snackbar.make(getView(),"Enter Valid EmailID",Snackbar.LENGTH_SHORT).show();
        }
        else if(pass.getText().toString().trim().length()<8)
        {
            Snackbar.make(getView(),"Password not strong enough (8 characters at least)",Snackbar.LENGTH_SHORT).show();
        }
        else if(!pass.getText().toString().equals(repass.getText().toString()))
        {
            Snackbar.make(getView(),"Passwords don't match",Snackbar.LENGTH_SHORT).show();
        }
        else {
            progressDialog.setMessage("Registering User");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            /*if(jsonObject.getString("message").contains("already"));
                            {
                                FragmentManager fragmentManager= getChildFragmentManager();
                                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.fragment,new Login());
                                fragmentTransaction.commit();
                                //Toast.makeText(getActivity(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                            }*/
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            error.printStackTrace();
                            Toast.makeText(getActivity(), "Error Occured", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Fname", fname.getText().toString().trim());
                    params.put("Lname", lname.getText().toString().trim());
                    params.put("Emailid", emailid.getText().toString().trim());
                    params.put("Epass", pass.getText().toString().trim());
                    params.put("Repass", repass.getText().toString().trim());
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }
    }


    @Override
    public void onClick(View v) {
        registerUser();
        //getActivity().finish();

        //startActivity(new Intent(getContext(),LogandSign.class));
    }


}
