package com.Apps4Ever.DueloMagos;

import android.content.Context;
import android.util.Log;

/**
 * Clase para la construccion especifica de una carta estilo MysticalSign, actualmente en uso solo para Jugador 2
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 13/02/2015.
 */
public class CardMysticalSign extends Carta {
    public CardMysticalSign(Context context, Jugador owner, Jugador enemigo) {
        super(context, owner, enemigo);
        this.setCoste(2);
        this.setTipo(Tipos.PERMANENTE);
        this.setNombre("MysticSign");
        this.setImagen(R.drawable.cardmysticalsign);
    }

    @Override
    public void startOfTurnTable() {
        super.startOfTurnTable();
        setAnimar(true);
    }

    @Override
    public void playCard() {
        super.playCard();
        getOwner().moveFromDeckToHand(1);
        Log.i("CARTA JUGADA", "CARTA JUGADA +2 VIDAS");
        Log.i("CARTA JUGADA", "DESPUES ANIMARSE");
    }
}
