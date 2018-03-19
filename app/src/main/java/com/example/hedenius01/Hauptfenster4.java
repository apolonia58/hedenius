package com.example.hedenius01;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.Date;

public class Hauptfenster4 extends AppCompatActivity {

    private Button btn_ja, btn_nein;
    private ImageView img_view;
    private int imgNumber = 0;
    private boolean correctAnswer;
    private long saveDateOnStart;
    private long saveDateOnAnswer;
    private long zeit;
    private int itemNumber = 0;
    private int answerNumber = -1;
    private int rundennummer = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixationskreuz);

        ////////////////////////////////////////////////////////////////////////////////
        Log.v("SPIEL RUNDE 4", "WECHSEL in Hauptfenster4 Aktivity.");
        ///////////////////////////////////////////////////////////////////////////////


        final int probandennummer = (int) getIntent().getExtras().get("probandennummer");
        final int zeitGraubild = (int) getIntent().getExtras().get("zeitGraubild");
        final int zeitOriginalbild = (int) getIntent().getExtras().get("zeitOriginalbild");
        final int zeitFix = (int) getIntent().getExtras().get("zeitFixkreuz");


        int zeitFixkreuz = zeitFix;
        int zeitOriginal = zeitOriginalbild;
        int zeitGrau = zeitGraubild;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_hauptfenster4);
            }
        }, zeitFixkreuz); //Solange wird das Fixkreuz angezeigt

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.graubild);
            }
        }, zeitFixkreuz + zeitOriginal);//muss drauf addiert werden, zum originalbild

        Log.v("Spiel Runde 4", "Apo ich bin in Runde 4");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // prepare the training ("recognition").
                prepareGame();
                // Show the first image.
                updateImage();
            }
        }, zeitFixkreuz + zeitOriginal + zeitGrau); //nach dieser zeit wird das layout für training angezeigt

    }

    private void updateImage(){
      /*  final int zeitElement = (int) getIntent().getExtras().get("zeitElement");
        int zeitElem = zeitElement;
        /////////////////////////////////////////////////////////////////////////////////////
        Log.v("MODUSWAHL", "zeitElement im Moduswahl ?"
                + zeitElement);*/
        Log.v("SPIEL RUNDE4", "img_view != nulL? " + (img_view != null));
        Log.v("SPIEL RUNDE4", "Bilder.images4[imagesNumber]  " + (Bilder.images4[imgNumber] ));
        /////////////////////////////////////////////////////////////////////////////////////
        img_view.setImageResource(Bilder.images4[imgNumber]);
        //sorgt dafür, dass das Element nach zetElem ms verschwindet und anstatt dessen stelle das Fixationskreuz erscheint
        //Erfassen der aktuellen Zeit zum späteren messen der benötigten Zeit
        saveDateOnStart = new Date(System.currentTimeMillis()).getTime();

        imgNumber++;
    }

    private void prepareGame() {

        final int probandennummer = (int) getIntent().getExtras().get("probandennummer");
        final int zeitGraubild = (int) getIntent().getExtras().get("zeitGraubild");
        final int zeitOriginalbild = (int) getIntent().getExtras().get("zeitOriginalbild");
        final int zeitFixkreuz = (int) getIntent().getExtras().get("zeitFixkreuz");

        // set content view
        setContentView(R.layout.activity_training);

        // get views and buttons
        img_view = (ImageView) findViewById(R.id.view_img);
        Log.v("SPIEL RUNDE4", "img_view found?"
                + (img_view != null));
        btn_ja = (Button) findViewById(R.id.ja_btn);
        btn_nein = (Button) findViewById(R.id.nein_btn);

        //Logik für den JA Button -->
        btn_ja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //HIer wird die Zeit erfasst, welche mit der StartZeit verglichen wird.
                saveDateOnAnswer = new Date(System.currentTimeMillis()).getTime();
                zeit = saveDateOnAnswer - saveDateOnStart;
                //Die gestoppte Zeit wird in die CSV geschrieben
                SaveTimeInCsv(zeit, true);
                //Toast.makeText(getApplicationContext(), "benötigte Zeit in ms:  " + zeit,Toast.LENGTH_SHORT).show();

                    if (imgNumber == Bilder.images4.length) {
                        Intent intent = new Intent(Hauptfenster4.this, Hauptfenster5.class);
                        intent.putExtra("probandennummer", probandennummer);
                        intent.putExtra("zeitGraubild", zeitGraubild);
                        intent.putExtra("zeitOriginalbild", zeitOriginalbild);
                        intent.putExtra("zeitFixkreuz", zeitFixkreuz);
                        startActivity(intent);
                    } else {
                        updateImage();
                    }

            }
        });


        //Logik für den NEIN Button
        btn_nein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //HIer wird die Zeit erfasst, welche mit der StartZeit verglichen wird.
                saveDateOnAnswer = new Date(System.currentTimeMillis()).getTime();
                zeit = saveDateOnAnswer - saveDateOnStart;
                //Die gestoppte Zeit wird in die CSV geschrieben
                SaveTimeInCsv(zeit, false);
                //Toast.makeText(getApplicationContext(), "benötigte Zeit in ms:  " + zeit,Toast.LENGTH_SHORT).show();


                    if (imgNumber == Bilder.images4.length) {
                        Intent intent = new Intent(Hauptfenster4.this, Hauptfenster5.class);
                        intent.putExtra("probandennummer", probandennummer);
                        intent.putExtra("zeitGraubild", zeitGraubild);
                        intent.putExtra("zeitOriginalbild", zeitOriginalbild);
                        intent.putExtra("zeitFixkreuz", zeitFixkreuz);
                        startActivity(intent);
                    } else {
                        updateImage();
                    }

            }
        });
    }

    //Bei onclick auf start wird diese Methode ausgeführt und es werden Daten in eine CSV geschrieben
    public void SaveTimeInCsv (long reactionTime, boolean selectAnswer){
        itemNumber++;
        answerNumber++;
        correctAnswer = Bilder.answers4[answerNumber];
        int endergebnis = ergebnis(correctAnswer, selectAnswer);
        final int probandennummer = (int) getIntent().getExtras().get("probandennummer");
        //Erstellen der CSV Datei, für jeden Probanden wird eine eigene CSV erstellt.
        String DateiNamen = "hedenius_" + probandennummer + ".csv";
        Log.v("CSV", "Apo die Zeiten wurden gespeichert ");
        String werte =   rundennummer  + " ; "  + rundennummer  +"."+ itemNumber + " ; " + reactionTime + " ; " + selectAnswer + " ; " + correctAnswer + " ; " + endergebnis + " \n ";




        //Öffnen einer Datei, Speichern in die Datei und schließen der Datei
        //Mode_Append generiert die Datei hedenius_01.csv, wenn sie nicht existiert und schreibt was rein
        try{
            FileOutputStream inCSV = openFileOutput( DateiNamen, Context.MODE_APPEND);
            inCSV.write( werte.getBytes() );
            Log.v("CSV", "write content: " + werte);
            inCSV.flush();
            inCSV.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        ///////////////////////////////////////////////////////////////////////////////////////
        Log.v("CSV", "Apo ich habe nun etwas in die File hedenius_01 geschrieben");
        ///////////////////////////////////////////////////////////////////////////////////////
    }

    public int ergebnis (boolean correctAnswer, boolean selectAnswer) {
        if (correctAnswer == true && selectAnswer == true) {
            return 1;
        } else if (correctAnswer == false && selectAnswer == false) {
            return 1;
        } else {
            return 0;
        }
    }

}