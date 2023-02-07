package com.uta.appeventosuta.mail;

import android.os.AsyncTask;
import android.widget.Toast;

import com.uta.appeventosuta.activity.MainActivity;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail extends AsyncTask<String, Void, Boolean> {

    private final String emailOrigen = "reflejatusentir@outlook.com";
    private final String password = "Chris1301199810100RTS";
    private Session session;
    //private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progressDialog = ProgressDialog.show(MainActivity.this, "Por favor espere", "Enviando correo...", true, false);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String emailDestino = params[0];
        String mensaje = "Mensaje desde la aplicación";

        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.office365.com");
            props.put("mail.smtp.port", "587");

            session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(emailOrigen, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailOrigen));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino));
            message.setSubject("Prueba de correo");
            message.setContent(mensaje, "text/html; charset=utf-8");
            Transport.send(message);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        /*progressDialog.dismiss();
        if (aBoolean) {
            Toast.makeText(MainActivity.this, "Correo enviado exitosamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Error al enviar el correo", Toast.LENGTH_LONG).show();
        }*/
    }

    private String generateCode() {
        String code = "";
        for (int i = 0; i < 4; i++) {
            code += String.valueOf((int) Math.floor(Math.random() * (9) + 1));
        }
        return code;
    }

}
