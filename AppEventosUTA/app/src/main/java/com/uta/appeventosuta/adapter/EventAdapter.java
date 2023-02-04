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

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    ArrayList<Event> eventList;
    Context context;
    String tag;

    public EventAdapter(ArrayList<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    public EventAdapter(ArrayList<Event> eventList, Context context, String tag) {
        this.eventList = eventList;
        this.context = context;
        this.tag = tag;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView;
        if (tag.equalsIgnoreCase("List")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_event, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_grid_event, parent, false);
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Event event = eventList.get(position);

        holder.type.setText(event.getType());
        holder.title.setText(event.getTitle());
        holder.subtitle.setText(event.getSubtitle());
        holder.initDate.setText(event.getInitDate());
        holder.finalDate.setText(event.getFinalDate());
        holder.location.setText(event.getLocation());
        Picasso.get()
                .load(event.getImage())
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(Exception e) {
                        Log.d("Error : ", e.getMessage());
                    }
                });

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*holder.addToCart.setVisibility(View.GONE);
                holder.subTotal.setVisibility(View.VISIBLE);

                _price = product.getPrice();
                _quantity = holder.quantity.getText().toString();
                _attribute = product.getAttribute();

                if (Integer.parseInt(_quantity) != 0) {
                    _subtotal = String.valueOf(Double.parseDouble(_price) * Integer.parseInt(_quantity));
                    holder.subTotal.setText(_quantity + "X" + _price + "= Rs." + _subtotal);
                    if (context instanceof ProductActivity) {
                        Cart cart = new Cart(product.getId(), product.getTitle(), product.getImage(), product.getCurrency(), _price, _attribute, _quantity, _subtotal);
                        cartList = ((BaseActivity) context).getCartList();
                        cartList.add(cart);
                        String cartStr = gson.toJson(cartList);
                        //Log.d("CART", cartStr);
                        localStorage.setCart(cartStr);
                        ((AddorRemoveCallbacks) context).onAddProduct();
                        notifyItemChanged(position);
                    }
                } else {
                    Toast.makeText(context, "Please Add Quantity", Toast.LENGTH_SHORT).show();
                }*/

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
        ProgressBar progressBar;
        CardView cardView;
        TextView type, title, subtitle, initDate, finalDate, location, addToCart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.eventImageR);
            progressBar = itemView.findViewById(R.id.progressbarR);
            cardView = itemView.findViewById(R.id.cvEventR);
            type = itemView.findViewById(R.id.txtEventTypeR);
            title = itemView.findViewById(R.id.txtEventTittleR);
            subtitle = itemView.findViewById(R.id.txtEventSubtittleR);
            initDate = itemView.findViewById(R.id.txtInitDateEventR);
            finalDate = itemView.findViewById(R.id.txtFinalDateEventR);
            location = itemView.findViewById(R.id.txtEventLocationR);
            addToCart = itemView.findViewById(R.id.add_to_cart);
        }
    }
}
