package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Clase para la construccion especifica de una carta estilo HealingSign, actualmente en uso solo para Jugador 2
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 13/02/2015.
 */
public class CardHealingSign extends Carta {
    public CardHealingSign(Context context, Jugador owner, Jugador enemigo) {
        super(context, owner, enemigo);
        this.setCoste(2);
        this.setTipo(Tipos.PERMANENTE);
        this.setNombre("HealSign");
        this.setImagen(R.drawable.cardhealingsign);
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
