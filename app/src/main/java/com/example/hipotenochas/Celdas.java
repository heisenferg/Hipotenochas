package com.example.hipotenochas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Button;

public class Celdas extends androidx.appcompat.widget.AppCompatButton {

	private final int fila;
	private final int columna;
	private final byte tieneHipotenocha;


	public boolean isPulsada() {
		return pulsada;
	}

	public void setPulsada(boolean pulsada) {
		this.pulsada = pulsada;
	}

	private boolean pulsada = false;


	public Celdas(Context context, int columna, int fila, byte tieneHipotenocha) {
		super(context);
		this.fila = fila;
		this.columna = columna;
		this.tieneHipotenocha = tieneHipotenocha;
	}

	public byte getTieneHipotenocha() {
		return tieneHipotenocha;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

}
