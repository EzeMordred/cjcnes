package com.uta.appeventosuta.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeventosuta.R;
import com.uta.appeventosuta.adapter.EventAdapter;
import com.uta.appeventosuta.control.Controller;
import com.uta.appeventosuta.model.Event;
import com.uta.appeventosuta.model.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventActivity extends AppCompatActivity {

    private EventAdapter eventAdapter;
    private String tag = "List", category;
    private RecyclerView recyclerView;
    private ArrayList<Event> events;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        events = new ArrayList<>();
        recyclerView = findViewById(R.id.rvEvents);
        category = getIntent().getStringExtra("category");

        Toolbar toolbar = findViewById(R.id.toolbarEvent);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setTitle(String.format("Categorías | %ss", category));
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getEventList();

    }

    private void getEventList() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/getAllEventsByCategory.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                events = Event.getEventsFromJson(new JSONArray(response));
                setUpRecyclerView();
            } catch (JSONException e) {
                System.err.println(e);
            }
        }, error -> Toast.makeText(this, Controller.getBigMessage("Error al cargar eventos"),Toast.LENGTH_LONG).show()) {
            @Override
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("CATEGORIA", category);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void setUpRecyclerView() {
        if(events.isEmpty()) {
            getEventList();
        }
        eventAdapter = new EventAdapter(events, this, tag);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eventAdapter);
    }

    private void setUpGridRecyclerView() {
        if (events.isEmpty()) {
            getEventList();
        }
        eventAdapter = new EventAdapter(events, this, tag);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eventAdapter);
    }

    public void onToggleClicked(View view) {
        if (tag.equalsIgnoreCase("List")) {
            tag = "Grid";
            setUpGridRecyclerView();
        } else {
            tag = "List";
            setUpRecyclerView();
        }
    }

}
