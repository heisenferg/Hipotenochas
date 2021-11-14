package com.example.hipotenochas;

import static com.example.hipotenochas.MainActivity.HIPOTENOCHA;

public class rellenarCeldas{
    private int [][] celda;

    public rellenarCeldas() {

    }

    public int[][] getCelda() {
        return celda;
    }

    public void setCelda(int[][] celda) {
        this.celda = celda;
    }

    public rellenarCeldas(int[][] celda) {
        this.celda = celda;
    }

    public rellenarCeldas crearTablero(Niveles nivel){
        int [][] celda = new int[nivel.getFilas()][nivel.getFilas()];
        int cuenta=0;

        for (int i=0; i< nivel.getFilas(); i++){
            int cuenta2=0;
            for (int j=0; j<nivel.getFilas();j++){
                celda[i][j]=0;
                if (cuenta<nivel.getHipotenochas()){
                    celda[i][j]= HIPOTENOCHA;
                    cuenta++;
                    cuenta2++;
                } else {
                    celda[i][j]=0;
                }
            }
        }
        rellenarCeldas tablero = new rellenarCeldas(celda);
        return tablero;

    }

}

