package com.uta.appeventosuta.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeventosuta.R;
import com.uta.appeventosuta.adapter.CategoryAdapter;
import com.uta.appeventosuta.control.Controller;
import com.uta.appeventosuta.model.Category;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    private ArrayList<Category> categoryList = new ArrayList<>();
    private RecyclerView rvCategories;
    private CategoryAdapter categoryAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    private Context context;

    public CategoryFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        rvCategories = view.findViewById(R.id.rvCategoriesF);
        getCategoryList();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("CATEGORÍAS");
    }

    private void getCategoryList() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/getAllCategories.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                ArrayList<Category> categories = Category.getCategoriesFromJson(new JSONArray(response));
                categoryAdapter = new CategoryAdapter(categories, getContext(), "Category");
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                rvCategories.setLayoutManager(mLayoutManager);
                rvCategories.setItemAnimator(new DefaultItemAnimator());
                rvCategories.setAdapter(categoryAdapter);
            } catch (JSONException e) {
                System.err.println(e);
            }
        }, error -> Toast.makeText(context, Controller.getBigMessage("Error al cargar categorías"),Toast.LENGTH_LONG).show());
        queue.add(stringRequest);
    }

}