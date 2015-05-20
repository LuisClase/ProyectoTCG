package com.Apps4Ever.DueloMagos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Clase para recibir y gestionar las notificaciones
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 18/03/2015.
 */
public class Recibidor extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent servicio=new Intent(context,ServicioAlarma.class);
        context.startService(servicio);
    }
}
