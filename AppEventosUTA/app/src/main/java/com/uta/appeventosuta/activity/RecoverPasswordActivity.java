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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.*;

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
            sendCode();
        }
    }

    private void sendCode() {
        if (user != null) {
            sendEmailCode(user.getEmail());
            unlockCodeView();
        }
    }

    private void sendEmailCode(String destinatario) {
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(propiedad);

        String emisor = "idktechnologiesma@gmail.com";
        String clave = "nuestraempresa";
        String asunto = "IDK: Recuperación de contraseña";
        String codigo = generateCode();
        String mensaje = "<table style=\"max-width: 600px; padding: 10px; margin:0 auto; border-collapse: collapse;\">\n"
                + "\n"
                + "            <tr>\n"
                + "                <td style=\"padding: 0\">\n"
                + "                    <img style=\"padding: 0; display: block\" src=\"https://i.postimg.cc/L8mpNs6q/Whats-App-Image-2022-01-17-at-7-09-25-PM.jpg\" width=\"100%\">\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "\n"
                + "            <tr>\n"
                + "                <td style=\"background-color: #ecf0f1\">\n"
                + "                    <div style=\"color: #34495e; margin: 4% 10% 2%; text-align: justify;font-family: sans-serif\">\n"
                + "                        <h2 style=\"color: #2991f9; margin: 0 0 7px\">Hola,</h2>\n"
                + "                        <p style=\"margin: 2px; font-size: 15px\">\n"
                + "                            usted ha solicitado recuperar su contraseña.<br><br>\n"
                + "                            Su código de verificación es:</p>\n"
                + "                        <p>"
                + codigo
                + "                        </p>\n"
                + "\n"
                + "                        <p style=\"color: #b3b3b3; font-size: 12px; text-align: center;margin: 30px 0 0\">IDK-Technologies 2022.</p>\n"
                + "                    </div>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "            <tr>\n"
                + "                <td style=\"padding: 0\">\n"
                + "                    <img style=\"padding: 0; display: block\" src=\"https://i.postimg.cc/8C3xQkDm/Whats-App-Image-2022-01-17-at-7-09-25-PM-1.jpg\" width=\"100%\">\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "        </table>";

        MimeMessage mail = new MimeMessage(session);

        try {
            mail.setFrom(new InternetAddress(emisor));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mail.setSubject(asunto);
            mail.setContent(mensaje, "text/html; charset=utf-8");

            Transport transporte = session.getTransport("smtp");
            transporte.connect(emisor, clave);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();

            backUpCode(codigo);
            Toast.makeText(this, Controller.getBigMessage("Te hemos enviado un correo\ncon tu clave de recuperación"),Toast.LENGTH_LONG).show();
        } catch (MessagingException ex) {
            Log.d(this.getClass().getSimpleName(), ex.toString());
        }
    }

    private String generateCode() {
        String code = "";
        for (int i = 0; i < 4; i++) {
            code += String.valueOf((int) Math.floor(Math.random() * (9) + 1));
        }
        return code;
    }

    private void backUpCode(String code) {
        
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