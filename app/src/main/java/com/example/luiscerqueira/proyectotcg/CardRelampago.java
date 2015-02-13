package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

public class CardRelampago extends Carta{

	public CardRelampago(Context context,Jugador owner, Jugador enemigo) {
		super(context, owner, enemigo);
		this.setCoste(1);
		this.setImagen(BitmapFactory.decodeResource(context.getResources(), R.drawable.cardlightning));
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
        ((JuegoActivity)getContexto()).mandarInvalidar();
	}

    @Override
	public void movedFromHandToTable() {
		super.movedFromHandToTable();
        setAnimar(true);
	}
	
}
