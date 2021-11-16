package com.example.hipotenochas;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

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

import java.time.temporal.ValueRange;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

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

        iniciarPartida(new nFacil());




    }

    int cuenta=nivel.getHipotenochas();


    private Tablero tablero;
    private Celdas[][] celda;
    private int hipotenochasRestantes=nivel.getHipotenochas();


    public void iniciarPartida(Niveles nivel) {

        tablero = new Tablero(nivel);
        celda = crearPartida(nivel);
        cuenta=nivel.getHipotenochas();
    }

    public Celdas[][] crearPartida(Niveles nivel) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        GridLayout grid = findViewById(R.id.gridLay);
        // Borramos todo para el botón iniciar partida no deje guardado la anterior.
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


        /*
         Pintamos el tablero. getFilas lo utilizo siempre tanto para
         filas como para columnas, porque en todos los niveles, es el
         mismo entero al ser cuadrado.
         */
        for (int i = 0; i < nivel.getFilas(); i++) {
            for (int j = 0; j < nivel.getFilas(); j++) {
                celda[i][j] = new Celdas(this, j, (byte) tablero.getCeldas()[i][j]);
                Celdas celda = this.celda[i][j];
                celda.setLayoutParams(layoutParams);
                celda.setText(String.valueOf(tablero.getCeldas()[i][j]));
                celda.setPadding(0, 0, 0, 0);
                celda.setTextColor(Color.WHITE);
                celda.setBackgroundResource(R.drawable.boton);
                celda.setOnClickListener(this);
                celda.setOnLongClickListener(this);
                grid.addView(celda);

            }
        }

        return celda;
    }



    //Al clickar
    @Override
    public void onClick(View v) {
        Celdas boton= (Celdas) v;
        boton.setBackgroundColor(Color.TRANSPARENT);

                if (boton.getText().equals(String.valueOf(HIPOTENOCHA))) {
                    // Le borramos el texto para que no interfiera con la imagen
                    boton.setText("");
                    boton.setBackground(personajeElegido.getImagenes());
                    Toast.makeText(this, R.string.loser, LENGTH_LONG).show();
                    partidaAcabada();
                }else {

                    boton.setBackgroundResource(R.drawable.boton2);
                }

    }


    public void partidaAcabada() {
        //Para finalizar, bloqueamos todos las celdas.
        for (int i=0; i< celda.length; i++){
            for (int j=0; j<celda[i].length; j++){
                celda[i][j].setEnabled(false);
            }
        }

    }


    public void cambiarGato(){
        Dialog gato = new Dialog(this);
        gato.setContentView(R.layout.spinnerpersonajes);
        Spinner spin = gato.findViewById(R.id.spin_gatos);
        AdaptadorPersonalizado adaptadorPersonalizado = new AdaptadorPersonalizado(this, personajesArray);
        spin.setAdapter(adaptadorPersonalizado);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                personajeElegido = personajesArray.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
        gato.show();
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
            case R.id.seleccionGato:
                cambiarGato();
                Toast.makeText(getApplicationContext(), R.string.cambioPersonaje, LENGTH_LONG).show();
                return true;
            case R.id.instrucciones:
                instrucciones();
                return true;
            case R.id.comenzarJuego:
                Toast.makeText(getApplicationContext(), R.string.toastcomenzar,
                        LENGTH_LONG).show();
                iniciarPartida(nivel);
                return true;
            case R.id.configurar:
                Toast.makeText(getApplicationContext(), R.string.confi,
                        LENGTH_LONG).show();
                return true;
            case R.id.nivelFacil:
                iniciarPartida(new nFacil());
                Toast.makeText(getApplicationContext(), R.string.nivelFacil, LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }



    // Instrucciones de juego

    public void instrucciones() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.tituloInstrucicnes);
        builder.setMessage(R.string.textInstruc);
        builder.setNegativeButton(R.string.instrucciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    // Click larga pulsación
    @Override
    public boolean onLongClick(View v) {
        Celdas boton= (Celdas) v;

        if (cuenta==0){
            Toast.makeText(this, R.string.Winner, LENGTH_SHORT).show();
            partidaAcabada();
        } else if (cuenta>0){

            if (boton.getText().equals(String.valueOf(HIPOTENOCHA))) {
                boton.setOnClickListener(null);
                boton.setText("");
                boton.setBackground(personajeElegido.getImagenes());
                Toast.makeText(this, R.string.encontrada, LENGTH_SHORT).show();
                cuenta = cuenta - 1;

            } else if (!boton.getText().equals(String.valueOf(HIPOTENOCHA))) {
                boton.setOnClickListener(null);
                Toast.makeText(this, R.string.loser, LENGTH_LONG).show();
                boton.setText("");
                boton.setBackgroundColor(Color.TRANSPARENT);
                boton.setBackgroundResource(R.drawable.b);
                partidaAcabada();
                cuenta=nivel.getHipotenochas();
            }
        }

        return false;

    }
}