package com.example.hipotenochas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends BaseAdapter {
    private ArrayList<Personajes> personajes;
    private Context context;

    public AdaptadorPersonalizado(Context context, ArrayList<Personajes> personajes) {
        setContext(context);
        setPersonajes(personajes);
    }
    public ArrayList<Personajes> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personajes> personajes) {
        this.personajes = personajes;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }





    @Override
    public int getCount() {
        return getPersonajes().size();
    }

    @Override
    public Object getItem(int i) {
        return getPersonajes().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item = view;

        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.spinnerpersonajes, null);
        }

        TextView nombre = item.findViewById(R.id.nombrePersonaje);
        nombre.setText(personajes.get(i).getNombre());

        ImageView imagen = item.findViewById(R.id.ImagengatoPersonaje);
        imagen.setImageDrawable(personajes.get(i).getImagenes());

        return item;
    }


}
