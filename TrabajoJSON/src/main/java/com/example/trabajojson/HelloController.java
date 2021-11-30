package com.example.trabajojson;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

public class HelloController implements Initializable {
    @FXML
    ComboBox<String> cajaPokemon;

    private Map<String, String> rutaPokemon;
    private Map<String, String> imagenPokemon;
    private Map<String, String> imagenPokemonShiny;
    @FXML
    TextArea resultado;
    @FXML
    TextArea resultadoMovimientos;
    @FXML
    TextArea resultadoHabilidades;
    @FXML
    //Image imagenPokemon = new Image(getClass().getResourceAsStream(""));
    ImageView imagenSprite;
    @FXML
    ImageView imagenSpriteShiny;//new ImageView(imagenPokemon);


    private void lecturaJSON(String pokemon){

        InputStream input;
        String respuesta;
        JSONObject jsonGeneral;
        String juego;


            try {
                resultado.setText("");
                input = new URL(rutaPokemon.get(pokemon)).openStream();
                BufferedReader bis = new BufferedReader(new InputStreamReader(input));
                respuesta = bis.readLine();
                jsonGeneral = new JSONObject(respuesta);
                JSONArray arrayJuegos = jsonGeneral.getJSONArray("game_indices");

                for (int i = 0; i < arrayJuegos.length(); i++) {
                    //System.out.println(Thread.currentThread().getName());
                    JSONObject infojuego = arrayJuegos.getJSONObject(i);
                    JSONObject ver = infojuego.getJSONObject("version");
                    juego = ver.getString("name");

                    resultado.appendText(juego);
                    resultado.appendText("\n");
                    resultado.setVisible(true);


                    //System.out.println(titulo);


                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        public void lecturaJSON2(String pokemon){

            InputStream input;
            String respuesta;
            JSONObject jsonGeneral2;
            String habilidad;

            try {
                resultadoHabilidades.setText("");
                input = new URL(rutaPokemon.get(pokemon)).openStream();
                BufferedReader bis = new BufferedReader(new InputStreamReader(input));
                respuesta = bis.readLine();
                jsonGeneral2 = new JSONObject(respuesta);
                JSONArray arrayHabilidades = jsonGeneral2.getJSONArray("abilities");

                for (int j = 0; j < arrayHabilidades.length(); j++) {
                    //System.out.println(Thread.currentThread().getName());
                    JSONObject infoHabilidades = arrayHabilidades.getJSONObject(j);
                    JSONObject ver1 = infoHabilidades.getJSONObject("ability");
                    habilidad = ver1.getString("name");

                    resultadoHabilidades.appendText(habilidad);
                    resultadoHabilidades.appendText("\n");
                    resultadoHabilidades.setVisible(true);
                }
            } catch (IOException e){
                e.printStackTrace();
            }

        }


   public void lecturaJSON3(String pokemon){

        InputStream input;
        String respuesta;
        JSONObject jsonGeneral3;
        String movimientos;

        try {
            resultadoMovimientos.setText("");
            input = new URL(rutaPokemon.get(pokemon)).openStream();
            BufferedReader bis = new BufferedReader(new InputStreamReader(input));
            respuesta = bis.readLine();
            jsonGeneral3 = new JSONObject(respuesta);
            JSONArray arrayMovimientos = jsonGeneral3.getJSONArray("moves");

            for (int j = 0; j < arrayMovimientos.length(); j++) {
                //System.out.println(Thread.currentThread().getName());
                JSONObject infoMovimientos = arrayMovimientos.getJSONObject(j);
                JSONObject ver1 = infoMovimientos.getJSONObject("move");
                movimientos = ver1.getString("name");

                resultadoMovimientos.appendText(movimientos);
                resultadoMovimientos.appendText("\n");
                resultadoMovimientos.setVisible(true);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      mapas();
      acciones();
      mapaImagen();
      mapaImagenShiny();
        resultado.setVisible(false);
        resultadoHabilidades.setVisible(false);
        resultadoMovimientos.setVisible(false);
        imagenSprite.setVisible(true);
        imagenSpriteShiny.setVisible(true);
        resultado.setEditable(false);
        resultadoHabilidades.setEditable(false);
       resultadoMovimientos.setEditable(false);
    }

    private void acciones() {


        cajaPokemon.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                lecturaJSON(t1);
                lecturaJSON2(t1);
                lecturaJSON3(t1);
                dibujarPokemon(t1);
                dibujarPokemonShiny(t1);

            }

        });
    }

    private void dibujarPokemon(String pokemon){

    imagenSprite.setImage(new Image (imagenPokemon.get(pokemon)));

    }
    private void dibujarPokemonShiny(String pokemon){

        imagenSpriteShiny.setImage(new Image (imagenPokemonShiny.get(pokemon)));

    }

    private void mapas() {
        rutaPokemon = new HashMap<String, String>();
        rutaPokemon.put("Mew", "https://pokeapi.co/api/v2/pokemon/mew");
        rutaPokemon.put("Celebi", "https://pokeapi.co/api/v2/pokemon/celebi");
        rutaPokemon.put("Jirachi", "https://pokeapi.co/api/v2/pokemon/jirachi");
        rutaPokemon.put("Manaphy", "https://pokeapi.co/api/v2/pokemon/manaphy");
        rutaPokemon.put("Victini", "https://pokeapi.co/api/v2/pokemon/victini");



        for (Map.Entry<String, String> entry : rutaPokemon.entrySet()) {
            cajaPokemon.getItems().add(entry.getKey());


        }
    }
    private void mapaImagen() {
        imagenPokemon = new HashMap<String, String>();
        imagenPokemon.put("Mew", "https://www.pkparaiso.com/imagenes/espada_escudo/sprites/animados-gigante/mew.gif");
        imagenPokemon.put("Celebi", "https://goelite.mx/images/celebi_2x.gif?crc=157243152");
        imagenPokemon.put("Jirachi", "https://professorlotus.com/Sprites/Jirachi.gif");
        imagenPokemon.put("Manaphy", "https://cdn.shopify.com/s/files/1/1793/0115/files/Manaphy_160x160.gif?v=1602764909");
        imagenPokemon.put("Victini", "https://projectpokemon.org/home/uploads/monthly_2018_03/iB70MJt.gif.4a6d1399e997748886dbd9cc6cc008c6.gif");


    }
    private void mapaImagenShiny() {
        imagenPokemonShiny = new HashMap<String, String>();
        imagenPokemonShiny.put("Mew", "https://www.pkparaiso.com/imagenes/espada_escudo/sprites/animados-gigante/mew-s.gif");
        imagenPokemonShiny.put("Celebi", "https://www.pkparaiso.com/imagenes/espada_escudo/sprites/animados-gigante/celebi-s.gif");
        imagenPokemonShiny.put("Jirachi", "https://cdn.shopify.com/s/files/1/1793/0115/files/Jirachi_70dd1f2f-00fc-4a82-bfe8-af5441d78292_160x160.gif?v=1582188333");
        imagenPokemonShiny.put("Manaphy", "https://cdn.shopify.com/s/files/1/1793/0115/files/Manaphy_52a1681f-4e5c-47e1-b22d-de1ae90a0f3e_160x160.gif?v=1606296090");
        imagenPokemonShiny.put("Victini", "https://www.pokencyclopedia.info/sprites/3ds/ani_6_shiny/3ani_-S_494__xy.gif");


    }



}