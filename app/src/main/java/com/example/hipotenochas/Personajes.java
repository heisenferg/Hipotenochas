package com.example.hipotenochas;


import android.graphics.drawable.Drawable;




public class Personajes {



    private String nombre;
    private Drawable imagenes;

    public Personajes(String nombre, Drawable imagenes) {
        setNombre(nombre);
        setImagenes(imagenes);
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Drawable getImagenes() {
        return imagenes;
    }

    public void setImagenes(Drawable imagenes) {
        this.imagenes = imagenes;
    }




}


