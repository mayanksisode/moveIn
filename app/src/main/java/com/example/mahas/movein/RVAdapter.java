package com.example.mahas.movein;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private List<ListItems> list;
    SharedPreferences sharedPreferences;


    public  List<ListItems> getList() {
        return list;
    }

    private Context context;

    public interface OnItemClickListener {
        void onItemClick(ListItems item);
    }
    //public final OnItemClickListener listener;
    public RVAdapter(List<ListItems> list, Context context) {
        this.list = list;
        this.context = context;
      //  this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext())
               .inflate(R.layout.listitem,parent,false);
       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
       final ListItems listItem =list.get(position);
       holder.textViewHead.setText(listItem.getTextHead());
       holder.textViewDesc.setText(listItem.getTextD());
       holder.textViewDesc2.setText(listItem.getTextD2());
        sharedPreferences=context.getSharedPreferences(Login.MyPREFERENCES,Context.MODE_PRIVATE);
       Picasso.get().load(Constants.URL+listItem.getURL()).fit().into(holder.imageView);
        final SharedPreferences.Editor editor= sharedPreferences.edit();
           holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(listItem.getTextD10()=="fandh") {
                   Intent i = new Intent(v.getContext(), ScrollingActivity.class);
                   i.putExtra("name", listItem.getTextD3());
                   i.putExtra("rcview", listItem.getTextD4());
                   i.putExtra("overv1", listItem.getTextD5());
                   i.putExtra("overv2", listItem.getTextD6());
                   i.putExtra("overv3", listItem.getTextD7());
                   i.putExtra("overv4", listItem.getTextD8());
                   i.putExtra("url", listItem.getURL());
                   i.putExtra("contact", listItem.getTextD9());
                   i.putExtra("storename",listItem.getTextHead());
                   i.putExtra("id",listItem.getId());
                   i.putExtra("tname",listItem.getTextD10());
                   i.putExtra("maplat","18.489759");
                   i.putExtra("maplong","73.820297");
                   i.putExtra("storename",listItem.getTextHead());
                   Log.d("like",listItem.isLiked()+"");
                   i.putExtra("liked",listItem.isLiked());
                   getList().get(sharedPreferences.getInt("poslike",0)).setLiked(sharedPreferences.getBoolean("statuslike",getList().get(sharedPreferences.getInt("poslike",0)).isLiked()));
                   i.putExtra("pos",position);

                   context.startActivity(i);
               }
               else
               {
                   Intent i = new Intent(v.getContext(), desc2.class);
                   i.putExtra("name", listItem.getTextD3());
                   i.putExtra("rcview", listItem.getTextD4());
                   i.putExtra("overv1", listItem.getTextD5());
                   i.putExtra("overv2", listItem.getTextD6());
                   i.putExtra("contact", listItem.getTextD7());
                  // i.putExtra("overv4", listItem.getTextD8());
                   i.putExtra("url", listItem.getURL());
                   i.putExtra("tname",listItem.getTname());
                  // i.putExtra("contact", listItem.getTextD9());
                   i.putExtra("maplat","18.489759");
                   i.putExtra("maplong","73.820297");
                   i.putExtra("storename",listItem.getTextHead());
                   i.putExtra("id",listItem.getId());
                   i.putExtra("liked",listItem.isLiked());
                   i.putExtra("pos",position);
                  // getList().get(sharedPreferences.getInt("poslike",0)).setLiked(sharedPreferences.getBoolean("statuslike",getList().get(sharedPreferences.getInt("poslike",0)).isLiked()));
                   context.startActivity(i);


               }
           }
       });



      // holder.bind(list.get(position),listener);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewHead;
        public TextView textViewDesc,textViewDesc2;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
           textViewHead=itemView.findViewById(R.id.THead);
           textViewDesc=itemView.findViewById(R.id.TDesc);
           textViewDesc2=itemView.findViewById(R.id.TDesc2);
           imageView=itemView.findViewById(R.id.cardImg);
        }


    }
}
