package com.conversor.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Convertidor {

    private static final String API_KEY = "c9b4ca3ca85ad4bc17858b86"; // Introduce tu API Key aquí
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // Método para convertir con la API de ExchangeRate-API
    public static double convertir(String monedaOrigen, String monedaDestino, double cantidad) {
        String urlString = construirUrl(monedaOrigen, monedaDestino, cantidad);
        String respuestaApi = realizarPeticion(urlString);

        if (respuestaApi != null) {
            return obtenerResultadoDesdeJson(respuestaApi);
        }

        // En caso de error, devolver -1
        return -1;
    }

    // Método para construir la URL de la petición
    private static String construirUrl(String monedaOrigen, String monedaDestino, double cantidad) {
        return BASE_URL + API_KEY + "/pair/" + monedaOrigen + "/" + monedaDestino + "/" + cantidad;
    }

    // Método para realizar la petición a la API
    private static String realizarPeticion(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;

            while ((linea = lector.readLine()) != null) {
                respuesta.append(linea);
            }
            lector.close();

            return respuesta.toString();
        } catch (Exception e) {
            System.out.println("Error al realizar la petición a la API: " + e.getMessage());
        }
        return null;
    }

    // Método para obtener el resultado desde la respuesta JSON
    private static double obtenerResultadoDesdeJson(String respuestaApi) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(respuestaApi);
            return rootNode.path("conversion_result").asDouble();
        } catch (Exception e) {
            System.out.println("Error al procesar la respuesta JSON: " + e.getMessage());
        }
        return -1;
    }
}
