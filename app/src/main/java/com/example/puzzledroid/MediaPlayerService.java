package com.example.puzzledroid;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Locale;

/*
* Clase con la que creamos un servicio para reproducir música y que implementa varias interfaces para
* gestionar eventos mientras esta sonando
* */


public class MediaPlayerService extends Service implements  MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener, AudioManager.OnAudioFocusChangeListener
{

    // Atributos globales de la clase

    private MediaPlayer mediaPlayer;
    private String mediafile; //dirección en la que está almacenado el archivo media

    //Binder que se da a los clientes de audio
    private final IBinder iBinder=new LocalBinder();

    //Método para inicializar mediaPlayer

    private void initMediaPlayer(){

        mediaPlayer=new MediaPlayer();

        //Preparar los listeners de eventos MediaPlayer

        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnInfoListener(this);

        // Resetear para que el mediaplayer no apunte a otra fuente de datos

        mediaPlayer.reset();

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try{
            mediaPlayer.setDataSource(String.valueOf(R.raw.sunwall));

        }catch (IOException e){
            e.printStackTrace();
            stopSelf();
        }
        mediaPlayer.prepareAsync();
    }

    // Metodo para reproducir Play

    private void playMedia(){

        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }// End playMedia

    // Método para parar la reproducción Stop

    private void stopMedia(){
        if (mediaPlayer==null) return;
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onAudioFocusChange(int focusChange) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

        // Se invoca cuando la reproducción finaliza
        stopMedia();
        // Parar servicio
        stopSelf();
    } // End onCompletion


    //Metodo para gestionar los errores
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {

        // Se invoca cuando hubo un error durante una operación asincrona
        switch (what){
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Log.d("MediaPlayer Error", "MEDIA ERROR NOT VALID FOR PROGRESSIVE PLAYBACK " + extra);
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Log.d("MediaPlayer Error", "MEDIA ERROR SERVER DIED " + extra);
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Log.d("MediaPlayer Error", "MEDIA ERROR UNKNOWN " + extra);
                break;
        }
        return false;
    }//End onError


    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        // Invocado cuando el audio está listo para su reproducción
        playMedia();
    } //End onPrepared

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    //

    public class LocalBinder extends Binder{

        public MediaPlayerService getService() {
            return MediaPlayerService.this;
        }
    }


}//End MediaPlayerService
