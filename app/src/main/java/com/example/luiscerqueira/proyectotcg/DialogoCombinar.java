package com.example.luiscerqueira.proyectotcg;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Luis Cerqueira on 04/03/2015.
 */
public class DialogoCombinar extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflador=getActivity().getLayoutInflater();
        View layout=inflador.inflate(R.layout.dialogo, null);
        builder.setView(layout).
            setPositiveButton("OK",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        //ImageView imagen=(ImageView)getActivity().findViewById(R.id.selectedImage);
        ImageView imagen=(ImageView) layout.findViewById(R.id.selectedImage);
        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),R.drawable.circulo);
        Log.i("COMBINAR","IMAGEN NULL?"+(imagen==null?"SI":"NO"));
        Log.i("COMBINAR","BITMAP NULL?"+(bitmap==null?"SI":"NO"));
//        imagen.setImageBitmap(bitmap);
        imagen.setImageBitmap(((ActivityCombinar)getActivity()).imagenCombinada);
        builder.setMessage("PRUEBA");
        builder.setTitle("TITULO");
        return builder.create();
    }
}
