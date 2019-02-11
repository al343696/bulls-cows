package al343696.uji.es.bullscows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    String colors, holes;
    boolean repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        colors = intent.getStringExtra("colors");
        holes = intent.getStringExtra("holes");
        repeat = intent.getBooleanExtra("repeat", false);
    }


}
