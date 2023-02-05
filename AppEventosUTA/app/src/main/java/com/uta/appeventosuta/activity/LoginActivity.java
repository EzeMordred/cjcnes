package com.uta.appeventosuta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class LoginActivity extends AppCompatActivity {

    private EditText txtUser, txtPassword;
    private Person loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
    }

    private void initComponents() {
        txtUser = findViewById(R.id.txtUserL);
        txtPassword = findViewById(R.id.txtPasswordL);
    }

    public void login(View view) {
        String user = txtUser.getText().toString();
        String password = txtPassword.getText().toString();
        if (!user.isEmpty() && !password.isEmpty()) {
            validateUser(user, password);
        }else {
            Toast.makeText(this, Controller.getBigMessage("Ingrese sus credenciales"), Toast.LENGTH_LONG).show();
        }
    }

    private void validateUser(String user, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            if (!response.isEmpty()) {
                try {
                    loggedInUser = Person.jsonToPerson(new JSONObject(response));
                    loadMain();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, Controller.getBigMessage("Credenciales incorrectas"), Toast.LENGTH_LONG).show();
            }
        }, error -> Toast.makeText(this, Controller.getBigMessage("Error de conexión"), Toast.LENGTH_LONG).show()){
            @Override
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("USUARIO", user);
                params.put("CLAVE", password);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void loadMain() {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", loggedInUser);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void loadSignIn(View v) {
        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        finish();
    }

    public void loadRecoverPassword(View v) {
        startActivity(new Intent(getApplicationContext(), RecoverPasswordActivity.class));
        finish();
    }

}