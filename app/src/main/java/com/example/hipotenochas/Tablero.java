package com.example.hipotenochas;

import static com.example.hipotenochas.MainActivity.HIPOTENOCHA;

import java.util.Random;

public class Tablero {

    private final int[][] celdas;

    public Tablero(int[][] casillas) {

        this.celdas = casillas;
    }

    public Tablero(Niveles nivel) {
        celdas = crearTablero(nivel).getCeldas();
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {

                int valor = celdas[i][j];
            /*    if (valor != ) {
                    casillas[i][j] = contarHipotenochasAlrededor(casillas, i, j);
                }*/
            }
        }
    }
/*
    public static int contarHipotenochasAlrededor(int[][] casillas, int celdaX, int celdaY) {
        int totalHipotenochas = 0;
        for (int i = celdaX - 1; i <= celdaX + 1; i++) {
            for (int j = celdaY - 1; j <= celdaY + 1; j++) {
                try {
                    if (casillas[i][j] == NOHIPOTENOCHA) {
                        totalHipotenochas++;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return totalHipotenochas;
    }
*/
    public int[][] getCeldas() {
        return celdas;
    }

    public Tablero crearTablero(Niveles nivel) {
        int[][] casillas = new int[nivel.getFilas()][nivel.getFilas()];
        int hipotenochasTotales = 0;
        for (int i = 0; i < nivel.getFilas(); i++) {
            int maxHipotenochasFila = nivel.getHipotenochas() / nivel.getFilas();
            int hipotenochasCreadas = 0;
            int numeroRandom = getRandomNumber(0, nivel.getFilas() - 1);
            for (int j = 0; j < nivel.getFilas(); j++) {
                casillas[i][j] = 0;
                if (hipotenochasTotales < nivel.getHipotenochas()
                        && hipotenochasCreadas < maxHipotenochasFila
                        && j == numeroRandom) {
                    casillas[i][j] = HIPOTENOCHA;
                    hipotenochasTotales++;
                    hipotenochasCreadas++;
                } else {
                    casillas[i][j] = 0;
                }

            }
        }
        Tablero tablero = new Tablero(casillas);
/*
        while (hipotenochasTotales < nivel.getHipotenochas()) {
            asignarHipotenochas(tablero.getCasillas(), getRandomNumber(0, nivel.getFilas() - 1),
                    getRandomNumber(0, nivel.getFilas() - 1));
            hipotenochasTotales++;
        }
*/
        return tablero;
    }
/*
    private void asignarHipotenochas(int[][] casillas, int i, int j) {
        if (casillas[i][j] == 0) {
            casillas[i][j] = HIPOTENOCHA;
        } else {
            try {
                asignarHipotenochas(casillas, i, j + 1);
            } catch (ArrayIndexOutOfBoundsException ignored) {
                asignarHipotenochas(casillas, i, 0);
            }
        }
    }
*/
    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
