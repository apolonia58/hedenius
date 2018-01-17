package com.example.hedenius01;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Moduswahl extends AppCompatActivity {

    Button btn_spiel, btn_training;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moduswahl);

        Log.v("MODUSWAHL", "Du bist im Moduswahl Apo");

        final int probandennummer = (int) getIntent().getExtras().get("probandennummer");
        final int zeitGraubild = (int) getIntent().getExtras().get("zeitGraubild");
        final int zeitOriginalbild = (int) getIntent().getExtras().get("zeitOriginalbild");
        final int zeitFixkreuz= (int) getIntent().getExtras().get("zeitFixkreuz");

        //Dient Zum prüfen, ob die Werte richtig übergeben werden (Später AUSKOMMENTIEREN)
        Log.v("MODUSWAHL", "Probandennummer im Moduswahl ?"
                + probandennummer);
        Log.v("MODUSWAHL", "zeitOriginalbild im Moduswahl ?"
                + zeitOriginalbild);
        Log.v("MODUSWAHL", "zeitGraubild im Moduswahl ?"
                + zeitGraubild);
        /*Log.v("MODUSWAHL", "zeitElement im Moduswahl ?"
                + zeitElement);*/
        ////////////////////////////////////////////////////////////////////////////////////

        btn_training = (Button) findViewById(R.id.training_btn);
        btn_spiel = (Button) findViewById(R.id.spiel_btn);


        //Button um den Trainings Modus zu starten
        btn_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Moduswahl.this, Training.class);
                 intent.putExtra("probandennummer", probandennummer);
                intent.putExtra("zeitGraubild", zeitGraubild);
                intent.putExtra("zeitOriginalbild", zeitOriginalbild);
                intent.putExtra("zeitFixkreuz", zeitFixkreuz);
                startActivity(intent);

                ////////////////////////////////////////////////////////////////////////////////
                Log.v("TRAINING", "WECHSEL in Training Aktivity.");
                ///////////////////////////////////////////////////////////////////////////////
            }
        });

        //Button um den Spiel Modus zu starten
        btn_spiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Moduswahl.this, Hauptfenster.class);
                intent.putExtra("probandennummer", probandennummer);
                intent.putExtra("zeitGraubild", zeitGraubild);
                intent.putExtra("zeitOriginalbild", zeitOriginalbild);
                intent.putExtra("zeitFixkreuz", zeitFixkreuz);
                startActivity(intent);
                ////////////////////////////////////////////////////////////////////////////////
                Log.v("RUNDE 1", "WECHSEL in Hauptfenster 1 Aktivity.");
                ///////////////////////////////////////////////////////////////////////////////
            }
        });
    }
}
