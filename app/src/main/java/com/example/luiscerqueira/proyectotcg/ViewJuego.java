package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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

import java.util.Timer;

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
    private boolean animacionJ1=false;
    private boolean animacionJ2=false;
    private int idTemp=-1;
    private int idAnimacion=-1;
    private int grados=0;

    int terminar=0;

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

        Log.i("CONSTRUCTOR","ACTIVO J1?"+jugador1.isActivo());
        Log.i("CONSTRUCTOR","ACTIVO J2?"+jugador2.isActivo());
        //jugador1
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
        canvas.drawText("Activo:"+(jugador1.isActivo()?"Si":"No") , jugador1.getDeckX(), jugador1.getDeckY() - (int) (0.7 * altoCarta), p);
        canvas.drawText("Vidas:" + jugador1.getVidas(), jugador1.getDeckX(), jugador1.getDeckY() - (int) (0.5 * altoCarta), p);
        canvas.drawText("Recursos:"+jugador1.getRecursos(), jugador1.getDeckX(), jugador1.getDeckY()-(int)(0.2*altoCarta), p);
        //jugador2
        p.setColor(Color.BLUE);
        canvas.drawText("Activo:"+(jugador2.isActivo()?"Si":"No") , jugador2.getDeckX(), jugador2.getDeckYfin() + (int) (0.7 * altoCarta), p);
        canvas.drawText("Vidas:" + jugador2.getVidas(), jugador2.getDeckX(), jugador2.getDeckYfin() + (int) (0.5 * altoCarta), p);
        canvas.drawText("Recursos:"+jugador2.getRecursos(), jugador2.getDeckX(), jugador2.getDeckYfin()+(int)(0.2*altoCarta), p);

        //Zona-Informacion
        p=new Paint();
        if(infoCard==null) {
            infoCard = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cardbackprueba);
        }
        infoCard = Bitmap.createScaledBitmap(infoCard, (int)(1.5*anchoCarta), (int)(altoCarta*1.5), true);
        if(jugador2.isActivo() && !animacionJ2){
            infoCard=girarBitmap(infoCard,180);
        }
        canvas.drawBitmap(infoCard, 0, (int)((altoPantalla/2)-(altoCarta*0.75)), null);
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
        if(jugador2.isActivo()){
            temp=girarBitmap(temp,180);
        }
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
        if(jugador2.isActivo()){
            temp=girarBitmap(temp,180);
        }
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
                Log.i("ONDRAW2", "DECK VACIO");
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
                if(jugador2.isActivo()) {
                    temp = Bitmap.createScaledBitmap(jugador2.getMano().get(i).getImagen(), anchoCarta, altoCarta, true);
                }else{
                    temp=Bitmap.createScaledBitmap(jugador2.getMano().get(i).getImagenBack(), anchoCarta, altoCarta, true);
                }
                Log.i("ONDRAW2", "Antes draw " + i);
                jugador2.getMano().get(i).setxInicio(jugador2.getManoX()+(int)(0.5*anchoCarta)*i);
                jugador2.getMano().get(i).setyInicio(jugador2.getManoY());
                jugador2.getMano().get(i).setyFin(jugador2.getManoYfin());
                if(i<jugador2.getMano().size()-1) {
                    jugador2.getMano().get(i).setxFin(jugador2.getMano().get(i).getxInicio()+(anchoCarta/2));
                }else{
                    jugador2.getMano().get(i).setxFin(jugador2.getMano().get(i).getxInicio() + anchoCarta);
                }
                if(jugador2.isActivo()){
                    temp=girarBitmap(temp,180);
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
                if(jugador2.isActivo()){
                    temp=girarBitmap(temp,180);
                }
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
                jugador2.getMesa().get(i).setxInicio(xTemp+(int)(0.5*anchoCarta)*i);
                jugador2.getMesa().get(i).setyInicio(jugador2.getMesaY());
                jugador2.getMesa().get(i).setyFin(jugador2.getMesaYfin());
                if(i<jugador2.getMesa().size()-1) {
                    Log.i("ONDRAW2", "IF"+i+"size"+(jugador2.getMesa().size()-1));
                    jugador2.getMesa().get(i).setxFin(jugador2.getMesa().get(i).getxInicio() + (anchoCarta / 2));
                }else{
                    Log.i("ONDRAW2", "ELSE"+anchoCarta);
                    jugador2.getMesa().get(i).setxFin(jugador2.getMesa().get(i).getxInicio() + anchoCarta);
                    Log.i("ONDRAW2", "Antes draw x2:" + jugador2.getMesa().get(i).getxFin());
                }
                if(jugador2.isActivo()){
                    temp=girarBitmap(temp,180);
                }
                canvas.drawBitmap(temp, jugador2.getMesa().get(i).getxInicio(), jugador2.getMesa().get(i).getyInicio(), null);
                p=new Paint();
                p.setColor(Color.GREEN);
                p.setStyle(Paint.Style.STROKE);
                Log.i("ONDRAW2", "Antes draw x:" + jugador2.getMesa().get(i).getxInicio());
                Log.i("ONDRAW2", "Antes draw x2:" + jugador2.getMesa().get(i).getxFin());
                Log.i("ONDRAW2", "Antes draw y:" + jugador2.getMesa().get(i).getyInicio());
                Log.i("ONDRAW2", "Antes draw y2:" + jugador2.getMesa().get(i).getyFin());
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
                if(jugador2.isActivo()){
                    temp=girarBitmap(temp,180);
                }
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
                if(jugador1.isActivo()) {
                    temp = Bitmap.createScaledBitmap(jugador1.getMano().get(i).getImagen(), anchoCarta, altoCarta, true);
                }else{
                    temp=Bitmap.createScaledBitmap(jugador1.getMano().get(i).getImagenBack(), anchoCarta, altoCarta, true);
                }
                Log.i("ONDRAW", "Antes draw " + i);
                if(jugador2.isActivo()){
                    temp=girarBitmap(temp,180);
                }
                canvas.drawBitmap(temp, jugador1.getManoX()+(int)(0.5*anchoCarta)*i, jugador1.getManoY(), null);
                jugador1.getMano().get(i).setxInicio(jugador1.getManoX()+(int)(0.5*anchoCarta)*i);
                jugador1.getMano().get(i).setyInicio(jugador1.getManoY());
                jugador1.getMano().get(i).setyFin(jugador1.getManoYfin());
                if(i<jugador1.getMano().size()-1) {
                    jugador1.getMano().get(i).setxFin(jugador1.getMano().get(i).getxInicio()+(anchoCarta/2));
                }else{
                    jugador1.getMano().get(i).setxFin(jugador1.getMano().get(i).getxInicio() + anchoCarta);
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
                if(jugador2.isActivo()){
                    temp=girarBitmap(temp,180);
                }
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
                if(jugador2.isActivo()){
                    temp=girarBitmap(temp,180);
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

        //ANIMACIONES
        Log.i("ONDRAW3", "ANIMACION");
        if(animacionJ2) {
            Log.i("ONDRAW3", "ANIMACIONJ2");
            Thread hilo=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    terminar=1;
                    animacionJ2=false;
                    grados=0;
                }
            });
            hilo.start();
            if(terminar==1){
                hilo.interrupt();
            }
            Bitmap animacion=BitmapFactory.decodeResource(getResources(),R.drawable.circle4a);
            animacion=Bitmap.createScaledBitmap(animacion,anchoCarta, anchoCarta, true);
            Log.i("ONDRAW3", "ANTES IF");
            if(terminar==0){
                Log.i("ONDRAW3", "DENTRO IF"+grados);
                grados+=15;
                Point punto=encontrarCartaMesa(idAnimacion);
                animacion=girarBitmap(animacion,grados);
                canvas.drawBitmap(animacion,punto.x,punto.y,null);
                this.invalidate();
            }
        }
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
                            if (event.getX() >= jugador1.getDeckX()
                                    && event.getX() <= jugador1.getDeckXfin()
                                    && event.getY() >= jugador1.getDeckY()
                                    && event.getY() <= jugador1.getDeckYfin()) {
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
                else if(jugador2.isActivo()) {
                    //Tocar DECK
                    if (event.getX() >= jugador2.getDeckX() && event.getX() < jugador2.getDeckXfin()
                            && event.getY() >= jugador2.getDeckY() && event.getY() < jugador2.getDeckYfin()) {
                        if (jugador2.getDeck().size() > 0 && jugador2.getDeck() != null) {
                            if (event.getX() >= jugador2.getDeckX()
                                    && event.getX() <= jugador2.getDeckXfin()
                                    && event.getY() >= jugador2.getDeckY()
                                    && event.getY() <= jugador2.getDeckYfin()) {
                                jugador2.setTocandoDeck(true);
                                Log.i("MULTITOUCH-Deck2", "TOCANDO-TRUE");
                            }
                        }
                    } else if (event.getX() >= jugador2.getManoX() && event.getX() < jugador2.getManoXfin()
                            && event.getY() >= jugador2.getManoY() && event.getY() < jugador2.getManoYfin()) {
                        //TOCAR MANO
                        if (jugador2.getMano().size() > 0 && jugador2.getMano() != null) {
                            Log.i("MULTITOUCH-MESA2", "IF-MANO");
                            for (int i = 0; i < jugador2.getMano().size(); i++) {
                                if (event.getX() >= jugador2.getMano().get(i).getxInicio()
                                        && event.getX() <= jugador2.getMano().get(i).getxFin()
                                        && event.getY() >= jugador2.getMano().get(i).getyInicio()
                                        && event.getY() <= jugador2.getMano().get(i).getyFin()) {
                                    jugador2.setTocandoMano(true);
                                    infoCard=jugador2.getMano().get(i).getImagen();
                                    idTemp=jugador2.getMano().get(i).getId();
                                    Log.i("MULTITOUCH-MANO2", "TOCANDO-TRUE");
                                }
                            }
                        }
                    } else if (event.getX() >= jugador2.getMesaX() && event.getX() < jugador2.getMesaXfin()
                            && event.getY() >= jugador2.getMesaY() && event.getY() < jugador2.getMesaYfin()) {
                        //Tocar MESA
                        if (jugador2.getMesa().size() > 0 && jugador2.getMesa() != null) {
                            Log.i("MULTITOUCH-MESA2", "IF-MESA");
                            for (int i = 0; i < jugador2.getMesa().size(); i++) {
                                if (event.getX() >= jugador2.getMesa().get(i).getxInicio()
                                        && event.getX() <= jugador2.getMesa().get(i).getxFin()
                                        && event.getY() >= jugador2.getMesa().get(i).getyInicio()
                                        && event.getY() <= jugador2.getMesa().get(i).getyFin()) {
                                    jugador2.setTocandoMesa(true);
                                    infoCard=jugador2.getMesa().get(i).getImagen();
                                    idTemp=jugador2.getMesa().get(i).getId();
                                    Log.i("MULTITOUCH-MESA2", "TOCANDO-TRUE");
                                }
                            }
                        }
                    } else if (event.getX() >= jugador2.getDescarteX() && event.getX() < jugador2.getDescarteXfin()
                            && event.getY() >= jugador2.getDescarteY() && event.getY() < jugador2.getDescarteYfin()) {
                        //Tocar DESCARTE
                        if (jugador2.getDescarte().size() > 0 && jugador2.getDescarte() != null) {
                            Log.i("MULTITOUCH-DESCARTE2", "IF-DESCARTE");
                            if (event.getX() >= jugador2.getDescarte().get(0)
                                    .getxInicio()
                                    && event.getX() <= jugador2.getDescarte().get(0)
                                    .getxFin()
                                    && event.getY() >= jugador2.getDescarte().get(0)
                                    .getyInicio()
                                    && event.getY() <= jugador2.getDescarte().get(0)
                                    .getyFin()) {
                                jugador2.setTocandoDescarte(true);
                                Log.i("MULTITOUCH-DESCARTE2", "TOCANDO-TRUE");
                            }
                        }
                    } else if (event.getX() >= (anchoPantalla / 2) - (anchoCarta) && event.getX() < (anchoPantalla / 2) + (anchoCarta)
                            && event.getY() >= (altoPantalla / 2) - (altoCarta / 4) && event.getY() < (altoPantalla / 2) + (altoCarta / 4)) {
                        //Tocar FinTurno
                        jugador2.setTocandoFinTurno(true);
                        Log.i("MULTITOUCH-FINTURNO2", "TOCANDO-TRUE");
                        this.invalidate();
                    }else if(event.getX() >= 0 && event.getX() < anchoCarta*1.5
                            && event.getY() >= (altoPantalla/2)-(altoCarta*1.2) && event.getY() < (altoPantalla/2)-(altoCarta*0.75)){
                        pulsandoPlay=true;
                        Log.i("MULTITOUCH-BTN PLAY2", "PULSADO");
                        this.invalidate();
                    }
                   // this.invalidate();
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
                        if (event.getX() >= jugador1.getDeckX()
                                && event.getX() <= jugador1.getDeckXfin()
                                && event.getY() >= jugador1.getDeckY()
                                && event.getY() <= jugador1.getDeckYfin()
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
                                    /*
                                    if (jugador1.getRecursos() >= jugador1.getMano().get(i).getCoste()) {
                                        jugador1.setRecursos(jugador1.getRecursos() - jugador1.getMano().get(i).getCoste());
                                        jugador1.moveCardFromHandToTable(jugador1.getMano().get(i).getId());
                                    } else {
                                        Log.i("JUGAR", "NOT ENOUGHT RESOURCES");
                                    }
                                    */
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
                        if(!jugador2.isActivo()) {
                            jugador2.setActivo(true);
                            jugador2.startOfTurn();
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
                else if(jugador2.isActivo()) {
                    //LEVANTAR DECK
                    if (jugador2.getDeck().size() > 0 && jugador2.getDeck() != null) {
                        Log.i("MULTITOUCH-DECK2", "1ºIF");
                        if (event.getX() >= jugador2.getDeckX()
                                && event.getX() <= jugador2.getDeckXfin()
                                && event.getY() >= jugador2.getDeckY()
                                && event.getY() <= jugador2.getDeckYfin()
                                && jugador2.isTocandoDeck()) {
                            Log.i("MULTITOUCH2", "draw card 1");
                            jugador2.setTocandoDeck(false);
                            jugador2.moveFromDeckToHand(1);
                            this.invalidate();
                        } else {
                            jugador2.setTocandoDeck(false);
                        }
                    } else {
                        jugador2.setTocandoDeck(false);
                    }
                    Log.i("MULTITOUCH-IF-MANO2", "ANTES");
                    if (event.getX() >= jugador2.getManoX() && event.getX() < jugador2.getManoXfin()
                            && event.getY() >= jugador2.getManoY() && event.getY() < jugador2.getManoYfin() && jugador2.isTocandoMano()) {
                        //TOCAR MANO
                        if (jugador2.getMano().size() > 0 && jugador2.getMano() != null) {
                            Log.i("MULTITOUCH-MANO2", "IF-MANO");
                            for (int i = 0; i < jugador2.getMano().size(); i++) {
                                if (event.getX() >= jugador2.getMano().get(i).getxInicio()
                                        && event.getX() <= jugador2.getMano().get(i).getxFin()
                                        && event.getY() >= jugador2.getMano().get(i).getyInicio()
                                        && event.getY() <= jugador2.getMano().get(i).getyFin()) {
                                    /*
                                    if (jugador2.getRecursos() >= jugador2.getMano().get(i).getCoste()) {
                                        jugador2.setRecursos(jugador2.getRecursos() - jugador2.getMano().get(i).getCoste());
                                        jugador2.moveCardFromHandToTable(jugador2.getMano().get(i).getId());
                                    } else {
                                        Log.i("JUGAR2", "NOT ENOUGHT RESOURCES");
                                    }
                                    */
                                    this.invalidate();
                                }
                            }
                        }
                        jugador2.setTocandoMano(false);
                    } else if (event.getX() >= jugador2.getMesaX() && event.getX() < jugador2.getMesaXfin()
                            && event.getY() >= jugador2.getMesaY() && event.getY() < jugador2.getMesaYfin() && jugador2.isTocandoMesa()) {
                        Log.i("MULTITOUCH-IF-MESA2", "DENTRO");
                        //Tocar MESA
                        if (jugador2.getMesa().size() > 0 && jugador2.getMesa() != null) {
                            Log.i("MULTITOUCH-MESA2", "IF-MESA");
                            for (int i = 0; i < jugador2.getMesa().size(); i++) {
                                if (event.getX() >= jugador2.getMesa().get(i).getxInicio()
                                        && event.getX() <= jugador2.getMesa().get(i).getxFin()
                                        && event.getY() >= jugador2.getMesa().get(i).getyInicio()
                                        && event.getY() <= jugador2.getMesa().get(i).getyFin()) {
                                    Log.i("MULTITOUCH-MESA2", "ANIMACION");
                                    animacionJ2=true;
                                    idAnimacion=jugador2.getMesa().get(i).getId();
                                    Log.i("MULTITOUCH-MESA2", "ANTES INVALIDATE");
                                    this.invalidate();
                                }
                            }
                        }
                        jugador2.setTocandoMesa(false);
                    } else if (event.getX() >= jugador2.getDescarteX() && event.getX() < jugador2.getDescarteXfin()
                            && event.getY() >= jugador2.getDescarteY() && event.getY() < jugador2.getDescarteYfin() && jugador2.isTocandoDescarte()) {
                        Log.i("MULTITOUCH-IF-DESCARTE2", "DENTRO");
                        //Tocar DESCARTE
                        if (jugador2.getDescarte().size() > 0 && jugador2.getDescarte() != null) {
                            Log.i("MULTITOUCH-DESCARTE2", "IF-DESCARTE");
                            if (event.getX() >= jugador2.getDescarte().get(0)
                                    .getxInicio()
                                    && event.getX() <= jugador2.getDescarte().get(0)
                                    .getxFin()
                                    && event.getY() >= jugador2.getDescarte().get(0)
                                    .getyInicio()
                                    && event.getY() <= jugador2.getDescarte().get(0)
                                    .getyFin()) {
                                jugador2.moveCardFromDiscardToDeck(jugador2
                                        .getDescarte().get(0).getId());
                                this.invalidate();
                            }
                        }
                        jugador2.setTocandoDescarte(false);
                    } else if (event.getX() >= (anchoPantalla / 2) - (anchoCarta) && event.getX() < (anchoPantalla / 2) + (anchoCarta)
                            && event.getY() >= (altoPantalla / 2) - (altoCarta / 4) && event.getY() < (altoPantalla / 2) + (altoCarta / 4) && jugador2.isTocandoFinTurno()) {
                        Log.i("MULTITOUCH-FIN TURNO2", "FIN TURNO");
                        jugador2.endOfTurn();
                        jugador2.setActivo(false);
                        jugador2.setTocandoFinTurno(false);
                        Log.i("MULTITOUCH-FIN TURNO2", "PRINCIPIO TURNO");
                        if(!jugador1.isActivo()) {
                            jugador1.setActivo(true);
                            jugador1.startOfTurn();
                        }
                        this.invalidate();
                    }else if(event.getX() >= 0 && event.getX() < anchoCarta*1.5
                            && event.getY() >= (altoPantalla/2)-(altoCarta*1.2) && event.getY() < (altoPantalla/2)-(altoCarta*0.75)){
                        for(int i=0;i<jugador2.getMesa().size();i++){
                            if(idTemp==jugador2.getMesa().get(i).getId()){
                                jugador2.moveCardFromTableToDiscard(jugador2.getMesa().get(i).getId());
                                this.invalidate();
                            }
                        }
                        for(int i=0;i<jugador2.getMano().size();i++){
                            if(idTemp==jugador2.getMano().get(i).getId()){
                                if (jugador2.getRecursos() >= jugador2.getMano().get(i).getCoste()) {
                                    jugador2.setRecursos(jugador1.getRecursos() - jugador2.getMano().get(i).getCoste());
                                    jugador2.moveCardFromHandToTable(jugador2.getMano().get(i).getId());
                                } else {
                                    Log.i("JUGAR2", "NOT ENOUGHT RESOURCES");
                                }
                                this.invalidate();
                            }
                        }
                        pulsandoPlay=false;
                        Log.i("MULTITOUCH-BTN PLAY2", "FIN");
                    }
                    Log.i("MULTITOUCH-IFS-FIN-IFS", "FIN");
                }
                //this.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
               // Log.i("MULTITOUCH","action move");
                break;
            default:
                Log.i("MULTITOUCH","Accion no definida");
        }
        return true;
    }

    public static Bitmap girarBitmap(Bitmap bitmap,float angulo){
        Matrix matrix = new Matrix();
        matrix.postRotate(angulo);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public Point encontrarCartaMesa(int id){
        Point p=new Point();
        for(int i=0;i<this.jugador1.getMesa().size();i++){
            if(this.jugador1.getMesa().get(i).getId()==id){
                p.set(jugador1.getMesa().get(i).getxInicio(),jugador1.getMesa().get(i).getyInicio());
            }
        }
        for(int i=0;i<this.jugador2.getMesa().size();i++){
            if(this.jugador2.getMesa().get(i).getId()==id){
                p.set(jugador2.getMesa().get(i).getxInicio(),jugador2.getMesa().get(i).getyInicio());
            }
        }
        return p;
    }
}

