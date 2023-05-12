package com.example.conversor.controllers;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class conversorController implements Initializable {

    ArrayList<String> tiposConversion = new ArrayList<>();
    ValorMonedas valorMonedas = new ValorMonedas();
    Double tipoMoneda = 0D;
    Double conversion=0D;


    @FXML
    public TextField boxImporte;
    @FXML
    public TextField boxConvertido;
    @FXML
    public Button btnConvertir;
    @FXML
    private Label lblPrueba;

    @FXML
    public ComboBox<String> cbTiposConversion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tiposConversion.add("De Dolar Oficial a Pesos Argentinos"); //operacion 0
        tiposConversion.add("De Pesos Argentinos a Dolar Oficial");//operacion 1
        tiposConversion.add("De Dolar-Blue a Pesos Argentinos");//operacion 2
        tiposConversion.add("De Pesos Argentinos a Dolar-Blue");//operacion 3
        tiposConversion.add("De Euro a Pesos Argentinos");//operacion 4
        tiposConversion.add("De Pesos Argentinos a Euro");//operacion 5
        tiposConversion.add("De Euro-Blue a Pesos Argentinos");//operacion 6
        tiposConversion.add("De Pesos Argentinos a Euro-Blue");//operacion 7


        ObservableList<String> lista = FXCollections.observableArrayList(tiposConversion);

        cbTiposConversion.setItems(lista);
        boxImporte.setTextFormatter(validarMoneda());




    }
    @FXML
    protected void convierte() {
        String valorConvertido = "Faltan Parametros. Por favor seleccione.";
        int tipoOperacion = cbTiposConversion.getSelectionModel().getSelectedIndex();

        if (!boxImporte.getText().isEmpty() && !(tipoOperacion == -1) ) {


            Double valorImporte = Double.valueOf(boxImporte.getText());
            valorConvertido = "Pesos Argentinos";

            if (tipoOperacion == 0) {
                tipoMoneda = valorMonedas.getDolarOficial();
                conversion = valorImporte * tipoMoneda;

            }
            if (tipoOperacion == 1) {
                tipoMoneda = valorMonedas.getDolarOficial();
                conversion = valorImporte / tipoMoneda;
                valorConvertido = "Dolar Oficial";
            }
            if (tipoOperacion == 2) {
                tipoMoneda = valorMonedas.getDolarBlue();
                conversion = valorImporte * tipoMoneda;

            }
            if (tipoOperacion == 3) {
                tipoMoneda = valorMonedas.getDolarBlue();
                conversion = valorImporte / tipoMoneda;
                valorConvertido = "Dolar Blue";
            }
            if (tipoOperacion == 4) {
                tipoMoneda = valorMonedas.getEuro();
                conversion = valorImporte * tipoMoneda;
            }
            if (tipoOperacion == 5) {
                tipoMoneda = valorMonedas.getEuro();
                conversion = valorImporte / tipoMoneda;
                valorConvertido = "Euro oficial";
            }
            if (tipoOperacion == 6) {
                tipoMoneda = valorMonedas.getBlueEuro();
                conversion = valorImporte * tipoMoneda;
            }
            if (tipoOperacion == 7) {
                tipoMoneda = valorMonedas.getBlueEuro();
                conversion = valorImporte / tipoMoneda;
                valorConvertido = "Euro Blue";
            }
        }
        boxConvertido.setText(String.valueOf(conversion));
        lblPrueba.setText(valorConvertido);


    }

    private TextFormatter<Double> validarMoneda() {
        DecimalFormat format = new DecimalFormat("#,##0.00");

        return new TextFormatter<>(c -> {
            if (c.getControlNewText().isEmpty()) {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition(0);
            Object value = format.parse(c.getControlNewText(), parsePosition);

            if (value == null || parsePosition.getIndex() < c.getControlNewText().length()) {
                return null;
            } else {
                return c;
            }
        });
    }


}
