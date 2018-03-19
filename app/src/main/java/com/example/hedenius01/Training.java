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


public class Training extends AppCompatActivity {

    private Button btn_ja, btn_nein;
    private ImageView img_view;
    private int imgNumber = 0;
    private boolean correctAnswer;
    // Zeit Variablen
    private long saveDateOnStart;
    private long saveDateOnAnswer;
    private long zeit;
    // Beim schreiben in die CSV Element Nummer
    private int itemNumber = 0;
    private int answerNumber = -1;
    private int trainingrundennummer = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixationskreuz);//als erste Aktivity wird das Fixationslayout geöffnet

        SaveInCsv();

        //Die Zeiten werden über die Aktivitys in Variablen weitergereicht
        final int probandennummer = (int) getIntent().getExtras().get("probandennummer");
        final int zeitGraubild = (int) getIntent().getExtras().get("zeitGraubild");
        final int zeitOriginalbild = (int) getIntent().getExtras().get("zeitOriginalbild");
        final int zeitFix = (int) getIntent().getExtras().get("zeitFixkreuz");

        //Dient Zum prüfen, ob die Werte richtig übergeben werden (Später AUSKOMMENTIEREN)
        Log.v("TRAINING RUNDE1", "Probandennummer im SPIELMODUS ?"
                + probandennummer);
        Log.v("TRAINING RUNDE1", "zeitOriginalbild im SPIELMODUS?"
                + zeitOriginalbild);
        Log.v("TRAINING RUNDE1", "zeitGraubild im SPIELMODUS ?"
                + zeitGraubild);
        ////////////////////////////////////////////////////////////////////////////////////

        //Zeiten Wielange die Bilder angezeigt werden sollen
        int zeitFixkreuz = zeitFix  ;
        int zeitOriginal = zeitOriginalbild;
        int zeitGrau = zeitGraubild;


        //Timer , welcher das Layout wechselt Fixationslayout-> OriginalbildLayout -> Graubildlayout ->training_layout
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.training1);
            }
        }, zeitFixkreuz); //Solange wird das Fixkreuz angezeigt

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.graubild);
            }
        }, zeitFixkreuz + zeitOriginal);//muss drauf addiert werden, zum originalbild

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Ausführen der Methode welche in die activity_training wechselt und dort die einzelen Bilder nacheinander abfrägt oder in die nächste aktivity wechselt .
                prepareGame();
                // anzeigen des erste Bildes.
                updateImage();
            }
        }, zeitFixkreuz + zeitOriginal + zeitGrau); //nach dieser zeit wird das layout für training angezeigt


    }

    //Methode welche die einzelnen Bilder aus dem array in der Bilder.class setzt und die antworten
    private void updateImage(){
        /////////////////////////////////////////////////////////////////////////////////////
        Log.v("TRAINING RUNDE1", "img_view != nulL? " + (img_view != null));
        Log.v("TRAINING RUNDE1", "Bilder.training1images[imagesNumber]  " + (Bilder.training1images[imgNumber] ));
        ///////////////////////////////////////////////////////////////////
        img_view.setImageResource(Bilder.training1images[imgNumber]);

        //Erfassen der aktuellen Zeit zum späteren messen der benötigten Zeit
        saveDateOnStart = new Date(System.currentTimeMillis()).getTime();

        imgNumber++;
    }

    private void prepareGame(){

        final int probandennummer = (int) getIntent().getExtras().get("probandennummer");
        final int zeitGraubild = (int) getIntent().getExtras().get("zeitGraubild");
        final int zeitOriginalbild = (int) getIntent().getExtras().get("zeitOriginalbild");
        final int zeitFixkreuz = (int) getIntent().getExtras().get("zeitFixkreuz");

        // set content view
        setContentView(R.layout.activity_training);

        // get views and buttons
        img_view = (ImageView) findViewById(R.id.view_img);
        Log.v("SPIEL RUNDE1", "img_view found?"
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

                //Die gestoppte Zeit und die ausgewählte Antwort wird in die CSV geschrieben
                SaveTimeInCsv(zeit, true);

                //Einkommentieren um die benötigte Zeit pro Runde als Toast angezeigt zu bekommen
                //Toast.makeText(getApplicationContext(), "benötigte Zeit in ms:  " + zeit,Toast.LENGTH_SHORT).show();

                    if (imgNumber == Bilder.training1images.length){
                        Intent intent = new Intent(Training.this, Training2.class);
                        intent.putExtra("probandennummer", probandennummer);
                        intent.putExtra("zeitGraubild", zeitGraubild);
                        intent.putExtra("zeitOriginalbild", zeitOriginalbild);
                        intent.putExtra("zeitFixkreuz", zeitFixkreuz);
                        startActivity(intent);
                    }else{
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
                //SaveSelectInCSV(false);

                //Einkommentieren um die benötigte Zeit pro Runde als Toast angezeigt zu bekommen
                //Toast.makeText(getApplicationContext(), "benötigte Zeit in ms:  " + zeit,Toast.LENGTH_SHORT).show();

                    if (imgNumber == Bilder.training1images.length){
                        Intent intent = new Intent(Training.this, Training2.class);
                        intent.putExtra("probandennummer", probandennummer);
                        intent.putExtra("zeitGraubild", zeitGraubild);
                        intent.putExtra("zeitOriginalbild", zeitOriginalbild);
                        intent.putExtra("zeitFixkreuz", zeitFixkreuz);
                        startActivity(intent);
                    }else{
                        updateImage();
                    }
            }
        });
    }

    public void SaveInCsv (){
        final int probandennummer = (int) getIntent().getExtras().get("probandennummer");
        //Erstellen der CSV Datei, für jeden Probanden wird eine eigene CSV erstellt.
        String DateiNamen = "hedenius_" + probandennummer + ".csv";
        String training = "Training Ergebnisse";
        String zeile2 = "Originalbild Nummer ; Element Nummer ; Reaktionszeit in ms ; eigene Antwort ; richtige Antwort ; Endergebnis " ;
        String newline = "\n";

        //Öffnen einer Datei, Speichern in die Datei und schließen der Datei
        //Mode_Append generiert die Datei hedenius_01.csv, wenn sie nicht existiert und schreibt was rein
        try{
            FileOutputStream inCSV = openFileOutput( DateiNamen, Context.MODE_APPEND);
            inCSV.write( newline.getBytes());
            inCSV.write( training.getBytes() );
            inCSV.write( newline.getBytes());
            inCSV.write( zeile2.getBytes() );
            inCSV.write( newline.getBytes());
            inCSV.flush();
            inCSV.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        ///////////////////////////////////////////////////////////////////////////////////////
        Log.v("CSV", "Apo ich habe nun etwas in die File hedenius_01 geschrieben");
        ///////////////////////////////////////////////////////////////////////////////////////
    }

    //Bei onclick auf start wird diese Methode ausgeführt und es werden Daten in eine CSV geschrieben
    public void SaveTimeInCsv (long reactionTime, boolean selectAnswer){
        itemNumber++;
        answerNumber++;
        correctAnswer = Bilder.training1answers[answerNumber];
        int endergebnis = ergebnis(correctAnswer, selectAnswer);
        final int probandennummer = (int) getIntent().getExtras().get("probandennummer");
        //Erstellen der CSV Datei, für jeden Probanden wird eine eigene CSV erstellt.
        String DateiNamen = "hedenius_" + probandennummer + ".csv";
        Log.v("CSV", "Apo die Zeiten wurden gespeichert ");
        String werte =   trainingrundennummer  + " ; " + trainingrundennummer + "."+ itemNumber +" ; " + reactionTime + " ; " + selectAnswer + " ; " + correctAnswer + " ; " + endergebnis + " \n ";
        String newline = "\n";



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

    //Endergebnis wird bestimmt, wenn Kind richtig geantwortet hat sprich richtige Antwort true war und er auch true gewählt hat
    //dann schreibe eine 1 in die csv genau so bei false/false, ansonsten schreibe eine 0 in die csv
    //addiert man später alle 1er zusammen so sieht man, wieviele richtige Antworten das Kind insgesammt hatte
    public int ergebnis (boolean correctAnswer, boolean selectAnswer){
        if (correctAnswer == true && selectAnswer == true){
            return 1;
        }else if (correctAnswer == false && selectAnswer == false){
            return 1;
        }else{
            return 0;
        }
    }

}