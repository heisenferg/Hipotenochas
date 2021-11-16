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

    public void limpiarTablero(int posColumna, int posFila, int [][] celdas){

        int columnaAnterior= posColumna-1,columnaPosterior=posColumna+1;
        int filaAnterior=posFila-1, filaPosterior=posFila+1;

        for (int k=0; k<posColumna; k++){
            for (int h=0; h < posFila; h++){
                if (celdas[k][h]==0){
                    for (int i=columnaAnterior; i<=columnaPosterior; i++){
                        for (int j=filaAnterior; j<=filaPosterior; j++){
                            Celdas[][] celda= new Celdas [i][j];
                            try {
                                if (celdas[i][j] == 0) {
                                    celda[i][j].setBackgroundResource(R.drawable.boton2);
                                }
                            } catch (Exception e){
                            }
                        }
                    }
                }


            }
        }

    }




    public Tablero(Niveles nivel) {
       celdas = generarTablero(nivel).getCeldas();
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

    public Tablero generarTablero(Niveles nivel) {
        int[][] celdas = new int[nivel.getFilas()][nivel.getFilas()];
        int suma = 0;
        Random aleatorio = new Random();
        Tablero tablero = new Tablero(celdas);

        // Bucle para poner a 0 las celdas
        for (int i=0; i<nivel.getFilas(); i++){
            for (int j=0; j<nivel.getFilas(); j++){
                celdas[i][j]=NOHIPOTENOCHA;
           }
        }
        // Bucle para poner -1 en filas y columnas ramdom siempre que no haya más que las del nivel y no la haya ya.
        for (int k = 0; k<= nivel.getHipotenochas();k++){
            int filaRandom = aleatorio.nextInt(nivel.getFilas()-1);
            int columnaRandom = aleatorio.nextInt(nivel.getFilas()-1);
                if (suma < nivel.getHipotenochas() && celdas[filaRandom][columnaRandom] != HIPOTENOCHA){
                    celdas[filaRandom][columnaRandom] = HIPOTENOCHA;
                    suma++;
                }
        }
        return tablero;
    }



}
