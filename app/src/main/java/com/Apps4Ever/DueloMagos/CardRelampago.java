package com.Apps4Ever.DueloMagos;

import android.content.Context;
import android.util.Log;

/**
 * Clase para la construccion especifica de una carta estilo Lightning, actualmente en uso solo para Jugador 2
 *
 * @author Luis Cerqueira
 */
public class CardRelampago extends Carta{

	public CardRelampago(Context context,Jugador owner, Jugador enemigo) {
		super(context, owner, enemigo);
		this.setCoste(1);
        this.setTipo(Tipos.HECHIZO);
        this.setNombre("Lightning");
		this.setImagen(R.drawable.cardlightning);
	}

	@Override
	public void playCard() {		
		super.playCard();
        Log.i("CARTA JUGADA", "VIDAS ENEMIGO"+getEnemigo().getVidas());
		getEnemigo().setVidas(getEnemigo().getVidas()-2);
		Log.i("CARTA JUGADA", "CARTA JUGADA -2 VIDAS");
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
