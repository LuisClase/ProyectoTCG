package com.example.luiscerqueira.proyectotcg;

        import android.app.Activity;
        import android.content.pm.ActivityInfo;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.Window;
        import android.view.WindowManager;

        import java.util.ArrayList;


public class JuegoActivity extends Activity {

    public ViewJuego pantallaJuego;
    public Jugador jugador1;
    public Jugador jugador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pantallaJuego=new ViewJuego(this);
        pantallaJuego.setKeepScreenOn(true);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(pantallaJuego);

        //Pruebas
        jugador1=new Jugador(20,1);
        if(jugador1==null) {
            Log.i("CONSTRUCTOR PRIN", "JUGADOR1:NULL");
        }else{
            Log.i("CONSTRUCTOR PRIN", "JUGADOR1:NO NULL");
        }
        jugador2=new Jugador(20,0);
        //Jugador1
        Bitmap cartaFront= BitmapFactory.decodeResource(getResources(), R.drawable.frontcard);
//        Carta cartaPrueba2=new Carta(this,jugador1,jugador2,R.drawable.saberchibi,R.drawable.cardbackprueba,R.drawable.circulo);
        CardRelampago cardRelampago=new CardRelampago(this, jugador1, jugador2);
        CardHeal cardHeal=new CardHeal(this,jugador1,jugador2);
        CardRitual cardRitual=new CardRitual(this,jugador1,jugador2);
        CardMentalSpiral cardMentalSpiral=new CardMentalSpiral(this,jugador1,jugador2);
        CardBurningSign cardBurningSign=new CardBurningSign(this,jugador1,jugador2);
        CardIgniteMemories cardIgniteMemories=new CardIgniteMemories(this,jugador1,jugador2);
        CardNightmare cardNightmare=new CardNightmare(this,jugador1,jugador2);
        CardTransfusion cardTransfusion=new CardTransfusion(this,jugador1,jugador2);
        CardHealingSign cardHealingSign=new CardHealingSign(this,jugador1,jugador2);
        CardMysticalSign cardMysticalSign=new CardMysticalSign(this,jugador1,jugador2);
        CardNaturalHelp cardNaturalHelp=new CardNaturalHelp(this,jugador1,jugador2);
        CardNaturalResources cardNaturalResources=new CardNaturalResources(this,jugador1,jugador2);
        CardNaturalSign cardNaturalSign=new CardNaturalSign(this,jugador1,jugador2);
//        jugador1.getDeck().add(cartaPrueba2);
//        jugador1.getDeck().add(cardRelampago);
//        jugador1.getDeck().add(cardHeal);
//        jugador1.getDeck().add(cardRitual);
//        jugador1.getDeck().add(cardMentalSpiral);
//        jugador1.getDeck().add(cardBurningSign);
//        jugador1.getDeck().add(cardIgniteMemories);
//        jugador1.getDeck().add(cardNightmare);
//        jugador1.getDeck().add(cardTransfusion);
//        jugador1.getDeck().add(cardHealingSign);
//        jugador1.getDeck().add(cardMysticalSign);
//        jugador1.getDeck().add(cardNaturalSign);
//        jugador1.getDeck().add(cardNaturalHelp);
//        jugador1.getDeck().add(cardNaturalResources);
        //PRUEBAS
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        SQLiteDatabase sqLiteDB=(new BDSQLite(this,"cartas",null,1)).getReadableDatabase();
        if(sqLiteDB!=null){
            Cursor c=sqLiteDB.rawQuery("select * from cartas",null);
            Carta carta=null;
            if(c.moveToFirst()){
                int jugador;
                String nombre="";
                int imagen;
                int cantidad;
                int coste;
                int[] datos = new int[17];
                int[] datos2 = new int[17];
                boolean[] boleans = new boolean[20];

                do{
                    jugador=c.getInt(c.getColumnIndex("jugador"));
                    nombre=c.getString(c.getColumnIndex("nombre"));
                    imagen=c.getInt(c.getColumnIndex("imagen"));
                    coste=c.getInt(c.getColumnIndex("coste"));
                    cantidad=c.getInt(c.getColumnIndex("cantidad"));
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
                    for(int j=0;j<cantidad;j++){
                        carta=new Carta(this,jugador1,jugador2,nombre,imagen,coste,datos,datos2,boleans);
                        jugador1.getDeck().add(carta);
                    }
                }while(c.moveToNext());
            }

        }else{
            sqLiteDB.close();
        }
        sqLiteDB.close();

        jugador1.setActivo(true);
        jugador1.barajar();
        jugador1.moveFromDeckToHand(3);
        //Jugador2
//        cartaPrueba2=new Carta(this,jugador2, jugador1,R.drawable.saberchibi, R.drawable.cardbackprueba, R.drawable.circulo);
        cardRelampago=new CardRelampago(this, jugador2, jugador1);
        cardHeal=new CardHeal(this,jugador2,jugador1);
        cardRitual=new CardRitual(this,jugador2,jugador1);
        cardMentalSpiral=new CardMentalSpiral(this,jugador2,jugador1);
        cardBurningSign=new CardBurningSign(this,jugador2,jugador1);
        cardIgniteMemories=new CardIgniteMemories(this,jugador2,jugador1);
        cardNightmare=new CardNightmare(this,jugador2,jugador1);
        cardTransfusion=new CardTransfusion(this,jugador2,jugador1);
        cardHealingSign=new CardHealingSign(this,jugador2,jugador1);
        cardMysticalSign=new CardMysticalSign(this,jugador2,jugador1);
        cardNaturalHelp=new CardNaturalHelp(this,jugador2,jugador1);
        cardNaturalResources=new CardNaturalResources(this,jugador2,jugador1);
        cardNaturalSign=new CardNaturalSign(this,jugador2,jugador1);
//        jugador2.getDeck().add(cartaPrueba2);
        jugador2.getDeck().add(cardRelampago);
        jugador2.getDeck().add(cardHeal);
        jugador2.getDeck().add(cardRitual);
        jugador2.getDeck().add(cardMentalSpiral);
        jugador2.getDeck().add(cardBurningSign);
        jugador2.getDeck().add(cardIgniteMemories);
        jugador2.getDeck().add(cardNightmare);
        jugador2.getDeck().add(cardTransfusion);
        jugador2.getDeck().add(cardHealingSign);
        jugador2.getDeck().add(cardMysticalSign);
        jugador2.getDeck().add(cardNaturalSign);
        jugador2.getDeck().add(cardNaturalHelp);
        jugador2.getDeck().add(cardNaturalResources);
        jugador2.setActivo(false);
        jugador2.barajar();
        jugador2.moveFromDeckToHand(3);

//        Log.i("MAZO2","TAMAÑO:"+jugador2.getDeck().size());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_juego, menu);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        pantallaJuego.getHilo().setFuncionando(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        pantallaJuego.getHilo().setFuncionando(false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pantallaJuego.getHilo().setFuncionando(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pantallaJuego.getHilo().setFuncionando(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        pantallaJuego.getHilo().setFuncionando(false);
    }
}

