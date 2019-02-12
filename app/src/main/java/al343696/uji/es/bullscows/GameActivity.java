package al343696.uji.es.bullscows;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;



public class GameActivity extends AppCompatActivity {

    //Context
    Context context;

    //Intent
    String colors, holes;
    boolean repeat;

    //GenerarCodigo
    int colorsInt, holesInt;
    String codigo;
    Random random = new Random();

    //DatosJugador
    TextView input = null;
    String respuestaJugador = "";

    //Toast
    Toast toast;
    CharSequence text;
    int duration = Toast.LENGTH_SHORT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        context = getApplicationContext();


        Intent intent = getIntent();
        colors = intent.getStringExtra("colors");
        holes = intent.getStringExtra("holes");
        repeat = intent.getBooleanExtra("repeat", false);



        Log.d("colors", colors);
        Log.d("holes", holes);
        Log.v("repeat", ""+repeat);

    /*TODO:
        colors = digitos diferentes, colors = 9, digitos del 0 al 9.
        holes = longitud del numero.
     */


        colorsInt = Integer.parseInt(colors);
        holesInt = Integer.parseInt(holes);
        codigo = generarCodigo(colorsInt,holesInt,repeat);

        Log.d("Codigo", codigo);

    }

    private String generarCodigo(int digitos, int longitud, boolean repeat) {

        String cadenaCodigo = "";
        int numeroActual;

        for (int i = 0; i < longitud; i++)
        {
            numeroActual = random.nextInt(digitos+1);
            Log.d("numeroActual",""+numeroActual);
            cadenaCodigo = cadenaCodigo + numeroActual;
        }

        return cadenaCodigo;
    }

    public void compruebaCodigo(View v){

        input = findViewById(R.id.numero);
        respuestaJugador = input.getText().toString();


        if (respuestaJugador.trim().length() != holesInt){
            Log.d("longError","¡Error de longitud!");

            //toast

            text = "¡Error de longitud!";
            toast = Toast.makeText(context,text,duration);

            toast.show();
        }
    }


}
