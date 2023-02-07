package com.uta.appeventosuta.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appeventosuta.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.uta.appeventosuta.model.Organizer;

import java.util.List;

public class OrganizerAdapter extends RecyclerView.Adapter<OrganizerAdapter.MyViewHolder> {

    List<Organizer> organizerList;
    Context context;

    public OrganizerAdapter(List<Organizer> offerList, Context context) {
        this.organizerList = offerList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_organizer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Organizer organizer = organizerList.get(position);
        Picasso.get().load(organizer.getImage()).error(R.drawable.no_image).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onError(Exception e) { Log.d("Error : ", e.getMessage()); }
        });
    }

    @Override
    public int getItemCount() { return organizerList.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgOrganizer);
            progressBar = itemView.findViewById(R.id.progressbar);
        }
    }

}