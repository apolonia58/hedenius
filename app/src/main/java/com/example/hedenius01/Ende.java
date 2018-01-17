package com.example.hedenius01;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Ende extends AppCompatActivity {


    Button btn_moduswahl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ende);

        ////////////////////////////////////////////////////////////////////////////////
        Log.v("ENDE", "WECHSEL in ENDE Aktivity.");
        ///////////////////////////////////////////////////////////////////////////////

        btn_moduswahl = (Button) findViewById(R.id.moduswahl_btn);

        btn_moduswahl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ende.this, Einstellungen.class);
                startActivity(intent);
            }
        });
    }
}
