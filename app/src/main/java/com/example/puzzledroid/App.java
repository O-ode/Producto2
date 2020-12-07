package com.example.puzzledroid;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


/*Clase envoltorio para el comportamiento general de la aplicacion.
* Si queremos hacer un setup p stop de la aplicación y no en una actividad particular
* lo hacemos en esta clase
*
* */
public class App extends Application {

    public static final String CHANNEL_RECORDS_ID="channelRecords";


    @Override
    public void onCreate() {
        super.onCreate();

        /*Hacemos la configuración de nuestro canales de notificación*/
        /*Método para crear el canal de notificación*/
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel recordChannel=new NotificationChannel(
                    CHANNEL_RECORDS_ID,"Canal de Records",
                    NotificationManager.IMPORTANCE_HIGH
            );
            recordChannel.setDescription("Notificación de record");
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(recordChannel);
        }
    }
}
