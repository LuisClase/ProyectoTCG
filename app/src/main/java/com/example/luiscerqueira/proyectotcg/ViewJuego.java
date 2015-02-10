package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * Created by Luis Cerqueira on 22/01/2015.
 */
public class ViewJuego extends View {

    private Bitmap fondo;
    private Bitmap cartaBack;
    private Bitmap avatar;
    private Bitmap temp;
    private Bitmap infoCard;
    private int anchoPantalla;
    private int altoPantalla;
    int anchoCarta;
    int altoCarta;
    private Jugador jugador1;
    private Jugador jugador2;
    boolean activado=false;
    private int x,xDeck,xDescarte;
    private int y,yDeck,yDescarte;
    private int alturaDeck;
    private int anchuraDeck;
    private boolean pulsandoPlay=false;
    private int idTemp=-1;

    public ViewJuego(Context context) {
        super(context);
        WindowManager wm=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display=wm.getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        anchoPantalla=size.x;
        altoPantalla=size.y;
        //Fondo
        fondo= BitmapFactory.decodeResource(getResources(),R.drawable.tablero);
        fondo=Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, true);
        //Pruebas

        //cartaBack=BitmapFactory.decodeResource(getResources(),R.drawable.cardback);
        anchoCarta=anchoPantalla/6;
        altoCarta=(int)(anchoCarta*1.39);
        //cartaBack=Bitmap.createScaledBitmap(cartaBack,anchoCarta,altoCarta,true);
        avatar=BitmapFactory.decodeResource(getResources(),R.drawable.saber);
        avatar=Bitmap.createScaledBitmap(avatar,anchoPantalla/8,altoPantalla/8,true);

        //Jugadores
        jugador1=((JuegoActivity)context).jugador1;
        jugador2=((JuegoActivity)context).jugador2;
        //deck
        alturaDeck=altoPantalla-altoCarta;
        anchuraDeck=anchoCarta;
        x=avatar.getWidth();
        y=alturaDeck;
        xDeck=x;
        yDeck=y;
        xDescarte=anchoPantalla-2*anchoCarta;
        yDescarte=y;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.anchoPantalla=w;
        this.altoPantalla=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Jugadores
        jugador1=((JuegoActivity)this.getContext()).jugador1;
        jugador2=((JuegoActivity)this.getContext()).jugador2;
        Log.i("ONDRAW","Despues actualizar");
        x=avatar.getWidth();
        y=alturaDeck;

        //jugador1
        jugador1.setActivo(true);
        jugador1.setDeckX(xDeck);
        jugador1.setDeckXfin(xDeck+anchoCarta);
        jugador1.setDeckY(yDeck);
        jugador1.setDeckYfin(yDeck+alturaDeck);
        jugador1.setDescarteX(anchoPantalla-(int)(anchoCarta*1.2));
        jugador1.setDescarteXfin(jugador1.getDescarteX()+anchoCarta);
        jugador1.setDescarteY(yDeck);
        jugador1.setDescarteYfin(yDeck+alturaDeck);
        jugador1.setManoX(jugador1.getDeckX()+(int)(1.2*anchoCarta));
        jugador1.setManoXfin(jugador1.getDescarteX()-(int)(0.5*anchoCarta));
        jugador1.setManoY(yDeck);
        jugador1.setManoYfin(yDeck+alturaDeck);
        jugador1.setMesaX(jugador1.getManoX());
        jugador1.setMesaXfin(jugador1.getManoXfin());
        jugador1.setMesaY(jugador1.getManoY()-(int)(1.2*altoCarta));
        jugador1.setMesaYfin(jugador1.getMesaY()+altoCarta);

        //jugador2
        jugador2.setActivo(true);
        jugador2.setDeckX(anchoPantalla-(int)(anchoCarta*1.2));
        jugador2.setDeckXfin(jugador2.getDeckX()+anchoCarta);
        jugador2.setDeckY(0);
        jugador2.setDeckYfin(jugador2.getDeckY()+altoCarta);
        jugador2.setDescarteX(xDeck);
        jugador2.setDescarteXfin(xDeck+anchoCarta);
        jugador2.setDescarteY(0);
        jugador2.setDescarteYfin(jugador2.getDeckY()+altoCarta);
        jugador2.setManoX(jugador1.getDeckX()+(int)(1.2*anchoCarta));
        jugador2.setManoXfin(jugador1.getDescarteX()-(int)(0.5*anchoCarta));
        jugador2.setManoY(0);
        jugador2.setManoYfin(jugador2.getManoY()+altoCarta);
        jugador2.setMesaX(jugador1.getManoX());
        jugador2.setMesaXfin(jugador1.getManoXfin());
        jugador2.setMesaY(jugador2.getManoYfin()+(int)(0.2*altoCarta));
        jugador2.setMesaYfin(jugador2.getMesaY()+altoCarta);

        canvas.drawBitmap(fondo,0,0,null);
        canvas.drawBitmap(avatar,0,altoPantalla-(altoPantalla/5),null);
        Paint p=new Paint();
        p.setColor(Color.RED);
        p.setTextSize(30);
        Log.i("VIDAS", "VIDAS" + jugador1.getVidas());
        //jugador1
        canvas.drawText("Vidas:" + jugador1.getVidas(), jugador1.getDeckX(), jugador1.getDeckY() - (int) (0.5 * altoCarta), p);
        canvas.drawText("Recursos:"+jugador1.getRecursos(), jugador1.getDeckX(), jugador1.getDeckY()-(int)(0.2*altoCarta), p);
        //jugador2
        p.setColor(Color.BLUE);
        canvas.drawText("Vidas:" + jugador2.getVidas(), jugador2.getDeckX(), jugador2.getDeckYfin() + (int) (0.5 * altoCarta), p);
        canvas.drawText("Recursos:"+jugador2.getRecursos(), jugador2.getDeckX(), jugador2.getDeckYfin()+(int)(0.2*altoCarta), p);

        //Zona-Informacion
        p=new Paint();
        if(infoCard==null) {
            infoCard = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cardbackprueba);
        }
        infoCard = Bitmap.createScaledBitmap(infoCard, (int)(1.5*anchoCarta), (int)(altoCarta*1.5), true);
        canvas.drawBitmap(infoCard, 0, (int)((altoPantalla/2)-(altoCarta*0.75)), null);
        if(idTemp!=-1){

        }
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0
                ,(int)((altoPantalla/2)-(altoCarta*0.75))
                ,(int)(anchoCarta*1.5)
                ,(int)((altoPantalla/2)+(altoCarta*0.75))
                ,p);
        Log.i("ONDRAW", "dibujando DESCARTE");

        //Boton Jugar
        p=new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0
                ,(int)((altoPantalla/2)-(altoCarta*1.2))
                ,(int)(anchoCarta*1.5)
                ,(int)((altoPantalla/2)-(altoCarta*0.75))
                ,p);
        Log.i("ONDRAW", "dibujando DESCARTE");
        temp=BitmapFactory.decodeResource(getContext().getResources(),R.drawable.botonplay);
        if(pulsandoPlay){
            temp=BitmapFactory.decodeResource(getContext().getResources(),R.drawable.botonplaypulsado);
        }
        temp = Bitmap.createScaledBitmap(temp, (int)(1.5*anchoCarta), (int)(altoCarta*0.4), true);
        canvas.drawBitmap(temp, 0, (int)((altoPantalla/2)-(altoCarta*1.2)), null);
        //Boton fin turno
        p=new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawRect((anchoPantalla/2)-(anchoCarta)
                ,(altoPantalla/2)-(altoCarta/4)
                ,(anchoPantalla/2)+(anchoCarta)
                ,(altoPantalla/2)+(altoCarta/4),p);

        temp=BitmapFactory.decodeResource(getContext().getResources(),R.drawable.botonnoactivo);
        if(jugador1.isTocandoFinTurno()){
            temp=BitmapFactory.decodeResource(getContext().getResources(),R.drawable.botonpresionado);
        }else if(jugador1.isActivo()){
            temp=BitmapFactory.decodeResource(getContext().getResources(),R.drawable.botonnoactivo);
        }
        temp = Bitmap.createScaledBitmap(temp, 2*anchoCarta, altoCarta/2, true);
        canvas.drawBitmap(temp, (anchoPantalla/2)-(anchoCarta), (altoPantalla/2)-(altoCarta/4), null);
        //Jugador2
        if(jugador2!=null){
            Log.i("ONDRAW","Antes deck");
            //Zona-Mano
            p=new Paint();
            p.setColor(Color.BLUE);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawRect(jugador2.getManoX()
                    ,jugador2.getManoY()
                    ,jugador2.getManoXfin()
                    ,jugador2.getManoYfin(),p);
            Log.i("ONDRAW2", "dibujando MANOx:"+jugador2.getManoX());
            Log.i("ONDRAW2", "dibujando MANOx2:"+jugador2.getManoY());
            Log.i("ONDRAW2", "dibujando MANOy:"+jugador2.getManoXfin());
            Log.i("ONDRAW2", "dibujando MANOy2:"+jugador2.getManoYfin());
            Log.i("ONDRAW2", "dibujando MANO");

            //Zona-Mesa
            p=new Paint();
            p.setColor(Color.BLUE);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawRect(jugador2.getMesaX()
                    ,jugador2.getMesaY()
                    ,jugador2.getMesaXfin()
                    ,jugador2.getMesaYfin()
                    ,p);
            Log.i("ONDRAW2", "dibujando MESAx:"+jugador2.getMesaX());
            Log.i("ONDRAW2", "dibujando MESAx2:"+jugador2.getMesaY());
            Log.i("ONDRAW2", "dibujando MESAy:"+jugador2.getMesaXfin());
            Log.i("ONDRAW2", "dibujando MESAy2:"+jugador2.getMesaYfin());
            Log.i("ONDRAW2", "dibujando MESA");

            //Zona-Descarte
            p=new Paint();
            p.setColor(Color.BLUE);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawRect(jugador2.getDescarteX()
                    ,jugador2.getDescarteY()
                    ,jugador2.getDescarteXfin()
                    ,jugador2.getDescarteYfin()
                    ,p);
            Log.i("ONDRAW2", "dibujando DESCARTEx:"+jugador2.getDescarteX());
            Log.i("ONDRAW2", "dibujando DESCARTEx2:"+jugador2.getDescarteY());
            Log.i("ONDRAW2", "dibujando DESCARTEy:"+jugador2.getDescarteXfin());
            Log.i("ONDRAW2", "dibujando DESCARTEy2:"+jugador2.getDescarteYfin());
            Log.i("ONDRAW2", "dibujando DESCARTE");

            //Zona-DECK
            if(jugador2.getDeck().size()>0){

                Log.i("ONDRAW2", "dibujando DECK:"+jugador2.getDeck().get(0).getId());
                temp = Bitmap.createScaledBitmap(jugador2.getDeck().get(0).getImagenBack(), anchoCarta, altoCarta, true);
                canvas.drawBitmap(temp, jugador2.getDeckX(), jugador2.getDeckY(), null);
                for(int i=0;i<jugador2.getDeck().size();i++) {
                    jugador2.getDeck().get(i).setxInicio(xDeck);
                    jugador2.getDeck().get(i).setxFin(xDeck + anchoCarta);
                    jugador2.getDeck().get(i).setyInicio(yDeck);
                    jugador2.getDeck().get(i).setyFin(yDeck + altoCarta);
                    p=new Paint();
                    p.setColor(Color.BLUE);
                    p.setStyle(Paint.Style.STROKE);
                }
            }else{
            }
            canvas.drawRect(jugador2.getDeckX()
                    ,jugador2.getDeckY()
                    ,jugador2.getDeckXfin()
                    ,jugador2.getDeckYfin(),p);
            Log.i("ONDRAW2", "dibujando DECK");
            Log.i("ONDRAW2","Antes bucle");

            //MANO
            for (int i = 0; i < jugador2.getMano().size(); i++) {
                Log.i("ONDRAW2", "Bucle " + i);
                temp = Bitmap.createScaledBitmap(jugador2.getMano().get(i).getImagen(), anchoCarta, altoCarta, true);
                Log.i("ONDRAW2", "Antes draw " + i);
                jugador2.getMano().get(i).setxInicio(x);
                jugador2.getMano().get(i).setyInicio(y);
                jugador2.getMano().get(i).setyFin(y+altoCarta);
                if(i<jugador2.getMano().size()-1) {
                    jugador2.getMano().get(i).setxFin(x+(anchoCarta/2));
                }else{
                    jugador2.getMano().get(i).setxFin(x + anchoCarta);
                }
                canvas.drawBitmap(temp, jugador2.getMano().get(i).getxInicio(), jugador2.getMano().get(i).getyInicio(), null);
                p=new Paint();
                p.setColor(Color.GREEN);
                p.setStyle(Paint.Style.STROKE);
                canvas.drawRect(jugador2.getMano().get(i).getxInicio()
                        ,jugador2.getMano().get(i).getyInicio()
                        ,jugador2.getMano().get(i).getxFin()
                        ,jugador2.getMano().get(i).getyFin(),p);
            }

            //DESCARTE
            for(int i=0;i<jugador2.getDescarte().size();i++){
                temp=Bitmap.createScaledBitmap(jugador2.getDescarte().get(0).getImagen(),anchoCarta,altoCarta,true);
                jugador2.getDescarte().get(i).setxInicio(jugador2.getDescarteX());
                jugador2.getDescarte().get(i).setxFin(jugador2.getDescarteXfin());
                jugador2.getDescarte().get(i).setyInicio(jugador2.getDescarteY());
                jugador2.getDescarte().get(i).setyFin(jugador2.getDescarteYfin());
                canvas.drawBitmap(temp,jugador2.getDescarteX(),jugador2.getDescarteY(),null);
                p=new Paint();
                p.setColor(Color.GREEN);
                p.setStyle(Paint.Style.STROKE);
                canvas.drawRect(jugador2.getDescarte().get(0).getxInicio()
                        ,jugador2.getDescarte().get(0).getyInicio()
                        ,jugador2.getDescarte().get(0).getxFin()
                        ,jugador2.getDescarte().get(0).getyFin(),p);
            }

            //MESA
            int xTemp=jugador2.getMesaX();
            int yTemp=jugador2.getMesaYfin();
            for (int i = 0; i < jugador2.getMesa().size(); i++) {
                Log.i("ONDRAW2", "Bucle " + i);
                temp = Bitmap.createScaledBitmap(jugador2.getMesa().get(i).getImagen(), anchoCarta, altoCarta, true);
                Log.i("ONDRAW2", "Antes draw " + i);
                jugador2.getMesa().get(i).setxInicio(xTemp);
                jugador2.getMesa().get(i).setyInicio(yTemp-altoCarta);
                jugador2.getMesa().get(i).setyFin(yTemp);
                if(i<jugador2.getMesa().size()-1) {
                    jugador2.getMesa().get(i).setxFin(xTemp+(anchoCarta/2));
                }else{
                    jugador2.getMesa().get(i).setxFin(xTemp + anchoCarta);
                }
                canvas.drawBitmap(temp, jugador2.getMesa().get(i).getxInicio(), jugador2.getMesa().get(i).getyInicio(), null);
                p=new Paint();
                p.setColor(Color.GREEN);
                p.setStyle(Paint.Style.STROKE);
                canvas.drawRect(jugador2.getMesa().get(i).getxInicio()
                        ,jugador2.getMesa().get(i).getyInicio()
                        ,jugador2.getMesa().get(i).getxFin()
                        ,jugador2.getMesa().get(i).getyFin(),p);
            }
        }else{
            Log.i("ONDRAW2","Jugador null");
        }
        //Jugador1
        if(jugador1!=null) {
            Log.i("ONDRAW","Antes deck");
            //Zona-Mano
            p=new Paint();
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawRect(jugador1.getManoX()
                    ,jugador1.getManoY()
                    ,jugador1.getManoXfin()
                    ,jugador1.getManoYfin(),p);
            Log.i("ONDRAW", "dibujando MANOx:"+jugador1.getManoX());
            Log.i("ONDRAW", "dibujando MANOx2:"+jugador1.getManoY());
            Log.i("ONDRAW", "dibujando MANOy:"+jugador1.getManoXfin());
            Log.i("ONDRAW", "dibujando MANOy2:"+jugador1.getManoYfin());
            Log.i("ONDRAW", "dibujando MANO");

            //Zona-Mesa
            p=new Paint();
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawRect(jugador1.getMesaX()
                    ,jugador1.getMesaY()
                    ,jugador1.getMesaXfin()
                    ,jugador1.getMesaYfin()
                    ,p);
            Log.i("ONDRAW", "dibujando MESA");

            //Zona-Descarte
            p=new Paint();
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawRect(jugador1.getDescarteX()
                    ,jugador1.getDescarteY()
                    ,jugador1.getDescarteXfin()
                    ,jugador1.getDescarteYfin()
                    ,p);
            Log.i("ONDRAW", "dibujando DESCARTE");

            //Zona-DECK
            if(jugador1.getDeck().size()>0){

                Log.i("ONDRAW", "dibujando DECK:"+jugador1.getDeck().get(0).getId());
                temp = Bitmap.createScaledBitmap(jugador1.getDeck().get(0).getImagenBack(), anchoCarta, altoCarta, true);
                canvas.drawBitmap(temp, xDeck, yDeck, null);
                for(int i=0;i<jugador1.getDeck().size();i++) {
                    jugador1.getDeck().get(i).setxInicio(xDeck);
                    jugador1.getDeck().get(i).setxFin(xDeck + anchoCarta);
                    jugador1.getDeck().get(i).setyInicio(yDeck);
                    jugador1.getDeck().get(i).setyFin(yDeck + altoCarta);
                }
                x+=anchoCarta*1.2;
            }else{
                x+=anchoCarta*1.2;
            }
            p=new Paint();
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawRect(jugador1.getDeckX()
                    ,jugador1.getDeckY()
                    ,jugador1.getDeckXfin()
                    ,jugador1.getDeckYfin(),p);
            Log.i("ONDRAW", "dibujando DECK");
            Log.i("ONDRAW","Antes bucle");

            //MANO
            for (int i = 0; i < jugador1.getMano().size(); i++) {
                Log.i("ONDRAW", "Bucle " + i);
                temp = Bitmap.createScaledBitmap(jugador1.getMano().get(i).getImagen(), anchoCarta, altoCarta, true);
                Log.i("ONDRAW", "Antes draw " + i);
                canvas.drawBitmap(temp, x, y, null);
                jugador1.getMano().get(i).setxInicio(x);
                jugador1.getMano().get(i).setyInicio(y);
                jugador1.getMano().get(i).setyFin(y+altoCarta);
                if(i<jugador1.getMano().size()-1) {
                    jugador1.getMano().get(i).setxFin(x+(anchoCarta/2));
                }else{
                    jugador1.getMano().get(i).setxFin(x + anchoCarta);
                }
                p=new Paint();
                p.setColor(Color.RED);
                p.setStyle(Paint.Style.STROKE);
                canvas.drawRect(jugador1.getMano().get(i).getxInicio()
                        ,jugador1.getMano().get(i).getyInicio()
                        ,jugador1.getMano().get(i).getxFin()
                        ,jugador1.getMano().get(i).getyFin(),p);
                x += anchoCarta / 2;
            }

            //DESCARTE
            for(int i=0;i<jugador1.getDescarte().size();i++){
                temp=Bitmap.createScaledBitmap(jugador1.getDescarte().get(0).getImagen(),anchoCarta,altoCarta,true);
                jugador1.getDescarte().get(i).setxInicio(jugador1.getDescarteX());
                jugador1.getDescarte().get(i).setxFin(jugador1.getDescarteXfin());
                jugador1.getDescarte().get(i).setyInicio(jugador1.getDescarteY());
                jugador1.getDescarte().get(i).setyFin(jugador1.getDescarteYfin());
                canvas.drawBitmap(temp,jugador1.getDescarteX(),y,null);
                p=new Paint();
                p.setColor(Color.RED);
                p.setStyle(Paint.Style.STROKE);
                canvas.drawRect(jugador1.getDescarte().get(0).getxInicio()
                        ,jugador1.getDescarte().get(0).getyInicio()
                        ,jugador1.getDescarte().get(0).getxFin()
                        ,jugador1.getDescarte().get(0).getyFin(),p);
            }

            //MESA
            int xTemp=jugador1.getMesaX();
            int yTemp=jugador1.getMesaYfin();
            for (int i = 0; i < jugador1.getMesa().size(); i++) {
                Log.i("ONDRAW", "Bucle " + i);
                temp = Bitmap.createScaledBitmap(jugador1.getMesa().get(i).getImagen(), anchoCarta, altoCarta, true);
                Log.i("ONDRAW", "Antes draw " + i);
                jugador1.getMesa().get(i).setxInicio(xTemp);
                jugador1.getMesa().get(i).setyInicio(yTemp-altoCarta);
                jugador1.getMesa().get(i).setyFin(yTemp);
                if(i<jugador1.getMesa().size()-1) {
                    jugador1.getMesa().get(i).setxFin(xTemp+(anchoCarta/2));
                }else{
                    jugador1.getMesa().get(i).setxFin(xTemp + anchoCarta);
                }
                canvas.drawBitmap(temp, jugador1.getMesa().get(i).getxInicio(), jugador1.getMesa().get(i).getyInicio(), null);
                p=new Paint();
                p.setColor(Color.RED);
                p.setStyle(Paint.Style.STROKE);
                canvas.drawRect(jugador1.getMesa().get(i).getxInicio()
                        ,jugador1.getMesa().get(i).getyInicio()
                        ,jugador1.getMesa().get(i).getxFin()
                        ,jugador1.getMesa().get(i).getyFin(),p);
                xTemp += anchoCarta / 2;
            }
        }else{
            Log.i("ONDRAW","Jugador null");
        }
        //canvas.drawBitmap(cartaBack,avatar.getWidth(),alturaDeck,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int accion=event.getActionMasked();
        switch(accion){
            case MotionEvent.ACTION_DOWN:
                Log.i("MULTITOUCH","action down");
                if(jugador1.isActivo()) {
                    //Tocar DECK
                    if (event.getX() >= jugador1.getDeckX() && event.getX() < jugador1.getDeckXfin()
                            && event.getY() >= jugador1.getDeckY() && event.getY() < jugador1.getDeckYfin()) {
                        if (jugador1.getDeck().size() > 0 && jugador1.getDeck() != null) {
                            if (event.getX() >= xDeck
                                    && event.getX() <= xDeck + anchoCarta
                                    && event.getY() >= yDeck
                                    && event.getY() <= yDeck + anchoCarta) {
                                jugador1.setTocandoDeck(true);
                                Log.i("MULTITOUCH-Deck", "TOCANDO-TRUE");
                            }
                        }
                    } else if (event.getX() >= jugador1.getManoX() && event.getX() < jugador1.getManoXfin()
                            && event.getY() >= jugador1.getManoY() && event.getY() < jugador1.getManoYfin()) {
                        //TOCAR MANO
                        if (jugador1.getMano().size() > 0 && jugador1.getMano() != null) {
                            Log.i("MULTITOUCH-MESA", "IF-MANO");
                            for (int i = 0; i < jugador1.getMano().size(); i++) {
                                if (event.getX() >= jugador1.getMano().get(i).getxInicio()
                                        && event.getX() <= jugador1.getMano().get(i).getxFin()
                                        && event.getY() >= jugador1.getMano().get(i).getyInicio()
                                        && event.getY() <= jugador1.getMano().get(i).getyFin()) {
                                    jugador1.setTocandoMano(true);
                                    infoCard=jugador1.getMano().get(i).getImagen();
                                    idTemp=jugador1.getMano().get(i).getId();
                                    ImageView imagenView=new ImageView(getContext());
                                    imagenView.setImageBitmap(jugador1.getMano().get(i).getImagen());
                                    imagenView.setX(jugador1.getMano().get(i).getxInicio());
                                    imagenView.setY(jugador1.getMano().get(i).getyInicio());
                                    Log.i("MULTITOUCH-MANO", "ANTES ANIMACION");
                                    Animation zoom= AnimationUtils.loadAnimation(this.getContext(), R.anim.animacionzoom);
                                    imagenView.startAnimation(zoom);
                                    Log.i("MULTITOUCH-MANO", "DESPUES ANIMACION");
                                    //imagenView.setZ(0);
                                    //jugador1.getMano().get(i).getImagen().
                                    Log.i("MULTITOUCH-MANO", "TOCANDO-TRUE");
                                }
                            }
                        }
                    } else if (event.getX() >= jugador1.getMesaX() && event.getX() < jugador1.getMesaXfin()
                            && event.getY() >= jugador1.getMesaY() && event.getY() < jugador1.getMesaYfin()) {
                        //Tocar MESA
                        if (jugador1.getMesa().size() > 0 && jugador1.getMesa() != null) {
                            Log.i("MULTITOUCH-MESA", "IF-MESA");
                            for (int i = 0; i < jugador1.getMesa().size(); i++) {
                                if (event.getX() >= jugador1.getMesa().get(i).getxInicio()
                                        && event.getX() <= jugador1.getMesa().get(i).getxFin()
                                        && event.getY() >= jugador1.getMesa().get(i).getyInicio()
                                        && event.getY() <= jugador1.getMesa().get(i).getyFin()) {
                                    jugador1.setTocandoMesa(true);
                                    infoCard=jugador1.getMesa().get(i).getImagen();
                                    idTemp=jugador1.getMesa().get(i).getId();
                                    Log.i("MULTITOUCH-MESA", "TOCANDO-TRUE");
                                }
                            }
                        }
                    } else if (event.getX() >= jugador1.getDescarteX() && event.getX() < jugador1.getDescarteXfin()
                            && event.getY() >= jugador1.getDescarteY() && event.getY() < jugador1.getDescarteYfin()) {
                        //Tocar DESCARTE
                        if (jugador1.getDescarte().size() > 0 && jugador1.getDescarte() != null) {
                            Log.i("MULTITOUCH-DESCARTE", "IF-DESCARTE");
                            if (event.getX() >= jugador1.getDescarte().get(0)
                                    .getxInicio()
                                    && event.getX() <= jugador1.getDescarte().get(0)
                                    .getxFin()
                                    && event.getY() >= jugador1.getDescarte().get(0)
                                    .getyInicio()
                                    && event.getY() <= jugador1.getDescarte().get(0)
                                    .getyFin()) {
                                jugador1.setTocandoDescarte(true);
                                Log.i("MULTITOUCH-DESCARTE", "TOCANDO-TRUE");
                            }
                        }
                    } else if (event.getX() >= (anchoPantalla / 2) - (anchoCarta) && event.getX() < (anchoPantalla / 2) + (anchoCarta)
                            && event.getY() >= (altoPantalla / 2) - (altoCarta / 4) && event.getY() < (altoPantalla / 2) + (altoCarta / 4)) {
                        //Tocar FinTurno
                       jugador1.setTocandoFinTurno(true);
                        Log.i("MULTITOUCH-FINTURNO", "TOCANDO-TRUE");
                        this.invalidate();
                    }else if(event.getX() >= 0 && event.getX() < anchoCarta*1.5
                            && event.getY() >= (altoPantalla/2)-(altoCarta*1.2) && event.getY() < (altoPantalla/2)-(altoCarta*0.75)){
                        pulsandoPlay=true;
                        Log.i("MULTITOUCH-BTN PLAY", "PULSADO");
                        this.invalidate();
                    }
                    this.invalidate();
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.i("MULTITOUCH","action pointer down");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("MULTITOUCH","action up");
            case MotionEvent.ACTION_POINTER_UP:
                Log.i("MULTITOUCH","action pointer up");
                if(jugador1.isActivo()) {
                    //LEVANTAR DECK
                    if (jugador1.getDeck().size() > 0 && jugador1.getDeck() != null) {
                        Log.i("MULTITOUCH-DECK", "1ºIF");
                        if (event.getX() >= xDeck
                                && event.getX() <= xDeck + anchoCarta
                                && event.getY() >= yDeck
                                && event.getY() <= yDeck + anchoCarta
                                && jugador1.isTocandoDeck()) {
                            Log.i("MULTITOUCH", "draw card 1");
                            jugador1.setTocandoDeck(false);
                            jugador1.moveFromDeckToHand(1);
                            this.invalidate();
                        } else {
                            jugador1.setTocandoDeck(false);
                        }
                    } else {
                        jugador1.setTocandoDeck(false);
                    }
                    Log.i("MULTITOUCH-IF-MANO", "ANTES");
                    if (event.getX() >= jugador1.getManoX() && event.getX() < jugador1.getManoXfin()
                            && event.getY() >= jugador1.getManoY() && event.getY() < jugador1.getManoYfin() && jugador1.isTocandoMano()) {
                        //TOCAR MANO
                        if (jugador1.getMano().size() > 0 && jugador1.getMano() != null) {
                            Log.i("MULTITOUCH-MESA", "IF-MANO");
                            for (int i = 0; i < jugador1.getMano().size(); i++) {
                                if (event.getX() >= jugador1.getMano().get(i).getxInicio()
                                        && event.getX() <= jugador1.getMano().get(i).getxFin()
                                        && event.getY() >= jugador1.getMano().get(i).getyInicio()
                                        && event.getY() <= jugador1.getMano().get(i).getyFin()) {
                                    if (jugador1.getRecursos() >= jugador1.getMano().get(i).getCoste()) {
                                        jugador1.setRecursos(jugador1.getRecursos() - jugador1.getMano().get(i).getCoste());
                                        jugador1.moveCardFromHandToTable(jugador1.getMano().get(i).getId());
                                    } else {
                                        Log.i("JUGAR", "NOT ENOUGHT RESOURCES");
                                    }
                                    this.invalidate();
                                }
                            }
                        }
                        jugador1.setTocandoMano(false);
                    } else if (event.getX() >= jugador1.getMesaX() && event.getX() < jugador1.getMesaXfin()
                            && event.getY() >= jugador1.getMesaY() && event.getY() < jugador1.getMesaYfin() && jugador1.isTocandoMesa()) {
                        Log.i("MULTITOUCH-IF-MESA", "DENTRO");
                        //Tocar MESA
                        if (jugador1.getMesa().size() > 0 && jugador1.getMesa() != null) {
                            Log.i("MULTITOUCH-MESA", "IF-MESA");
                            for (int i = 0; i < jugador1.getMesa().size(); i++) {
                                if (event.getX() >= jugador1.getMesa().get(i).getxInicio()
                                        && event.getX() <= jugador1.getMesa().get(i).getxFin()
                                        && event.getY() >= jugador1.getMesa().get(i).getyInicio()
                                        && event.getY() <= jugador1.getMesa().get(i).getyFin()) {
                                   // ScaleAnimation animation = new ScaleAnimation(fromXscale, toXscale, fromYscale, toYscale, Animation.RELATIVE_TO_SELF, (float)0.5, Animation.RELATIVE_TO_SELF, (float)0.5);

                                    Log.i("MULTITOUCH-MESA", "ANIMACION" +
                                            "");
                                   /* ScaleAnimation animation = new ScaleAnimation(jugador1.getMesa().get(i).getxInicio(), jugador1.getMesa().get(i).getxInicio()-anchoCarta/2,
                                            jugador1.getMesa().get(i).getyInicio(), jugador1.getMesa().get(i).getyInicio()-altoCarta/2, Animation.RELATIVE_TO_SELF, (float)0.5, Animation.RELATIVE_TO_SELF, (float)0.5);

                                  */
                                    //jugador1.moveCardFromTableToDiscard(jugador1.getMesa().get(i).getId());
                                    ImageView imagenView=new ImageView(getContext());

                                    Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.cardbackprueba);
                                    Drawable prueba=new BitmapDrawable(getResources(),bitmap);
                                    imagenView.setImageDrawable(prueba);

                                    imagenView.setImageBitmap(jugador1.getMesa().get(i).getImagen());
                                    imagenView.setX(jugador1.getMesa().get(i).getxInicio());
                                    imagenView.setY(jugador1.getMesa().get(i).getyInicio());
                                    imagenView.setVisibility(VISIBLE);

                                    Log.i("MULTITOUCH-MESA", "ANTES ANIMACION "+jugador1.getMesa().get(i));
                                    Animation zoom= AnimationUtils.loadAnimation(this.getContext(), R.anim.animacionzoom);
                                    imagenView.startAnimation(zoom);

                                    Log.i("MULTITOUCH-MESA", "DESPUES ANIMACION");
                                    //this.invalidate();
                                }
                            }
                        }
                        jugador1.setTocandoMesa(false);
                    } else if (event.getX() >= jugador1.getDescarteX() && event.getX() < jugador1.getDescarteXfin()
                            && event.getY() >= jugador1.getDescarteY() && event.getY() < jugador1.getDescarteYfin() && jugador1.isTocandoDescarte()) {
                        Log.i("MULTITOUCH-IF-DESCARTE", "DENTRO");
                        //Tocar DESCARTE
                        if (jugador1.getDescarte().size() > 0 && jugador1.getDescarte() != null) {
                            Log.i("MULTITOUCH-DESCARTE", "IF-DESCARTE");
                            if (event.getX() >= jugador1.getDescarte().get(0)
                                    .getxInicio()
                                    && event.getX() <= jugador1.getDescarte().get(0)
                                    .getxFin()
                                    && event.getY() >= jugador1.getDescarte().get(0)
                                    .getyInicio()
                                    && event.getY() <= jugador1.getDescarte().get(0)
                                    .getyFin()) {
                                jugador1.moveCardFromDiscardToDeck(jugador1
                                        .getDescarte().get(0).getId());
                                this.invalidate();
                            }
                        }
                        jugador1.setTocandoDescarte(false);
                    } else if (event.getX() >= (anchoPantalla / 2) - (anchoCarta) && event.getX() < (anchoPantalla / 2) + (anchoCarta)
                            && event.getY() >= (altoPantalla / 2) - (altoCarta / 4) && event.getY() < (altoPantalla / 2) + (altoCarta / 4) && jugador1.isTocandoFinTurno()) {
                        Log.i("MULTITOUCH-FIN TURNO", "FIN TURNO");
                        jugador1.endOfTurn();
                        jugador1.setActivo(false);
                        jugador1.setTocandoFinTurno(false);
                        Log.i("MULTITOUCH-FIN TURNO", "PRINCIPIO TURNO");
                        if(!jugador1.isActivo()) {
                            jugador1.startOfTurn();
                        }
                        this.invalidate();
                    }else if(event.getX() >= 0 && event.getX() < anchoCarta*1.5
                            && event.getY() >= (altoPantalla/2)-(altoCarta*1.2) && event.getY() < (altoPantalla/2)-(altoCarta*0.75)){
                        for(int i=0;i<jugador1.getMesa().size();i++){
                            if(idTemp==jugador1.getMesa().get(i).getId()){
                                jugador1.moveCardFromTableToDiscard(jugador1.getMesa().get(i).getId());
                                this.invalidate();
                            }
                        }
                        for(int i=0;i<jugador1.getMano().size();i++){
                            if(idTemp==jugador1.getMano().get(i).getId()){
                                if (jugador1.getRecursos() >= jugador1.getMano().get(i).getCoste()) {
                                    jugador1.setRecursos(jugador1.getRecursos() - jugador1.getMano().get(i).getCoste());
                                    jugador1.moveCardFromHandToTable(jugador1.getMano().get(i).getId());
                                } else {
                                    Log.i("JUGAR", "NOT ENOUGHT RESOURCES");
                                }
                                this.invalidate();
                            }
                        }
                        pulsandoPlay=false;
                        Log.i("MULTITOUCH-BTN PLAY", "FIN");
                    }
                    Log.i("MULTITOUCH-IFS-FINIFS", "FIN");
                }
                this.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("MULTITOUCH","action move");
                break;
            default:
                Log.i("MULTITOUCH","Accion no definida");
        }
        return true;
    }
}

