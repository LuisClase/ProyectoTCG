package com.example.luiscerqueira.proyectotcg;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import java.util.*;
import android.util.*;
import android.widget.*;
import android.app.*;
import android.content.*;



public class ActivityMazos extends ListActivity {
    public Context contexto;
    Jugador jugador1;
    public BDSQLite bd=new BDSQLite(this,"cartas",null,1);
    public SQLiteDatabase sqLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("BD","NULL?"+bd==null?"SI":"NO" );
        sqLiteDB=bd.getReadableDatabase();
        ArrayList<Carta> cartas=new ArrayList<Carta>();

        Log.i("BD","ANTES ONCREATE");
        if(sqLiteDB!=null){
            Cursor c=sqLiteDB.rawQuery("select * from cartas",null);
            Carta carta=null;
            if(c.moveToFirst()){
                int jugador;
                String nombre="";
                int imagen;
                int cantidad;
                int coste;
                int tipo;
                int[] datos = new int[17];
                int[] datos2 = new int[17];
                boolean[] boleans = new boolean[20];

                do{
                    jugador=c.getInt(c.getColumnIndex("jugador"));
                    nombre=c.getString(c.getColumnIndex("nombre"));
                    imagen=c.getInt(c.getColumnIndex("imagen"));
                    coste=c.getInt(c.getColumnIndex("coste"));
                    cantidad=c.getInt(c.getColumnIndex("cantidad"));
                    tipo=c.getInt(c.getColumnIndex("tipo"));
                    datos[0]=c.getInt(c.getColumnIndex("dañoEnemigo"));
                    datos[1]=(c.getInt(c.getColumnIndex("curaEnemigo")));
                    datos[2]=(c.getInt(c.getColumnIndex("cartasEnemigo")));
                    datos[3]=(c.getInt(c.getColumnIndex("descarteEnemigo")));
                    datos[4]=(c.getInt(c.getColumnIndex("recursosEnemigo")));
                    datos[5]=(c.getInt(c.getColumnIndex("moverMesaAManoEnemigo")));
                    datos[6]=(c.getInt(c.getColumnIndex("moverDescarteAmanoEnemigo")));
                    datos[7]=(c.getInt(c.getColumnIndex("moverDeckAmanoEnemigo")));
                    datos[8]=(c.getInt(c.getColumnIndex("moverMesaADeckEnemigo")));
                    datos[9]=(c.getInt(c.getColumnIndex("moverDescarteADeckEnemigo")));
                    datos[10]=(c.getInt(c.getColumnIndex("moverManoADeckEnemigo")));
                    datos[11]=(c.getInt(c.getColumnIndex("moverMesaADescarteEnemigo")));
                    datos[12]=(c.getInt(c.getColumnIndex("moverManoADescarteEnemigo")));
                    datos[13]=(c.getInt(c.getColumnIndex("moverDeckADescarteEnemigo")));
                    datos[14]=(c.getInt(c.getColumnIndex("moverDescarteAMesaEnemigo")));
                    datos[15]=(c.getInt(c.getColumnIndex("moverManoAMesaEnemigo")));
                    datos[16]=(c.getInt(c.getColumnIndex("moverDeckAMesaEnemigo")));
                    datos2[0]=(c.getInt(c.getColumnIndex("dañoOwner")));
                    datos2[1]=(c.getInt(c.getColumnIndex("curaOwner")));
                    datos2[2]=(c.getInt(c.getColumnIndex("cartasOwner")));
                    datos2[3]=(c.getInt(c.getColumnIndex("descarteOwner")));
                    datos2[4]=(c.getInt(c.getColumnIndex("recursosOwner")));
                    datos2[5]=(c.getInt(c.getColumnIndex("moverMesaAManoOwner")));
                    datos2[6]=(c.getInt(c.getColumnIndex("moverDescarteAmanoOwner")));
                    datos2[7]=(c.getInt(c.getColumnIndex("moverDeckAmanoOwner")));
                    datos2[8]=(c.getInt(c.getColumnIndex("moverMesaADeckOwner")));
                    datos2[9]=(c.getInt(c.getColumnIndex("moverDescarteADeckOwner")));
                    datos2[10]=(c.getInt(c.getColumnIndex("moverManoADeckOwner")));
                    datos2[11]=(c.getInt(c.getColumnIndex("moverMesaADescarteOwner")));
                    datos2[12]=(c.getInt(c.getColumnIndex("moverManoADescarteOwner")));
                    datos2[13]=(c.getInt(c.getColumnIndex("moverDeckADescarteOwner")));
                    datos2[14]=(c.getInt(c.getColumnIndex("moverDescarteAMesaOwner")));
                    datos2[15]=(c.getInt(c.getColumnIndex("moverManoAMesaOwner")));
                    datos2[16]=(c.getInt(c.getColumnIndex("moverDeckAMesaOwner")));
                    boleans[0]=(c.getInt(c.getColumnIndex("onMoveMesaADescarte"))>0);
                    boleans[1]=(c.getInt(c.getColumnIndex("onMoveMesaADeck"))>0);
                    boleans[2]=(c.getInt(c.getColumnIndex("onMoveMesaAMano"))>0);
                    boleans[3]=(c.getInt(c.getColumnIndex("onMoveDescarteAMesa"))>0);
                    boleans[4]=(c.getInt(c.getColumnIndex("onMoveDescarteADeck"))>0);
                    boleans[5]=(c.getInt(c.getColumnIndex("onMoveDescarteAMano"))>0);
                    boleans[6]=(c.getInt(c.getColumnIndex("onMoveDeckADescarte"))>0);
                    boleans[7]=(c.getInt(c.getColumnIndex("onMoveDeckAMesa"))>0);
                    boleans[8]=(c.getInt(c.getColumnIndex("onMoveDeckAMano"))>0);
                    boleans[9]=(c.getInt(c.getColumnIndex("onMoveManoADescarte"))>0);
                    boleans[10]=(c.getInt(c.getColumnIndex("onMoveManoAMesa"))>0);
                    boleans[11]=(c.getInt(c.getColumnIndex("onMoveManoADeck"))>0);
                    boleans[12]=(c.getInt(c.getColumnIndex("onStartTurnTable"))>0);
                    boleans[13]=(c.getInt(c.getColumnIndex("onStartTurnHand"))>0);
                    boleans[14]=(c.getInt(c.getColumnIndex("onStartTurnDiscard"))>0);
                    boleans[15]=(c.getInt(c.getColumnIndex("onStartTurnDeck"))>0);
                    boleans[16]=(c.getInt(c.getColumnIndex("onEndTurnTable"))>0);
                    boleans[17]=(c.getInt(c.getColumnIndex("onEndTurnHand"))>0);
                    boleans[18]=(c.getInt(c.getColumnIndex("onEndTurnDiscard"))>0);
                    boleans[19]=(c.getInt(c.getColumnIndex("onEndTurnDeck"))>0);
                    Tipos tipos;
                    if(tipo==Tipos.HECHIZO.ordinal()){
                        tipos=Tipos.HECHIZO;
                    }
                    else if(tipo==Tipos.PERMANENTE.ordinal()){
                        tipos=Tipos.PERMANENTE;
                    }
                    else if(tipo==Tipos.MAGO.ordinal()){
                        tipos=Tipos.MAGO;
                    }
                    else{
                        tipos=Tipos.HECHIZO;
                    }
                    carta=new Carta(this,null,null,nombre,imagen,coste,tipos,datos,datos2,boleans);
                    carta.setCantidad(cantidad);
                    cartas.add(carta);
                }while(c.moveToNext());
            }

        }else{
            sqLiteDB.close();
        }
        sqLiteDB.close();
//        setContentView(R.layout.activity_activity_mazos);
//        jugador1=getIntent().getParcelableExtra("jugador");
//        Log.i("PARCELABLE","TAMAÑO:"+jugador1.getDeck().size());
//        for(int i=0;i<jugador1.getDeck().size();i++){
//            Log.i("PARCELABLE","CARTA:"+jugador1.getDeck().get(i).getNombre());
//        }
        /*
        int[]valoresEnemigo={
//                this.dañoEnemigo=valoresEnemigo[0];
                2,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[]valoresOwner={
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };

        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
        //this.OnMoveMesaADeck=valoresPlay[1];
                false,
        //this.OnMoveMesaAMano=valoresPlay[2];
                false,
        //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
        //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                false,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };

        cartas.add(new Carta(this,null,null,"Prueba",R.drawable.saberchibi,1,valoresEnemigo,valoresOwner,valoresPlay));
        cartas.add(new CardHealingSign(this,null,null));
        cartas.add(new CardHeal(this,null,null));
        cartas.add(new CardBurningSign(this,null,null));
        cartas.add(new CardIgniteMemories(this,null,null));
        cartas.add(new CardMentalSpiral(this,null,null));
        cartas.add(new CardMysticalSign(this,null,null));
        cartas.add(new CardNaturalHelp(this,null,null));
        cartas.add(new CardNaturalResources(this,null,null));
        cartas.add(new CardRitual(this,null,null));
        cartas.add(new CardTransfusion(this,null,null));
        */
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
