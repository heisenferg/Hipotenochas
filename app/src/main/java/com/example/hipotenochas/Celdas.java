package com.example.hipotenochas;

import android.content.Context;

public class Celdas extends androidx.appcompat.widget.AppCompatButton {

    private final int fila;

    public Celdas(Context context, int fila) {
        super(context);
        this.fila = fila;

     //   this.siHipotenocha = siHipotenocha;
    }
    public Celdas(Context context, int fila, byte siHipotenocha) {
        super(context);
        this.fila = fila;


    }

    public int getFila() {
        return fila;
    }


}
