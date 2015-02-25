package com.example.luiscerqueira.proyectotcg;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import java.util.*;
import android.util.*;
import android.widget.*;
import android.app.*;
import android.content.*;



public class ActivityMazos extends ListActivity {
    Context contexto;
    Jugador jugador1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_activity_mazos);
//        jugador1=getIntent().getParcelableExtra("jugador");
//        Log.i("PARCELABLE","TAMAÑO:"+jugador1.getDeck().size());
//        for(int i=0;i<jugador1.getDeck().size();i++){
//            Log.i("PARCELABLE","CARTA:"+jugador1.getDeck().get(i).getNombre());
//        }
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        int[]valoresEnemigo={
//                this.dañoEnemigo=valoresEnemigo[0];
                2,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[]valoresOwner={
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        cartas.add(new Carta(this,null,null,"Prueba",R.drawable.saberchibi,1,valoresEnemigo,valoresOwner));
        cartas.add(new CardHealingSign(this,null,null));
        cartas.add(new CardHeal(this,null,null));
        cartas.add(new CardBurningSign(this,null,null));
        cartas.add(new CardIgniteMemories(this,null,null));
        cartas.add(new CardMentalSpiral(this,null,null));
        cartas.add(new CardMysticalSign(this,null,null));
        cartas.add(new CardNaturalHelp(this,null,null));
        cartas.add(new CardNaturalResources(this,null,null));
        cartas.add(new CardRitual(this,null,null));
        cartas.add(new CardTransfusion(this,null,null));
        setListAdapter(new Adaptador(this,cartas));
        contexto=getListView().getContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_mazos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
