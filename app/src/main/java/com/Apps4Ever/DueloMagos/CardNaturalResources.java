package com.Apps4Ever.DueloMagos;

import android.content.Context;
import android.util.Log;

/**
 * Clase para la construccion especifica de una carta estilo NaturalResources, actualmente en uso solo para Jugador 2
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 13/02/2015.
 */
public class CardNaturalResources extends Carta {
    public CardNaturalResources(Context context, Jugador owner, Jugador enemigo) {
        super(context, owner, enemigo);
        this.setCoste(2);
        this.setTipo(Tipos.HECHIZO);
        this.setNombre("NaturalResources");
        this.setImagen(R.drawable.cardnaturalresources);
    }

    @Override
    public void playCard() {
        super.playCard();
        getOwner().setRecursos(getOwner().getRecursos()+5);
        Log.i("CARTA JUGADA", "CARTA JUGADA +5 RECURSOS");
        getOwner().moveCardFromTableToDiscard(getId());
    }

    @Override
    public void movedFromHandToTable() {
        super.movedFromHandToTable();
        setAnimar(true);
    }
}