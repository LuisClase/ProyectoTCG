package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

public class CardRelampago extends Carta{

	public CardRelampago(Context context, int id, Jugador owner, Jugador enemigo) {
		super(context, id, owner, enemigo);
		this.setCoste(1);
		this.setImagen(BitmapFactory.decodeResource(context.getResources(), R.drawable.cardlightning));
	}

	@Override
	public void playCard() {		
		super.playCard();
		getOwner().setVidas(getOwner().getVidas()-2);
		Log.i("CARTA JUGADA", "CARTA JUGADA -2 VIDAS");
	}

	@Override
	public void movedFromHandToTable() {
		super.movedFromHandToTable();
		this.playCard();
	}
	
}
