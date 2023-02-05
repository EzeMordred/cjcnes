package com.uta.appeventosuta.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeventosuta.R;
import com.uta.appeventosuta.adapter.UpcomingEventAdapter;
import com.uta.appeventosuta.control.Controller;
import com.uta.appeventosuta.model.Category;
import com.uta.appeventosuta.model.Event;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class UpcomingEventFragment extends Fragment {

    private RecyclerView nRecyclerView;
    private UpcomingEventAdapter upcomingAdapter;

    public UpcomingEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_event, container, false);
        nRecyclerView = view.findViewById(R.id.rvUpcomingEventF);

        getUpcomingEvents();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("PRÓXIMOS EVENTOS");
    }

    private void getUpcomingEvents() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/getTopUpcomingEvents.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                ArrayList<Event> events = Event.getEventsFromJson(new JSONArray(response));
                upcomingAdapter = new UpcomingEventAdapter(events, getContext(), "New");
                RecyclerView.LayoutManager pLayoutManager = new LinearLayoutManager(getContext());
                nRecyclerView.setLayoutManager(pLayoutManager);
                nRecyclerView.setItemAnimator(new DefaultItemAnimator());
                nRecyclerView.setAdapter(upcomingAdapter);
            } catch (JSONException e) {
                System.err.println(e);
            }
        }, error -> Toast.makeText(getContext(), Controller.getBigMessage("Error al cargar eventos"),Toast.LENGTH_LONG).show());
        queue.add(stringRequest);
    }

}
