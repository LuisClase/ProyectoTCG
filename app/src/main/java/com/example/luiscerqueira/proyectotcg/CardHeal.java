package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Luis Cerqueira on 10/02/2015.
 */
public class CardHeal extends Carta {

    public CardHeal(Context context, int id, Jugador owner, Jugador enemigo) {
        super(context, id, owner, enemigo);
        this.setCoste(2);
        this.setImagen(BitmapFactory.decodeResource(context.getResources(), R.drawable.cardheal));
    }

    @Override
    public void playCard() {
        super.playCard();
        getOwner().setVidas(getOwner().getVidas()+4);
        Log.i("CARTA JUGADA", "CARTA JUGADA +4 VIDAS");
    }

    @Override
    public void movedFromHandToTable() {
        super.movedFromHandToTable();
        this.playCard();
    }
}
