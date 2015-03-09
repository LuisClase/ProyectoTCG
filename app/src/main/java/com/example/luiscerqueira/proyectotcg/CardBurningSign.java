package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Luis Cerqueira on 12/02/2015.
 */
public class CardBurningSign extends Carta {

    public CardBurningSign(Context context,Jugador owner, Jugador enemigo) {
        super(context, owner, enemigo);
        this.setCoste(2);
        this.setTipo(Tipos.PERMANENTE);
        this.setNombre("BurningSign");
        this.setImagen(R.drawable.cardburningsign);
    }

    @Override
    public void startOfTurnTable() {
        super.startOfTurnTable();
        setAnimar(true);
    }

    @Override
    public void playCard() {
        super.playCard();
        Log.i("CARTA JUGADA", "VIDAS ENEMIGO" + getEnemigo().getVidas());
        getEnemigo().setVidas(getEnemigo().getVidas() - 2);
        Log.i("CARTA JUGADA", "CARTA JUGADA -2 VIDAS");
        Log.i("CARTA JUGADA", "DESPUES ANIMARSE");
    }
}
