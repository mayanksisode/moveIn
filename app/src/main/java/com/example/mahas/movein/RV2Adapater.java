package com.example.mahas.movein;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RV2Adapater extends RecyclerView.Adapter<RV2Adapater.ViewHolder> {
    private List<ListItems> list;
    private Context context;

    public RV2Adapater(List<ListItems> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RV2Adapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem2,parent,false);
        return new RV2Adapater.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RV2Adapater.ViewHolder holder, int position) {
        final ListItems listItem =list.get(position);
        holder.textViewHead.setText(listItem.getTextHead());
        Picasso.get().load(Constants.URL+listItem.getURL()).fit().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewHead;
        public ImageView imageView;
        public ViewHolder(View itemView)
        {
            super(itemView);
            textViewHead=itemView.findViewById(R.id.THead1);
            imageView=itemView.findViewById(R.id.cardImg1);
        }

}

}