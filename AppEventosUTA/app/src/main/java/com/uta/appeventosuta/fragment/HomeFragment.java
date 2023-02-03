package com.uta.appeventosuta.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeventosuta.R;
//import com.quintus.labs.grocerystore.adapter.NewProductAdapter;
//import com.quintus.labs.grocerystore.adapter.PopularProductAdapter;
import com.uta.appeventosuta.activity.MainActivity;
import com.uta.appeventosuta.adapter.CategoryAdapter;
import com.uta.appeventosuta.adapter.HomeSliderAdapter;
import com.uta.appeventosuta.adapter.UpcomingEventAdapter;
import com.uta.appeventosuta.control.Controller;
import com.uta.appeventosuta.model.Category;
import com.uta.appeventosuta.model.Event;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    Timer timer;
    int page_position = 0;
    //Data data;
    private int dotscount;
    private ImageView[] dots;
    private List<Category> categoryList = new ArrayList<>();
    private RecyclerView rvCategories, rvUpcomingEvents, pRecyclerView;
    private CategoryAdapter categoryAdapter;
    private UpcomingEventAdapter upcomingEventAdapter;
    //private PopularProductAdapter pAdapter;
    private Integer[] images = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4, R.drawable.slider5};
    private Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    public HomeFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //data = new Data();
        rvCategories = view.findViewById(R.id.rvCategoriesH);
        rvUpcomingEvents = view.findViewById(R.id.rvUpcomingEventsH);
        pRecyclerView = view.findViewById(R.id.popular_product_rv);

        getCategoryList();
        getUpcomingEventsList();

        /*pAdapter = new PopularProductAdapter(data.getPopularList(), getContext(), "Home");
        RecyclerView.LayoutManager pLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        pRecyclerView.setLayoutManager(pLayoutManager);
        pRecyclerView.setItemAnimator(new DefaultItemAnimator());
        pRecyclerView.setAdapter(pAdapter);*/

        timer = new Timer();
        viewPager = view.findViewById(R.id.viewPager);

        sliderDotspanel = view.findViewById(R.id.sliderDots);

        HomeSliderAdapter viewPagerAdapter = new HomeSliderAdapter(getContext(), images);
        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        scheduleSlider();
        return view;
    }

    public void scheduleSlider() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == dotscount) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                viewPager.setCurrentItem(page_position, true);
            }
        };
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 4000);
    }

    @Override
    public void onStop() {
        timer.cancel();
        super.onStop();
    }

    @Override
    public void onPause() {
        timer.cancel();
        super.onPause();
    }

    public void onLetsClicked(View view) {
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("INICIO");
    }

    private void getCategoryList() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/getAllCategories.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                ArrayList<Category> categories = Category.getCategoriesFromJson(new JSONArray(response));
                categoryAdapter = new CategoryAdapter(categories, getContext(), "Home");
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                rvCategories.setLayoutManager(mLayoutManager);
                rvCategories.setItemAnimator(new DefaultItemAnimator());
                rvCategories.setAdapter(categoryAdapter);
            } catch (JSONException e) {
                System.err.println(e);
            }
        }, error -> Toast.makeText(context, Controller.getBigMessage("Error al cargar categorías"),Toast.LENGTH_LONG).show());
        queue.add(stringRequest);
    }

    private void getUpcomingEventsList() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/getAllCourses.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                ArrayList<Event> events = Event.getEventsFromJson(new JSONArray(response));
                upcomingEventAdapter = new UpcomingEventAdapter(events, getContext(), "Home");
                RecyclerView.LayoutManager nLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                rvUpcomingEvents.setLayoutManager(nLayoutManager);
                rvUpcomingEvents.setItemAnimator(new DefaultItemAnimator());
                rvUpcomingEvents.setAdapter(upcomingEventAdapter);
            } catch (JSONException e) {
                System.err.println(e);
            }
        }, error -> Toast.makeText(context, Controller.getBigMessage("Error al cargar eventos"),Toast.LENGTH_LONG).show());
        queue.add(stringRequest);
    }

}
