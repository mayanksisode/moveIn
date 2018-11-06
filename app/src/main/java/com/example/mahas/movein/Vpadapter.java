package com.example.mahas.movein;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Vpadapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;


    public Vpadapter(Context context)
    {
        this.context=context;
    }
    public int [] slidepage={R.drawable.books,R.drawable.station,R.drawable.medical};

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject( View view, Object object) {
        return view==(ConstraintLayout)object;
    }

    public Object instantiateItem( ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.imaglist,container,false);
        ImageView imageview=(ImageView) view.findViewById(R.id.imageView4);
        imageview.setImageResource(slidepage[position]);

        container.addView(view);
        return view;
    };

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }

}
