package com.example.luiscerqueira.proyectotcg;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;


public class ActivityTienda extends Activity {
    Jugador jugador;
    SharedPreferences preferenciasJugador;
    SharedPreferences.Editor editorJugador;
    TextView txtDinero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_tienda);

        jugador=new Jugador(20,0);

        SharedPreferences preferencias=getSharedPreferences("Generales", Context.MODE_APPEND);
        String nombreJugador=getIntent().getStringExtra("JUGADOR");
        for(int i=0;i<preferencias.getAll().size();i++){
            if (preferencias.getString(String.format("JUGADOR"+i),"NULL").equals(nombreJugador)){
                preferenciasJugador= getSharedPreferences(preferencias.getString(String.format("JUGADOR" + i), "NULL"), Context.MODE_PRIVATE);
                jugador.setNombre(preferencias.getString(String.format("JUGADOR" + i), "NULL"));
                jugador.setRecursosIniciales(preferenciasJugador.getInt("RECURSOS", 0));
                jugador.setVidasIniciales(preferenciasJugador.getInt("VIDAS", 0));
                jugador.setCartasIniciales(preferenciasJugador.getInt("MANO", 0));
                jugador.setPoder(preferenciasJugador.getInt("PODER", 0));
                jugador.setDinero(preferenciasJugador.getInt("DINERO", 0));

                Log.d("SHAREDPREF", "NOMBRE:" + jugador.getNombre());
                Log.d("SHAREDPREF", "MANO:" + jugador.getCartasIniciales());
                Log.d("SHAREDPREF", "RECURSOS:" + jugador.getRecursosIniciales());
                Log.d("SHAREDPREF", "VIDAS:" + jugador.getVidasIniciales());
            }
        }
        editorJugador=preferenciasJugador.edit();

        txtDinero=(TextView)findViewById(R.id.txtDinero);
        txtDinero.setText("Dinero: "+jugador.getDinero());

        FloatingActionsMenu fam = (FloatingActionsMenu) findViewById(R.id.famVidas);
        Log.d("NULL?","FAMVIDAS CREADO null?"+(fam==null?"NULL":"NO NULL"));
        FloatingActionButton fbtnMasUno =new FloatingActionButton(getBaseContext());
        fbtnMasUno.setTitle("Vidas +1/-20G");
        fbtnMasUno.setSize(FloatingActionButton.SIZE_MINI);
        fbtnMasUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jugador.getDinero()>=20) {
                    jugador.setDinero(jugador.getDinero()-20);
                    editorJugador.putInt("VIDAS", (preferenciasJugador.getInt("VIDAS", 0) + 1));
                    editorJugador.putInt("DINERO",jugador.getDinero());
                    txtDinero.setText("Dinero: " + jugador.getDinero());
                    editorJugador.apply();
                }
            }
        });
        FloatingActionButton fbtnMasDiez=new FloatingActionButton(getBaseContext());
        fbtnMasDiez.setTitle("Vidas +10/-200G");
        fbtnMasDiez.setSize(FloatingActionButton.SIZE_MINI);
        fbtnMasDiez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jugador.getDinero()>=200) {
                    jugador.setDinero(jugador.getDinero()-200);
                    editorJugador.putInt("VIDAS", (preferenciasJugador.getInt("VIDAS", 0) + 10));
                    txtDinero.setText("Dinero: " + jugador.getDinero());
                    editorJugador.putInt("DINERO", jugador.getDinero());
                    editorJugador.apply();
                }
            }
        });
        Log.d("NULL?", "MASDIEZ null?" + (fbtnMasDiez == null ? "NULL" : "NO NULL"));
        Log.d("NULL?","FAMVIDAS null?"+(fam==null?"NULL":"NO NULL"));
        fam.addButton(fbtnMasDiez);
        fam.addButton(fbtnMasUno);

        FloatingActionsMenu famCartas = (FloatingActionsMenu) findViewById(R.id.famCartas);
        final FloatingActionButton fbtnCartasMasDiez=new FloatingActionButton(getBaseContext());
        fbtnCartasMasDiez.setTitle("Cartas +2/-40G");
        fbtnCartasMasDiez.setSize(FloatingActionButton.SIZE_MINI);
        fbtnCartasMasDiez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jugador.getDinero()>=40 ) {
                    jugador.setDinero(jugador.getDinero() - 40);
                    editorJugador.putInt("MANO", (preferenciasJugador.getInt("MANO", 0) + 2));
                    txtDinero.setText("Dinero: " + jugador.getDinero());
                    editorJugador.putInt("DINERO", jugador.getDinero());
                    if((preferenciasJugador.getInt("MANO", 0)>=5)){
                        editorJugador.putInt("MANO", 5);
                        fbtnCartasMasDiez.setActivated(false);
                    }
                    editorJugador.apply();
                }
            }
        });
        FloatingActionButton fbtnCartasMasUno=new FloatingActionButton(getBaseContext());
        fbtnCartasMasUno.setTitle("Cartas +1/-20G");
        fbtnCartasMasUno.setSize(FloatingActionButton.SIZE_MINI);
        fbtnCartasMasDiez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jugador.getDinero()>=20) {
                    jugador.setDinero(jugador.getDinero()-20);
                    editorJugador.putInt("MANO", (preferenciasJugador.getInt("MANO", 0) + 1));
                    txtDinero.setText("Dinero: " + jugador.getDinero());
                    editorJugador.putInt("DINERO", jugador.getDinero());
                    if((preferenciasJugador.getInt("MANO", 0)>=5)){
                        editorJugador.putInt("MANO", 5);
                        fbtnCartasMasDiez.setActivated(false);
                    }
                    editorJugador.apply();
                }
            }
        });
        famCartas.addButton(fbtnCartasMasDiez);
        famCartas.addButton(fbtnCartasMasUno);

        FloatingActionsMenu famRecursos = (FloatingActionsMenu) findViewById(R.id.famRecursos);
        FloatingActionButton fbtnRecursosMasDiez=new FloatingActionButton(getBaseContext());
        fbtnRecursosMasDiez.setTitle("Recursos +10/-200G");
        fbtnRecursosMasDiez.setSize(FloatingActionButton.SIZE_MINI);
        fbtnRecursosMasDiez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jugador.getDinero()>=200) {
                    jugador.setDinero(jugador.getDinero()-200);
                    editorJugador.putInt("RECURSOS", (preferenciasJugador.getInt("RECURSOS", 0) + 10));
                    txtDinero.setText("Dinero: " + jugador.getDinero());
                    editorJugador.putInt("DINERO", jugador.getDinero());
                    editorJugador.apply();
                }
            }
        });
        FloatingActionButton fbtnRecursosMasUno=new FloatingActionButton(getBaseContext());
        fbtnRecursosMasUno.setTitle("Recursos +1/-20G");
        fbtnRecursosMasUno.setSize(FloatingActionButton.SIZE_MINI);
        fbtnRecursosMasUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jugador.getDinero()>=20) {
                    jugador.setDinero(jugador.getDinero()-20);
                    editorJugador.putInt("RECURSOS", (preferenciasJugador.getInt("RECURSOS", 0) + 1));
                    txtDinero.setText("Dinero: " + jugador.getDinero());
                    editorJugador.putInt("DINERO", jugador.getDinero());
                    editorJugador.apply();
                }
            }
        });
        famRecursos.addButton(fbtnRecursosMasDiez);
        famRecursos.addButton(fbtnRecursosMasUno);

        FloatingActionsMenu famPoder = (FloatingActionsMenu) findViewById(R.id.famPoder);
        FloatingActionButton fbtnPoderMasDiez=new FloatingActionButton(getBaseContext());
        fbtnPoderMasDiez.setTitle("Poder +10/-200G");
        fbtnPoderMasDiez.setSize(FloatingActionButton.SIZE_MINI);
        fbtnPoderMasDiez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jugador.getDinero()>=200) {
                    jugador.setDinero(jugador.getDinero()-200);
                    editorJugador.putInt("PODER", (preferenciasJugador.getInt("PODER", 0) + 10));
                    txtDinero.setText("Dinero: " + jugador.getDinero());
                    editorJugador.putInt("DINERO", jugador.getDinero());
                    editorJugador.apply();
                }
            }
        });
        FloatingActionButton fbtnPoderMasUno=new FloatingActionButton(getBaseContext());
        fbtnPoderMasUno.setTitle("Poder +1/-20G");
        fbtnPoderMasUno.setSize(FloatingActionButton.SIZE_MINI);
        fbtnPoderMasUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jugador.getDinero()>=20) {
                    jugador.setDinero(jugador.getDinero()-20);
                    editorJugador.putInt("PODER", (preferenciasJugador.getInt("PODER", 0) + 1));
                    txtDinero.setText("Dinero: " + jugador.getDinero());
                    editorJugador.putInt("DINERO",jugador.getDinero());
                    editorJugador.apply();
                }
            }
        });
        famPoder.addButton(fbtnPoderMasDiez);
        famPoder.addButton(fbtnPoderMasUno);
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
