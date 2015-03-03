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
    private boolean seleccionada=false;
    private String nombre;
    private int id;
    private int coste;
    private Jugador owner;
    private Jugador enemigo;
    private int cantidad;
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
    private boolean OnStartTurnTable=false;
    private boolean OnStartTurnHand=false;
    private boolean OnStartTurnDiscard=false;
    private boolean OnStartTurnDeck=false;
    private boolean OnEndTurnTable=false;
    private boolean OnEndTurnHand=false;
    private boolean OnEndTurnDiscard=false;
    private boolean OnEndTurnDeck=false;

    public Carta(Context context,Jugador owner,Jugador enemigo,String nombre,int imagen,int coste,int[]valoresEnemigo,int[]valoresOwner, boolean[]valoresPlay){
        this.nombre=nombre;
        this.id= UUID.randomUUID().hashCode();
        Log.i("UUID","HASCODE:"+this.getId());
        this.coste=coste;
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
        this.imagen=imagen;
        this.imagenBack=R.drawable.cardbackprueba;
        this.imagenAnimacion= R.drawable.circulo;
        this.cantidad=0;
        this.dañoEnemigo=valoresEnemigo[0];
        this.curaEnemigo=valoresEnemigo[1];
        this.cartasEnemigo=valoresEnemigo[2];
        this.descarteEnemigo=valoresEnemigo[3];
        this.recursosEnemigo=valoresEnemigo[4];
        this.moverMesaAManoEnemigo=valoresEnemigo[5];
        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
        this.moverDeckAManoEnemigo=valoresEnemigo[7];
        this.moverMesaADeckEnemigo=valoresEnemigo[8];
        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
        this.moverManoADeckEnemigo=valoresEnemigo[10];
        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
        this.moverManoADescarteEnemigo=valoresEnemigo[12];
        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
        this.moverManoAMesaEnemigo=valoresEnemigo[15];
        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
        //
        this.dañoOwner=valoresOwner[0];
        this.curaOwner=valoresOwner[1];
        this.cartasOwner=valoresOwner[2];
        this.descarteOwner=valoresOwner[3];
        this.recursosOwner=valoresOwner[4];
        this.moverMesaAManoOwner=valoresOwner[5];
        this.moverDescarteAManoOwner=valoresOwner[6];
        this.moverDeckAManoOwner=valoresOwner[7];
        this.moverMesaADeckOwner=valoresOwner[8];
        this.moverDescarteADeckOwner=valoresOwner[9];
        this.moverManoADeckOwner=valoresOwner[10];
        this.moverMesaADescarteOwner=valoresOwner[11];
        this.moverManoADescarteOwner=valoresOwner[12];
        this.moverDeckADescarteOwner=valoresOwner[13];
        this.moverDescarteAMesaOwner=valoresOwner[14];
        this.moverManoAMesaOwner=valoresOwner[15];
        this.moverDeckAMesaOwner=valoresOwner[16];
        //
        this.OnMoveMesaADescarte=valoresPlay[0];
        this.OnMoveMesaADeck=valoresPlay[1];
        this.OnMoveMesaAMano=valoresPlay[2];
        this.OnMoveDescarteAMesa=valoresPlay[3];
        this.OnMoveDescarteADeck=valoresPlay[4];
        this.OnMoveDescarteAMano=valoresPlay[5];
        this.OnMoveDeckADescarte=valoresPlay[6];
        this.OnMoveDeckAMesa=valoresPlay[7];
        this.OnMoveDeckAMano=valoresPlay[8];
        this.OnMoveManoADescarte=valoresPlay[9];
        this.OnMoveManoAMesa=valoresPlay[10];
        this.OnMoveManoADeck=valoresPlay[11];
        this.OnStartTurnTable=valoresPlay[12];
        this.OnStartTurnHand=valoresPlay[13];
        this.OnStartTurnDiscard=valoresPlay[14];
        this.OnStartTurnDeck=valoresPlay[15];
        this.OnEndTurnTable=valoresPlay[16];
        this.OnEndTurnHand=valoresPlay[17];
        this.OnEndTurnDiscard=valoresPlay[18];
        this.OnEndTurnDeck=valoresPlay[19];
    }

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
        this.cantidad=0;
        this.contexto=context;
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
        this.cantidad=0;
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
        this.cantidad=0;
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
        if(isOnStartTurnTable()){
            setAnimar(true);
        }
    }
    public void startOfTurnHand(){
        if(isOnStartTurnHand()){
            setAnimar(true);
        }
    }
    public void startOfTurnDiscard(){
        if(isOnStartTurnDiscard()){
            setAnimar(true);
        }
    }
    public void startOfTurnDeck(){
        if(isOnStartTurnDeck()){
            setAnimar(true);
        }
    }
    public void endOfTurnTable(){
        if(isOnEndTurnTable()){
            setAnimar(true);
        }
    }
    public void endOfTurnHand(){
        if(isOnEndTurnHand()){
            setAnimar(true);
        }
    }
    public void endOfTurnDiscard(){
        if(isOnEndTurnDiscard()){
            setAnimar(true);
        }
    }
    public void endOfTurnDeck(){
        if(isOnEndTurnDeck()){
            setAnimar(true);
        }
    }

    public void movedFromDeckToHand(){
        if(isOnMoveDeckAMano()){
            setAnimar(true);
        }
    }

    public void movedFromDeckToTable(){
        if(isOnMoveDeckAMesa()){
            setAnimar(true);
        }}

    public void movedFromDeckToDiscard(){
        if(isOnMoveDeckADescarte()){
            setAnimar(true);
        }}

    public void movedFromHandToDiscard(){
        if(isOnMoveManoADescarte()){
            setAnimar(true);
        }}

    public void movedFromHandToDeck(){
        if(isOnMoveManoADeck()){
            setAnimar(true);
        }}

    public void movedFromHandToTable(){
        if(isOnMoveManoAMesa()){
            setAnimar(true);
        }}

    public void movedFromTableToDiscard(){
        if(isOnMoveMesaADescarte()){
            setAnimar(true);
        }}

    public void movedFromTableToDeck(){
        if(isOnMoveMesaADeck()){
            setAnimar(true);
        }}

    public void movedFromTableToHand(){
        if(isOnMoveMesaAMano()){
            setAnimar(true);
        }}

    public void movedFromDiscardToTable(){
        if(isOnMoveDescarteAMesa()){
            setAnimar(true);
        }}

    public void movedFromDiscardToDeck(){
        if(isOnMoveDescarteADeck()){
            setAnimar(true);
        }}

    public void movedFromDiscardToHand(){
        if(isOnMoveDescarteAMano()){
            setAnimar(true);
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    public boolean isOnStartTurnTable() {
        return OnStartTurnTable;
    }

    public void setOnStartTurnTable(boolean onStartTurnTable) {
        OnStartTurnTable = onStartTurnTable;
    }

    public boolean isOnStartTurnHand() {
        return OnStartTurnHand;
    }

    public void setOnStartTurnHand(boolean onStartTurnHand) {
        OnStartTurnHand = onStartTurnHand;
    }

    public boolean isOnStartTurnDiscard() {
        return OnStartTurnDiscard;
    }

    public void setOnStartTurnDiscard(boolean onStartTurnDiscard) {
        OnStartTurnDiscard = onStartTurnDiscard;
    }

    public boolean isOnStartTurnDeck() {
        return OnStartTurnDeck;
    }

    public void setOnStartTurnDeck(boolean onStartTurnDeck) {
        OnStartTurnDeck = onStartTurnDeck;
    }

    public boolean isOnEndTurnTable() {
        return OnEndTurnTable;
    }

    public void setOnEndTurnTable(boolean onEndTurnTable) {
        OnEndTurnTable = onEndTurnTable;
    }

    public boolean isOnEndTurnHand() {
        return OnEndTurnHand;
    }

    public void setOnEndTurnHand(boolean onEndTurnHand) {
        OnEndTurnHand = onEndTurnHand;
    }

    public boolean isOnEndTurnDiscard() {
        return OnEndTurnDiscard;
    }

    public void setOnEndTurnDiscard(boolean onEndTurnDiscard) {
        OnEndTurnDiscard = onEndTurnDiscard;
    }

    public boolean isOnEndTurnDeck() {
        return OnEndTurnDeck;
    }

    public void setOnEndTurnDeck(boolean onEndTurnDeck) {
        OnEndTurnDeck = onEndTurnDeck;
    }
}
