package com.example.luiscerqueira.proyectotcg;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Luis Cerqueira on 04/03/2015.
 */
public class DialogoCombinar extends DialogFragment {

    int puntos=0;

    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    TextView txt5;
    TextView txt6;
    TextView txt7;
    TextView txt8;
    TextView txt9;
    TextView txt10;
    TextView txt11;
    TextView txt12;
//    TextView txt13;
    TextView txt14;
    AlertDialog.Builder builder;
    Carta cartaTemp;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflador=getActivity().getLayoutInflater();
        View layout=inflador.inflate(R.layout.dialogo, null);
        builder.setView(layout).
            setPositiveButton("OK",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        cartaTemp=((ActivityCombinar)getActivity()).cartaPropia;
        txt1=(TextView)layout.findViewById(R.id.textView);
        txt2=(TextView)layout.findViewById(R.id.textView2);
        txt3=(TextView)layout.findViewById(R.id.textView3);
        txt4=(TextView)layout.findViewById(R.id.textView4);
        txt5=(TextView)layout.findViewById(R.id.textView5);
        txt6=(TextView)layout.findViewById(R.id.textView6);
        txt7=(TextView)layout.findViewById(R.id.textView7);
        txt8=(TextView)layout.findViewById(R.id.textView8);
        txt9=(TextView)layout.findViewById(R.id.textView9);
        txt10=(TextView)layout.findViewById(R.id.textView10);
        txt11=(TextView)layout.findViewById(R.id.textView11);
        txt12=(TextView)layout.findViewById(R.id.textView12);
//        txt13=(TextView)layout.findViewById(R.id.textView13);
        txt14=(TextView)layout.findViewById(R.id.textView14);

        Button btn1=(Button)layout.findViewById(R.id.button);
        Button btn2=(Button)layout.findViewById(R.id.button2);
        Button btn3=(Button)layout.findViewById(R.id.button3);
        Button btn4=(Button)layout.findViewById(R.id.button4);
        Button btn5=(Button)layout.findViewById(R.id.button5);
        Button btn6=(Button)layout.findViewById(R.id.button6);
        Button btn7=(Button)layout.findViewById(R.id.button7);
        Button btn8=(Button)layout.findViewById(R.id.button8);
        Button btn9=(Button)layout.findViewById(R.id.button9);
        Button btn10=(Button)layout.findViewById(R.id.button10);
        Button btn11=(Button)layout.findViewById(R.id.button11);
        Button btn12=(Button)layout.findViewById(R.id.button12);
        Button btn13=(Button)layout.findViewById(R.id.button13);
        Button btn14=(Button)layout.findViewById(R.id.button14);
        Button btn15=(Button)layout.findViewById(R.id.button15);
        Button btn16=(Button)layout.findViewById(R.id.button16);
        Button btn17=(Button)layout.findViewById(R.id.button17);
        Button btn18=(Button)layout.findViewById(R.id.button18);
        Button btn19=(Button)layout.findViewById(R.id.button19);
        Button btn20=(Button)layout.findViewById(R.id.button20);
        Button btn21=(Button)layout.findViewById(R.id.button21);
        Button btn22=(Button)layout.findViewById(R.id.button22);
        Button btn23=(Button)layout.findViewById(R.id.button23);
        Button btn24=(Button)layout.findViewById(R.id.button24);

        //ImageView imagen=(ImageView)getActivity().findViewById(R.id.selectedImage);
//        ImageView imagen=(ImageView) layout.findViewById(R.id.selectedImage);
        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),R.drawable.circulo);
//        Log.i("COMBINAR","IMAGEN NULL?"+(imagen==null?"SI":"NO"));
        Log.i("COMBINAR","BITMAP NULL?"+(bitmap==null?"SI":"NO"));
//        imagen.setImageBitmap(bitmap);
//        imagen.setImageBitmap(((ActivityCombinar)getActivity()).imagenCombinada);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setDañoEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getDañoEnemigo()+1);
                Log.i("CREADORCARTAS","DAÑO: "+((ActivityCombinar)getActivity()).cartaPropia.getDañoEnemigo());
                ((ActivityCombinar)getActivity()).puntos--;
                txt1.setText("Daño Enemigo: "+((ActivityCombinar)getActivity()).cartaPropia.getDañoEnemigo());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getDañoEnemigo()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setDañoEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getDañoEnemigo()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt1.setText("Daño Enemigo: " + ((ActivityCombinar)getActivity()).cartaPropia.getDañoEnemigo());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setCuraEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getCuraEnemigo()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt2.setText("Cura Enemigo: "+((ActivityCombinar)getActivity()).cartaPropia.getCuraEnemigo());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getCuraEnemigo()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setCuraEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getCuraEnemigo()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt2.setText("Cura Enemigo: " + ((ActivityCombinar)getActivity()).cartaPropia.getCuraEnemigo());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setCartasEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getCartasEnemigo()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt3.setText("Cartas a Enemigo: "+((ActivityCombinar)getActivity()).cartaPropia.getCartasEnemigo());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getCartasEnemigo()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setCartasEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getCartasEnemigo()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt3.setText("Cartas a Enemigo: " + ((ActivityCombinar)getActivity()).cartaPropia.getCartasEnemigo());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setDescarteEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getDescarteEnemigo()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt4.setText("Descarte Enemigo: "+((ActivityCombinar)getActivity()).cartaPropia.getDescarteEnemigo());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getDescarteEnemigo()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setDescarteEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getDescarteEnemigo()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt4.setText("Descartar Enemigo: " + ((ActivityCombinar)getActivity()).cartaPropia.getDescarteEnemigo());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setRecursosEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getRecursosOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt5.setText("Recursos a Enemigo: "+((ActivityCombinar)getActivity()).cartaPropia.getRecursosOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getRecursosOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setRecursosEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getRecursosOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt5.setText("Recursos a Enemigo: " + ((ActivityCombinar)getActivity()).cartaPropia.getRecursosOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaAManoEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaAManoEnemigo()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt6.setText("Mover de mesa a mano Enemigo: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaAManoEnemigo());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaAManoEnemigo()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaAManoEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaAManoEnemigo()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt6.setText("Mover de mesa a mano Enemigo: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaAManoEnemigo());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverDeckAManoEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckAManoEnemigo()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt7.setText("Mover de deck a mano Enemigo: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckAManoEnemigo());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckAManoEnemigo()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverDeckAManoEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckAManoEnemigo()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt7.setText("Mover de deck a mano Enemigo: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckAManoEnemigo());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaADeckEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADeckEnemigo()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt8.setText("Mover de mesa a deck Enemigo: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADeckEnemigo());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADeckEnemigo()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaADeckEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADeckEnemigo()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt8.setText("Mover de mesa a dek Enemigo: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADeckEnemigo());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverDescarteADeckEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverDescarteADeckEnemigo()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt9.setText("Mover de descarte a deck Enemigo: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverDescarteADeckEnemigo());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverDescarteADeckEnemigo()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverDescarteADeckEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverDescarteADeckEnemigo()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt9.setText("Mover de descarte a deck Enemigo: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverDescarteADeckEnemigo());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverManoADeckEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverManoADeckEnemigo()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt10.setText("Mover de mano a deck Enemigo: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverManoADeckEnemigo());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverManoADeckEnemigo()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverManoADeckEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverManoADeckEnemigo()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt10.setText("Mover de mano a deck Enemigo: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverManoADeckEnemigo());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverDeckADescarteEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckADescarteEnemigo()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt11.setText("Mover de deck a descarte Enemigo: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckADescarteEnemigo());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckADescarteEnemigo()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverDeckADescarteEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckADescarteEnemigo()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt11.setText("Mover de deck a descarte Enemigo: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckADescarteEnemigo());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaADescarteEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADescarteEnemigo()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt12.setText("Destruir de mesa: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADescarteEnemigo());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADescarteEnemigo()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaADescarteEnemigo(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADescarteEnemigo()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt12.setText("Destruir de mesa: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADescarteEnemigo());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        builder.setMessage("PRUEBA");
        builder.setTitle("Puntos: 0");
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        Bitmap imagen1=((ActivityCombinar)getActivity()).imagenMarco;
        Bitmap imagen2=((BitmapDrawable)((ActivityCombinar)getActivity()).imagenCentral.getDrawable()).getBitmap();
        ((ActivityCombinar)getActivity()).imagenCombinada=((ActivityCombinar)getActivity()).overlay(imagen1,imagen2);
        ((ActivityCombinar)getActivity()).imagenCarta.setImageBitmap(((ActivityCombinar)getActivity()).imagenCombinada);
    }
}
