package com.example.hipotenochas;

public class celda {
    private int fila;
    private byte hipotenocha;

    public celda(int fila, byte hipotenocha) {
        this.fila = fila;
        this.hipotenocha = hipotenocha;
    }



    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public byte getHipotenocha() {
        return hipotenocha;
    }

    public void setHipotenocha(byte hipotenocha) {
        this.hipotenocha = hipotenocha;
    }
}
