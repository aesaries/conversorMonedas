package com.example.conversor.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.json.*;


public class ValorMonedas {

    Double dolarOficial = 0D;
    Double dolarBlue = 0D;
    Double euro = 0D;
    Double blueEuro = 0D;

    public ValorMonedas() {
        obtenerValoresAPI();

    }

    public void obtenerValoresAPI(){
        try {
            // Establecer la URL de la API REST
            URL url = new URL("https://api.bluelytics.com.ar/v2/latest");

            // Crear una conexión HTTP
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Establecer el método de la petición
            con.setRequestMethod("GET");

            // Leer la respuesta de la API REST
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parsear la respuesta JSON
            JSONObject json = new JSONObject(response.toString());
            JSONObject oficial = json.getJSONObject("oficial");
            JSONObject blue = json.getJSONObject("blue");
            JSONObject euro = json.getJSONObject("oficial_euro");
            JSONObject blueEuro = json.getJSONObject("blue_euro");

            var dolarOficial = oficial.getInt("value_avg");
            var dolarBlue = blue.getInt("value_avg");
            var euroOficial = euro.getInt("value_avg");
            var euroBlue = blueEuro.getInt("value_avg");

            this.dolarOficial = (double)dolarOficial;
            this.dolarBlue = (double)dolarBlue;
            this.euro = (double)euroOficial;
            this.blueEuro = (double)euroBlue;


            // Imprimir los resultados
              } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fallo de conexion");
            mostrarVentana("Error de conexion",
                    "No se puede conectar con el servicio web",
                    "Por favor revise su conexion a internet y vuelva a intentar luego");
        }
    }
    public void mostrarVentana(String titulo, String mensaje, String descripcion) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(mensaje);
        alert.setContentText(descripcion);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.out.println("presiono ok");


            // Acciones a realizar si se presiona el botón "Aceptar"

        } else {
            // Acciones a realizar si se presiona el botón "Cancelar" o se cierra la ventana
            System.out.println("presiono cancel");
        }
        Platform.exit();
    }


    public Double getDolarOficial() {
        return dolarOficial;
    }



    public Double getDolarBlue() {
        return dolarBlue;
    }



    public Double getEuro() {
        return euro;
    }



    public Double getBlueEuro() {
        return blueEuro;
    }


}
