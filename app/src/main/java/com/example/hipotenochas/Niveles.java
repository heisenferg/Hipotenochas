package com.example.hipotenochas;

public abstract class Niveles {

    //Clase nivel fácil
    public static final class nFacil {
        public static final int FILAS=8;
        public static final int HIPOTENOCHAS=2;
        public static final int NIVEL=0;
    }

    //Clase nivel medio
    public static final class nMedio {
        public static final int FILAS=12;
        public static final int HIPOTENOCHAS=30;
        public static final int NIVEL=1;
    }

    //Clase nivel dificil
    public static final class nDificil {
        public static final int FILAS=16;
        public static final int HIPOTENOCHAS=60;
        public static final int NIVEL=2;
    }

    abstract public int getFilas();
    abstract public int getHipotenochas();
    abstract public int getNivel();

}
