package com.example.mahas.movein;


import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.transition.Explode;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


public class LogandSign extends FragmentActivity   {
    Button login;
    Button signup;
    Button mainlogin;
    public EditText fname,lname,emailid,pass,repass;
    public ProgressDialog progressDialog;
    NukeSSLCerts nukeSSLCerts= new NukeSSLCerts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setContentView(R.layout.activity_logandsign_page);
        //getWindow().setAllowEnterTransitionOverlap(true);
       // getWindow().setEnterTransition(new Explode());
        //getWindow().setExitTransition(new Fade());
        nukeSSLCerts.nuke();
        setUpUIViews();



    }



    public void setUpUIViews()
    {
        final LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        final View view = inflater.inflate(R.layout.fragment_signup, null, false);
        login=(Button)findViewById(R.id.btlog);
        signup=(Button)findViewById(R.id.btsign);
        mainlogin=(Button)findViewById(R.id.btlogin);
    }

    public void onClick(View view) {
        Fragment fr;
        if(view==findViewById(R.id.btlog)) {
            fr = new Login();

            login.setBackground(getDrawable(R.drawable.onesideround));
            signup.setBackground(getDrawable(R.drawable.roundgrey2));
        }
        else
        {
            fr= new Signup();

            login.setBackground(getDrawable(R.drawable.roundgray));
            signup.setBackground(getDrawable(R.drawable.onesideround2));
        }


        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fr);
        fragmentTransaction.commit();
    }




}

