package com.uta.appeventosuta.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appeventosuta.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.uta.appeventosuta.model.Event;

import java.util.ArrayList;

public class UpcomingEventAdapter extends RecyclerView.Adapter<UpcomingEventAdapter.MyViewHolder> {

    ArrayList<Event> eventList;
    Context context;
    String tag;

    public UpcomingEventAdapter(ArrayList<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    public UpcomingEventAdapter(ArrayList<Event> eventList, Context context, String tag) {
        this.eventList = eventList;
        this.context = context;
        this.tag = tag;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView;
        if (tag.equalsIgnoreCase("Home")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_upcoming_event_home, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_upcoming_event, parent, false);
        }

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final Event event = eventList.get(position);

        if(!tag.equalsIgnoreCase("Home")) {
            holder.subTitle.setText(event.getSubtittle());
            holder.modality.setText(event.getModality());
        }

        holder.title.setText(event.getTitle());
        holder.initDate.setText(event.getInitDate());
        holder.finalDate.setText(event.getFinalDate());
        Picasso.get().load("https://proyectosuta2.000webhostapp.com/eventos_uta/images/courseImage.png")
                .error(R.drawable.no_image).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onError(Exception e) {
                Log.d("Error : ", e.getMessage());
            }
        });

        holder.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Detalles por tipo de evento
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context, ProductViewActivity.class);
                intent.putExtra("id", product.getId());
                intent.putExtra("title", product.getTitle());
                intent.putExtra("image", product.getImage());
                intent.putExtra("price", product.getPrice());
                intent.putExtra("currency", product.getCurrency());
                intent.putExtra("attribute", product.getAttribute());
                intent.putExtra("discount", product.getDiscount());
                intent.putExtra("description", product.getDescription());

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, subTitle, modality, initDate, finalDate, signUp;
        ProgressBar progressBar;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.eventImage);
            title = itemView.findViewById(R.id.txtEventTittle);
            subTitle = itemView.findViewById(R.id.txtEventSubtittle);
            modality = itemView.findViewById(R.id.txtEventModality);
            initDate = itemView.findViewById(R.id.txtInitDateEvent);
            finalDate = itemView.findViewById(R.id.txtFinalDateEvent);
            signUp = itemView.findViewById(R.id.btnSignUpEvent);
            progressBar = itemView.findViewById(R.id.progressbarR);
            cardView = itemView.findViewById(R.id.cvEvent);

        }
    }
}
