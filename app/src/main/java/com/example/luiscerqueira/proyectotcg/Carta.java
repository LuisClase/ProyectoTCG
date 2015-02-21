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
    private int imagen;
    private int imagenBack;
    private int xInicio;
    private int yInicio;
    private int xFin;
    private int yFin;
    private int grados;
    private boolean animar;
    private int imagenAnimacion;
    private Context contexto;
    //enemigo
    private int dañoEnemigo;
    private int curaEnemigo;
    private int cartasEnemigo;
    private int descarteEnemigo;
    private int recursosEnemigo;
    private int moverMesaAManoEnemigo;
    private int moverDescarteAManoEnemigo;
    private int moverDeckAManoEnemigo;
    private int moverMesaADeckEnemigo;
    private int moverDescarteADeckEnemigo;
    private int moverManoADeckEnemigo;
    private int moverMesaADescarteEnemigo;
    private int moverManoADescarteEnemigo;
    private int moverDeckADescarteEnemigo;
    private int moverDescarteAMesaEnemigo;
    private int moverManoAMesaEnemigo;
    private int moverDeckAMesaEnemigo;
    //owner
    private int dañoOwner=0;
    private int curaOwner=0;
    private int cartasOwner=0;
    private int descarteOwner=0;
    private int recursosOwner=0;
    private int moverMesaAManoOwner=0;
    private int moverDescarteAManoOwner=0;
    private int moverDeckAManoOwner=0;
    private int moverMesaADeckOwner=0;
    private int moverDescarteADeckOwner=0;
    private int moverManoADeckOwner=0;
    private int moverMesaADescarteOwner=0;
    private int moverManoADescarteOwner=0;
    private int moverDeckADescarteOwner=0;
    private int moverDescarteAMesaOwner=0;
    private int moverManoAMesaOwner=0;
    private int moverDeckAMesaOwner=0;
    //eventos
    private boolean OnMoveMesaADescarte=false;
    private boolean OnMoveMesaADeck=false;
    private boolean OnMoveMesaAMano=false;
    private boolean OnMoveDescarteAMesa=false;
    private boolean OnMoveDescarteADeck=false;
    private boolean OnMoveDescarteAMano=false;
    private boolean OnMoveDeckADescarte=false;
    private boolean OnMoveDeckAMesa=false;
    private boolean OnMoveDeckAMano=false;
    private boolean OnMoveManoADescarte=false;
    private boolean OnMoveManoAMesa=false;
    private boolean OnMoveManoADeck=false;

    public Carta(Context context,Jugador owner,Jugador enemigo,int imagen,int imagenBack,int imagenAnimacion){
        this.nombre="Saber";
        this.id= UUID.randomUUID().hashCode();
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

    public Carta(Context context,Jugador owner,Jugador enemigo,String nombre,int imagen){
        this.nombre=nombre;
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
        this.imagen=imagen;
        this.imagenBack=R.drawable.cardbackprueba;
        this.imagenAnimacion= R.drawable.circulo;
    }

    public Carta(Context context,Jugador owner,Jugador enemigo){
        this.nombre="Saber";
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
//        Log.i("OWNER","VIDAS:"+getOwner().getVidas());
        this.enemigo=enemigo;
        this.imagen=R.drawable.frontcard;
        this.imagenBack=R.drawable.cardbackprueba;
        this.imagenAnimacion= R.drawable.circulo;

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
                synchronized (((JuegoActivity)getContexto()).pantallaJuego.getSurfaceHolder()) {
                    playCard();
                }
            }
        });
        Log.i("HILO","ANTES START");
        hilo.start();
    }

    public void playCard(){
        if(dañoEnemigo>0){
            enemigo.setVidas(enemigo.getVidas()-dañoEnemigo);
        }
        if(descarteEnemigo>0){
            enemigo.moveFromHandToDiscard(descarteEnemigo);
        }
        if(curaEnemigo>0){
            enemigo.setVidas(enemigo.getVidas()+curaEnemigo);
        }
        if(recursosEnemigo!=0){
            enemigo.setRecursos(enemigo.getRecursos()+recursosEnemigo);
        }
        if(dañoOwner>0){
            owner.setVidas(owner.getVidas()-dañoOwner);
        }
        if(descarteOwner>0){
            owner.moveFromHandToDiscard(descarteOwner);
        }
        if(curaOwner>0){
            owner.setVidas(owner.getVidas()+curaOwner);
        }
        if(recursosOwner!=0){
            owner.setRecursos(owner.getRecursos()+recursosOwner);
        }
        //owner.setRecursos(owner.getRecursos()-getCoste());
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

    public void movedFromDeckToHand(){
        if(isOnMoveDeckAMano()){
            playCard();
        }
    }

    public void movedFromDeckToTable(){
        if(isOnMoveDeckAMesa()){
            playCard();
        }}

    public void movedFromDeckToDiscard(){
        if(isOnMoveDeckADescarte()){
            playCard();
        }}

    public void movedFromHandToDiscard(){
        if(isOnMoveManoADescarte()){
            playCard();
        }}

    public void movedFromHandToDeck(){
        if(isOnMoveManoADeck()){
            playCard();
        }}

    public void movedFromHandToTable(){
        if(isOnMoveManoAMesa()){
            playCard();
        }}

    public void movedFromTableToDiscard(){
        if(isOnMoveMesaADescarte()){
            playCard();
        }}

    public void movedFromTableToDeck(){
        if(isOnMoveMesaADeck()){
            playCard();
        }}

    public void movedFromTableToHand(){
        if(isOnMoveMesaAMano()){
            playCard();
        }}

    public void movedFromDiscardToTable(){
        if(isOnMoveDescarteAMesa()){
            playCard();
        }}

    public void movedFromDiscardToDeck(){
        if(isOnMoveDescarteADeck()){
            playCard();
        }}

    public void movedFromDiscardToHand(){
        if(isOnMoveDescarteAMano()){
            playCard();
        }}

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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getImagenBack() {
        return imagenBack;
    }

    public void setImagenBack(int imagenBack) {
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

    public int getImagenAnimacion() {
        return imagenAnimacion;
    }

    public void setImagenAnimacion(int imagenAnimacion) {
        this.imagenAnimacion = imagenAnimacion;
    }

    public int getGrados() {
        return grados;
    }

    public void setGrados(int grados) {
        if(grados>=18){
            grados=0;
        }
        this.grados = grados;
    }


    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }


    public int getDañoEnemigo() {
        return dañoEnemigo;
    }

    public void setDañoEnemigo(int dañoEnemigo) {
        this.dañoEnemigo = dañoEnemigo;
    }

    public int getCuraEnemigo() {
        return curaEnemigo;
    }

    public void setCuraEnemigo(int curaEnemigo) {
        this.curaEnemigo = curaEnemigo;
    }

    public int getCartasEnemigo() {
        return cartasEnemigo;
    }

    public void setCartasEnemigo(int cartasEnemigo) {
        this.cartasEnemigo = cartasEnemigo;
    }

    public int getDescarteEnemigo() {
        return descarteEnemigo;
    }

    public void setDescarteEnemigo(int descarteEnemigo) {
        this.descarteEnemigo = descarteEnemigo;
    }

    public int getRecursosEnemigo() {
        return recursosEnemigo;
    }

    public void setRecursosEnemigo(int recursosEnemigo) {
        this.recursosEnemigo = recursosEnemigo;
    }

    public int getMoverMesaAManoEnemigo() {
        return moverMesaAManoEnemigo;
    }

    public void setMoverMesaAManoEnemigo(int moverMesaAManoEnemigo) {
        this.moverMesaAManoEnemigo = moverMesaAManoEnemigo;
    }

    public int getMoverDescarteAManoEnemigo() {
        return moverDescarteAManoEnemigo;
    }

    public void setMoverDescarteAManoEnemigo(int moverDescarteAManoEnemigo) {
        this.moverDescarteAManoEnemigo = moverDescarteAManoEnemigo;
    }

    public int getMoverDeckAManoEnemigo() {
        return moverDeckAManoEnemigo;
    }

    public void setMoverDeckAManoEnemigo(int moverDeckAManoEnemigo) {
        this.moverDeckAManoEnemigo = moverDeckAManoEnemigo;
    }

    public int getMoverMesaADeckEnemigo() {
        return moverMesaADeckEnemigo;
    }

    public void setMoverMesaADeckEnemigo(int moverMesaADeckEnemigo) {
        this.moverMesaADeckEnemigo = moverMesaADeckEnemigo;
    }

    public int getMoverDescarteADeckEnemigo() {
        return moverDescarteADeckEnemigo;
    }

    public void setMoverDescarteADeckEnemigo(int moverDescarteADeckEnemigo) {
        this.moverDescarteADeckEnemigo = moverDescarteADeckEnemigo;
    }

    public int getMoverManoADeckEnemigo() {
        return moverManoADeckEnemigo;
    }

    public void setMoverManoADeckEnemigo(int moverManoADeckEnemigo) {
        this.moverManoADeckEnemigo = moverManoADeckEnemigo;
    }

    public int getMoverMesaADescarteEnemigo() {
        return moverMesaADescarteEnemigo;
    }

    public void setMoverMesaADescarteEnemigo(int moverMesaADescarteEnemigo) {
        this.moverMesaADescarteEnemigo = moverMesaADescarteEnemigo;
    }

    public int getMoverManoADescarteEnemigo() {
        return moverManoADescarteEnemigo;
    }

    public void setMoverManoADescarteEnemigo(int moverManoADescarteEnemigo) {
        this.moverManoADescarteEnemigo = moverManoADescarteEnemigo;
    }

    public int getMoverDeckADescarteEnemigo() {
        return moverDeckADescarteEnemigo;
    }

    public void setMoverDeckADescarteEnemigo(int moverDeckADescarteEnemigo) {
        this.moverDeckADescarteEnemigo = moverDeckADescarteEnemigo;
    }

    public int getMoverDescarteAMesaEnemigo() {
        return moverDescarteAMesaEnemigo;
    }

    public void setMoverDescarteAMesaEnemigo(int moverDescarteAMesaEnemigo) {
        this.moverDescarteAMesaEnemigo = moverDescarteAMesaEnemigo;
    }

    public int getMoverManoAMesaEnemigo() {
        return moverManoAMesaEnemigo;
    }

    public void setMoverManoAMesaEnemigo(int moverManoAMesaEnemigo) {
        this.moverManoAMesaEnemigo = moverManoAMesaEnemigo;
    }

    public int getMoverDeckAMesaEnemigo() {
        return moverDeckAMesaEnemigo;
    }

    public void setMoverDeckAMesaEnemigo(int moverDeckAMesaEnemigo) {
        this.moverDeckAMesaEnemigo = moverDeckAMesaEnemigo;
    }

    public int getDañoOwner() {
        return dañoOwner;
    }

    public void setDañoOwner(int dañoOwner) {
        this.dañoOwner = dañoOwner;
    }

    public int getCuraOwner() {
        return curaOwner;
    }

    public void setCuraOwner(int curaOwner) {
        this.curaOwner = curaOwner;
    }

    public int getCartasOwner() {
        return cartasOwner;
    }

    public void setCartasOwner(int cartasOwner) {
        this.cartasOwner = cartasOwner;
    }

    public int getDescarteOwner() {
        return descarteOwner;
    }

    public void setDescarteOwner(int descarteOwner) {
        this.descarteOwner = descarteOwner;
    }

    public int getRecursosOwner() {
        return recursosOwner;
    }

    public void setRecursosOwner(int recursosOwner) {
        this.recursosOwner = recursosOwner;
    }

    public int getMoverMesaAManoOwner() {
        return moverMesaAManoOwner;
    }

    public void setMoverMesaAManoOwner(int moverMesaAManoOwner) {
        this.moverMesaAManoOwner = moverMesaAManoOwner;
    }

    public int getMoverDescarteAManoOwner() {
        return moverDescarteAManoOwner;
    }

    public void setMoverDescarteAManoOwner(int moverDescarteAManoOwner) {
        this.moverDescarteAManoOwner = moverDescarteAManoOwner;
    }

    public int getMoverDeckAManoOwner() {
        return moverDeckAManoOwner;
    }

    public void setMoverDeckAManoOwner(int moverDeckAManoOwner) {
        this.moverDeckAManoOwner = moverDeckAManoOwner;
    }

    public int getMoverMesaADeckOwner() {
        return moverMesaADeckOwner;
    }

    public void setMoverMesaADeckOwner(int moverMesaADeckOwner) {
        this.moverMesaADeckOwner = moverMesaADeckOwner;
    }

    public int getMoverDescarteADeckOwner() {
        return moverDescarteADeckOwner;
    }

    public void setMoverDescarteADeckOwner(int moverDescarteADeckOwner) {
        this.moverDescarteADeckOwner = moverDescarteADeckOwner;
    }

    public int getMoverManoADeckOwner() {
        return moverManoADeckOwner;
    }

    public void setMoverManoADeckOwner(int moverManoADeckOwner) {
        this.moverManoADeckOwner = moverManoADeckOwner;
    }

    public int getMoverMesaADescarteOwner() {
        return moverMesaADescarteOwner;
    }

    public void setMoverMesaADescarteOwner(int moverMesaADescarteOwner) {
        this.moverMesaADescarteOwner = moverMesaADescarteOwner;
    }

    public int getMoverManoADescarteOwner() {
        return moverManoADescarteOwner;
    }

    public void setMoverManoADescarteOwner(int moverManoADescarteOwner) {
        this.moverManoADescarteOwner = moverManoADescarteOwner;
    }

    public int getMoverDeckADescarteOwner() {
        return moverDeckADescarteOwner;
    }

    public void setMoverDeckADescarteOwner(int moverDeckADescarteOwner) {
        this.moverDeckADescarteOwner = moverDeckADescarteOwner;
    }

    public int getMoverDescarteAMesaOwner() {
        return moverDescarteAMesaOwner;
    }

    public void setMoverDescarteAMesaOwner(int moverDescarteAMesaOwner) {
        this.moverDescarteAMesaOwner = moverDescarteAMesaOwner;
    }

    public int getMoverManoAMesaOwner() {
        return moverManoAMesaOwner;
    }

    public void setMoverManoAMesaOwner(int moverManoAMesaOwner) {
        this.moverManoAMesaOwner = moverManoAMesaOwner;
    }

    public int getMoverDeckAMesaOwner() {
        return moverDeckAMesaOwner;
    }

    public void setMoverDeckAMesaOwner(int moverDeckAMesaOwner) {
        this.moverDeckAMesaOwner = moverDeckAMesaOwner;
    }

    public boolean isOnMoveManoADeck() {
        return OnMoveManoADeck;
    }

    public void setOnMoveManoADeck(boolean onMoveManoADeck) {
        OnMoveManoADeck = onMoveManoADeck;
    }

    public boolean isOnMoveMesaADescarte() {
        return OnMoveMesaADescarte;
    }

    public void setOnMoveMesaADescarte(boolean onMoveMesaADescarte) {
        OnMoveMesaADescarte = onMoveMesaADescarte;
    }

    public boolean isOnMoveMesaADeck() {
        return OnMoveMesaADeck;
    }

    public void setOnMoveMesaADeck(boolean onMoveMesaADeck) {
        OnMoveMesaADeck = onMoveMesaADeck;
    }

    public boolean isOnMoveMesaAMano() {
        return OnMoveMesaAMano;
    }

    public void setOnMoveMesaAMano(boolean onMoveMesaAMano) {
        OnMoveMesaAMano = onMoveMesaAMano;
    }

    public boolean isOnMoveDescarteAMesa() {
        return OnMoveDescarteAMesa;
    }

    public void setOnMoveDescarteAMesa(boolean onMoveDescarteAMesa) {
        OnMoveDescarteAMesa = onMoveDescarteAMesa;
    }

    public boolean isOnMoveDescarteADeck() {
        return OnMoveDescarteADeck;
    }

    public void setOnMoveDescarteADeck(boolean onMoveDescarteADeck) {
        OnMoveDescarteADeck = onMoveDescarteADeck;
    }

    public boolean isOnMoveDescarteAMano() {
        return OnMoveDescarteAMano;
    }

    public void setOnMoveDescarteAMano(boolean onMoveDescarteAMano) {
        OnMoveDescarteAMano = onMoveDescarteAMano;
    }

    public boolean isOnMoveDeckADescarte() {
        return OnMoveDeckADescarte;
    }

    public void setOnMoveDeckADescarte(boolean onMoveDeckADescarte) {
        OnMoveDeckADescarte = onMoveDeckADescarte;
    }

    public boolean isOnMoveDeckAMesa() {
        return OnMoveDeckAMesa;
    }

    public void setOnMoveDeckAMesa(boolean onMoveDeckAMesa) {
        OnMoveDeckAMesa = onMoveDeckAMesa;
    }

    public boolean isOnMoveDeckAMano() {
        return OnMoveDeckAMano;
    }

    public void setOnMoveDeckAMano(boolean onMoveDeckAMano) {
        OnMoveDeckAMano = onMoveDeckAMano;
    }

    public boolean isOnMoveManoADescarte() {
        return OnMoveManoADescarte;
    }

    public void setOnMoveManoADescarte(boolean onMoveManoADescarte) {
        OnMoveManoADescarte = onMoveManoADescarte;
    }

    public boolean isOnMoveManoAMesa() {
        return OnMoveManoAMesa;
    }

    public void setOnMoveManoAMesa(boolean onMoveManoAMesa) {
        OnMoveManoAMesa = onMoveManoAMesa;
    }

}
