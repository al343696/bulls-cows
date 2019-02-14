package al343696.uji.es.bullscows;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;



public class GameActivity extends AppCompatActivity {

    //Intent data
    String colors, holes;
    boolean repeat;

    //Generate code
    int colorsInt, holesInt;
    String codigo;
    Random random = new Random();
    ArrayList digitList = new ArrayList();

    //Player info
    TextView input = null;
    String respuestaJugador = "";
    int attempts = 0;

    //Toast
    Toast toast;
    CharSequence text;
    int duration = Toast.LENGTH_SHORT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        colors = intent.getStringExtra("colors");
        holes = intent.getStringExtra("holes");
        repeat = intent.getBooleanExtra("repeat", false);

        Log.d("colors", colors);
        Log.d("holes", holes);
        Log.v("repeat", ""+repeat);

        /*
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
        int numeroActual, i = 0;
        boolean addNumber = true;

        if(repeat)
        {
            for(int k = 0; k < longitud; k++) {
                numeroActual = random.nextInt(digitos + 1);
                Log.d("numeroActual", "" + numeroActual);
                cadenaCodigo = cadenaCodigo + numeroActual;
            }

        }
        else {
            while(i < longitud) {
                numeroActual = random.nextInt(digitos + 1);
                Log.d("numeroActual", "" + numeroActual);

                if(!digitList.isEmpty()) {
                    for (int j = 0; j < digitList.size(); j++) {

                        if(digitList.get(j).equals(numeroActual))
                        {
                            Log.d("numero","repetido");
                            addNumber = false;
                            break;
                        }
                    }
                }

                digitList.add(numeroActual);

                if(addNumber) cadenaCodigo = cadenaCodigo + numeroActual;
                else i--;



                i++;
                addNumber = true;
            }
        }

        return cadenaCodigo;
    }

    public void compruebaCodigo(View v){

        input = findViewById(R.id.numero);
        respuestaJugador = input.getText().toString();

        try{
            Integer.parseInt(respuestaJugador);

            if(respuestaJugador.trim().length() != holesInt){
                Log.d("longError","¡Error de longitud!");

                //generamos toast de error de longitudes
                text = "Lengths not matching!";
                toast = Toast.makeText(this,text,duration);
                toast.show();
            }
            else{
                if (respuestaJugador.equals(codigo)){
                    Log.d("ok","acierto");

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Hola");
                    alertDialog.setMessage("Attempts: "+attempts);
                    alertDialog.setPositiveButton("Gonorrea!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("esto",""+attempts);
                        }
                    });
                    alertDialog.create().show();
                }
                else attempts++;
            }
        }catch (NumberFormatException exception)
        {
            //generamos toast de error numerico
            text = "NaN";
            toast = Toast.makeText(this,text,duration);
            toast.show();
        }


    }


}
