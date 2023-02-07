package com.uta.appeventosuta.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeventosuta.R;
import com.uta.appeventosuta.control.Controller;
import com.uta.appeventosuta.control.Validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    private EditText txtId, txtName, txtLastName, txtEmail, txtUsername, txtPassword, txtConfirmPassword;
    private ArrayList<EditText> fields;
    private TextView txtPasswordCoincidence;
    private Button btnDate, btnCreate, btnCancel;
    private DatePickerDialog datePickerDialog;

    private int defaultColor, errorColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        initComponents();
        defaultColor = ContextCompat.getColor(getApplicationContext(), R.color.dark_text);
        errorColor = ContextCompat.getColor(getApplicationContext(), R.color.error_color);
        initDatePicker();
        initEvents();
    }

    private void initComponents() {
        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtLastName = findViewById(R.id.txtLastName);
        btnDate = findViewById(R.id.btnBirthDate);
        txtEmail = findViewById(R.id.txtEmail);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        txtPasswordCoincidence = findViewById(R.id.txtPasswordCoincidence);
        fields = new ArrayList<>(Arrays.asList(txtId, txtName, txtLastName, txtEmail, txtUsername,
                                                txtPassword, txtConfirmPassword));
        btnCancel = findViewById(R.id.btnCancel);
        btnCreate = findViewById(R.id.btnCreate);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            String date = makeDateString(day, month, year);
            btnDate.setText(date);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //btnDate.setText(getTodayDate());
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - Controller.eighteenYears);
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    private String makeDateString(int day, int month, int year) {
        return year + "-" + month + "-" + day;
        //return day + " " +getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month) {
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return months[month];
    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initEvents() {
        txtId.setOnKeyListener((view, i, keyEvent) -> {
            if (!Validation.validateId(txtId.getText().toString())) {
                txtId.setTextColor(errorColor);
            } else {
                txtId.setTextColor(defaultColor);
            }
            defineButtonState();
            return false;
        });
        txtEmail.setOnKeyListener((view, i, keyEvent) -> {
            if (!Validation.validateEmail(txtEmail.getText().toString())) {
                txtEmail.setTextColor(errorColor);
            } else {
                txtEmail.setTextColor(defaultColor);
            }
            defineButtonState();
            return false;
        });
        txtUsername.setOnKeyListener((view, i, keyEvent) -> {
            if (!Validation.validateUser(txtUsername.getText().toString())) {
                txtUsername.setTextColor(errorColor);
            } else {
                txtUsername.setTextColor(defaultColor);
            }
            defineButtonState();
            return false;
        });
        txtPassword.setOnKeyListener((view, i, keyEvent) -> {
            if (!Validation.validatePassword(txtPassword.getText().toString())) {
                txtPassword.setTextColor(errorColor);
            } else {
                txtPassword.setTextColor(defaultColor);
            }
            defineButtonState();
            return false;
        });
        txtConfirmPassword.setOnKeyListener((view, i, keyEvent) -> {
            if (!txtPassword.getText().toString().equals(txtConfirmPassword.getText().toString())) {
                txtConfirmPassword.setTextColor(errorColor);
                txtPasswordCoincidence.setText("Las contraseñas no coinciden");
            } else {
                txtConfirmPassword.setTextColor(defaultColor);
                txtPasswordCoincidence.setText("");
            }
            defineButtonState();
            return false;
        });
    }

    private void defineButtonState() {
        for (EditText field : fields) {
            if (field.getCurrentTextColor() == errorColor || field.getText().toString().equals("")) {
                btnCreate.setEnabled(false);
                return;
            }
        }
        btnCreate.setEnabled(true);
    }

    public void createAccount(View v) {
        RequestQueue queue = Volley.newRequestQueue(this);;
        String urlPerson = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/saveUserPerson.php";
        StringRequest requestPerson = new StringRequest(Request.Method.POST, urlPerson, response -> {
            Toast.makeText(this,Controller.getBigMessage("Cuenta creada"),Toast.LENGTH_LONG).show();
            createUser();
        }, error -> Toast.makeText(this,Controller.getBigMessage("Error al crear cuenta"),Toast.LENGTH_LONG).show())
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ID_PERSONA", txtId.getText().toString());
                params.put("PRIMER_NOMBRE", formatName(txtName.getText().toString()));
                params.put("PRIMER_APELLIDO", formatName(txtLastName.getText().toString()));
                params.put("FECHA_NACIMIENTO", btnDate.getText().toString());
                params.put("EMAIL", txtEmail.getText().toString());
                params.put("PARTICULAR", "1");
                return params;
            }
        };
        queue.add(requestPerson);
    }

    private String formatName(String name) {
        return String.format("%s%s", name.substring(0,1).toUpperCase(), name.substring(1).toLowerCase());
    }

    private void createUser() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlAccount = "https://proyectosuta2.000webhostapp.com/eventos_uta/models/saveAccount.php";
        StringRequest requestAccount = new StringRequest(Request.Method.POST, urlAccount, response -> {
            Toast.makeText(this,Controller.getBigMessage("Ya puedes iniciar sesión"),Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }, error -> Toast.makeText(this,Controller.getBigMessage("Inténtelo más tarde"),Toast.LENGTH_LONG).show())
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ID_PERSONA", txtId.getText().toString());
                params.put("USUARIO", txtUsername.getText().toString());
                params.put("CLAVE", txtPassword.getText().toString());
                return params;
            }
        };
        queue.add(requestAccount);
    }

    public void returnToLogin(View v) {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }

}