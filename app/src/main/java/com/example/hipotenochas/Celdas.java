package com.example.hipotenochas;

import android.content.Context;

public class Celdas extends androidx.appcompat.widget.AppCompatButton {

    private final int fila;

    public Celdas(Context context, int fila) {
        super(context);
        this.fila = fila;
    }


    public Celdas(Context context, int fila, int celda) {
        super(context);
        this.fila = fila;
    }

    public int getFila() {
        return fila;
    }


}
