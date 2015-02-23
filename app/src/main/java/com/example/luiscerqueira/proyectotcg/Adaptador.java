package com.example.luiscerqueira.proyectotcg;

/**
 * Created by soylu_000 on 21/02/2015.
 */
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.content.*;
import java.util.*;

public class Adaptador extends BaseAdapter {
     public ViewHolder contenedor=null;

    class ViewHolder{
        ImageView imagen;
        TextView txtNombre;
        TextView txtCantidad;
        Button btnAñadir;
        Button btnMenos;
        Carta carta;
    }
    ArrayList<Carta> mazo;
    LayoutInflater inflador;

    public Adaptador(Context context, ArrayList<Carta> mazo){
        this.inflador=LayoutInflater.from(context);
        this.mazo=mazo;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
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
        if(vista==null){

            vista=inflador.inflate(R.layout.vistacartas,null);
            contenedor=new ViewHolder();
            contenedor.imagen=(ImageView) vista.findViewById(R.id.logo);
            contenedor.txtCantidad=(TextView) vista.findViewById(R.id.txtCantidad);
            contenedor.txtNombre=(TextView) vista.findViewById(R.id.txtNombreCarta);
            contenedor.btnAñadir=(Button) vista.findViewById(R.id.btnAñadir);
            contenedor.btnMenos=(Button) vista.findViewById(R.id.btnMenos);
            contenedor.carta=(Carta)getItem(position);
            contenedor.btnMenos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("BTN MENOS ANTES","Cantidad:"+contenedor.carta.getCantidad());
                    if (contenedor.carta.getCantidad() > 0) {
                        contenedor.carta.setCantidad(contenedor.carta.getCantidad() - 1);
                        contenedor.txtCantidad.setText(contenedor.carta.getCantidad() + "");
                        Log.i("BTN MENOS DESPUES","Cantidad:"+contenedor.carta.getCantidad());
                    }
                }
            });
            contenedor.btnAñadir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("BTN AÑADIR ANTES","Cantidad:"+contenedor.carta.getCantidad());
                    if(contenedor.carta.getCantidad()<4){
                        contenedor.carta.setCantidad(contenedor.carta.getCantidad()+1);
                        contenedor.txtCantidad.setText(contenedor.carta.getCantidad() + "");
                        Log.i("BTN AÑADIR DESPUES","Cantidad:"+contenedor.carta.getCantidad());
                    }
                }
            });
            vista.setTag(contenedor);
        }else{
            contenedor=(ViewHolder) vista.getTag();
        }

        contenedor.imagen.setImageResource(contenedor.carta.getImagen());
        contenedor.txtNombre.setText(contenedor.carta.getNombre());
        contenedor.txtCantidad.setText(contenedor.carta.getCantidad() + "");
        return vista;
    }
}
