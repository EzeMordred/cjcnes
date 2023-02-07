package com.uta.appeventosuta.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeventosuta.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.uta.appeventosuta.adapter.OrganizerAdapter;
import com.uta.appeventosuta.control.Controller;
import com.uta.appeventosuta.model.Organizer;
import com.uta.appeventosuta.model.Webinar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebinarViewActivity extends AppCompatActivity {

    private TextView title, dateTime, transmission, speakerName, speakerJob;
    private ImageView speakerImage, transmissionImage;
    private OrganizerAdapter organizerAdapter;
    private ArrayList<Organizer> organizers;
    private RecyclerView recyclerView;
    private RelativeLayout signup;
    private Webinar webinar;

    //private int requestsCount = 0, requestsFinished = 0;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webinar_view);
        id = getIntent().getStringExtra("id");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initComponents();
        getWebinar();
        getOrganizerList();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webinar.getLink()));
                startActivity(browserIntent);
            }
        });

        /*share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEntry = "";
                Intent textShareIntent = new Intent(Intent.ACTION_SEND);
                textShareIntent.putExtra(Intent.EXTRA_TEXT, userEntry);
                textShareIntent.setType("text/plain");
                startActivity(textShareIntent);
            }
        });*/

    }

    private void initComponents() {
        title = findViewById(R.id.txtTitleW);
        dateTime = findViewById(R.id.txtDateTimeW);
        speakerImage = findViewById(R.id.imgSpeaker);
        speakerName = findViewById(R.id.txtSpeakerNameW);
        speakerJob = findViewById(R.id.txtSpeakerJobW);
        transmissionImage = findViewById(R.id.imgTransmission);
        organizers = new ArrayList<>();
        recyclerView = findViewById(R.id.rvOrganizer);
        signup = findViewById(R.id.SignUp);
    }

    private void getWebinar() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/getWebinarById.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                webinar = Webinar.jsonToWebinar(new JSONObject(response));
                fillWebinarData();
            } catch (JSONException e) {
                System.err.println(e);
            }
        }, error -> Toast.makeText(this, Controller.getBigMessage("Error de conexión"), Toast.LENGTH_LONG).show()){
            @Override
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("WEBINAR_ID", id);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getOrganizerList() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/getOrganizersById.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                organizers = Organizer.getOrganizersFromJson(new JSONArray(response));
                organizerAdapter = new OrganizerAdapter(organizers, this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(organizerAdapter);
            } catch (JSONException e) {
                Log.d(this.getClass().getSimpleName(), "Error de conversión JSON.");
            }
        }, error -> Log.d(this.getClass().getSimpleName(), "Error de petición sql.")) {
            @Override
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("EVENTO_ID", id);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void fillWebinarData() {
        if (webinar == null) {
            getWebinar();
        } else {
            title.setText(webinar.getTitle());
            dateTime.setText(String.format("%s\n%s", webinar.getInitDate(), webinar.getInitTime()));
            Picasso.get().load(webinar.getSpeakerImage()).error(R.drawable.no_image).into(speakerImage);
            speakerName.setText(String.format("%s %s %s", webinar.getSpeakerPrefix(), webinar.getSpeakerName(), webinar.getSpeakerLastName()));
            speakerJob.setText(webinar.getSpeakerJob());
            transmissionImage.setImageDrawable(getTransmissionImage());
        }
    }

    private Drawable getTransmissionImage() {
        int idDrawable;
        if(webinar.getLink().contains("zoom")) {
            idDrawable= R.drawable.zoom_50;
        } else if (webinar.getLink().contains("teams")) {
            idDrawable = R.drawable.teams_50;
        } else if (webinar.getLink().contains("facebook")) {
            idDrawable = R.drawable.facebook_live_50;
        } else {
            idDrawable = R.drawable.virtual_50;
        }
        return ContextCompat.getDrawable(this, idDrawable);
    }

    /*private void getWebinar() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String[] urls = new String[]{
                "https://www.example1.com",
                "https://www.example2.com"
        };
        requestsCount = urls.length;
        for (String url : urls) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            requestsFinished++;
                            if (requestsFinished == requestsCount) {
                                //startNextActivity();
                            }
                        }
                    }, error -> Toast.makeText(this, Controller.getBigMessage("Error al cargar el evento"), Toast.LENGTH_LONG).show());
            requestQueue.add(stringRequest);
        }
    }*/

}
