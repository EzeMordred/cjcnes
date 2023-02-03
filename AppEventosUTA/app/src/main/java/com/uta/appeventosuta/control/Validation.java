package com.uta.appeventosuta.control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean validateUser(String user) {
        Pattern pattern = Pattern.compile("^(?=.{6,30}$)(?![.])(?!.*[.]{2})[a-zA-Z0-9._]+(?<![.])$");
        Matcher matcher = pattern.matcher(user);
        return matcher.matches();
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /*public static boolean validarFechaDMA(String fecha) {
        if (fecha.length() < 10) {
            return false;
        }
        try {
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            format.setLenient(false);
            format.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[0-9-]+$");
        Matcher matcher = pattern.matcher(fecha);
        return matcher.matches();
    }*/

    public static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,30}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validatePhone(String phone) {
        if (phone.isEmpty()) {
            return true;
        }
        if (phone.length() < 10) {
            return false;
        }
        Pattern pattern = Pattern.compile("^09.*");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean validateId(String id) {
        if (id.length() < 10) {
            return false;
        }
        int checkDigit;
        int oddSum = 0;
        int pairsSum = 0;
        int provinceCode = Integer.parseInt(id.substring(0, 2));//Generamos el código de provincia como entero.
        //Evaluamos que la cédula tenga 10 dígitos, el tercer dígito entre 0 y 6, y un código de provincia válido:
        if (((int) id.charAt(2) - 48) <= 6 && ((provinceCode >= 1 && provinceCode <= 24) || provinceCode == 30)) {
            int odd;
            for (int j = 0; j < 10; j += 2) {
                odd = (Integer.parseInt(id.charAt(j) + "")) * 2;//Transformamos las posiciones impares a enteros.
                if (odd > 9) {//Si la posición impar multiplicada por 2 es mayor a 9, le restamos 9.
                    odd -= 9;
                }
                oddSum += odd;//Suma iterativa de posiciones impares.
                if (j < 8) {//Se controla que el útimo dígito no interfiera en la operación.
                    pairsSum += (Integer.parseInt(id.charAt(j + 1) + ""));//Suma iterativa de posiciones pares.
                }
            }
            checkDigit = (oddSum + pairsSum) % 10;//Se calcula el dígito verificador con el módulo 10.
            if (checkDigit != 0) {
                checkDigit = 10 - checkDigit;//Si el módulo es distinto de cero, se lo resta de 10.
            }
            //Se compara el último resultado con el último dígito de la cédula.
            return checkDigit == (Integer.parseInt(id.charAt(9) + ""));
        }
        return false;
    }

    /*public static void convertirMayuscula(java.awt.event.KeyEvent evt) {
        char validar = evt.getKeyChar();
        if (Character.isLowerCase(validar)) {
            String strCadena = ("" + validar).toUpperCase();
            validar = strCadena.charAt(0);
            evt.setKeyChar(validar);
        }
    }*/

    /*public static void bloquearCopyPaste(java.awt.event.KeyEvent evt) {
        if (evt.isControlDown()) {
            evt.consume();
        }
    }*/

}
