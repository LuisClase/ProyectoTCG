package com.Apps4Ever.DueloMagos;

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
import android.widget.TextView;

/**
 * Clase para gestionar el dialogo de la activity combinar donde elegiremos las caracteristicas de nuestra carta
 * que afectan al enemigo
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 04/03/2015.
 */
public class DialogoCombinarPropio extends DialogFragment {

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
    TextView txt13;
    TextView txt14;
    AlertDialog.Builder builder;

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
                ((ActivityCombinar)getActivity()).cartaPropia.setDañoOwner(((ActivityCombinar)getActivity()).cartaPropia.getDañoOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt1.setText("Daño Jugador: "+((ActivityCombinar)getActivity()).cartaPropia.getDañoOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getDañoOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setDañoOwner(((ActivityCombinar)getActivity()).cartaPropia.getDañoOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt1.setText("Daño Jugador: " + ((ActivityCombinar)getActivity()).cartaPropia.getDañoOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setCuraOwner(((ActivityCombinar)getActivity()).cartaPropia.getCuraOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt2.setText("Cura Jugador: "+((ActivityCombinar)getActivity()).cartaPropia.getCuraOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getCuraOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setCuraOwner(((ActivityCombinar)getActivity()).cartaPropia.getCuraOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt2.setText("Cura Jugador: " + ((ActivityCombinar)getActivity()).cartaPropia.getCuraOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setCartasOwner(((ActivityCombinar)getActivity()).cartaPropia.getCartasOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt3.setText("Cartas a Jugador: "+((ActivityCombinar)getActivity()).cartaPropia.getCartasOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getCartasOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setCartasOwner(((ActivityCombinar)getActivity()).cartaPropia.getCartasOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt3.setText("Cartas a Jugador: " + ((ActivityCombinar)getActivity()).cartaPropia.getCartasOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setDescarteOwner(((ActivityCombinar)getActivity()).cartaPropia.getDescarteOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt4.setText("Descarte Jugador: "+((ActivityCombinar)getActivity()).cartaPropia.getDescarteOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getDescarteOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setDescarteOwner(((ActivityCombinar)getActivity()).cartaPropia.getDescarteOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt4.setText("Descartar Jugador: " + ((ActivityCombinar)getActivity()).cartaPropia.getDescarteOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setRecursosOwner(((ActivityCombinar)getActivity()).cartaPropia.getRecursosOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt5.setText("Recursos a Jugador: "+((ActivityCombinar)getActivity()).cartaPropia.getRecursosOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getRecursosOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setRecursosOwner(((ActivityCombinar)getActivity()).cartaPropia.getRecursosOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt5.setText("Recursos a Jugador: " + ((ActivityCombinar)getActivity()).cartaPropia.getRecursosOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaAManoOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaAManoOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt6.setText("Mover de mesa a mano Jugador: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaAManoOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaAManoOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaAManoOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaAManoOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt6.setText("Mover de mesa a mano Jugador: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaAManoOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverDeckAManoOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckAManoOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt7.setText("Mover de deck a mano Jugador: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckAManoOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckAManoOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverDeckAManoOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckAManoOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt7.setText("Mover de deck a mano Jugador: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckAManoOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaADeckOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADeckOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt8.setText("Mover de mesa a deck Jugador: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADeckOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADeckOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaADeckOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADeckOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt8.setText("Mover de mesa a dek Jugador: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADeckOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverDescarteADeckOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverDescarteADeckOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt9.setText("Mover de descarte a deck Jugador: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverDescarteADeckOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverDescarteADeckOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverDescarteADeckOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverDescarteADeckOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt9.setText("Mover de descarte a deck Jugador: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverDescarteADeckOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverManoADeckOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverManoADeckOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt10.setText("Mover de mano a deck Jugador: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverManoADeckOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverManoADeckOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverManoADeckOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverManoADeckOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt10.setText("Mover de mano a deck Jugador: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverManoADeckOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverDeckADescarteOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckADescarteOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt11.setText("Mover de deck a descarte Jugador: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckADescarteOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckADescarteOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverDeckADescarteOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckADescarteOwner()-1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt11.setText("Mover de deck a descarte Jugador: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverDeckADescarteOwner());
                    txt14.setText("Puntos: " + ((ActivityCombinar)getActivity()).puntos);
                }
            }
        });
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaADescarteOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADescarteOwner()+1);
                ((ActivityCombinar)getActivity()).puntos--;
                txt12.setText("Destruir de mesa: "+((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADescarteOwner());
                txt14.setText("Puntos: "+((ActivityCombinar)getActivity()).puntos);
            }
        });
        btn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADescarteOwner()>0) {
                    ((ActivityCombinar)getActivity()).cartaPropia.setMoverMesaADescarteOwner(((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADescarteOwner()+1);
                    ((ActivityCombinar)getActivity()).puntos++;
                    txt12.setText("Destruir de mesa: " + ((ActivityCombinar)getActivity()).cartaPropia.getMoverMesaADescarteOwner());
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
