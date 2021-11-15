package com.example.hipotenochas;

import android.content.Context;

public class Celdas extends androidx.appcompat.widget.AppCompatButton {

    private final int fila;
    //private final byte siHipotenocha;


    public Celdas(Context context, int fila, byte siHipotenocha) {
        super(context);
        this.fila = fila;

     //   this.siHipotenocha = siHipotenocha;
    }

   /* public byte getSiHipotenocha() {
        return siHipotenocha;
    }*/

    public int getFila() {
        return fila;
    }


}
