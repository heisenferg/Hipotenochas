package com.example.hipotenochas;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.app.Person;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int HIPOTENOCHA=-1;
    public static final int NOHIPOTENOCHA=0;
   Niveles nivel = new nFacil();
   private int [][] celda;
   private final ArrayList<Personajes> personajesArray = new ArrayList<>();
   private Personajes personajeElegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Desde aquí

        Personajes personaje = new Personajes();

        Spinner listaSpinner = (Spinner) findViewById(R.id.personajes);
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Collections.singletonList(personaje.getNombre()));
        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        listaSpinner.setAdapter(adaptador);
        listaSpinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);


      /Hasta aquí*/



        if (nivel.getNivel()==0){
            iniciarPartida(new nFacil());
            Recorrer();
        } else if (nivel.getNivel()==1){
            iniciarPartida(new nMedio());
            Recorrer();
        } else if (nivel.getNivel()==2){
            iniciarPartida(new nDificil());
            Recorrer();
        }


    }

    // Añadir botones
    public void iniciarPartida(Niveles nivel){

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLay);
        Button boton;

        gridLayout.setRowCount(nivel.getFilas());
        gridLayout.setColumnCount(nivel.getFilas());
        int casillas = nivel.getFilas()* nivel.getFilas();

        celda = new int [nivel.getFilas()][nivel.getFilas()];

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.setMargins(0, 0, 0, 10);

            for (int i = 0; i < casillas; i++) {
                boton = new Button(this);
                boton.setLayoutParams(new ViewGroup.LayoutParams(
                         gridLayout.getLayoutParams().width/nivel.getFilas(),
                        gridLayout.getLayoutParams().height/nivel.getFilas()));

                boton.setId(View.generateViewId());
                boton.setOnClickListener(this);
                gridLayout.addView(boton,i);
            }


    }

    public void Recorrer(){
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLay);
       Button boton;

        for (int i=0; i< gridLayout.getChildCount(); i++){
            View v;
            v= gridLayout.getChildAt(i);
            if (v.getClass().getSimpleName().equals("Button")) {
                boton = (Button) v;
               boton.setBackgroundColor(Color.rgb(255,0,0));
            }
        }
    }

    //Respuesta
    @Override
    public void onClick(View v) {
        if (v.getClass().getSimpleName().equals("Button")){
            Button boton = (Button) v;
            boton.setBackgroundColor(Color.rgb(0,255,0));
            accion(boton);
        }
    }


    //Desde aquí




 public void cambiarGato(){
        Dialog personaje = new Dialog(this);
        personaje.setContentView(R.layout.spinnerpersonajes);
        personaje.setTitle("Personaje gatuno");
        Spinner spin = personaje.findViewById(R.id.spin_gatos);
        AdaptadorPersonalizado adaptadorPersonalizado = new AdaptadorPersonalizado(this, personajesArray);
        spin.setAdapter((SpinnerAdapter) adaptadorPersonalizado);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                personajeElegido = personajesArray.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
 }



















    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.mimenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.personajes:
                cambiarGato();
                Toast.makeText(getApplicationContext(), "Cambiando personaje...", Toast.LENGTH_LONG).show();

                return true;
            case R.id.instrucciones:
                Toast.makeText(getApplicationContext(), "Se ha pulsado en Cliente",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.comenzarJuego:
                Toast.makeText(getApplicationContext(), "Se ha pulsado en Cliente",
                        Toast.LENGTH_LONG).show();
            case R.id.configurar:
                Toast.makeText(getApplicationContext(), "Se ha pulsado en facturas",
                        Toast.LENGTH_LONG).show();

                return true;
        }


        return super.onOptionsItemSelected(item);
    }



    public void accion(Button boton){

    }
}