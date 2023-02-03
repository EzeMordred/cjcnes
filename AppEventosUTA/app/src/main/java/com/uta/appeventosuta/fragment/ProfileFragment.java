package com.uta.appeventosuta.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeventosuta.R;
import com.uta.appeventosuta.control.Controller;
import com.uta.appeventosuta.model.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {

    TextView txtId, txtName, txtBirthDate, txtEmail, txtUser;

    String idUser;
    Context context;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public ProfileFragment(String idUser, Context context) {
        this.idUser = idUser;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("PERFIL");
        initComponents(view);
        loadUserData();
    }

    private void initComponents(View view) {
        txtId = view.findViewById(R.id.txtIdP);
        txtName = view.findViewById(R.id.txtNameP);
        txtBirthDate = view.findViewById(R.id.txtBirthDateP);
        txtEmail = view.findViewById(R.id.txtEmailP);
        txtUser = view.findViewById(R.id.txtUsernameP);
    }

    private void loadUserData() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/getPerson.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                Person user = Person.jsonToPerson(new JSONObject(response));
                txtId.setText(user.getId());
                txtName.setText(String.format("%s %s", user.getName(), user.getLastName()));
                txtBirthDate.setText(user.getBirthDate());
                txtEmail.setText(user.getEmail());
            } catch (JSONException e) {
                System.err.println(e);
            }
        }, error -> Toast.makeText(context, Controller.getBigMessage("Error al cargar datos"),Toast.LENGTH_LONG).show()) {
            @Override
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("ID_PERSONA", idUser);
                return params;
            }
        };
        queue.add(stringRequest);
    }

}