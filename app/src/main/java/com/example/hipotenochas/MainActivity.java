package com.example.hipotenochas;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   Niveles nivel = new nDificil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            for (int i = 0; i < casillas; i++) {
                boton = new Button(this);
                boton.setLayoutParams(new ViewGroup.LayoutParams(
                       gridLayout.getLayoutParams().width/nivel.getFilas(),
                        gridLayout.getLayoutParams().height/nivel.getFilas()));

                boton.setId(View.generateViewId());
             //   boton.setBackgroundColor();
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
               // boton.setBackgroundColor(Color.rgb(255,0,0));
            }
        }
    }

    //Respuesta
    @Override
    public void onClick(View v) {
        if (v.getClass().getSimpleName().equals("Button")){
            ImageButton boton = (ImageButton) v;
            accion(boton);
        }
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
            case R.id.BuscarCliente:
                Toast.makeText(getApplicationContext(), "Se ha pulsado Buscar cliente...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Clientes:
                Toast.makeText(getApplicationContext(), "Se ha pulsado en Cliente",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.Facturas:
                Toast.makeText(getApplicationContext(), "Se ha pulsado en facturas",
                        Toast.LENGTH_LONG).show();
            case R.id.ajustes:
            case R.id.confClientes:
            case R.id.confFacturas:
            case R.id.NuevaFactura:
            case R.id.NuevoCliente:
                Toast.makeText(getApplicationContext(), "Otro botón fue pulsado",
                        Toast.LENGTH_LONG).show();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }



    public void accion(ImageButton boton){

    }
}