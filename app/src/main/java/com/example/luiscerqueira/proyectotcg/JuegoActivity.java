package com.example.luiscerqueira.proyectotcg;

        import android.app.Activity;
        import android.content.pm.ActivityInfo;
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
        Carta cartaPrueba2=new Carta(this,jugador1,jugador2,R.drawable.saberchibi,R.drawable.cardbackprueba,R.drawable.circulo);
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
        jugador1.getDeck().add(cartaPrueba2);
        jugador1.getDeck().add(cardRelampago);
        jugador1.getDeck().add(cardHeal);
        jugador1.getDeck().add(cardRitual);
        jugador1.getDeck().add(cardMentalSpiral);
        jugador1.getDeck().add(cardBurningSign);
        jugador1.getDeck().add(cardIgniteMemories);
        jugador1.getDeck().add(cardNightmare);
        jugador1.getDeck().add(cardTransfusion);
        jugador1.getDeck().add(cardHealingSign);
        jugador1.getDeck().add(cardMysticalSign);
        jugador1.getDeck().add(cardNaturalSign);
        jugador1.getDeck().add(cardNaturalHelp);
        jugador1.getDeck().add(cardNaturalResources);
        jugador1.setActivo(true);
        jugador1.moveFromDeckToHand(3);
        //Jugador2
        cartaPrueba2=new Carta(this,jugador2, jugador1,R.drawable.saberchibi, R.drawable.cardbackprueba, R.drawable.circulo);
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
        jugador2.getDeck().add(cartaPrueba2);
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

