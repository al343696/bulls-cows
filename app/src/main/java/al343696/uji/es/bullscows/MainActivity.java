package al343696.uji.es.bullscows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textColors, textHoles;
    ToggleButton buttonRepeat;

    String colors, holes;
    boolean repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public void onClick(View v) {

        textColors = findViewById(R.id.colors);
        colors = textColors.getText().toString();

        textHoles = findViewById(R.id.holes);
        holes = textHoles.getText().toString();

        buttonRepeat = findViewById(R.id.buttonRepeat);
        repeat = buttonRepeat.isChecked();

        Intent goToGameActivity = new Intent(this, GameActivity.class);
        goToGameActivity.putExtra("colors", colors);
        goToGameActivity.putExtra("holes", holes);
        goToGameActivity.putExtra("repeat", repeat);
        startActivity(goToGameActivity);

    }
}
