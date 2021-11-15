package com.example.hipotenochas;

import static com.example.hipotenochas.MainActivity.HIPOTENOCHA;
import static com.example.hipotenochas.MainActivity.NOHIPOTENOCHA;

import java.util.Random;

public class Tablero {

    private final int[][] celdas;

    public Tablero(int[][] celdas) {

        this.celdas = celdas;
    }

    public static int sumaraAlrededores(int posColumna, int posFila, int [][] celdas){
        int suma=0;
      int columnaAnterior= posColumna-1,columnaPosterior=posColumna+1;
      int filaAnterior=posFila-1, filaPosterior=posFila+1;
        for (int i=columnaAnterior; i<=columnaPosterior; i++){
            for (int j=filaAnterior; j<=filaPosterior; j++){

                try {
                    if (celdas[i][j] == HIPOTENOCHA) {
                        suma++;
                    }
                } catch (Exception e){
                }
            }
        }
        return suma;
    }

    public Tablero(Niveles nivel) {
       celdas = crearTablero(nivel).getCeldas();
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {

                int suma = celdas[i][j];

                if(suma==NOHIPOTENOCHA){
                    celdas[i][j] = sumaraAlrededores(i, j, celdas);
                }

            }
        }


    }

    public int[][] getCeldas() {
        return celdas;
    }

    public Tablero crearTablero(Niveles nivel) {
        int[][] casillas = new int[nivel.getFilas()][nivel.getFilas()];
        int suma = 0;
        Random aleatorio = new Random();
        Tablero tablero = new Tablero(casillas);

        // Bucle para poner a 0 las casillas
        for (int i=0; i<nivel.getFilas(); i++){
            for (int j=0; j<nivel.getFilas(); j++){
                casillas[i][j]=NOHIPOTENOCHA;
           }
        }
        // Bucle para poner -1 en filas y columnas ramdom siempre que no haya más que las del nivel y no la haya ya.
        for (int k = 0; k<= nivel.getHipotenochas();k++){
            int filaRandom = aleatorio.nextInt(nivel.getFilas()-1);
            int columnaRandom = aleatorio.nextInt(nivel.getFilas()-1);
            if (suma < nivel.getHipotenochas() && casillas[filaRandom][columnaRandom] != HIPOTENOCHA){
                casillas[filaRandom][columnaRandom] = HIPOTENOCHA;
                suma++;
            }
        }
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

}
