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
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity<relleno> extends AppCompatActivity implements View.OnClickListener{

    public static final int HIPOTENOCHA=-1;
    public static final int NOHIPOTENOCHA=0;


   private final ArrayList<Personajes> personajesArray = new ArrayList<Personajes>();
   private Personajes personajeElegido;
    Niveles nivel = new nFacil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Personajes
        Personajes gato1 =new Personajes("Gato Dubitativo",
                ContextCompat.getDrawable(this, R.drawable.gato));
        Personajes gato2 = new Personajes("Gato enfadado",
                ContextCompat.getDrawable(this, R.drawable.gato2));
        Personajes gato3 = new Personajes("Gato triste",
                ContextCompat.getDrawable(this, R.drawable.gato3));
        personajesArray.add(gato1);
        personajesArray.add(gato2);
        personajesArray.add(gato3);
        personajeElegido=gato1;



        if (nivel.getNivel()==0){
           iniciarPartida(new nFacil());

        } else if (nivel.getNivel()==1){
            iniciarPartida(new nMedio());

        } else if (nivel.getNivel()==2){
            iniciarPartida(new nDificil());

        }


    }





    private Tablero tablero;
    private Celdas[][] celda;
    private int hipotenochasRestantes;







    public void iniciarPartida(Niveles nivel) {

        tablero = new Tablero(nivel);
        celda = crearPartida(nivel);
        this.hipotenochasRestantes = nivel.getHipotenochas();
    }

    public Celdas[][] crearPartida(Niveles nivel) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        GridLayout grid = findViewById(R.id.gridLay);
        // Borramos todo para el botón iniciar partida
        grid.removeAllViews();
        grid.setColumnCount(nivel.getFilas());
        grid.setRowCount(nivel.getFilas());
        celda = new Celdas[nivel.getFilas()][nivel.getFilas()];


        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.setMargins(0, 0, 0, 0);
        param.height = ViewGroup.LayoutParams.MATCH_PARENT;
        param.width = ViewGroup.LayoutParams.MATCH_PARENT;
        float densidad = getResources().getDisplayMetrics().density;
        float dpPx = (int) (50 * densidad + 0.4f);
        height = (int) (height- dpPx);

        LinearLayout.LayoutParams layoutParams = new
                LinearLayout.LayoutParams(width / nivel.getFilas(), height / nivel.getFilas());
        layoutParams.setMargins(0, 0, 0, 0);


        for (int i = 0; i < nivel.getFilas(); i++) {
            for (int j = 0; j < nivel.getFilas(); j++) {
                celda[i][j] = new Celdas(this, j, (byte) tablero.getCeldas()[i][j]);
                Celdas casilla = celda[i][j];
                casilla.setLayoutParams(layoutParams);
                casilla.setPulsada(false);
                casilla.setText(String.valueOf(tablero.getCeldas()[i][j]));
                casilla.setPadding(0, 0, 0, 0);
                celda[i][j].setBackgroundColor(Color.BLACK);
                casilla.setTextColor(Color.BLACK);
                casilla.setOnClickListener(this);
             //   casilla.setOnLongClickListener(this);
                grid.addView(casilla);

            }
        }

        return celda;
    }




/*
    // Añadir botones
    public void iniciarPartida(Niveles nivel){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        GridLayout gridLayout =  (GridLayout) findViewById(R.id.gridLay);
        Button boton;
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.setMargins(0, 0, 0, 0);
        gridLayout.setRowCount(nivel.getFilas());
        gridLayout.setColumnCount(nivel.getFilas());

        int casillas = nivel.getFilas()* nivel.getFilas();

      //  celda = new int [nivel.getFilas()][nivel.getFilas()];
rellenarCeldas relleno = new rellenarCeldas();

        for (int i = 0; i < casillas; i++) {
            for (int j = 0; j < nivel.getFilas(); j++) {
                boton = new Button(this);
                boton.setLayoutParams(new ViewGroup.LayoutParams(
                        gridLayout.getLayoutParams().width/nivel.getFilas(),
                        gridLayout.getLayoutParams().height/nivel.getFilas()));

                boton.setId(View.generateViewId());
                boton.setOnClickListener(this);
                gridLayout.addView(boton,i);
            }
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

*/


    //Respuesta
    @Override
    public void onClick(View v) {
        Celdas boton= (Celdas) v;
        boton.setBackgroundColor(Color.RED);

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