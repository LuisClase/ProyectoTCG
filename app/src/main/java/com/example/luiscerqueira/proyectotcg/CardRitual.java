package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Luis Cerqueira on 10/02/2015.
 */
public class CardRitual extends Carta {
    public CardRitual(Context context, int id, Jugador owner, Jugador enemigo) {
        super(context, id, owner, enemigo);
        this.setCoste(2);
        this.setImagen(BitmapFactory.decodeResource(context.getResources(), R.drawable.cardritual));
    }

    @Override
    public void playCard() {
        super.playCard();
        getOwner().setVidas(getOwner().getVidas()-1);
        getOwner().moveFromDeckToHand(1);
        Log.i("CARTA JUGADA", "CARTA JUGADA -1 VIDAS +1CARTA");
    }

    @Override
    public void movedFromHandToTable() {
        super.movedFromHandToTable();
        this.playCard();
    }
}
