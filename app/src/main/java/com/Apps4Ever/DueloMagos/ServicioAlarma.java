package com.Apps4Ever.DueloMagos;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Clase para gestionar el servicio de alarma y notificaciones del dispositivo
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 18/03/2015.
 */
public class ServicioAlarma extends Service {
    private NotificationManager manager;

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        manager=(NotificationManager)this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1=new Intent(this.getApplicationContext(),MainActivity.class);

        Notification notificacion=new Notification(R.drawable.ic_launcher,"Tus cartas estan cogiendo polvo",System.currentTimeMillis());
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent=PendingIntent.getActivity(this.getApplicationContext(),0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        notificacion.flags |= Notification.FLAG_AUTO_CANCEL;
        notificacion.setLatestEventInfo(this.getApplicationContext(),"TCG Duelo de magos","Tus cartas estan cogiendo polvo",pendingIntent);

        manager.notify(0,notificacion);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
