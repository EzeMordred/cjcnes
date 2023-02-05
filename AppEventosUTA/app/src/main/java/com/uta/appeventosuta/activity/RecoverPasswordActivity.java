package com.uta.appeventosuta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeventosuta.R;
import com.uta.appeventosuta.control.Controller;
import com.uta.appeventosuta.control.Validation;
import com.uta.appeventosuta.model.Person;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class RecoverPasswordActivity extends AppCompatActivity {

    private EditText txtUserEmail, txtCode1, txtCode2, txtCode3, txtCode4;
    private Button btnSend;
    private TextView txtCountdown;
    private LinearLayout codePanel;

    private int defaultColor, errorColor;

    private RequestQueue queue;
    private Person user;
    private Boolean request = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        Toolbar toolbar = findViewById(R.id.toolbarRecoveryPassword);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        initComponents();
        defaultColor = ContextCompat.getColor(getApplicationContext(), R.color.dark_text);
        errorColor = ContextCompat.getColor(getApplicationContext(), R.color.error_color);
        this.queue = Volley.newRequestQueue(this);
        initEvents();
    }

    private void initComponents() {
        txtUserEmail = findViewById(R.id.txtUserEmail);
        txtCode1 = findViewById(R.id.txtCode1);
        txtCode2 = findViewById(R.id.txtCode2);
        txtCode3 = findViewById(R.id.txtCode3);
        txtCode4 = findViewById(R.id.txtCode4);
        btnSend = findViewById(R.id.btnSend);
        txtCountdown = findViewById(R.id.txtCountdown);
        codePanel = findViewById(R.id.codePanel);
    }

    private void initEvents() {
        txtUserEmail.setOnKeyListener((view, i, keyEvent) -> {
            if (Validation.validateEmail(txtUserEmail.getText().toString())
                    || Validation.validateUser(txtUserEmail.getText().toString())) {
                txtUserEmail.setTextColor(defaultColor);
            } else {
                txtUserEmail.setTextColor(errorColor);
            }
            defineButtonState();
            return false;
        });
    }

    private void defineButtonState() {
        if (txtUserEmail.getCurrentTextColor() == errorColor
                || txtUserEmail.getText().toString().equals("")) {
            btnSend.setEnabled(false);
            return;
        }
        btnSend.setEnabled(true);
    }

    public void sendRequest(View v) {
        if (validateData() && btnSend.isEnabled()) {
            //solicitarCodigo();
        }
    }

    private boolean validateData() {
        if (!registeredUser() && !registeredMail()) {
            Toast.makeText(this, Controller.getBigMessage("Usuario no encontrado"),Toast.LENGTH_LONG).show();
            txtUserEmail.requestFocus();
            txtUserEmail.setTextColor(errorColor);
            return false;
        }
        if (activeRequest()) {
            Toast.makeText(this, Controller.getBigMessage("Compruebe el código de verificación en su correo"),Toast.LENGTH_LONG).show();
            unlockCodeView();
            return false;
        }
        /*if (usuarioBloqueado()) {
            JOptionPane.showMessageDialog(this, "Cuenta bloqueada. Contáctese con\nidktechnologiesma@gmail.com");
            jtxtCorreoUsuario.requestFocus();
            jtxtCorreoUsuario.setForeground(colorError);
            return false;
        }*/
        return true;
    }

    private boolean registeredMail() {
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/getUserByEmail.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                if(!response.isEmpty()) {
                    user = Person.jsonToPerson((new JSONArray(response)).getJSONObject(0));
                }
            } catch (JSONException e) {
                System.err.println(e);
            }
        }, error -> Log.d(this.getClass().getSimpleName(), "Error de conexión. Email.")) {
            @Override
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("CREDENCIAL", txtUserEmail.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
        return user != null;
    }

    private boolean registeredUser() {
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/getUserByUsername.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                if(!response.isEmpty()) {
                    user = Person.jsonToPerson((new JSONArray(response)).getJSONObject(0));
                }
            } catch (JSONException e) {
                System.err.println(e);
            }
        }, error -> Log.d(this.getClass().getSimpleName(), "Error de conexión. Usuario.")) {
            @Override
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("CREDENCIAL", txtUserEmail.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
        return user != null;
    }

    private boolean activeRequest() {
        String url = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/getRequestByPersonId.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            if(!response.isEmpty()) {
                request = true;
            }
        }, error -> Log.d(this.getClass().getSimpleName(), "Error de conexión. Solicitud.")) {
            @Override
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("CREDENCIAL", user.getName());
                return params;
            }
        };
        queue.add(stringRequest);
        return request;
    }

    private void unlockCodeView() {
        txtUserEmail.setEnabled(false);
        btnSend.setEnabled(false);
        codePanel.setVisibility(View.VISIBLE);
        txtCode1.requestFocus();
    }

}