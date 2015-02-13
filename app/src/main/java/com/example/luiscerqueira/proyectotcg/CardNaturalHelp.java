package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Luis Cerqueira on 13/02/2015.
 */
public class CardNaturalHelp extends Carta {
    public CardNaturalHelp(Context context, Jugador owner, Jugador enemigo) {
        super(context, owner, enemigo);
        this.setCoste(0);
        this.setNombre("NaturalHelp");
        this.setImagen(R.drawable.cardnaturalhelp);
    }

    @Override
    public void playCard() {
        super.playCard();
        getOwner().setRecursos(getOwner().getRecursos()+2);
        Log.i("CARTA JUGADA", "CARTA JUGADA +2 RECURSOS");
        getOwner().moveCardFromTableToDiscard(getId());
    }

    @Override
    public void movedFromHandToTable() {
        super.movedFromHandToTable();
        setAnimar(true);
    }
}
