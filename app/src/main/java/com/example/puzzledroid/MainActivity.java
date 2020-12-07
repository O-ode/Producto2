/**
 * Clase principal con la lógica para el inicio y los modos del juego
 */




package com.example.puzzledroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Método para mostrar y ocultar el menu en el action bar
     */

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.help_actionbar,menu);
        return true;}

    /**
     * Método para asignar función a menu de Ayuda
     */

    public boolean onOptionsItemSelected(MenuItem item){

        int i=item.getItemId();
        if(i==R.id.item_help){
            //funcion a ejecutar
            Intent intent=new Intent(this,WebViewActivity.class);
            startActivity(intent);
        }
            return super.onOptionsItemSelected(item);
    }

    /**
     * Método para activar pantalla puzzlelist en modo monojugador
     */

    public void activaMonojugador(View view){
        Boolean esmonojugador=true;
        Intent intent=new Intent(MainActivity.this,PuzzleList.class);
        intent.putExtra("esmonojugador",esmonojugador);
        startActivity(intent);
    }

    /**
     * Método para activar multijugador ( provisionalmente activamos el login )
     */

    public void activaMultijugador(View view){
        Intent intent=new Intent(MainActivity.this,loginActivity.class);
        startActivity(intent);
    }

    /**
     * Método para salir de la aplicación
     */

    public void cerrarAplicacion(View view){
        MainActivity.this.finish();
        System.exit(0);
    }

    /**
     * Método para activar pantalla ListarRecords y mostrar los 10 primeros en el ranking
     */

    public void mostrarListaRecords(View view){
        Intent intent=new Intent(MainActivity.this,ListarRecords.class);
        startActivity(intent);
    }

}