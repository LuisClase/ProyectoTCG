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
import java.util.Random;

/**
 * Created by soylu_000 on 09/05/2015.
 */
public class ViewIA extends SurfaceView implements SurfaceHolder.Callback {

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
    int turnoIA=0;

    public ViewIA(Context context) {
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

        anchoCarta=anchoPantalla/6;
        altoCarta=(int)(anchoCarta*1.39);
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
        jugador1=((JuegoIaActivity)context).jugador1;
        jugador2=((JuegoIaActivity)context).jugador2;

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
        jugador1=((JuegoIaActivity)context).jugador1;
//        if(jugador1==null) {
//            Log.i("CONSTRUCTOR", "JUGADOR1:NULL");
//        }else{
////            Log.i("CONSTRUCTOR", "JUGADOR1:NO NULL");
//        }
        jugador2=((JuegoIaActivity)context).jugador2;
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
                        if(jugador1!=null && jugador2!=null && surfaceHolder!=null) {
                            cambios();
                            try {
                                while(c==null){
                                    surfaceHolder=getHolder();
                                    c=surfaceHolder.lockCanvas();
                                }
                                dibujar(c);
                                if(!jugador1.isActivo()){
                                    if(turnoIA==0){
                                        jugador2.startOfTurn();
                                    }
                                    Log.d("TURNO-IA","CONTADOR++"+turnoIA);
                                    turnoIA++;
                                    int recursos=jugador2.getRecursos();
                                    ArrayList<Carta>cartasPosibles=new ArrayList<Carta>();
                                    for(int i=0;i<jugador2.getMano().size();i++){
                                        if(jugador2.getMano().get(i).getCoste()<recursos){
                                            cartasPosibles.add(jugador2.getMano().get(i));
                                        }
                                    }
                                    if((turnoIA==60||turnoIA==300) && recursos>=0){
                                        cartasPosibles.clear();
                                        for(int i=0;i<jugador2.getMano().size();i++){
                                            if(jugador2.getMano().get(i).getCoste()<=recursos){
                                                cartasPosibles.add(jugador2.getMano().get(i));
                                            }
                                        }
                                        Log.d("TURNO-IA","CARTASPOSIBLES: "+cartasPosibles.size());
                                        if (cartasPosibles.size() > 0) {
                                            Random r=new Random();
                                            int temp=r.nextInt(cartasPosibles.size());
                                            jugador2.setRecursos(jugador2.getRecursos() - cartasPosibles.get(temp).getCoste());
                                            jugador2.moveCardFromHandToTable(cartasPosibles.get(temp).getId());
                                            turnoIA-=60;
                                            if(turnoIA<=0){
                                                turnoIA=1;
                                            }
                                        }else{
                                            turnoIA=400;
                                        }
                                        cartasPosibles.clear();
                                        for(int i=0;i<jugador2.getMano().size();i++){
                                            if(jugador2.getMano().get(i).getCoste()<recursos){
                                                cartasPosibles.add(jugador2.getMano().get(i));
                                            }
                                        }
                                        Log.d("TURNO-IA","CARTASPOSIBLES--2: "+cartasPosibles.size());
                                        if(cartasPosibles.size()>0){
                                            turnoIA=0;
                                        }
                                    }
                                    if(turnoIA>=400){
                                        jugador2.endOfTurn();
                                        Log.d("TURNO-IA","CAMBIO TURNO");
                                        jugador1.setActivo(true);
                                        jugador2.setActivo(false);
                                        jugador1.startOfTurn();
                                        turnoIA=0;
                                    }
                                }
                            }catch (IndexOutOfBoundsException e){
                                e.printStackTrace();
                                cambios();
                            }
                        }else{
                            surfaceHolder=getHolder();
                            jugador1=((JuegoIaActivity)context).jugador1;
                            jugador2=((JuegoIaActivity)context).jugador2;
                            inicializarCosas((JuegoIaActivity)context);
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
            if (contManoJ1 != jugador1.getMano().size()) {
                if(arrayManoJ1.size()!=0) {
                    arrayManoJ1.clear();
                }
                for (int i = 0; i < jugador1.getMano().size(); i++) {
                    if(!jugador1.getMano().get(i).isCreada()) {
                        arrayManoJ1.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), jugador1.getMano().get(i).getImagen()), anchoCarta, altoCarta, true));
                    }else{
                        arrayManoJ1.add(Bitmap.createScaledBitmap(jugador1.getMano().get(i).getImagenCreada(), anchoCarta, altoCarta, true));
                    }
                }
                contManoJ1=jugador1.getMano().size();
            }
            if (contManoJ2 != jugador2.getMano().size()) {
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
            canvas.drawBitmap(fondo,0,0,null);
//            canvas.drawBitmap(avatar,0,altoPantalla-(altoPantalla/5),null);
            Paint p=new Paint();
            if(jugador1.getVidas()<=0){
                p.setColor(Color.RED);
                p.setTextSize(60);
                canvas.drawText("JUGADOR 1 PIERDE" ,anchoPantalla/4,altoPantalla/2, p);
            }
            else if(jugador2.getVidas()<=0){
                p.setColor(Color.RED);
                p.setTextSize(60);
                canvas.drawText("IA PIERDE", anchoPantalla / 4, altoPantalla / 2, p);
                final SharedPreferences.Editor editorJugador = ((JuegoIaActivity)getContext()).preferenciasJugador.edit();
                editorJugador.putInt("DINERO", ((JuegoIaActivity)getContext()).preferenciasJugador.getInt("DINERO",0)+50);
                editorJugador.apply();
            }else {
                p.setColor(Color.RED);
                p.setTextSize(30);
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
                temp = botonPlay;
                if (pulsandoPlay) {
                    temp = botonPlayPulsado;
                }
                temp = Bitmap.createScaledBitmap(temp, (int) (1.5 * anchoCarta), (int) (altoCarta * 0.4), true);
                canvas.drawBitmap(temp, 0, (int) ((altoPantalla / 2) - (altoCarta * 1.2)), null);

                //Boton fin turno
                temp = botonActivo;
                if (jugador1.isTocandoFinTurno()) {
                    temp = botonPresionado;
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

                    //Zona-DECK
                    if (jugador2.getDeck().size() > 0) {
                        temp = topDeck;
                        canvas.drawBitmap(temp, jugador2.getDeckX(), jugador2.getDeckY(), null);
                        for (int i = 0; i < jugador2.getDeck().size(); i++) {
                            jugador2.getDeck().get(i).setxInicio(xDeck);
                            jugador2.getDeck().get(i).setxFin(xDeck + anchoCarta);
                            jugador2.getDeck().get(i).setyInicio(yDeck);
                            jugador2.getDeck().get(i).setyFin(yDeck + altoCarta);
                        }
                    } else {
                        Log.i("ONDRAW2", "DECK VACIO");
                    }
                    //MANO
                    for (int i = 0; i < jugador2.getMano().size(); i++) {
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
                        if (i < jugador2.getMano().size() - 1) {
                            jugador2.getMano().get(i).setxFin(jugador2.getMano().get(i).getxInicio() + (int) (distanciaMinima * anchoCarta) * (i+1));
                        } else {
                            jugador2.getMano().get(i).setxFin(jugador2.getMano().get(i).getxInicio() + anchoCarta);
                        }
                        if (jugador2.isActivo()) {
                            temp = girarBitmap(temp, 180);
                        }
                        canvas.drawBitmap(temp, jugador2.getManoX() + (int) (distanciaMinima * anchoCarta) * i, jugador2.getMano().get(i).getyInicio(), null);
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

                    //MESA
                    int xTemp = jugador2.getMesaX();
                    int yTemp = jugador2.getMesaYfin();
                    for (int i = 0; i < jugador2.getMesa().size(); i++) {
                        temp = arrayMesaJ2.get(i);
                        jugador2.getMesa().get(i).setxInicio(xTemp + (int) (0.5 * anchoCarta) * i);
                        jugador2.getMesa().get(i).setyInicio(jugador2.getMesaY());
                        jugador2.getMesa().get(i).setyFin(jugador2.getMesaYfin());
                        if (i < jugador2.getMesa().size() - 1) {
                            jugador2.getMesa().get(i).setxFin(jugador2.getMesa().get(i).getxInicio() + (anchoCarta / 2));
                        } else {
                            jugador2.getMesa().get(i).setxFin(jugador2.getMesa().get(i).getxInicio() + anchoCarta);
                      }
                        if (jugador2.isActivo()) {
                            temp = girarBitmap(temp, 180);
                        }
                        canvas.drawBitmap(temp, jugador2.getMesa().get(i).getxInicio(), jugador2.getMesa().get(i).getyInicio(), null);
//
                    }

                } else {
                    Log.i("ONDRAW2", "Jugador null");
                }

                //Jugador1
                if (jugador1 != null) {
                    //Zona-Mano

                    //Zona-Mesa

                    //Zona-Descarte

                    //Zona-DECK
                    if (jugador1.getDeck().size() > 0) {
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
                    } else {
                        x += anchoCarta * 1.2;
                    }

                    //MANO
                    for (int i = 0; i < jugador1.getMano().size(); i++) {
                        if (jugador1.isActivo()) {
                            temp = arrayManoJ1.get(i);
                        } else {
                            temp = topDeck;
                        }
                        if (jugador2.isActivo()) {
                            temp = girarBitmap(temp, 180);
                        }
                        double distanciaMinima=0;
                        if(jugador1.getMano().size()>0) {
                            distanciaMinima = ((jugador1.getManoXfin()-jugador1.getManoX()) / (double)jugador1.getMano().size())/anchoCarta;
                        }
                        canvas.drawBitmap(temp, jugador1.getManoX() + (int) (distanciaMinima * anchoCarta) * i, jugador1.getManoY(), null);
                       jugador1.getMano().get(i).setxInicio(jugador1.getManoX() + (int) (distanciaMinima * anchoCarta) * i);
                       jugador1.getMano().get(i).setyInicio(jugador1.getManoY());
                        jugador1.getMano().get(i).setyFin(jugador1.getManoYfin());
                        if (i < jugador1.getMano().size() - 1) {
                            jugador1.getMano().get(i).setxFin(jugador1.getMano().get(i).getxInicio() + (int) (distanciaMinima * anchoCarta) * (i+1));
                       } else {
                            jugador1.getMano().get(i).setxFin(jugador1.getMano().get(i).getxInicio() + anchoCarta);
                        }
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

                    //MESA
                    int xTemp = jugador1.getMesaX();
                    int yTemp = jugador1.getMesaYfin();
                    for (int i = 0; i < jugador1.getMesa().size(); i++) {
                        temp = arrayMesaJ1.get(i);
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
                      xTemp += anchoCarta / 2;
                    }
                } else {
                    Log.i("ONDRAW", "Jugador null");
                }
                //ANIMACIONES

                for (int i = 0; i < jugador2.getMesa().size(); i++) {
                    if (jugador2.getMesa().get(i).isAnimar()) {
                        animacion=arrayefectoGiro.get(jugador2.getMesa().get(i).getGrados());
                        jugador2.getMesa().get(i).setGrados(jugador2.getMesa().get(i).getGrados() + 1);
                        canvas.drawBitmap(animacion, jugador2.getMesa().get(i).getxInicio(), jugador2.getMesa().get(i).getyInicio(), null);
                    }
                }
                for (int i = 0; i < jugador1.getMesa().size(); i++) {
                    if (jugador1.getMesa().get(i).isAnimar()) {
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
                                Log.i("MULTITOUCH-MESA", "IF-MANO " + i + " x//" + jugador1.getMano().get(i).getxInicio());
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
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
//                Log.i("MULTITOUCH","action pointer down");
                break;
            case MotionEvent.ACTION_UP:
//                Log.i("MULTITOUCH","action up");
            case MotionEvent.ACTION_POINTER_UP:
//                Log.i("MULTITOUCH","action pointer up");
                if(jugador1.getVidas()<=0 || jugador2.getVidas()<=0){
                    JuegoIaActivity host=(JuegoIaActivity)this.getContext();
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
//                        Log.i("MULTITOUCH-DECK", "IF");
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
        matrix= new Matrix();
        float width=0;
        float height=0;
        for(int i=0;i<jugador2.getMesa().size();i++){
            if(jugador2.getMesa().get(i).getId()==idAnimacion){
                width=BitmapFactory.decodeResource(context.getResources(), jugador2.getMesa().get(i).getImagen()).getWidth();
                height=BitmapFactory.decodeResource(context.getResources(), jugador2.getMesa().get(i).getImagen()).getHeight();
            }
        }
        float px = width/2;
        float py = height/2;
        matrix.postTranslate(-bitmap.getWidth()/2, -bitmap.getHeight()/2);
        matrix.postRotate(angulo);
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
            inicializarCosas((JuegoIaActivity)context);
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
            inicializarCosas((JuegoIaActivity)context);
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

