package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.Random;

/**
 * Clase para la construccion especifica de una carta estilo MentalSpiral, actualmente en uso solo para Jugador 2
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 12/02/2015.
 */
public class CardMentalSpiral extends Carta {
    public CardMentalSpiral(Context context, Jugador owner, Jugador enemigo) {
        super(context,owner, enemigo);
        this.setCoste(2);
        this.setTipo(Tipos.HECHIZO);
        this.setNombre("MentalSpiral");
        this.setImagen(R.drawable.cardmentalspiral2);
    }

    @Override
    public void playCard() {
        super.playCard();
        Random random=new Random();
        if(getEnemigo().getMano().size()>=1) {
            int idtemp= getEnemigo().getMano().get(random.nextInt(getEnemigo().getMano().size())).getId();
            getEnemigo().moveCardFromHandToDiscard(idtemp);
        }
        getOwner().moveFromDeckToHand(1);
        Log.i("CARTA JUGADA", "CARTA JUGADA -1 VIDAS +1CARTA");
        getOwner().moveCardFromTableToDiscard(getId());
    }

    @Override
    public void movedFromHandToTable() {
        super.movedFromHandToTable();
        setAnimar(true);
    }
}

