package com.example.mahas.movein;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    //Button gotit;

    public SliderAdapter(Context context)
    {
        this.context=context;
    }

    public int[] slide_images ={
            R.drawable.building,
            R.drawable.furniture,
            R.drawable.medical,
            R.drawable.station,
            R.drawable.wifi,
    };

    public String[] slide_headings={
            "Flats & Hostels",
            "Furniture",
            "Medical Stores",
            "Stationary Stores",
            "WIFI Providers",

    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImageView=(ImageView) view.findViewById(R.id.imageView);
        TextView slideHeading=(TextView)view.findViewById(R.id.textView);
        TextView sildeDesc=(TextView)view.findViewById(R.id.textView2);
        slideHeading.setTextColor(Color.BLACK);
        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        container.addView(view);
        return view;
    };

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }

}
