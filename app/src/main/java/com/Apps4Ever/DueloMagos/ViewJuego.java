package com.Apps4Ever.DueloMagos;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase para la gestion del apartado grafico de la ventana de juego
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 22/01/2015.
 */
public class ViewJuego extends SurfaceView implements SurfaceHolder.Callback {

    private static final int MIN_DXDY = 2;
    final private static HashMap<Integer, PointF> posiciones=new HashMap<>();
    private SurfaceHolder surfaceHolder;
    private Context context;
    private Bitmap fondo;
    //private Bitmap cartaBack;
    private Bitmap avatar;
    private Bitmap temp;
    private Bitmap topDeck= BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cardbackprueba);
    private Bitmap botonPlay= BitmapFactory.decodeResource(getContext().getResources(), R.drawable.botonplay);
    private Bitmap botonPlayPulsado= BitmapFactory.decodeResource(getContext().getResources(), R.drawable.botonplaypulsado);
    private Bitmap botonActivo= BitmapFactory.decodeResource(getContext().getResources(), R.drawable.botonactivo);
    private Bitmap botonPresionado= BitmapFactory.decodeResource(getContext().getResources(), R.drawable.botonpresionado);
    private Bitmap infoCard= BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cardbackprueba);
    private Bitmap infoCardJ2= BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cardbackprueba);
    private Bitmap animacion1;
    private Bitmap animacion2;
    private ArrayList<Bitmap> arrayefectoGiro;
    private ArrayList<Bitmap> arrayMesaJ1=new ArrayList<Bitmap>();
    private int contMesaJ1=0;
    private ArrayList<Bitmap> arrayManoJ1=new ArrayList<Bitmap>();
    private int contManoJ1=0;
    private ArrayList<Bitmap> arrayMesaJ2=new ArrayList<Bitmap>();
    private int contMesaJ2=0;
    private ArrayList<Bitmap> arrayManoJ2=new ArrayList<Bitmap>();
    private int contManoJ2=0;
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
    private boolean funcionando=false;
    private boolean pulsandoPlay=false;
    private boolean animacionJ1=false;
    private boolean animacionJ2=false;
    private int idTemp=-1;
    private int idAnimacion=-1;
    private int grados=0;
    private Matrix matrix;
    private Hilo hilo;
    private long tiempodormido=0;
    private final int FPS=20;
    private final int TPS= 1000000000;
    private int FRAGMENTO_TEMPORAL = TPS / FPS;
    private long tiempoReferencia=System.nanoTime();
    Bitmap animacion;
    int terminar=0;

    public ViewJuego(Context context) {
        super(context);
        this.surfaceHolder=getHolder();
        this.surfaceHolder.addCallback(this);
        this.context=context;
        hilo=new Hilo();
        setFocusable(true);
        WindowManager wm=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display=wm.getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        anchoPantalla=size.x;
        altoPantalla=size.y;
        //Pruebas

        //cartaBack=BitmapFactory.decodeResource(getResources(),R.drawable.cardback);
        anchoCarta=anchoPantalla/6;
        altoCarta=(int)(anchoCarta*1.39);
        //cartaBack=Bitmap.createScaledBitmap(cartaBack,anchoCarta,altoCarta,true);
        avatar=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.saber),anchoPantalla/8,altoPantalla/8,true);
        //Imagenes
        topDeck= Bitmap.createScaledBitmap(topDeck, anchoCarta, altoCarta, true);
        botonActivo=Bitmap.createScaledBitmap(botonActivo, (int)(anchoCarta*2), (int)(altoCarta*0.45), true);
        botonPlay=Bitmap.createScaledBitmap(botonPlay, (int)(anchoCarta*2), (int)(altoCarta*0.45), true);
        botonPlayPulsado=Bitmap.createScaledBitmap(botonPlayPulsado, (int)(anchoCarta*2), (int)(altoCarta*0.45), true);

        arrayefectoGiro=new ArrayList<Bitmap>();
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo0), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo1), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo2), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo3), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo4), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo5), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo6), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo7), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo8), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo9), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo10), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo11), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo12), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo13), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo14), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo15), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo16), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo17), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo18), anchoCarta, altoCarta, true));

        //Jugadores
        jugador1=((JuegoActivity)context).jugador1;
        /*
        if(jugador1==null) {
            Log.i("CONSTRUCTOR", "JUGADOR1:NULL");
        }else{
            Log.i("CONSTRUCTOR", "JUGADOR1:NO NULL");
        }*/
        jugador2=((JuegoActivity)context).jugador2;
        /*
        if(jugador2==null) {
            Log.i("CONSTRUCTOR", "JUGADOR2:NULL");
        }else{
            Log.i("CONSTRUCTOR", "JUGADOR2:NO NULL");
        }*/

        //deck

        //deck
        alturaDeck=(altoPantalla)-(int)(altoCarta*1.1);
        anchuraDeck=anchoCarta;
        x=avatar.getWidth();
        y=alturaDeck;
        xDeck=x;
        yDeck=y;
        xDescarte=anchoPantalla-2*anchoCarta;
        yDescarte=y;
//        alturaDeck=altoPantalla-altoCarta;
//        anchuraDeck=anchoCarta;
//        x=avatar.getWidth();
//        y=alturaDeck;
//        xDeck=x;
//        yDeck=y;
//        xDescarte=anchoPantalla-2*anchoCarta;
//        yDescarte=y;
        //Log.i("CONSTRUCTOR", "FIN CONSTRUCTOR");
    }

    /**
     * Funcion para inicializar las variables mas importantes de la clase
     * @param context contexto desde el que se llama la funcion
     */
    public void inicializarCosas(Context context){

//        WindowManager wm=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
//        Display display=wm.getDefaultDisplay();
//        Point size=new Point();
//        display.getSize(size);
//        anchoPantalla=size.x;
//        altoPantalla=size.y;
//        //deck
//        alturaDeck=(altoPantalla)-(int)(altoCarta*1.1);
//        anchuraDeck=anchoCarta;
//        x=avatar.getWidth();
//        y=alturaDeck;
//        xDeck=x;
//        yDeck=y;
//        xDescarte=anchoPantalla-2*anchoCarta;
//        yDescarte=y;
//        anchoCarta=anchoPantalla/6;
//        altoCarta=(int)(anchoCarta*1.39);
        //cartaBack=Bitmap.createScaledBitmap(cartaBack,anchoCarta,altoCarta,true);
        avatar=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.saber),anchoPantalla/8,altoPantalla/8,true);
        //Imagenes
        topDeck= Bitmap.createScaledBitmap(topDeck, anchoCarta, altoCarta, true);
        botonActivo=Bitmap.createScaledBitmap(botonActivo, anchoCarta*2, (int)(altoCarta*0.45), true);
        botonPresionado=Bitmap.createScaledBitmap(botonPresionado, anchoCarta*2, (int)(altoCarta*0.45), true);
        botonPlay=Bitmap.createScaledBitmap(botonPlay, anchoCarta*2, (int)(altoCarta*0.45), true);
        botonPlayPulsado=Bitmap.createScaledBitmap(botonPlayPulsado, anchoCarta*2, (int)(altoCarta*0.45), true);

        arrayefectoGiro=new ArrayList<Bitmap>();
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo0), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo1), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo2), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo3), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo4), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo5), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo6), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo7), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo8), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo9), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo10), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo11), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo12), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo13), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo14), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo15), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo16), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo17), anchoCarta, altoCarta, true));
        arrayefectoGiro.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.circulo18), anchoCarta, altoCarta, true));

        //Jugadores
        jugador1=((JuegoActivity)context).jugador1;
//        if(jugador1==null) {
//            Log.i("CONSTRUCTOR", "JUGADOR1:NULL");
//        }else{
////            Log.i("CONSTRUCTOR", "JUGADOR1:NO NULL");
//        }
        jugador2=((JuegoActivity)context).jugador2;
//        if(jugador2==null) {
//            Log.i("CONSTRUCTOR", "JUGADOR2:NULL");
//        }else{
//            Log.i("CONSTRUCTOR", "JUGADOR2:NO NULL");
//        }
//        //deck
//        alturaDeck=(altoPantalla)-(int)(altoCarta*1.1);
//        anchuraDeck=anchoCarta;
//        x=avatar.getWidth();
//        y=alturaDeck;
//        xDeck=x;
//        yDeck=y;
//        xDescarte=anchoPantalla-2*anchoCarta;
//        yDescarte=y;
//        Log.i("CONSTRUCTOR", "FIN CONSTRUCTOR INICIALIZACION");

//        Log.i("CONSTRUCTOR", "ALTURA DECK: "+alturaDeck);
//        Log.i("CONSTRUCTOR", "ALTURA PANTALLA: "+altoPantalla);
//        Log.i("CONSTRUCTOR", "ALTURA CARTA: "+altoCarta);
//        Log.i("CONSTRUCTOR", "Y DECK: "+y);
        //jugador1
        jugador1.setDeckX(xDeck);
        jugador1.setDeckXfin(xDeck+anchoCarta);
        jugador1.setDeckY(yDeck);
        jugador1.setDeckYfin(yDeck+altoCarta);
        jugador1.setDescarteX(anchoPantalla-(int)(anchoCarta*1.2));
        jugador1.setDescarteXfin(jugador1.getDescarteX()+anchoCarta);
        jugador1.setDescarteY(yDeck);
        jugador1.setDescarteYfin(yDeck+altoCarta);
        jugador1.setManoX(jugador1.getDeckX()+(int)(1.2*anchoCarta));
        jugador1.setManoXfin(jugador1.getDescarteX()-(int)(0.5*anchoCarta));
        jugador1.setManoY(yDeck);
        jugador1.setManoYfin(yDeck+altoCarta);
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

    }

    /**
     * Clase interna para la gestion del hilo necesario para el SurfaceView
     *
     * @author Luis Cerqueira
     */
    class Hilo extends Thread{
        public Hilo(){
            //Fondo
            fondo= BitmapFactory.decodeResource(getResources(), R.drawable.tablero);
        }

        @Override
        public void run() {
            while(funcionando){
                Canvas c=null;

//                WindowManager wm=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
//                Display display=wm.getDefaultDisplay();
//                Point size=new Point();
//                display.getSize(size);
//                anchoPantalla=size.x;
//                altoPantalla=size.y;
                //deck
                alturaDeck=(altoPantalla)-(int)(altoCarta*1.1);
                anchuraDeck=anchoCarta;
                x=avatar.getWidth();
                y=alturaDeck;
                xDeck=x;
                yDeck=y;
                xDescarte=anchoPantalla-2*anchoCarta;
                yDescarte=y;
                try{
                    synchronized (surfaceHolder){
                        c=surfaceHolder.lockCanvas();
//                        Log.i("NULLPOINTER","NULL5?"+(surfaceHolder==null?"Si":"No"));
                        if(jugador1!=null && jugador2!=null && surfaceHolder!=null) {
                            cambios();
//                            Log.i("NULLPOINTER","NULL3?"+(c==null?"Si":"No"));
                            try {
                                while(c==null){
                                    surfaceHolder=getHolder();
                                    c=surfaceHolder.lockCanvas();
//                                    Log.i("NULLPOINTER","NULL4?"+(c==null?"Si":"No"));
//                                    Log.i("NULLPOINTER","NULL5?"+(surfaceHolder==null?"Si":"No"));
                                }
                                dibujar(c);
                            }catch (IndexOutOfBoundsException e){
                                e.printStackTrace();
                                cambios();
                            }
                        }else{
                            surfaceHolder=getHolder();
                            jugador1=((JuegoActivity)context).jugador1;
                            jugador2=((JuegoActivity)context).jugador2;
                            inicializarCosas((JuegoActivity)context);
                           // Log.i("RUN", "JUGADORES:"+(jugador1!=null?"1NO NULL":"1NULL")+(jugador2!=null?"2NO NULL":"2NULL"));
                        }
                    }
                }finally{
                    if(c!=null){
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                tiempoReferencia+=FRAGMENTO_TEMPORAL;
                tiempodormido=tiempoReferencia-System.nanoTime();
                if(tiempodormido>0){
                    try{
//                        Log.i("THREADSLEEP", "tiempo:"+tiempodormido/1000000);
                        Thread.sleep(tiempodormido/1000000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }

//            Log.i("SURFACE","HILO DESTROYED");
        }

       public void setFuncionando(boolean flag){
            funcionando=flag;
        }
        public void setSurfaceSize(int width, int height){
            synchronized (surfaceHolder){
                anchoPantalla=width;
                altoPantalla=height;
                if(fondo!=null){
                    fondo=Bitmap.createScaledBitmap(fondo,width,height,true);
                }
            }
        }

        /**
         * Funcion para comprobar los cambios en las distintas zonas de juego
         */
        protected void cambios(){
//            Log.i("CAMBIOS", "TAMAÑOJ2 MANO:"+jugador2.getMano().size());
//            Log.i("CAMBIOS", "TAMAÑOJ2 ARRAYMANO:"+arrayManoJ2.size());
//            Log.i("CAMBIOS", "TAMAÑOJ1 MANO:"+jugador1.getMano().size());
//            Log.i("CAMBIOS", "TAMAÑOJ1 ARRAYMANO:"+arrayManoJ1.size());
            if (contManoJ1 != jugador1.getMano().size()) {
//                Log.i("CAMBIOS", "CAMBIANDO MANO J1");
               // Log.i("CAMBIOS", "ARRAYMANO null?:"+(arrayManoJ1==null?"SI":"NO"));
                if(arrayManoJ1.size()!=0) {
//                   Log.i("CAMBIOS", "TAMAÑO!=0");
                    arrayManoJ1.clear();
                }
                for (int i = 0; i < jugador1.getMano().size(); i++) {
                    //infoCard = Bitmap.createScaledBitmap(infoCard, (int) (1.5 * anchoCarta), (int) (altoCarta * 1.5), true);
                    if(!jugador1.getMano().get(i).isCreada()) {
                        arrayManoJ1.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), jugador1.getMano().get(i).getImagen()), anchoCarta, altoCarta, true));
                    }else{
                        arrayManoJ1.add(Bitmap.createScaledBitmap(jugador1.getMano().get(i).getImagenCreada(), anchoCarta, altoCarta, true));
                    }
                }
                contManoJ1=jugador1.getMano().size();
            }
            if (contManoJ2 != jugador2.getMano().size()) {
//                Log.i("CAMBIOS", "CAMBIANDO MANO J2");
                if(arrayManoJ2.size()!=0) {
                    arrayManoJ2.clear();
                }
                for (int i = 0; i < jugador2.getMano().size(); i++) {
                    arrayManoJ2.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), jugador2.getMano().get(i).getImagen()), anchoCarta, altoCarta, true));
                }
                contManoJ2=jugador2.getMano().size();
            }
            if (contMesaJ1 != jugador1.getMesa().size()) {
                if(arrayMesaJ1.size()!=0) {
                    arrayMesaJ1.clear();
                }
                for (int i = 0; i < jugador1.getMesa().size(); i++) {
                    if(!jugador1.getMesa().get(i).isCreada()) {
                        arrayMesaJ1.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), jugador1.getMesa().get(i).getImagen()), anchoCarta, altoCarta, true));
                    }else{
                        arrayMesaJ1.add(Bitmap.createScaledBitmap(jugador1.getMesa().get(i).getImagenCreada(), anchoCarta, altoCarta, true));
                    }
                }
                contMesaJ1=jugador1.getMesa().size();
            }
            if (contMesaJ2 != jugador2.getMesa().size()) {
                if(arrayMesaJ2.size()!=0) {
                    arrayMesaJ2.clear();
                }
                for (int i = 0; i < jugador2.getMesa().size(); i++) {
                    arrayMesaJ2.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), jugador2.getMesa().get(i).getImagen()), anchoCarta, altoCarta, true));
                }
                contMesaJ2=jugador2.getMesa().size();
            }
        }

        /**
         * Funcion de dibujado que pintara las cosas necesarias en la pantalla de juego
         * @param canvas Parte de la pantalla sobra la que queremos dibujar
         */
        protected void dibujar(Canvas canvas) {

            //Jugadores
//            jugador1=((JuegoActivity)context).jugador1;
//            jugador2=((JuegoActivity)context).jugador2;
//            Log.i("ONDRAW","Despues actualizar");
//            x=avatar.getWidth();
//            y=alturaDeck;

//            Log.i("CONSTRUCTOR","ACTIVO J1?"+jugador1.isActivo());
//            Log.i("CONSTRUCTOR","ACTIVO J2?"+jugador2.isActivo());
//            Log.i("NULLPOINTER","NULL?"+(fondo==null?"Si":"No"));
//            Log.i("NULLPOINTER","NULL2?"+(canvas==null?"Si":"No"));
            canvas.drawBitmap(fondo,0,0,null);
//            canvas.drawBitmap(avatar,0,altoPantalla-(altoPantalla/5),null);
            Paint p=new Paint();
            if(jugador1.getVidas()<=0){
//                Log.i("ONDRAW5", "JUGADOR1 VIDAS0-PIERDE");
                p.setColor(Color.RED);
                p.setTextSize(60);
                canvas.drawText("JUGADOR 1 PIERDE" ,anchoPantalla/4,altoPantalla/2, p);
            }
            else if(jugador2.getVidas()<=0){
//                Log.i("ONDRAW5", "JUGADOR2 VIDAS0-PIERDE");
                p.setColor(Color.RED);
                p.setTextSize(60);
                canvas.drawText("JUGADOR 2 PIERDE", anchoPantalla / 4, altoPantalla / 2, p);
                final SharedPreferences.Editor editorJugador = ((JuegoActivity)getContext()).preferenciasJugador.edit();
                editorJugador.putInt("DINERO", ((JuegoActivity) getContext()).preferenciasJugador.getInt("DINERO", 0) + 20);
                editorJugador.apply();
            }else {
                p.setColor(Color.RED);
                p.setTextSize(30);
//                Log.i("VIDAS", "VIDAS" + jugador1.getVidas());
                //jugador1
                canvas.drawText("Activo:" + (jugador1.isActivo() ? "Si" : "No"), jugador1.getDeckX()/2, jugador1.getDeckY() - (int) (0.7 * altoCarta), p);
                canvas.drawText("Vidas:" + jugador1.getVidas(), jugador1.getDeckX()/2, jugador1.getDeckY() - (int) (0.5 * altoCarta), p);
                canvas.drawText("Recursos:" + jugador1.getRecursos(), jugador1.getDeckX()/2, jugador1.getDeckY() - (int) (0.2 * altoCarta), p);
                //jugador2
                p.setColor(Color.BLUE);
                canvas.drawText("Activo:" + (jugador2.isActivo() ? "Si" : "No"), (int)(jugador2.getDeckX()/1.1), jugador2.getDeckYfin() + (int) (0.7 * altoCarta), p);
                canvas.drawText("Vidas:" + jugador2.getVidas(), (int)(jugador2.getDeckX()/1.1), jugador2.getDeckYfin() + (int) (0.5 * altoCarta), p);
                canvas.drawText("Recursos:" + jugador2.getRecursos(), (int)(jugador2.getDeckX()/1.1), jugador2.getDeckYfin() + (int) (0.2 * altoCarta), p);

                //Zona-Informacion
                p = new Paint();
                infoCard = Bitmap.createScaledBitmap(infoCard, (int) (1.5 * anchoCarta), (int) (altoCarta * 1.5), true);
                if (jugador2.isActivo() && !animacionJ2) {
                    infoCardJ2 = Bitmap.createScaledBitmap(infoCardJ2, (int) (1.5 * anchoCarta), (int) (altoCarta * 1.5), true);
                    infoCard = infoCardJ2;
                }
                canvas.drawBitmap(infoCard, 0, (int) ((altoPantalla / 2) - (altoCarta * 0.75)), null);
//                p.setColor(Color.RED);
//                p.setStyle(Paint.Style.STROKE);
//                canvas.drawRect(0
//                        , (int) ((altoPantalla / 2) - (altoCarta * 0.75))
//                        , (int) (anchoCarta * 1.5)
//                        , (int) ((altoPantalla / 2) + (altoCarta * 0.75))
//                        , p);
//                Log.i("ONDRAW", "dibujando DESCARTE");

                //Boton Jugar
//                p = new Paint();
//                p.setColor(Color.RED);
//                p.setStyle(Paint.Style.STROKE);
//                canvas.drawRect(0
//                        , (int) ((altoPantalla / 2) - (altoCarta * 1.2))
//                        , (int) (anchoCarta * 1.5)
//                        , (int) ((altoPantalla / 2) - (altoCarta * 0.75))
//                        , p);
//                Log.i("ONDRAW", "dibujando DESCARTE");
                temp = botonPlay;
                if (pulsandoPlay) {
                    temp = botonPlayPulsado;
                }
                temp = Bitmap.createScaledBitmap(temp, (int) (1.5 * anchoCarta), (int) (altoCarta * 0.4), true);
                if (jugador2.isActivo()) {
                    temp = girarBitmap(temp, 180);
                }
                canvas.drawBitmap(temp, 0, (int) ((altoPantalla / 2) - (altoCarta * 1.2)), null);
                //Boton fin turno
//                p = new Paint();
//                p.setColor(Color.RED);
//                p.setStyle(Paint.Style.STROKE);
//                canvas.drawRect((anchoPantalla / 2) - (anchoCarta)
//                        , (altoPantalla / 2) - (altoCarta / 4)
//                        , (anchoPantalla / 2) + (anchoCarta)
//                        , (altoPantalla / 2) + (altoCarta / 4), p);

                temp = botonActivo;
                if (jugador1.isTocandoFinTurno()) {
                    temp = botonPresionado;
                }
                if (jugador2.isActivo()) {
                    temp = girarBitmap(temp, 180);
                }
                canvas.drawBitmap(temp, (anchoPantalla / 2) - (anchoCarta), (altoPantalla / 2) - (altoCarta / 4), null);

                //ONTOUCH DRAG & DROP
                for(PointF posicion:posiciones.values()){
                    float x=posicion.x-infoCard.getWidth()/2;
                    float y=posicion.y-infoCard.getHeight()/2;
                    canvas.drawBitmap(infoCard,x,y,null);
                }


                //Jugador2
                if (jugador2 != null) {
//                    Log.i("ONDRAW", "Antes deck");
                    //Zona-Mano
//                    p = new Paint();
//                    p.setColor(Color.BLUE);
//                    p.setStyle(Paint.Style.STROKE);
//                    canvas.drawRect(jugador2.getManoX()
//                            , jugador2.getManoY()
//                            , jugador2.getManoXfin()
//                            , jugador2.getManoYfin(), p);
//                    Log.i("ONDRAW2", "dibujando MANOx:" + jugador2.getManoX());
//                    Log.i("ONDRAW2", "dibujando MANOx2:" + jugador2.getManoY());
//                    Log.i("ONDRAW2", "dibujando MANOy:" + jugador2.getManoXfin());
//                    Log.i("ONDRAW2", "dibujando MANOy2:" + jugador2.getManoYfin());
//                    Log.i("ONDRAW2", "dibujando MANO");

                    //Zona-Mesa
//                    p = new Paint();
//                    p.setColor(Color.BLUE);
//                    p.setStyle(Paint.Style.STROKE);
//                    canvas.drawRect(jugador2.getMesaX()
//                            , jugador2.getMesaY()
//                            , jugador2.getMesaXfin()
//                            , jugador2.getMesaYfin()
//                            , p);
//                    Log.i("ONDRAW2", "dibujando MESAx:" + jugador2.getMesaX());
//                    Log.i("ONDRAW2", "dibujando MESAx2:" + jugador2.getMesaY());
//                    Log.i("ONDRAW2", "dibujando MESAy:" + jugador2.getMesaXfin());
//                    Log.i("ONDRAW2", "dibujando MESAy2:" + jugador2.getMesaYfin());
//                    Log.i("ONDRAW2", "dibujando MESA");

                    //Zona-Descarte
//                    p = new Paint();
//                    p.setColor(Color.BLUE);
//                    p.setStyle(Paint.Style.STROKE);
//                    canvas.drawRect(jugador2.getDescarteX()
//                            , jugador2.getDescarteY()
//                            , jugador2.getDescarteXfin()
//                            , jugador2.getDescarteYfin()
//                            , p);
//                    Log.i("ONDRAW2", "dibujando DESCARTEx:" + jugador2.getDescarteX());
//                    Log.i("ONDRAW2", "dibujando DESCARTEx2:" + jugador2.getDescarteY());
//                    Log.i("ONDRAW2", "dibujando DESCARTEy:" + jugador2.getDescarteXfin());
//                    Log.i("ONDRAW2", "dibujando DESCARTEy2:" + jugador2.getDescarteYfin());
//                    Log.i("ONDRAW2", "dibujando DESCARTE");

                    //Zona-DECK
                    if (jugador2.getDeck().size() > 0) {

//                        Log.i("ONDRAW2", "dibujando DECK:" + jugador2.getDeck().get(0).getId());
                        temp = topDeck;
                        canvas.drawBitmap(temp, jugador2.getDeckX(), jugador2.getDeckY(), null);
                        for (int i = 0; i < jugador2.getDeck().size(); i++) {
                            jugador2.getDeck().get(i).setxInicio(xDeck);
                            jugador2.getDeck().get(i).setxFin(xDeck + anchoCarta);
                            jugador2.getDeck().get(i).setyInicio(yDeck);
                            jugador2.getDeck().get(i).setyFin(yDeck + altoCarta);
//                            p = new Paint();
//                            p.setColor(Color.BLUE);
//                            p.setStyle(Paint.Style.STROKE);
                        }
                    } else {
//                        Log.i("ONDRAW2", "DECK VACIO");
                    }
//                    canvas.drawRect(jugador2.getDeckX()
//                            , jugador2.getDeckY()
//                            , jugador2.getDeckXfin()
//                            , jugador2.getDeckYfin(), p);
//                    Log.i("ONDRAW2", "dibujando DECK");
//                    Log.i("ONDRAW2", "Antes bucle");

                    //MANO
//                    Log.i("ONDRAW2", "CARTAS MANO J2 " + jugador2.getMano().size());
//                    Log.i("ONDRAW2", "CARTAS ARRAYMANO J2 " + arrayManoJ2.size());
                    for (int i = 0; i < jugador2.getMano().size(); i++) {
//                        Log.i("ONDRAW2", "Bucle " + i);
                        if (jugador2.isActivo()) {
                            temp = arrayManoJ2.get(i);
                        } else {
                            temp = topDeck;
                        }
                        double distanciaMinima=0;
                        if(jugador2.getMano().size()>0) {
                            distanciaMinima = ((jugador2.getManoXfin()-jugador2.getManoX()) / (double)jugador2.getMano().size())/anchoCarta;
                        }
                        jugador2.getMano().get(i).setxInicio(jugador2.getManoX() + (int) (distanciaMinima * anchoCarta) * i);
                        jugador2.getMano().get(i).setyInicio(jugador2.getManoY());
                        jugador2.getMano().get(i).setyFin(jugador2.getManoYfin());
//                        Log.i("ONDRAW2", "Antes draw " + i);
                        if (i < jugador2.getMano().size() - 1) {
                            jugador2.getMano().get(i).setxFin(jugador2.getMano().get(i).getxInicio() + (int) (distanciaMinima * anchoCarta) * (i+1));
                        } else {
                            jugador2.getMano().get(i).setxFin(jugador2.getMano().get(i).getxInicio() + anchoCarta);
                        }
                        if (jugador2.isActivo()) {
                            temp = girarBitmap(temp, 180);
                        }
                        canvas.drawBitmap(temp, jugador2.getManoX() + (int) (distanciaMinima * anchoCarta) * i, jugador2.getMano().get(i).getyInicio(), null);
//                        p = new Paint();
//                        p.setColor(Color.GREEN);
//                        p.setStyle(Paint.Style.STROKE);
//                        canvas.drawRect(jugador2.getMano().get(i).getxInicio()
//                                , jugador2.getMano().get(i).getyInicio()
//                                , jugador2.getMano().get(i).getxFin()
//                                , jugador2.getMano().get(i).getyFin(), p);
                    }

                    //DESCARTE
                    if (jugador2.getDescarte().size() > 0) {
                        for (int i = 0; i < jugador2.getDescarte().size(); i++) {
                            jugador2.getDescarte().get(i).setxInicio(jugador2.getDescarteX());
                            jugador2.getDescarte().get(i).setxFin(jugador2.getDescarteXfin());
                            jugador2.getDescarte().get(i).setyInicio(jugador2.getDescarteY());
                            jugador2.getDescarte().get(i).setyFin(jugador2.getDescarteYfin());
                        }
                        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), jugador2.getDescarte().get(jugador2.getDescarte().size() - 1).getImagen()), anchoCarta, altoCarta, true);
                        if (jugador2.isActivo()) {
                            temp = girarBitmap(temp, 180);
                        }
                        canvas.drawBitmap(temp, jugador2.getDescarteX(), jugador2.getDescarteY(), null);
                    }
//                    p = new Paint();
//                    p.setColor(Color.GREEN);
//                    p.setStyle(Paint.Style.STROKE);
//                    canvas.drawRect(jugador2.getDescarteX()
//                            , jugador2.getDescarteY()
//                            , jugador2.getDescarteXfin()
//                            , jugador2.getDescarteYfin(), p);

                    //MESA
                    int xTemp = jugador2.getMesaX();
                    int yTemp = jugador2.getMesaYfin();
                    for (int i = 0; i < jugador2.getMesa().size(); i++) {
//                        Log.i("ONDRAW2", "Bucle " + i);
//                        Log.d("ANTES", jugador2.getMesa().toString());
                        temp = arrayMesaJ2.get(i);
//                        Log.i("ONDRAW2", "Antes draw " + i);
//                        Log.d("DESPUES", jugador2.getMesa().toString());
                        jugador2.getMesa().get(i).setxInicio(xTemp + (int) (0.5 * anchoCarta) * i);
                        jugador2.getMesa().get(i).setyInicio(jugador2.getMesaY());
                        jugador2.getMesa().get(i).setyFin(jugador2.getMesaYfin());
                        if (i < jugador2.getMesa().size() - 1) {
//                            Log.i("ONDRAW2", "IF" + i + "size" + (jugador2.getMesa().size() - 1));
                            jugador2.getMesa().get(i).setxFin(jugador2.getMesa().get(i).getxInicio() + (anchoCarta / 2));
                        } else {
//                            Log.i("ONDRAW2", "ELSE" + anchoCarta);
                            jugador2.getMesa().get(i).setxFin(jugador2.getMesa().get(i).getxInicio() + anchoCarta);
//                            Log.i("ONDRAW2", "Antes draw x2:" + jugador2.getMesa().get(i).getxFin());
                        }
                        if (jugador2.isActivo()) {
                            temp = girarBitmap(temp, 180);
                        }
                        canvas.drawBitmap(temp, jugador2.getMesa().get(i).getxInicio(), jugador2.getMesa().get(i).getyInicio(), null);
//                        p = new Paint();
//                        p.setColor(Color.GREEN);
//                        p.setStyle(Paint.Style.STROKE);
//                        Log.i("ONDRAW2", "Antes draw x:" + jugador2.getMesa().get(i).getxInicio());
//                        Log.i("ONDRAW2", "Antes draw x2:" + jugador2.getMesa().get(i).getxFin());
//                        Log.i("ONDRAW2", "Antes draw y:" + jugador2.getMesa().get(i).getyInicio());
//                        Log.i("ONDRAW2", "Antes draw y2:" + jugador2.getMesa().get(i).getyFin());
//                        canvas.drawRect(jugador2.getMesa().get(i).getxInicio()
//                                , jugador2.getMesa().get(i).getyInicio()
//                                , jugador2.getMesa().get(i).getxFin()
//                                , jugador2.getMesa().get(i).getyFin(), p);
                    }

                } else {
//                    Log.i("ONDRAW2", "Jugador null");
                }

//                Log.i("ONDRAW3", "ANTES JUGADOR 1");
                //Jugador1
                if (jugador1 != null) {
//                    Log.i("ONDRAW3", "Antes deck J1");
                    //Zona-Mano
//                    p = new Paint();
//                    p.setColor(Color.RED);
//                    p.setStyle(Paint.Style.STROKE);
//                    canvas.drawRect(jugador1.getManoX()
//                            , jugador1.getManoY()
//                            , jugador1.getManoXfin()
//                            , jugador1.getManoYfin(), p);
//                    Log.i("ONDRAW", "dibujando MANOx:" + jugador1.getManoX());
//                    Log.i("ONDRAW", "dibujando MANOx2:" + jugador1.getManoY());
//                    Log.i("ONDRAW", "dibujando MANOy:" + jugador1.getManoXfin());
//                    Log.i("ONDRAW", "dibujando MANOy2:" + jugador1.getManoYfin());
//                    Log.i("ONDRAW", "dibujando MANO");

                    //Zona-Mesa
//                    p = new Paint();
//                    p.setColor(Color.RED);
//                    p.setStyle(Paint.Style.STROKE);
//                    canvas.drawRect(jugador1.getMesaX()
//                            , jugador1.getMesaY()
//                            , jugador1.getMesaXfin()
//                            , jugador1.getMesaYfin()
//                            , p);
//                    Log.i("ONDRAW", "dibujando MESA");

                    //Zona-Descarte
//                    p = new Paint();
//                    p.setColor(Color.RED);
//                    p.setStyle(Paint.Style.STROKE);
//                    canvas.drawRect(jugador1.getDescarteX()
//                            , jugador1.getDescarteY()
//                            , jugador1.getDescarteXfin()
//                            , jugador1.getDescarteYfin()
//                            , p);
//                    Log.i("ONDRAW", "dibujando DESCARTE");

                    //Zona-DECK
                    if (jugador1.getDeck().size() > 0) {

//                        Log.i("ONDRAW", "dibujando DECK:" + jugador1.getDeck().get(0).getId());
                        temp = topDeck;
                        if (jugador2.isActivo()) {
                            temp = girarBitmap(temp, 180);
                        }
                        canvas.drawBitmap(temp, xDeck, yDeck, null);
                        for (int i = 0; i < jugador1.getDeck().size(); i++) {
                            jugador1.getDeck().get(i).setxInicio(xDeck);
                            jugador1.getDeck().get(i).setxFin(xDeck + anchoCarta);
                            jugador1.getDeck().get(i).setyInicio(yDeck);
                            jugador1.getDeck().get(i).setyFin(yDeck + altoCarta);
                        }
                        x += anchoCarta * 1.2;
//                        p = new Paint();
//                        p.setColor(Color.RED);
//                        p.setStyle(Paint.Style.STROKE);
//                        canvas.drawRect(jugador1.getDeck().get(0).getxInicio()
//                                , jugador1.getDeck().get(0).getyInicio()
//                                , jugador1.getDeck().get(0).getxFin()
//                                , jugador1.getDeck().get(0).getyFin(), p);
                    } else {
                        x += anchoCarta * 1.2;
                    }
//                    Log.i("ONDRAW", "dibujando DECK");
//                    Log.i("ONDRAW", "Antes bucle");

                    //MANO
                    for (int i = 0; i < jugador1.getMano().size(); i++) {
//                        Log.i("ONDRAW", "Bucle " + i);
                        if (jugador1.isActivo()) {
                            temp = arrayManoJ1.get(i);
                        } else {
                            temp = topDeck;
                        }
//                        Log.i("ONDRAW", "Antes draw " + i);
                        if (jugador2.isActivo()) {
                            temp = girarBitmap(temp, 180);
                        }
                        double distanciaMinima=0;
                        if(jugador1.getMano().size()>0) {
                            distanciaMinima = ((jugador1.getManoXfin()-jugador1.getManoX()) / (double)jugador1.getMano().size())/anchoCarta;
                        }
//                        distanciaMinima=0.5;
//                        Log.i("ONDRAW", "DISTANCIA MINIMA " + distanciaMinima);
                        canvas.drawBitmap(temp, jugador1.getManoX() + (int) (distanciaMinima * anchoCarta) * i, jugador1.getManoY(), null);
//                        Log.i("ONDRAW", "MANO "+i+"X " + jugador1.getManoX()+" "+(int) (distanciaMinima * anchoCarta) * i);
                        jugador1.getMano().get(i).setxInicio(jugador1.getManoX() + (int) (distanciaMinima * anchoCarta) * i);
//                        Log.i("ONDRAW", "MANO "+i+"X-DEF " + jugador1.getMano().get(i).getxInicio());
                        jugador1.getMano().get(i).setyInicio(jugador1.getManoY());
                        jugador1.getMano().get(i).setyFin(jugador1.getManoYfin());
                        if (i < jugador1.getMano().size() - 1) {
                            jugador1.getMano().get(i).setxFin(jugador1.getMano().get(i).getxInicio() + (int) (distanciaMinima * anchoCarta) * (i+1));
//                            Log.i("ONDRAW", "MANO "+i+"X-DEF " + jugador1.getMano().get(i).getxFin());
                        } else {
                            jugador1.getMano().get(i).setxFin(jugador1.getMano().get(i).getxInicio() + anchoCarta);
                        }
//                        p = new Paint();
//                        p.setColor(Color.RED);
//                        p.setStyle(Paint.Style.STROKE);
//                        canvas.drawRect(jugador1.getMano().get(i).getxInicio()
//                                , jugador1.getMano().get(i).getyInicio()
//                                , jugador1.getMano().get(i).getxFin()
//                                , jugador1.getMano().get(i).getyFin(), p);
                        x += anchoCarta / 2;
                    }

                    //DESCARTE
                    if(jugador1.getDescarte().size()>0) {
                        for (int i = 0; i < jugador1.getDescarte().size(); i++) {
                            jugador1.getDescarte().get(i).setxInicio(jugador1.getDescarteX());
                            jugador1.getDescarte().get(i).setxFin(jugador1.getDescarteXfin());
                            jugador1.getDescarte().get(i).setyInicio(jugador1.getDescarteY());
                            jugador1.getDescarte().get(i).setyFin(jugador1.getDescarteYfin());
                        }
                        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), jugador1.getDescarte().get(jugador1.getDescarte().size() - 1).getImagen()), anchoCarta, altoCarta, true);
                        if (jugador2.isActivo()) {
                            temp = girarBitmap(temp, 180);
                        }
                        canvas.drawBitmap(temp, jugador1.getDescarteX(), y, null);
                    }
//                    p = new Paint();
//                    p.setColor(Color.RED);
//                    p.setStyle(Paint.Style.STROKE);
//                    canvas.drawRect(jugador1.getDescarteX()
//                            , jugador1.getDescarteY()
//                            , jugador1.getDescarteXfin()
//                            , jugador1.getDescarteYfin(), p);

                    //MESA
                    int xTemp = jugador1.getMesaX();
                    int yTemp = jugador1.getMesaYfin();
                    for (int i = 0; i < jugador1.getMesa().size(); i++) {
//                        Log.i("ONDRAW", "Bucle " + i);
                        temp = arrayMesaJ1.get(i);
//                        Log.i("ONDRAW", "Antes draw " + i);
                        jugador1.getMesa().get(i).setxInicio(xTemp);
                        jugador1.getMesa().get(i).setyInicio(yTemp - altoCarta);
                        jugador1.getMesa().get(i).setyFin(yTemp);
                        if (i < jugador1.getMesa().size() - 1) {
                            jugador1.getMesa().get(i).setxFin(xTemp + (anchoCarta / 2));
                        } else {
                            jugador1.getMesa().get(i).setxFin(xTemp + anchoCarta);
                        }
                        if (jugador2.isActivo()) {
                            temp = girarBitmap(temp, 180);
                        }
                        canvas.drawBitmap(temp, jugador1.getMesa().get(i).getxInicio(), jugador1.getMesa().get(i).getyInicio(), null);
//                        p = new Paint();
//                        p.setColor(Color.RED);
//                        p.setStyle(Paint.Style.STROKE);
//                        canvas.drawRect(jugador1.getMesa().get(i).getxInicio()
//                                , jugador1.getMesa().get(i).getyInicio()
//                                , jugador1.getMesa().get(i).getxFin()
//                                , jugador1.getMesa().get(i).getyFin(), p);
                        xTemp += anchoCarta / 2;
                    }
                } else {
//                    Log.i("ONDRAW", "Jugador null");
                }
                //ANIMACIONES

//                Log.i("ONDRAW3", "ANIMACION");
//                Log.i("ONDRAW3", "ANIMACIONJ2");
//                Log.i("ONDRAW3", "ANTES IF");
                for (int i = 0; i < jugador2.getMesa().size(); i++) {
//                   Log.i("ONDRAW4", "ANTES IF2:" + jugador1.getMesa().get(i).isAnimar());
                    if (jugador2.getMesa().get(i).isAnimar()) {
//                        Log.i("ONDRAW4", "DENTRO IF" + jugador1.getMesa().get(i).getGrados());
                        animacion=arrayefectoGiro.get(jugador2.getMesa().get(i).getGrados());
                        jugador2.getMesa().get(i).setGrados(jugador2.getMesa().get(i).getGrados() + 1);
//                        Log.i("ONDRAW3", "ANIMACION J2 ID:"+jugador2.getMesa().get(i).getNombre()+" x:"+jugador2.getMesa().get(i).getxInicio()+" y:"+jugador2.getMesa().get(i).getyInicio());
                        canvas.drawBitmap(animacion, jugador2.getMesa().get(i).getxInicio(), jugador2.getMesa().get(i).getyInicio(), null);
//                        Log.i("ONDRAW3", "DESPUES DIBUJAR"+jugador2.getMesa().get(i).getGrados());
                    }
                }
//                Log.i("ONDRAW4", "ANIMACIONJ1");
//                Log.i("ONDRAW4", "ANTES IF");
                for (int i = 0; i < jugador1.getMesa().size(); i++) {
//                    Log.i("ONDRAW4", "ANTES IF2:" + jugador1.getMesa().get(i).isAnimar());
                    if (jugador1.getMesa().get(i).isAnimar()) {
//                        Log.i("ONDRAW4", "DENTRO IF" + jugador1.getMesa().get(i).getGrados());
                        animacion=arrayefectoGiro.get(jugador1.getMesa().get(i).getGrados());
                        jugador1.getMesa().get(i).setGrados(jugador1.getMesa().get(i).getGrados() + 1);
                        canvas.drawBitmap(animacion,jugador1.getMesa().get(i).getxInicio(), jugador1.getMesa().get(i).getyInicio(), null);
                    }
                }
            }
            if(jugador1.getVidas()<=0||jugador2.getVidas()<=0) {
                if (jugador1.isActivo() || jugador2.isActivo()) {
                    jugador1.setActivo(false);
                    jugador2.setActivo(false);
                }
            }
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.anchoPantalla=w;
        this.altoPantalla=h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int accion=event.getActionMasked();
        switch(accion){
            case MotionEvent.ACTION_DOWN:
//                Log.i("MULTITOUCH","action down");
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
//                                Log.i("MULTITOUCH-Deck", "TOCANDO-TRUE");
                            }
                        }
                    } else if (event.getX() >= jugador1.getManoX() && event.getX() < jugador1.getManoXfin()
                            && event.getY() >= jugador1.getManoY() && event.getY() < jugador1.getManoYfin()) {
                        //TOCAR MANO
                        if (jugador1.getMano().size() > 0 && jugador1.getMano() != null) {
//                            Log.i("MULTITOUCH-MESA", "IF-MANO");
                            for (int i = 0; i < jugador1.getMano().size(); i++) {
                                Log.i("MULTITOUCH-MESA", "IF-MANO "+i+" x//"+jugador1.getMano().get(i).getxInicio());
                                Log.i("MULTITOUCH-MESA", "IF-MANO "+i+" x2//"+jugador1.getMano().get(i).getxFin());
                                Log.i("MULTITOUCH-MESA", "IF-MANO "+i+" y//"+jugador1.getMano().get(i).getyInicio());
                                Log.i("MULTITOUCH-MESA", "IF-MANO "+i+" y2//"+jugador1.getMano().get(i).getyFin());
                                Log.i("MULTITOUCH-MESA", "IF-MANO EVENT"+i+" x//"+event.getX());
                                Log.i("MULTITOUCH-MESA", "IF-MANO EVENT"+i+" y//"+event.getY());

                                if (event.getX() >= jugador1.getMano().get(i).getxInicio()
                                        && event.getX() <= jugador1.getMano().get(i).getxFin()
                                        && event.getY() >= jugador1.getMano().get(i).getyInicio()
                                        && event.getY() <= jugador1.getMano().get(i).getyFin()) {
                                    jugador1.setTocandoMano(true);
                                    infoCard=arrayManoJ1.get(i);
                                    idTemp=jugador1.getMano().get(i).getId();
                                    Log.i("MULTITOUCH-MANO", "TOCANDO-TRUE");

                                    int pointerIndex=event.getActionIndex();
                                    int pointerID=event.getPointerId(pointerIndex);
                                    PointF posicion=new PointF(event.getX(pointerIndex),event.getY(pointerIndex));
                                    posiciones.put(pointerID,posicion);
                                }
                            }
                        }
                    } else if (event.getX() >= jugador1.getMesaX() && event.getX() < jugador1.getMesaXfin()
                            && event.getY() >= jugador1.getMesaY() && event.getY() < jugador1.getMesaYfin()) {
                        //Tocar MESA
                        if (jugador1.getMesa().size() > 0 && jugador1.getMesa() != null) {
//                            Log.i("MULTITOUCH-MESA", "IF-MESA");
                            for (int i = 0; i < jugador1.getMesa().size(); i++) {
                                if (event.getX() >= jugador1.getMesa().get(i).getxInicio()
                                        && event.getX() <= jugador1.getMesa().get(i).getxFin()
                                        && event.getY() >= jugador1.getMesa().get(i).getyInicio()
                                        && event.getY() <= jugador1.getMesa().get(i).getyFin()) {
                                    jugador1.setTocandoMesa(true);
                                    infoCard=arrayMesaJ1.get(i);
                                    idTemp=jugador1.getMesa().get(i).getId();
//                                    Log.i("MULTITOUCH-MESA", "TOCANDO-TRUE");
                                }
                            }
                        }
                    } else if (event.getX() >= jugador1.getDescarteX() && event.getX() < jugador1.getDescarteXfin()
                            && event.getY() >= jugador1.getDescarteY() && event.getY() < jugador1.getDescarteYfin()) {
                        //Tocar DESCARTE
                        if (jugador1.getDescarte().size() > 0 && jugador1.getDescarte() != null) {
//                            Log.i("MULTITOUCH-DESCARTE", "IF-DESCARTE");
                            if (event.getX() >= jugador1.getDescarte().get(0)
                                    .getxInicio()
                                    && event.getX() <= jugador1.getDescarte().get(0)
                                    .getxFin()
                                    && event.getY() >= jugador1.getDescarte().get(0)
                                    .getyInicio()
                                    && event.getY() <= jugador1.getDescarte().get(0)
                                    .getyFin()) {
                                jugador1.setTocandoDescarte(true);
//                                Log.i("MULTITOUCH-DESCARTE", "TOCANDO-TRUE");
                            }
                        }
                    } else if (event.getX() >= (anchoPantalla / 2) - (anchoCarta) && event.getX() < (anchoPantalla / 2) + (anchoCarta)
                            && event.getY() >= (altoPantalla / 2) - (altoCarta / 4) && event.getY() < (altoPantalla / 2) + (altoCarta / 4)) {
                        //Tocar FinTurno
                       jugador1.setTocandoFinTurno(true);
//                        Log.i("MULTITOUCH-FINTURNO", "TOCANDO-TRUE");
                    }else if(event.getX() >= 0 && event.getX() < anchoCarta*1.5
                            && event.getY() >= (altoPantalla/2)-(altoCarta*1.2) && event.getY() < (altoPantalla/2)-(altoCarta*0.75)){
                        pulsandoPlay=true;
//                        Log.i("MULTITOUCH-BTN PLAY", "PULSADO");
                    }
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
//                                Log.i("MULTITOUCH-Deck2", "TOCANDO-TRUE");
                            }
                        }
                    } else if (event.getX() >= jugador2.getManoX() && event.getX() < jugador2.getManoXfin()
                            && event.getY() >= jugador2.getManoY() && event.getY() < jugador2.getManoYfin()) {
                        //TOCAR MANO
                        if (jugador2.getMano().size() > 0 && jugador2.getMano() != null) {
//                            Log.i("MULTITOUCH-MESA2", "IF-MANO");
                            for (int i = 0; i < jugador2.getMano().size(); i++) {
                                if (event.getX() >= jugador2.getMano().get(i).getxInicio()
                                        && event.getX() <= jugador2.getMano().get(i).getxFin()
                                        && event.getY() >= jugador2.getMano().get(i).getyInicio()
                                        && event.getY() <= jugador2.getMano().get(i).getyFin()) {
                                    jugador2.setTocandoMano(true);
                                    infoCardJ2=girarBitmap(arrayManoJ2.get(i),180);
                                    idTemp=jugador2.getMano().get(i).getId();
//                                    Log.i("MULTITOUCH-MANO2", "TOCANDO-TRUE");

                                    int pointerIndex=event.getActionIndex();
                                    int pointerID=event.getPointerId(pointerIndex);
                                    PointF posicion=new PointF(event.getX(pointerIndex),event.getY(pointerIndex));
                                    posiciones.put(pointerID,posicion);
                                }
                            }
                        }
                    } else if (event.getX() >= jugador2.getMesaX() && event.getX() < jugador2.getMesaXfin()
                            && event.getY() >= jugador2.getMesaY() && event.getY() < jugador2.getMesaYfin()) {
                        //Tocar MESA
                        if (jugador2.getMesa().size() > 0 && jugador2.getMesa() != null) {
//                            Log.i("MULTITOUCH-MESA2", "IF-MESA");
                            for (int i = 0; i < jugador2.getMesa().size(); i++) {
                                if (event.getX() >= jugador2.getMesa().get(i).getxInicio()
                                        && event.getX() <= jugador2.getMesa().get(i).getxFin()
                                        && event.getY() >= jugador2.getMesa().get(i).getyInicio()
                                        && event.getY() <= jugador2.getMesa().get(i).getyFin()) {
                                    jugador2.setTocandoMesa(true);
                                    infoCardJ2=girarBitmap(arrayMesaJ2.get(i),180);
                                    idTemp=jugador2.getMesa().get(i).getId();
//                                    Log.i("MULTITOUCH-MESA2", "TOCANDO-TRUE");
                                }
                            }
                        }
                    } else if (event.getX() >= jugador2.getDescarteX() && event.getX() < jugador2.getDescarteXfin()
                            && event.getY() >= jugador2.getDescarteY() && event.getY() < jugador2.getDescarteYfin()) {
                        //Tocar DESCARTE
                        if (jugador2.getDescarte().size() > 0 && jugador2.getDescarte() != null) {
//                            Log.i("MULTITOUCH-DESCARTE2", "IF-DESCARTE");
                            if (event.getX() >= jugador2.getDescarte().get(0)
                                    .getxInicio()
                                    && event.getX() <= jugador2.getDescarte().get(0)
                                    .getxFin()
                                    && event.getY() >= jugador2.getDescarte().get(0)
                                    .getyInicio()
                                    && event.getY() <= jugador2.getDescarte().get(0)
                                    .getyFin()) {
                                jugador2.setTocandoDescarte(true);
//                                Log.i("MULTITOUCH-DESCARTE2", "TOCANDO-TRUE");
                            }
                        }
                    } else if (event.getX() >= (anchoPantalla / 2) - (anchoCarta) && event.getX() < (anchoPantalla / 2) + (anchoCarta)
                            && event.getY() >= (altoPantalla / 2) - (altoCarta / 4) && event.getY() < (altoPantalla / 2) + (altoCarta / 4)) {
                        //Tocar FinTurno
                        jugador2.setTocandoFinTurno(true);
//                        Log.i("MULTITOUCH-FINTURNO2", "TOCANDO-TRUE");
                    }else if(event.getX() >= 0 && event.getX() < anchoCarta*1.5
                            && event.getY() >= (altoPantalla/2)-(altoCarta*1.2) && event.getY() < (altoPantalla/2)-(altoCarta*0.75)){
                        pulsandoPlay=true;
//                        Log.i("MULTITOUCH-BTN PLAY2", "PULSADO");
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
//                Log.i("MULTITOUCH","action pointer down");
                break;
            case MotionEvent.ACTION_UP:
//                Log.i("MULTITOUCH","action up");
            case MotionEvent.ACTION_POINTER_UP:
//                Log.i("MULTITOUCH","action pointer up");
                if(jugador1.getVidas()<=0 || jugador2.getVidas()<=0){
                   JuegoActivity host=(JuegoActivity)this.getContext();
//                    Log.i("MULTITOUCH3","HOST CREADO");
//                    Log.i("MULTITOUCH3","ON BACK?");
                    host.onBackPressed();
                }
                if(jugador1.isActivo()) {
                    int pointerIndex=event.getActionIndex();
                    int pointerID=event.getPointerId(pointerIndex);
                    PointF posicion =posiciones.get(pointerID);
                    try {
                        if (posicion.x > jugador1.getMesaX() && posicion.x < jugador1.getMesaXfin()
                                && posicion.y > jugador1.getMesaY() && posicion.y < jugador1.getMesaYfin()) {
                            Log.i("DRAG", "DROP MESA X&Y");
                            for(int i=0;i<jugador1.getMano().size();i++){
                                if(jugador1.getMano().get(i).getId()==idTemp){
                                    if (jugador1.getRecursos() >= jugador1.getMano().get(i).getCoste()) {
                                        synchronized (this.getSurfaceHolder()) {
                                            jugador1.setRecursos(jugador1.getRecursos() - jugador1.getMano().get(i).getCoste());
                                            jugador1.moveCardFromHandToTable(jugador1.getMano().get(i).getId());
                                        }
                                    } else {
                                        Toast.makeText(this.getContext(), "No hay recursos suficientes", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                        posiciones.remove(pointerID);
                    }catch(NullPointerException e){

                    }

                    //LEVANTAR DECK
                    if (jugador1.getDeck().size() > 0 && jugador1.getDeck() != null) {
//                        Log.i("MULTITOUCH-DECK", "1ºIF");
                        if (event.getX() >= jugador1.getDeckX()
                                && event.getX() <= jugador1.getDeckXfin()
                                && event.getY() >= jugador1.getDeckY()
                                && event.getY() <= jugador1.getDeckYfin()
                                && jugador1.isTocandoDeck()) {
//                            Log.i("MULTITOUCH", "draw card 1");
                            jugador1.setTocandoDeck(false);
//                            jugador1.moveFromDeckToHand(1);
                        } else {
                            jugador1.setTocandoDeck(false);
                        }
                    } else {
                        jugador1.setTocandoDeck(false);
                    }
//                    Log.i("MULTITOUCH-IF-MANO", "ANTES");
                    if (event.getX() >= jugador1.getManoX() && event.getX() < jugador1.getManoXfin()
                            && event.getY() >= jugador1.getManoY() && event.getY() < jugador1.getManoYfin() && jugador1.isTocandoMano()) {
                        //TOCAR MANO
                        if (jugador1.getMano().size() > 0 && jugador1.getMano() != null) {
//                            Log.i("MULTITOUCH-MESA", "IF-MANO");
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
                                }
                            }
                        }
                        jugador1.setTocandoMano(false);
                    } else if (event.getX() >= jugador1.getMesaX() && event.getX() < jugador1.getMesaXfin()
                            && event.getY() >= jugador1.getMesaY() && event.getY() < jugador1.getMesaYfin() && jugador1.isTocandoMesa()) {
//                        Log.i("MULTITOUCH-IF-MESA", "DENTRO");
                        //Tocar MESA
                        if (jugador1.getMesa().size() > 0 && jugador1.getMesa() != null) {
//                            Log.i("MULTITOUCH-MESA", "IF-MESA");
                            for (int i = 0; i < jugador1.getMesa().size(); i++) {
                                if (event.getX() >= jugador1.getMesa().get(i).getxInicio()
                                        && event.getX() <= jugador1.getMesa().get(i).getxFin()
                                        && event.getY() >= jugador1.getMesa().get(i).getyInicio()
                                        && event.getY() <= jugador1.getMesa().get(i).getyFin()) {
//                                    Log.i("MULTITOUCH-MESA", "ANIMACION");
//                                    animacionJ1=true;
//                                    idAnimacion=jugador1.getMesa().get(i).getId();
//                                    jugador1.getMesa().get(i).setAnimar(true);
                                }
                            }
                        }
                        jugador1.setTocandoMesa(false);
                    } else if (event.getX() >= jugador1.getDescarteX() && event.getX() < jugador1.getDescarteXfin()
                            && event.getY() >= jugador1.getDescarteY() && event.getY() < jugador1.getDescarteYfin() && jugador1.isTocandoDescarte()) {
//                        Log.i("MULTITOUCH-IF-DESCARTE", "DENTRO");
                        //Tocar DESCARTE
                        if (jugador1.getDescarte().size() > 0 && jugador1.getDescarte() != null) {
//                            Log.i("MULTITOUCH-DESCARTE", "IF-DESCARTE");
                            if (event.getX() >= jugador1.getDescarte().get(0)
                                    .getxInicio()
                                    && event.getX() <= jugador1.getDescarte().get(0)
                                    .getxFin()
                                    && event.getY() >= jugador1.getDescarte().get(0)
                                    .getyInicio()
                                    && event.getY() <= jugador1.getDescarte().get(0)
                                    .getyFin()) {
//                                jugador1.moveCardFromDiscardToDeck(jugador1
//                                        .getDescarte().get(0).getId());
                            }
                        }
                        jugador1.setTocandoDescarte(false);
                    } else if (event.getX() >= (anchoPantalla / 2) - (anchoCarta) && event.getX() < (anchoPantalla / 2) + (anchoCarta)
                            && event.getY() >= (altoPantalla / 2) - (altoCarta / 4) && event.getY() < (altoPantalla / 2) + (altoCarta / 4) && jugador1.isTocandoFinTurno()) {
//                        Log.i("MULTITOUCH-FIN TURNO", "FIN TURNO");
                        jugador1.endOfTurn();
                        jugador1.setActivo(false);
                        jugador1.setTocandoFinTurno(false);
//                        Log.i("MULTITOUCH-FIN TURNO", "PRINCIPIO TURNO");
                        if(!jugador2.isActivo()) {
                            jugador2.setActivo(true);
//                        Log.i("MULTITOUCH-FIN TURNO", "PRINCIPIO TURNO");
                            jugador2.startOfTurn();
                        }
                    }else if(event.getX() >= 0 && event.getX() < anchoCarta*1.5
                            && event.getY() >= (altoPantalla/2)-(altoCarta*1.2) && event.getY() < (altoPantalla/2)-(altoCarta*0.75)){
                        for(int i=0;i<jugador1.getMesa().size();i++){
                            if(idTemp==jugador1.getMesa().get(i).getId()){
                                jugador1.moveCardFromTableToDiscard(jugador1.getMesa().get(i).getId());
                            }
                        }
                        for(int i=0;i<jugador1.getMano().size();i++){
                            if(idTemp==jugador1.getMano().get(i).getId()){
                                if (jugador1.getRecursos() >= jugador1.getMano().get(i).getCoste()) {
                                    synchronized (this.getSurfaceHolder()) {
                                        jugador1.setRecursos(jugador1.getRecursos() - jugador1.getMano().get(i).getCoste());
                                        jugador1.moveCardFromHandToTable(jugador1.getMano().get(i).getId());
                                    }
                                } else {
                                    Toast.makeText(this.getContext(),"No hay recursos suficientes",Toast.LENGTH_SHORT).show();
//                                    Log.i("JUGAR", "NOT ENOUGHT RESOURCES");
                                }
                            }
                        }
                        pulsandoPlay=false;
//                        Log.i("MULTITOUCH-BTN PLAY", "FIN");
                    }
//                    Log.i("MULTITOUCH-IFS-FINIFS", "FIN");
                }
                else if(jugador2.isActivo()) {
                    int pointerIndex=event.getActionIndex();
                    int pointerID=event.getPointerId(pointerIndex);
                    PointF posicion =posiciones.get(pointerID);
                    try {
                        Log.i("DRAG", "x "+posicion.x);
                        Log.i("DRAG", "Y "+posicion.y);
                        Log.i("DRAG", "MESA X "+jugador2.getMesaX());
                        Log.i("DRAG", "MESA X& "+jugador2.getMesaXfin());
                        Log.i("DRAG", "MESA Y "+jugador2.getMesaY());
                        Log.i("DRAG", "MESA &Y "+jugador2.getMesaYfin());
                        if (posicion.x > jugador2.getMesaX() && posicion.x < jugador2.getMesaXfin()
                                && posicion.y > jugador2.getMesaY() && posicion.y < jugador2.getMesaYfin()) {
                            Log.i("DRAG", "DROP MESA X&Y");
                            for(int i=0;i<jugador2.getMano().size();i++){
                                if(jugador2.getMano().get(i).getId()==idTemp){
                                    if (jugador2.getRecursos() >= jugador2.getMano().get(i).getCoste()) {
                                        synchronized (this.getSurfaceHolder()) {
                                            jugador2.setRecursos(jugador2.getRecursos() - jugador2.getMano().get(i).getCoste());
                                            jugador2.moveCardFromHandToTable(jugador2.getMano().get(i).getId());
                                        }
                                    } else {
                                        Toast.makeText(this.getContext(), "No hay recursos suficientes", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                        posiciones.remove(pointerID);
                    }catch(NullPointerException e){

                    }
                    //LEVANTAR DECK
                    if (jugador2.getDeck().size() > 0 && jugador2.getDeck() != null) {
//                        Log.i("MULTITOUCH-DECK2", "1ºIF");
                        if (event.getX() >= jugador2.getDeckX()
                                && event.getX() <= jugador2.getDeckXfin()
                                && event.getY() >= jugador2.getDeckY()
                                && event.getY() <= jugador2.getDeckYfin()
                                && jugador2.isTocandoDeck()) {
//                            Log.i("MULTITOUCH2", "draw card 1");
                            jugador2.setTocandoDeck(false);
//                            jugador2.moveFromDeckToHand(1);
                        } else {
                            jugador2.setTocandoDeck(false);
                        }
                    } else {
                        jugador2.setTocandoDeck(false);
                    }
//                    Log.i("MULTITOUCH-IF-MANO2", "ANTES");
                    if (event.getX() >= jugador2.getManoX() && event.getX() < jugador2.getManoXfin()
                            && event.getY() >= jugador2.getManoY() && event.getY() < jugador2.getManoYfin() && jugador2.isTocandoMano()) {
                        //TOCAR MANO
                        if (jugador2.getMano().size() > 0 && jugador2.getMano() != null) {
//                            Log.i("MULTITOUCH-MANO2", "IF-MANO");
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
                                }
                            }
                        }
                        jugador2.setTocandoMano(false);
                    } else if (event.getX() >= jugador2.getMesaX() && event.getX() < jugador2.getMesaXfin()
                            && event.getY() >= jugador2.getMesaY() && event.getY() < jugador2.getMesaYfin() && jugador2.isTocandoMesa()) {
//                        Log.i("MULTITOUCH-IF-MESA2", "DENTRO");
                        //Tocar MESA
                        if (jugador2.getMesa().size() > 0 && jugador2.getMesa() != null) {
//                            Log.i("MULTITOUCH-MESA2", "IF-MESA");
                            for (int i = 0; i < jugador2.getMesa().size(); i++) {
                                if (event.getX() >= jugador2.getMesa().get(i).getxInicio()
                                        && event.getX() <= jugador2.getMesa().get(i).getxFin()
                                        && event.getY() >= jugador2.getMesa().get(i).getyInicio()
                                        && event.getY() <= jugador2.getMesa().get(i).getyFin()) {
//                                    Log.i("MULTITOUCH-MESA2", "ANIMACION");
                                    animacionJ2=true;
                                    idAnimacion=jugador2.getMesa().get(i).getId();
                                    jugador2.getMesa().get(i).setAnimar(true);
                                }
                            }
                        }
                        jugador2.setTocandoMesa(false);
                    } else if (event.getX() >= jugador2.getDescarteX() && event.getX() < jugador2.getDescarteXfin()
                            && event.getY() >= jugador2.getDescarteY() && event.getY() < jugador2.getDescarteYfin() && jugador2.isTocandoDescarte()) {
//                        Log.i("MULTITOUCH-IF-DESCARTE2", "DENTRO");
                        //Tocar DESCARTE
                        if (jugador2.getDescarte().size() > 0 && jugador2.getDescarte() != null) {
//                            Log.i("MULTITOUCH-DESCARTE2", "IF-DESCARTE");
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
                            }
                        }
                        jugador2.setTocandoDescarte(false);
                    } else if (event.getX() >= (anchoPantalla / 2) - (anchoCarta) && event.getX() < (anchoPantalla / 2) + (anchoCarta)
                            && event.getY() >= (altoPantalla / 2) - (altoCarta / 4) && event.getY() < (altoPantalla / 2) + (altoCarta / 4) && jugador2.isTocandoFinTurno()) {
//                        Log.i("MULTITOUCH-FIN TURNO2", "FIN TURNO");
                        jugador2.endOfTurn();
                        jugador2.setActivo(false);
                        jugador2.setTocandoFinTurno(false);
//                        Log.i("MULTITOUCH-FIN TURNO2", "PRINCIPIO TURNO");
                        if(!jugador1.isActivo()) {
                            jugador1.setActivo(true);
                            jugador1.startOfTurn();
                        }
                    }else if(event.getX() >= 0 && event.getX() < anchoCarta*1.5
                            && event.getY() >= (altoPantalla/2)-(altoCarta*1.2) && event.getY() < (altoPantalla/2)-(altoCarta*0.75)){
                        for(int i=0;i<jugador2.getMesa().size();i++){
                            if(idTemp==jugador2.getMesa().get(i).getId()){
                                jugador2.moveCardFromTableToDiscard(jugador2.getMesa().get(i).getId());
                            }
                        }
                        for(int i=0;i<jugador2.getMano().size();i++){
                            if(idTemp==jugador2.getMano().get(i).getId()){
                                if (jugador2.getRecursos() >= jugador2.getMano().get(i).getCoste()) {
                                    synchronized (this.getSurfaceHolder()) {
                                        jugador2.setRecursos(jugador2.getRecursos() - jugador2.getMano().get(i).getCoste());
                                        jugador2.moveCardFromHandToTable(jugador2.getMano().get(i).getId());
                                    }
                                } else {
                                    Toast.makeText(this.getContext(),"No hay recursos suficientes",Toast.LENGTH_SHORT).show();
//                                    Log.i("JUGAR2", "NOT ENOUGHT RESOURCES");
                                }
                            }
                        }
                        pulsandoPlay=false;
//                        Log.i("MULTITOUCH-BTN PLAY2", "FIN");
                    }
//                    Log.i("MULTITOUCH-IFS-FIN-IFS", "FIN");
                }
                break;
            case MotionEvent.ACTION_MOVE:
               Log.i("MULTITOUCH","action move");
                for (int i=0;i<event.getPointerCount();i++){
                    int id=event.getPointerId(i);
                    PointF posicion=posiciones.get(id);
                    if(null!=posicion){
                        if(Math.abs(posicion.x-event.getX(i))>MIN_DXDY
                                || Math.abs(posicion.y-event.getY(i))>MIN_DXDY){
                            posicion.set(event.getX(i),event.getY(i));
                        }
                    }
                }

                break;
            default:
//                Log.i("MULTITOUCH","Accion no definida");
        }
        return true;
    }

    /**
     * Funcion para el girado de un itmap un numero de terminado de grados
     * @param bitmap Imagen que se quiere rotar
     * @param angulo numero de grados que se desea rotar la imagen
     * @return imagen una vez rotada
     */
    public Bitmap girarBitmap(Bitmap bitmap,float angulo){
//        Matrix matrix = new Matrix();
//        matrix.postRotate(angulo);
        matrix= new Matrix();
        float width=0;
        float height=0;
//        angulo += 10;
        for(int i=0;i<jugador2.getMesa().size();i++){
            if(jugador2.getMesa().get(i).getId()==idAnimacion){
//                Log.i("ROTACION","DENTRO IF");
                width=BitmapFactory.decodeResource(context.getResources(), jugador2.getMesa().get(i).getImagen()).getWidth();
                height=BitmapFactory.decodeResource(context.getResources(), jugador2.getMesa().get(i).getImagen()).getHeight();
            }
        }
        float px = width/2;
        float py = height/2;
        matrix.postTranslate(-bitmap.getWidth()/2, -bitmap.getHeight()/2);
        matrix.postRotate(angulo);
//        matrix.postTranslate(px, py);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * Funcion para la rotacion de un circulo
     * @param bitmap imagen circular que se desea rotar
     * @param angulo numero de grados que se desea rotar la imagen
     * @return imagen una vez rotada
     */
    public Bitmap girarCirculo(Bitmap bitmap,float angulo){
        Matrix matrix = new Matrix();
        matrix.postRotate(angulo);
        /*
        matrix.postRotate(angulo,90,90);

        float[] valores= new float[9];
        matrix.getValues(valores);
        double[][] matrizValores= new double[3][3];
        int k=0;
        for (int i=0;i<3;i++)
            for (int j=0;j<3;j++){
                matrizValores[i][j]=valores[k];
                k++;
            }
        double determinante= getDecDet(matrizValores);

        Log.i("GIRACIRCULO",matrix.toString()+" "+determinante);

        matrix.setScale((float)(1/determinante),(float)(1/determinante));
//        matrix= new Matrix();
//        float width=bitmap.getWidth();
//        float height=bitmap.getHeight();
////        angulo += 10;
//        for(int i=0;i<jugador2.getMesa().size();i++){
//            if(jugador2.getMesa().get(i).getId()==idAnimacion){
//                Log.i("ROTACION","DENTRO IF");
//                width=jugador2.getMesa().get(i).getImagen().getWidth();
//                height=jugador2.getMesa().get(i).getImagen().getHeight();
//            }
//        }
//        float px = width/2;
//        float py = height/2;
//        matrix.postTranslate(-bitmap.getWidth()/2, -bitmap.getHeight()/2);
//        matrix.postRotate(angulo);
//        matrix.postTranslate(px, py);
*/
        return Bitmap.createBitmap(bitmap, 0, 0, 180, 180, matrix, true);
    }

    /**
     * Funcion para encontrar una carta especifica
     * @param id identificador de la carta a buscar
     * @return punto donde se encuentra la carta
     */
    public Point encontrarCarta(int id){
        Point p=new Point();
        //MAZO
        for(int i=0;i<this.jugador1.getDeck().size();i++){
            if(this.jugador1.getDeck().get(i).getId()==id){
                p.set(jugador1.getDeck().get(i).getxInicio(),jugador1.getDeck().get(i).getyInicio());
            }
        }
        for(int i=0;i<this.jugador2.getDeck().size();i++){
            if(this.jugador2.getDeck().get(i).getId()==id){
                p.set(jugador2.getDeck().get(i).getxInicio(),BitmapFactory.decodeResource(context.getResources(), jugador2.getDeck().get(i).getImagen()).getHeight()/2);
            }
        }
        //DESCARTE
        for(int i=0;i<this.jugador1.getDescarte().size();i++){
            if(this.jugador1.getDescarte().get(i).getId()==id){
                p.set(jugador1.getDescarte().get(i).getxInicio(),jugador1.getDescarte().get(i).getyInicio());
            }
        }
        for(int i=0;i<this.jugador2.getDescarte().size();i++){
            if(this.jugador2.getDescarte().get(i).getId()==id){
                p.set(jugador2.getDescarte().get(i).getxInicio(),BitmapFactory.decodeResource(context.getResources(), jugador2.getDescarte().get(i).getImagen()).getHeight()/2);
            }
        }
        //MANO
        for(int i=0;i<this.jugador1.getMano().size();i++){
            if(this.jugador1.getMano().get(i).getId()==id){
                p.set(jugador1.getMano().get(i).getxInicio(),jugador1.getMano().get(i).getyInicio());
            }
        }
        for(int i=0;i<this.jugador2.getMano().size();i++){
            if(this.jugador2.getMano().get(i).getId()==id){
                p.set(jugador2.getMano().get(i).getxInicio(),BitmapFactory.decodeResource(context.getResources(), jugador2.getMano().get(i).getImagen()).getHeight()/2);
            }
        }
        //MESA
        for(int i=0;i<this.jugador1.getMesa().size();i++){
            if(this.jugador1.getMesa().get(i).getId()==id){
                p.set(jugador1.getMesa().get(i).getxInicio(),jugador1.getMesa().get(i).getyInicio());
            }
        }
        for(int i=0;i<this.jugador2.getMesa().size();i++){
            if(this.jugador2.getMesa().get(i).getId()==id){
                p.set(jugador2.getMesa().get(i).getxInicio(),BitmapFactory.decodeResource(context.getResources(), jugador2.getMesa().get(i).getImagen()).getHeight()/2);
            }
        }
        return p;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (this.hilo.getState() == Thread.State.TERMINATED) {
            this.hilo = new Hilo();
        }
        this.hilo.setFuncionando(true);
        this.hilo.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        hilo.setSurfaceSize(width,height);
//        Log.i("SURFACE","SURFACE DESTROYED");
        anchoPantalla=width;
        altoPantalla=height;
        //deck
        alturaDeck=(altoPantalla)-(int)(altoCarta*1.1);
        anchuraDeck=anchoCarta;
        x=avatar.getWidth();
        y=alturaDeck;
        xDeck=x;
        yDeck=y;
        xDescarte=anchoPantalla-2*anchoCarta;
        yDescarte=y;

        if(jugador1==null){
            inicializarCosas((JuegoActivity)context);
        }
        //jugador1
        if(jugador1!=null) {
            jugador1.setDeckX(xDeck);
            jugador1.setDeckXfin(xDeck + anchoCarta);
            jugador1.setDeckY(yDeck);
            jugador1.setDeckYfin(yDeck + altoCarta);
            jugador1.setDescarteX(anchoPantalla - (int) (anchoCarta * 1.2));
            jugador1.setDescarteXfin(jugador1.getDescarteX() + anchoCarta);
            jugador1.setDescarteY(yDeck);
            jugador1.setDescarteYfin(yDeck + altoCarta);
            jugador1.setManoX(jugador1.getDeckX() + (int) (1.2 * anchoCarta));
            jugador1.setManoXfin(jugador1.getDescarteX() - (int) (0.5 * anchoCarta));
            jugador1.setManoY(yDeck);
            jugador1.setManoYfin(yDeck + altoCarta);
            jugador1.setMesaX(jugador1.getManoX());
            jugador1.setMesaXfin(jugador1.getManoXfin());
            jugador1.setMesaY(jugador1.getManoY() - (int) (1.2 * altoCarta));
            jugador1.setMesaYfin(jugador1.getMesaY() + altoCarta);
        }
        if(jugador2==null){
            inicializarCosas((JuegoActivity)context);
        }
        //jugador2
        if(jugador2!=null) {
            jugador2.setDeckX(anchoPantalla - (int) (anchoCarta * 1.2));
            jugador2.setDeckXfin(jugador2.getDeckX() + anchoCarta);
            jugador2.setDeckY(0);
            jugador2.setDeckYfin(jugador2.getDeckY() + altoCarta);
            jugador2.setDescarteX(xDeck);
            jugador2.setDescarteXfin(xDeck + anchoCarta);
            jugador2.setDescarteY(0);
            jugador2.setDescarteYfin(jugador2.getDeckY() + altoCarta);
            jugador2.setManoX(jugador1.getDeckX() + (int) (1.2 * anchoCarta));
            jugador2.setManoXfin(jugador1.getDescarteX() - (int) (0.5 * anchoCarta));
            jugador2.setManoY(0);
            jugador2.setManoYfin(jugador2.getManoY() + altoCarta);
            jugador2.setMesaX(jugador1.getManoX());
            jugador2.setMesaXfin(jugador1.getManoXfin());
            jugador2.setMesaY(jugador2.getManoYfin() + (int) (0.2 * altoCarta));
            jugador2.setMesaYfin(jugador2.getMesaY() + altoCarta);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hilo.setFuncionando(false);
//        Log.i("SURFACE","SURFACE DESTROYED");
        try {
            hilo.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public Hilo getHilo() {
        return hilo;
    }

    public void setHilo(Hilo hilo) {
        this.hilo = hilo;
    }
}

