package com.example.calculadora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;

enum Operacion {SUMA, RESTA, MULTIPLICACION, DIVISION, PORCENTAJE};

public class HelloController implements Initializable, EventHandler<ActionEvent> {
    @FXML
    Button cero, uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve, sumar, restar, multiplicar, dividir, resultado, borrar, porcentaje, coma;
    @FXML
    TextField pantalla;

    String primerOperando = "";
    String segundoOperando  = "";
    double res = 0;

    @FXML
    Label historial;


    Operacion operacion;

    //uno esperando el primer operador

    //dos esperando el segundo operador


    int estado = 1;

    boolean decimal=false;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instancias();
        acciones();

    }

   public void acciones() {
        pantalla.setEditable(false);
        cero.setOnAction(this);
        uno.setOnAction(this);
        dos.setOnAction(this);
        tres.setOnAction(this);
        cuatro.setOnAction(this);
        cinco.setOnAction(this);
        seis.setOnAction(this);
        siete.setOnAction(this);
        ocho.setOnAction(this);
        nueve.setOnAction(this);
        sumar.setOnAction(this);
        restar.setOnAction(this);
        multiplicar.setOnAction(this);
        dividir.setOnAction(this);
        resultado.setOnAction(this);
        borrar.setOnAction(this);
        porcentaje.setOnAction(this);
        coma.setOnAction(this);
    }

    private void instancias() {

    }

    private void inicializarVariables(){
        estado = 1;
        decimal=false;
        primerOperando = "";
        segundoOperando = "";
        pantalla.setText("");
    }

    private Operacion GetOperacion(Button b)
    {
       if (b == sumar)
          return Operacion.SUMA;

       if (b == restar)
           return Operacion.RESTA;

        if (b == multiplicar)
            return Operacion.MULTIPLICACION;

        if (b == dividir)
            return Operacion.DIVISION;

        if (b == porcentaje)
            return Operacion.PORCENTAJE;

        return Operacion.SUMA;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        Button b = (Button) actionEvent.getSource();

        if ((actionEvent.getSource() == cero) ||
           (actionEvent.getSource() == uno) ||
           (actionEvent.getSource() == dos) ||
           (actionEvent.getSource() == tres) ||
           (actionEvent.getSource() == cuatro) ||
           (actionEvent.getSource() == cinco) ||
           (actionEvent.getSource() == seis) ||
           (actionEvent.getSource() == siete) ||
           (actionEvent.getSource() == ocho) ||
           (actionEvent.getSource() == nueve))
        {
            if (estado == 1) {
                primerOperando = primerOperando + b.getText();
                pantalla.setText(primerOperando);
            } else {
                segundoOperando = segundoOperando + b.getText();
                pantalla.setText(segundoOperando);
            }

        }else if ((actionEvent.getSource() == sumar) ||
                  (actionEvent.getSource() == restar) ||
                  (actionEvent.getSource() == multiplicar) ||
                  (actionEvent.getSource() == dividir) ||
                  (actionEvent.getSource() == porcentaje))
        {
            if (estado == 1)
            {
              operacion = GetOperacion((Button) actionEvent.getSource());
              estado = 2;
              decimal=false;
              pantalla.setText(pantalla.getText() + " " + b.getText());

            }
        }
        else if (actionEvent.getSource() == coma){
            if (estado == 1) {
                if (!primerOperando.contains(".")) {
                    primerOperando = primerOperando + b.getText();
                }
            } else {
                if (!segundoOperando.contains(".")) {
                    segundoOperando = segundoOperando + b.getText();
                }
            }
        }


        else if (actionEvent.getSource() == resultado){

            if (estado==2){
                switch (operacion){
                    case SUMA:
                        res =  Double.parseDouble(primerOperando) +  Double.parseDouble(segundoOperando);
                        historial.setText(" Operación: " + primerOperando +" + "+segundoOperando);
                        break;

                    case RESTA:
                        res = Double.parseDouble(primerOperando) -  Double.parseDouble(segundoOperando);
                        historial.setText(" Operación: " + primerOperando +" - "+segundoOperando);
                        break;

                    case MULTIPLICACION:
                        res = Double.parseDouble(primerOperando) * Double.parseDouble(segundoOperando);
                        historial.setText(" Operación: " + primerOperando +" * "+segundoOperando);
                        break;

                    case DIVISION:
                        res = Double.parseDouble(primerOperando) / Double.parseDouble(segundoOperando);
                        historial.setText(" Operación: " + primerOperando +" / "+segundoOperando);
                        break;

                    case PORCENTAJE:
                        res = Double.parseDouble(primerOperando) * Double.parseDouble(segundoOperando)/100.0;
                        historial.setText(" Operación: " + primerOperando +" % "+segundoOperando);
                        break;

                }

                if (res - Math.floor(res) == 0)
                {
                    pantalla.setText(Integer.toString((int)res));
                }
                else {
                    pantalla.setText(Double.toString(res));
                }
                estado =1;
                primerOperando = "";
                segundoOperando = "";
                decimal = false;

            }

        }else if (actionEvent.getSource() == borrar){
          inicializarVariables();
        }

    }
}