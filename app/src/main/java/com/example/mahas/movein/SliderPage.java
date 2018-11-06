package com.example.mahas.movein;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SliderPage extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPre2" ;
    public ViewPager mSlideVP;
    public LinearLayout mDots;
    private  SliderAdapter sliderAdapter;
    private TextView[] mdot;
    private Button gotit;
    private Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences shp=getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        if(sharedpreferences.getString("name",null)!=null)
        {
            finish();
           startActivity(new Intent(SliderPage.this,MainActivity.class));
        }
        else if(shp.getString("appopen",null)!=null)
        {
            finish();
            startActivity(new Intent(SliderPage.this,LogandSign.class));
            //Toast.makeText(getParent(),sharedpreferences.getString("appopen",null),Toast.LENGTH_SHORT).show();
        }

            //startActivity(new Intent(SliderPage.this,SliderPage.class));
        window= getWindow();
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setContentView(R.layout.activity_slider_page);
        window.setExitTransition(new Fade());
        mSlideVP=(ViewPager) findViewById(R.id.slideviewpager);
        mDots=(LinearLayout) findViewById(R.id.dotsLayout);
        gotit=(Button) findViewById(R.id.button3);
        sliderAdapter = new SliderAdapter(this);
        mSlideVP.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addDotsIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor= shp.edit();
                editor.putString("appopen","true");
                editor.apply();
                //Toast.makeText(getParent(),sharedpreferences.getString("appopen",null),Toast.LENGTH_SHORT);
                finish();
                startActivity(new Intent(SliderPage.this,LogandSign.class), ActivityOptions.makeSceneTransitionAnimation(SliderPage.this).toBundle());
            }
        });
    }
    public void addDotsIndicator(int position)
    {
        mdot =  new TextView[5];
        mDots.removeAllViews();

        for(int i=0; i<mdot.length;i++)
        {
            //mdot[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            mdot[i]= new TextView(this);
            mdot[i].setText(Html.fromHtml("&#8226;"));
            mdot[i].setTextSize(35);
            mdot[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDots.addView(mdot[i]);
            if(position==mdot.length-1)
            {
                gotit.setVisibility(View.VISIBLE);
            }
        }
        if(mdot.length>0)
        {
            mdot[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

    }



}
