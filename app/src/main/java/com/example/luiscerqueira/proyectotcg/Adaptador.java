package com.example.luiscerqueira.proyectotcg;

/**
 * Created by soylu_000 on 21/02/2015.
 */
import android.view.*;
import android.widget.*;
import android.content.*;
import java.util.*;

public class Adaptador extends BaseAdapter {

    class ViewHolder{
        ImageView imagen;
        TextView txtNombre;
        TextView txtCantidad;
        Button btnAñadir;
        Button btnMenos;
    }
    ArrayList<Carta> mazo;
    LayoutInflater inflador;

    public Adaptador(Context context, ArrayList<Carta> mazo){
        this.inflador=LayoutInflater.from(context);
        this.mazo=mazo;
    }

    @Override
    public int getCount() {
        return mazo.size();
    }

    @Override
    public Object getItem(int position) {
        return mazo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View vista, ViewGroup grupo) {
        ViewHolder contenedor=null;
        if(vista==null){
            vista=inflador.inflate(R.layout.vistacartas,null);
            contenedor=new ViewHolder();
            contenedor.imagen=(ImageView) vista.findViewById(R.id.logo);
            contenedor.txtCantidad=(TextView) vista.findViewById(R.id.txtCantidad);
            contenedor.txtNombre=(TextView) vista.findViewById(R.id.txtNombreCarta);
            contenedor.btnAñadir=(Button) vista.findViewById(R.id.btnAñadir);
            contenedor.btnMenos=(Button) vista.findViewById(R.id.btnMenos);
            vista.setTag(contenedor);
        }else{
            contenedor=(ViewHolder) vista.getTag();
        }

        Carta c=(Carta)getItem(position);
        contenedor.imagen.setImageResource(c.getImagen());
        contenedor.txtNombre.setText(c.getNombre());
        contenedor.txtCantidad.setText(c.getCantidad()+"");
        return vista;
    }
}
