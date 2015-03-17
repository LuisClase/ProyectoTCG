package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Clase para la construccion especifica de una carta estilo Nightmare, actualmente en uso solo para Jugador 2
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 12/02/2015.
 */
public class CardNightmare extends Carta {

    public CardNightmare(Context context,Jugador owner, Jugador enemigo) {
        super(context, owner, enemigo);
        this.setCoste(5);
        this.setTipo(Tipos.HECHIZO);
        this.setNombre("Nightmare");
        this.setImagen(R.drawable.cardnightmare);
    }

    @Override
    public void playCard() {
        super.playCard();
        Log.i("CARTA JUGADA", "VIDAS ENEMIGO" + getEnemigo().getVidas());
        getEnemigo().moveFromDeckToDiscard(15);
        Log.i("CARTA JUGADA", "CARTA JUGADA -15 CARTAS");
        Log.i("CARTA JUGADA", "DESPUES ANIMARSE");
        getOwner().moveCardFromTableToDiscard(getId());
        Log.i("CARTA MOVIDA", "MOVIDA");
    }

    @Override
    public void movedFromHandToTable() {
        super.movedFromHandToTable();
        setAnimar(true);
    }
}
