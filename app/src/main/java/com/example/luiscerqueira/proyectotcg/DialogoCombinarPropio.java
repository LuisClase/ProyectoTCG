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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Luis Cerqueira on 04/03/2015.
 */
public class DialogoCombinarPropio extends DialogFragment {

    int puntos=0;

    int daño=0;
    int cura=0;
    int cartas=0;
    int descarte=0;
    int recursos=0;
    int moverMesaMano=0;
    int moverDeckMano=0;
    int moverMesaDeck=0;
    int moverdescarteDeck=0;
    int moverManoDeck=0;
    int moverDeckDescarte=0;
    int destruirMesa=0;
    int moverDescarteMesa=0;
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
//        txt13=(TextView)layout.findViewById(R.id.textView14);

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
                daño++;
                puntos--;
                txt1.setText("Daño Jugador: "+daño);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daño>0) {
                    daño--;
                    puntos++;
                    txt1.setText("Daño Jugador: " + daño);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cura++;
                puntos--;
                txt1.setText("Cura Jugador: "+cura);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cura>0) {
                    cura--;
                    puntos++;
                    txt1.setText("Cura Jugador: " + cura);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartas++;
                puntos--;
                txt1.setText("Cartas a Jugador: "+cartas);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartas>0) {
                    cartas--;
                    puntos++;
                    txt1.setText("Cartas a Jugador: " + cartas);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descarte++;
                puntos--;
                txt1.setText("Descarte Jugador: "+descarte);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(descarte>0) {
                    descarte--;
                    puntos++;
                    txt1.setText("Descartar Jugador: " + daño);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recursos++;
                puntos--;
                txt1.setText("Recursos a Jugador: "+recursos);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recursos>0) {
                    recursos--;
                    puntos++;
                    txt1.setText("Recursos a Jugador: " + recursos);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moverMesaMano++;
                puntos--;
                txt1.setText("Mover de mesa a mano Jugador: "+moverMesaMano);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moverMesaMano>0) {
                    moverMesaMano--;
                    puntos++;
                    txt1.setText("Mover de mesa a mano Jugador: " + moverMesaMano);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moverDeckMano++;
                puntos--;
                txt1.setText("Mover de deck a mano Jugador: "+moverDeckMano);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moverDeckMano>0) {
                    moverDeckMano--;
                    puntos++;
                    txt1.setText("Mover de deck a mano Jugador: " + moverDeckMano);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moverMesaDeck++;
                puntos--;
                txt1.setText("Mover de mesa a deck Jugador: "+moverMesaDeck);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moverMesaDeck>0) {
                    moverMesaDeck--;
                    puntos++;
                    txt1.setText("Mover de mesa a dek Jugador: " + moverMesaDeck);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moverdescarteDeck++;
                puntos--;
                txt1.setText("Mover de descarte a deck Jugador: "+moverdescarteDeck);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moverdescarteDeck>0) {
                    moverdescarteDeck--;
                    puntos++;
                    txt1.setText("Mover de descarte a deck Jugador: " + moverdescarteDeck);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moverManoDeck++;
                puntos--;
                txt1.setText("Mover de mano a deck Jugador: "+moverManoDeck);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moverManoDeck>0) {
                    moverManoDeck--;
                    puntos++;
                    txt1.setText("Mover de mano a deck Jugador: " + moverManoDeck);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moverDeckDescarte++;
                puntos--;
                txt1.setText("Mover de deck a descarte Jugador: "+moverDeckDescarte);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moverDeckDescarte>0) {
                    moverDeckDescarte--;
                    puntos++;
                    txt1.setText("Mover de deck a descarte Jugador: " + moverDeckDescarte);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destruirMesa++;
                puntos--;
                txt1.setText("Destruir de mesa: "+destruirMesa);
                txt14.setText("Puntos: "+puntos);
            }
        });
        btn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(destruirMesa>0) {
                    destruirMesa--;
                    puntos++;
                    txt1.setText("Destruir de mesa: " + destruirMesa);
                    txt14.setText("Puntos: " + puntos);
                }
            }
        });
        builder.setMessage("PRUEBA");
        builder.setTitle("Puntos: 0");
        return builder.create();
    }
}
