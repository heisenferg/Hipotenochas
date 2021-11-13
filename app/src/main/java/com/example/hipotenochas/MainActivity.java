package com.example.hipotenochas;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int HIPOTENOCHA=-1;
    public static final int NOHIPOTENOCHA=0;

   private int [][] celda;
   private final ArrayList<Personajes> personajesArray = new ArrayList<>();
   private Personajes personajeElegido;
    Niveles nivel = new nFacil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Personajes
        Personajes gato1 =new Personajes("Gato Dubitativo",
                ContextCompat.getDrawable(this, R.drawable.gato));
        Personajes gato2 = new Personajes("Gato engadado",
                ContextCompat.getDrawable(this, R.drawable.gato2));
        Personajes gato3 = new Personajes("Gato triste",
                ContextCompat.getDrawable(this, R.drawable.gato3));
        personajesArray.add(gato1);
        personajesArray.add(gato2);
        personajesArray.add(gato3);
        personajeElegido=gato1;



        if (nivel.getNivel()==0){
            iniciarPartida(new nFacil());
         //   Recorrer();
        } else if (nivel.getNivel()==1){
            iniciarPartida(new nMedio());
          //  Recorrer();
        } else if (nivel.getNivel()==2){
            iniciarPartida(new nDificil());
          //  Recorrer();
        }


    }

    // Añadir botones
    public void iniciarPartida(Niveles nivel){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;



        GridLayout gridLayout =  (GridLayout) findViewById(R.id.gridLay);
     //   RelativeLayout r = (RelativeLayout) findViewById(R.id.layout_principal);
        Button boton;
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.setMargins(0, 0, 0, 0);
        gridLayout.setRowCount(nivel.getFilas());
        gridLayout.setColumnCount(nivel.getFilas());

        int casillas = nivel.getFilas()* nivel.getFilas();

        celda = new int [nivel.getFilas()][nivel.getFilas()];


        for (int i = 0; i < casillas; i++) {
            boton = new Button(this);
            boton.setLayoutParams(new ViewGroup.LayoutParams(
                    gridLayout.getLayoutParams().width/nivel.getFilas(),
                    gridLayout.getLayoutParams().height/nivel.getFilas()));

            boton.setId(View.generateViewId());
            boton.setOnClickListener(this);
            gridLayout.addView(boton,i);
        }

        for (int i=0; i< gridLayout.getChildCount(); i++){
            View v;
            v= gridLayout.getChildAt(i);
            if (v.getClass().getSimpleName().equals("Button")) {
                boton = (Button) v;
                boton.setBackgroundColor(Color.rgb(255,0,0));
            }
        }

    }
/*
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
*/
    //Respuesta
    @Override
    public void onClick(View v) {
        if (v.getClass().getSimpleName().equals("Button")){
            Button boton = (Button) v;
            boton.setBackgroundColor(Color.rgb(0,255,0));

        }
    }




    public void cambiarGato(){
        Dialog personaje = new Dialog(this);
        setContentView(R.layout.spinnerpersonajes);
       personaje.setContentView(R.layout.spinnerpersonajes);
        personaje.setTitle("Personaje gatuno");
        Spinner spin = personaje.findViewById(R.id.spin_gatos);
        AdaptadorPersonalizado adaptadorPersonalizado = new AdaptadorPersonalizado(this, personajesArray);
        spin.setAdapter(adaptadorPersonalizado);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                personajeElegido = personajesArray.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                personaje.dismiss();
            }

        });
        personaje.show();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.mimenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        Instrucciones instrucciones = new Instrucciones();
        switch (id){
            case R.id.seleccion:
                cambiarGato();
                Toast.makeText(getApplicationContext(), R.string.cambioPersonaje, Toast.LENGTH_LONG).show();
                return true;
            case R.id.instrucciones:
                instrucciones();
                return true;
            case R.id.comenzarJuego:
                Toast.makeText(getApplicationContext(), R.string.toastcomenzar,
                        Toast.LENGTH_LONG).show();
                iniciarPartida(nivel);
                return true;
                //Recorrer();
            case R.id.configurar:
                Toast.makeText(getApplicationContext(), R.string.confi,
                        Toast.LENGTH_LONG).show();

                return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void instrucciones() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Instrucciones");
        builder.setMessage(R.string.textInstruc);
        builder.setNegativeButton("Cerrar instrucciones", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}