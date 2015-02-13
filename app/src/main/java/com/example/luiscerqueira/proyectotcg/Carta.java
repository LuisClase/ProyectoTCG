package com.example.luiscerqueira.proyectotcg;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.util.Log;

        import java.util.UUID;

/**
 * Created by Luis Cerqueira on 22/01/2015.
 */
public class Carta {
    private String nombre;
    private int id;
    private int coste;
    private Jugador owner;
    private Jugador enemigo;
    private Bitmap imagen;
    private Bitmap imagenBack;
    private int xInicio;
    private int yInicio;
    private int xFin;
    private int yFin;
    private int grados;
    private boolean animar;
    private Bitmap imagenAnimacion;
    private Context contexto;

    public Carta(Context context,Jugador owner,Jugador enemigo,Bitmap imagen,Bitmap imagenBack,Bitmap imagenAnimacion){
        this.nombre="Prueba";
        this.id=id;
        this.coste=0;
        this.xInicio=0;
        this.yInicio=0;
        this.xFin=0;
        this.yFin=0;
        this.grados=0;
        this.animar=false;
        this.owner=owner;
        this.enemigo=enemigo;
        this.imagen=imagen;
        this.imagenBack=imagenBack;
        this.imagenAnimacion=imagenAnimacion;
    }

    public Carta(Context context,Jugador owner,Jugador enemigo){
        this.nombre="Prueba";
        this.id= UUID.randomUUID().hashCode();
        Log.i("UUID","HASCODE:"+this.getId());
        this.coste=0;
        this.xInicio=0;
        this.yInicio=0;
        this.xFin=0;
        this.yFin=0;
        this.grados=0;
        this.animar=false;
        this.owner=owner;
        this.contexto=context;
        Log.i("OWNER","VIDAS:"+getOwner().getVidas());
        this.enemigo=enemigo;
        Bitmap temp=BitmapFactory.decodeResource(context.getResources(), R.drawable.frontcard);
        this.imagen=temp;
        temp=BitmapFactory.decodeResource(context.getResources(), R.drawable.cardbackprueba);
        this.imagenBack=temp;
        temp=BitmapFactory.decodeResource(context.getResources(), R.drawable.circle4a);
        this.imagenAnimacion=temp;

    }

    public void animacion() {
        Thread hilo=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("HILO","SLEEP");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setAnimar(false);
                Log.i("HILO","GRADOS 0");
                setGrados(0);
                playCard();
            }
        });
        Log.i("HILO","ANTES START");
        hilo.start();
    }

    public void playCard(){

    }

    public void startOfTurnTable(){
    }
    public void startOfTurnHand(){
    }
    public void startOfTurnDiscard(){
    }
    public void startOfTurnDeck(){
    }
    public void endOfTurnTable(){
    }
    public void endOfTurnHand(){
    }
    public void endOfTurnDiscard(){
    }
    public void endOfTurnDeck(){
    }

    public void movedFromDeckToHand(){}

    public void movedFromDeckToTable(){}

    public void movedFromDeckToDiscard(){}

    public void movedFromHandToDiscard(){}

    public void movedFromHandToDeck(){}

    public void movedFromHandToTable(){}

    public void movedFromTableToDiscard(){}

    public void movedFromTableToDeck(){}

    public void movedFromTableToHand(){}

    public void movedFromDiscardToTable(){}

    public void movedFromDiscardToDeck(){}

    public void movedFromDiscardToHand(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public Jugador getOwner() {
        return owner;
    }

    public void setOwner(Jugador owner) {
        this.owner = owner;
    }

    public Jugador getEnemigo() {
        return enemigo;
    }

    public void setEnemigo(Jugador enemigo) {
        this.enemigo = enemigo;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagenBack() {
        return imagenBack;
    }

    public void setImagenBack(Bitmap imagenBack) {
        this.imagenBack = imagenBack;
    }

    public int getyFin() {
        return yFin;
    }

    public void setyFin(int yFin) {
        this.yFin = yFin;
    }

    public int getxInicio() {
        return xInicio;
    }

    public void setxInicio(int xInicio) {
        this.xInicio = xInicio;
    }

    public int getyInicio() {
        return yInicio;
    }

    public void setyInicio(int yInicio) {
        this.yInicio = yInicio;
    }

    public int getxFin() {
        return xFin;
    }

    public void setxFin(int xFin) {
        this.xFin = xFin;
    }

    public boolean isAnimar() {
        return animar;
    }

    public void setAnimar(boolean animar) {
        if(animar){
            animacion();
        }
        this.animar = animar;
    }

    public Bitmap getImagenAnimacion() {
        return imagenAnimacion;
    }

    public void setImagenAnimacion(Bitmap imagenAnimacion) {
        this.imagenAnimacion = imagenAnimacion;
    }

    public int getGrados() {
        return grados;
    }

    public void setGrados(int grados) {
        this.grados = grados;
    }


    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }
}
