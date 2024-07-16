package com.conversor;

import java.util.Arrays;
import java.util.List;

import com.conversor.view.Menu;

public class Main {
    public static void main(String[] args) {
        List<String> monedas = Arrays.asList("USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD");
        Menu menu = new Menu(monedas);
        menu.mostrarMenu();
    }
}