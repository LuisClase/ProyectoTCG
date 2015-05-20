package com.Apps4Ever.DueloMagos;

import android.content.Context;
import android.util.Log;

/**
 * Clase para la construccion especifica de una carta estilo NaturalSign, actualmente en uso solo para Jugador 2
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 13/02/2015.
 */
public class CardNaturalSign extends Carta {
    public CardNaturalSign(Context context, Jugador owner, Jugador enemigo) {
        super(context, owner, enemigo);
        this.setCoste(2);
        this.setTipo(Tipos.PERMANENTE);
        this.setNombre("NaturalSign");
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
        getOwner().setRecursos(getOwner().getRecursos()+2);
        Log.i("CARTA JUGADA", "CARTA JUGADA +2 VIDAS");
        Log.i("CARTA JUGADA", "DESPUES ANIMARSE");
    }
}
