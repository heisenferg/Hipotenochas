package com.example.hipotenochas;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Personajes extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String [] nombre = {"Gato pensativo", "Gato serio", "Gato enfadado"};
    int [] imagenes = {R.drawable.gato, R.drawable.gato2, R.drawable.gato3};

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public class AdaptadorPersonalizado extends ArrayAdapter<String>{

        public AdaptadorPersonalizado(Context context, int resource, String[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getDropDownView(int position, View cnvView, ViewGroup prnt){
            return crearFilaPersonalizada(position,cnvView,prnt);
        }

        @Override
        public View getView (int position, View cnvView, ViewGroup prnt){
            return crearFilaPersonalizada(position,cnvView,prnt);
        }

        public View crearFilaPersonalizada(int position, View converView, ViewGroup prnt){
            LayoutInflater inflater = getLayoutInflater();
            View miFila = inflater.inflate(R.layout.spinnerpersonajes, prnt, false);
            TextView nombreGato = miFila.findViewById(R.id.nombreGato);
            nombreGato.setText(nombre[position]);

            ImageView imagen = miFila.findViewById(R.id.fotoGato);
            imagen.setImageResource(imagenes[position]);

            return miFila;
        }

    }


    public void elegirGato(){
        Dialog gato = new Dialog(this);
        gato.setContentView(R.layout.spinnerpersonajes);
        gato.setTitle("Personajes gatunos");
        Spinner sp = gato.findViewById(R.id.personajes);
        AdaptadorPersonalizado adaptadorPersonalizado =
                new AdaptadorPersonalizado(this, R.layout.spinnerpersonajes, nombre);
        sp.setAdapter(adaptadorPersonalizado);
        sp.setOnItemSelectedListener(this);
    }



}
