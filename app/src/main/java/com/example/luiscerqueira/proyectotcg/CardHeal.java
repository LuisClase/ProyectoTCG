package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Clase para la construccion especifica de una carta estilo Heal, actualmente en uso solo para Jugador 2
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 10/02/2015.
 */
public class CardHeal extends Carta {

    public CardHeal(Context context,Jugador owner, Jugador enemigo) {
        super(context,owner, enemigo);
        this.setCoste(2);
        this.setTipo(Tipos.HECHIZO);
        this.setNombre("Heal");
        this.setImagen(R.drawable.cardheal);
    }

    @Override
    public void playCard() {
        super.playCard();
        getOwner().setVidas(getOwner().getVidas()+4);
        Log.i("CARTA JUGADA", "CARTA JUGADA +4 VIDAS");
        getOwner().moveCardFromTableToDiscard(getId());
    }

    @Override
    public void movedFromHandToTable() {
        super.movedFromHandToTable();
        setAnimar(true);
    }
}
