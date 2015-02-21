package com.example.luiscerqueira.proyectotcg;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import java.util.*;
import android.util.*;
import android.widget.*;
import android.app.*;
import android.content.*;



public class ActivityMazos extends ListActivity {
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_activity_mazos);
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        cartas.add(new CardHealingSign(this,null,null));
        cartas.add(new CardHeal(this,null,null));
        cartas.add(new CardBurningSign(this,null,null));
        cartas.add(new CardIgniteMemories(this,null,null));
        cartas.add(new CardMentalSpiral(this,null,null));
        cartas.add(new CardMysticalSign(this,null,null));
        cartas.add(new CardNaturalHelp(this,null,null));
        cartas.add(new CardNaturalResources(this,null,null));
        cartas.add(new CardHealingSign(this,null,null));
        cartas.add(new CardRitual(this,null,null));
        cartas.add(new CardTransfusion(this,null,null));
        setListAdapter(new Adaptador(this,cartas));
        contexto=getListView().getContext();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_mazos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
