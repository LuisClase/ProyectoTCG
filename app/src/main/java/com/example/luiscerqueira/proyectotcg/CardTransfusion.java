package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Clase para la construccion especifica de una carta estilo Transfusion, actualmente en uso solo para Jugador 2
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 12/02/2015.
 */
public class CardTransfusion extends Carta {

    public CardTransfusion(Context context,Jugador owner, Jugador enemigo) {
        super(context, owner, enemigo);
        this.setCoste(2);
        this.setTipo(Tipos.HECHIZO);
        this.setNombre("Transfusion");
        this.setImagen(R.drawable.cardvitaltransfusion);
    }

    @Override
    public void playCard() {
        super.playCard();
        Log.i("CARTA JUGADA", "VIDAS ENEMIGO" + getEnemigo().getVidas());
        getEnemigo().setVidas(getEnemigo().getVidas()-3);
        getOwner().setVidas(getOwner().getVidas()+2);
        Log.i("CARTA JUGADA", "CARTA JUGADA -3 VIDAS");
        Log.i("CARTA JUGADA", "CARTA JUGADA +2 VIDAS OWNER");
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
