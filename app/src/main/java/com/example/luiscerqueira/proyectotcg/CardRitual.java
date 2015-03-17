package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Clase para la construccion especifica de una carta estilo Ritual, actualmente en uso solo para Jugador 2
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 10/02/2015.
 */
public class CardRitual extends Carta {
    public CardRitual(Context context, Jugador owner, Jugador enemigo) {
        super(context, owner, enemigo);
        this.setCoste(2);
        this.setTipo(Tipos.HECHIZO);
        this.setNombre("Ritual");
        this.setImagen(R.drawable.cardritual);
    }

    @Override
    public void playCard() {
        super.playCard();
        getEnemigo().setVidas(getEnemigo().getVidas()-1);
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
