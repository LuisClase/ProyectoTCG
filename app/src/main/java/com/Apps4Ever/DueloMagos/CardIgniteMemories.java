package com.Apps4Ever.DueloMagos;

import android.content.Context;
import android.util.Log;

/**
 * Clase para la construccion especifica de una carta estilo IgniteMemories, actualmente en uso solo para Jugador 2
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 12/02/2015.
 */
public class CardIgniteMemories extends Carta{

    public CardIgniteMemories(Context context,Jugador owner, Jugador enemigo) {
        super(context, owner, enemigo);
        this.setCoste(5);
        this.setTipo(Tipos.HECHIZO);
        this.setNombre("IgniteMemories");
        this.setImagen(R.drawable.cardignitememories);
    }

    @Override
    public void playCard() {
        super.playCard();
        Log.i("CARTA JUGADA", "VIDAS ENEMIGO" + getEnemigo().getVidas());
        getEnemigo().setVidas(getEnemigo().getVidas()-getEnemigo().getDescarte().size());
        Log.i("CARTA JUGADA", "CARTA JUGADA -"+getEnemigo().getDescarte().size()+" VIDAS");
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
