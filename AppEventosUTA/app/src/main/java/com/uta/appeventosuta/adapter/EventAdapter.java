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
                    .inflate(R.layout.row_products, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_grid_event, parent, false);
        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Event event = eventList.get(position);

        holder.title.setText(product.getTitle());
        holder.offer.setText(product.getDiscount());
        holder.attribute.setText(product.getAttribute());
        holder.currency.setText(product.getCurrency());
        holder.price.setText(product.getPrice());
        Picasso.get()
                .load(product.getImage())
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


        if (product.getDiscount() == null || product.getDiscount().length() == 0) {
            holder.offer.setVisibility(View.GONE);
        }

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
        TextView title;
        ProgressBar progressBar;
        CardView cardView;
        TextView offer, currency, price, quantity, attribute, addToCart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.eventImageR);
            title = itemView.findViewById(R.id.txtEventTittle);
            progressBar = itemView.findViewById(R.id.progressbarR);
            cardView = itemView.findViewById(R.id.cvEventR);
            offer = itemView.findViewById(R.id.product_discount);
            currency = itemView.findViewById(R.id.product_currency);
            price = itemView.findViewById(R.id.product_price);
            quantity = itemView.findViewById(R.id.quantity);
            addToCart = itemView.findViewById(R.id.add_to_cart);
            attribute = itemView.findViewById(R.id.product_attribute);
        }
    }
}
