package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Luis Cerqueira on 13/02/2015.
 */
public class CardHealingSign extends Carta {
    public CardHealingSign(Context context, Jugador owner, Jugador enemigo) {
        super(context, owner, enemigo);
        this.setCoste(2);
        this.setImagen(R.drawable.cardnaturalsign);
    }

    @Override
    public void startOfTurnTable() {
        super.startOfTurnTable();
        setAnimar(true);
    }

    @Override
    public void playCard() {
        super.playCard();
        getOwner().setVidas(getOwner().getVidas() + 2);
        Log.i("CARTA JUGADA", "CARTA JUGADA +2 VIDAS");
        Log.i("CARTA JUGADA", "DESPUES ANIMARSE");
    }
}
