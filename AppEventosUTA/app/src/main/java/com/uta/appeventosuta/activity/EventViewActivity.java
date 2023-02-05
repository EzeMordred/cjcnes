package com.uta.appeventosuta.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appeventosuta.R;

import java.util.ArrayList;
import java.util.List;

public class EventViewActivity extends AppCompatActivity {

    public TextView quantity, inc, dec;
    String _id, _title, _image, _description, _price, _currency, _discount, _attribute;
    TextView id, title, description, price, currency, discount, attribute;
    ImageView imageView;
    ProgressBar progressBar;
    LinearLayout addToCart, share;
    RelativeLayout quantityLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product_view);

        Intent intent = getIntent();

        _id = intent.getStringExtra("id");
        _title = intent.getStringExtra("title");
        _image = intent.getStringExtra("image");
        _description = intent.getStringExtra("description");
        _price = intent.getStringExtra("price");
        _currency = intent.getStringExtra("currency");
        _discount = intent.getStringExtra("discount");
        _attribute = intent.getStringExtra("attribute");

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEntry = _image + "\n" + _title + "\n" + _description + "\n" + _attribute + "-" + _currency + _price + "(" + _discount + ")";
                Intent textShareIntent = new Intent(Intent.ACTION_SEND);
                textShareIntent.putExtra(Intent.EXTRA_TEXT, userEntry);
                textShareIntent.setType("text/plain");
                startActivity(textShareIntent);
            }
        });

    }

}
