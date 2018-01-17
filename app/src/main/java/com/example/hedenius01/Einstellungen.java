package com.example.hedenius01;
/**
 * Created by Abdullah Yildirim
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileOutputStream;

//in dieser Klasse werden alle Einstellungen vorgenommen (Originalbild/Graubild/Element in ms/Probandennummer)
public class Einstellungen extends AppCompatActivity {

    EditText et_probandennummer, et_originalbild, et_graubild, et_fixkreuz;
    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);

        ////////////////////////////////////////////////////////////////////////////////
        Log.v("Einstellungen", "Einstellung Aktivity.");
        ///////////////////////////////////////////////////////////////////////////////

        btn_start = (Button) findViewById(R.id.start_btn);
        et_graubild = (EditText) findViewById(R.id.graubild_et);
        et_originalbild = (EditText)findViewById(R.id.originalbild_et);
        et_probandennummer = (EditText)findViewById(R.id.probandennummer_et);
        et_fixkreuz = (EditText)findViewById(R.id.fixkreuz_et);

        //Laden der Daten welche in SpeichernDaten gespeichert wurden (Shared Preferences)
        daten();
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //Prüfen, ob die Eingaben passen
                prüfen();
                //Schreiben der Probandennummer, Originalbildzeit, Graubildzeit, Elementanzeigezeit in die csv
                SaveInCsv();
            }
        });
    }

    //Prüfen der Eingabefelder und übergabe der Eingaben an die anderen aktivitys.
    public void prüfen (){
        //abgefangen, dass alle Felder ausgefüllt werden müssen um in die nächste Aktivity zu wechseln.
        if (et_probandennummer.getText().toString().trim().equals("")) {
            et_probandennummer.setError("Die Probandennummer ist ein Pflichtfeld und muss mindestens aus einer Zahl bestehen!");
        } else if (et_originalbild.getText().toString().trim().equals("")) {
            et_originalbild.setError("Bitte wähle aus wielange das Originalbild mindestens angezeigt werden soll! Mindestanzeigedauer beträgt 100 Millisekunden!");
        } else if (et_graubild.getText().toString().trim().equals("")) {
            et_graubild.setError("Bitte wähle aus wielange das Graubild mindestens angezeigt werden soll! Mindestanzeigedauer beträgt 100 Millisekunden!");
        } else if (et_fixkreuz.getText().toString().trim().equals("")) {
            et_fixkreuz.setError("Bitte wähle aus wielange das Fixationskreuz in den jeweiligen Runden angezeigt werden soll!");
        }
        else {
            //Einstellungswerte werden in den Variablen probandenummer, zeitgraubild, zeitoriginalbild gespeichert
            int probandennummer , zeitOriginalbild, zeitGraubild, zeitFixkreuz;
            probandennummer = Integer.parseInt(et_probandennummer.getText().toString());
            zeitGraubild = Integer.parseInt(et_graubild.getText().toString());
            zeitOriginalbild = Integer.parseInt(et_originalbild.getText().toString());
            zeitFixkreuz = Integer.parseInt(et_fixkreuz.getText().toString());

            //Dient Zum prüfen, ob die Werte richtig übergeben werden (Später AUSKOMMENTIEREN)
            ///////////////////////////////////////////////////////////////////////////////////
            Log.v("Einstellungen", "Probandennummer ?"
                    + probandennummer);
            Log.v("Einstellungen", "zeitOriginalbild ?"
                    + zeitOriginalbild);
            Log.v("Einstellungen", "zeitGraubild ?"
                    + zeitGraubild);
           Log.v("Einstellungen", "zeitFixkreuz ?"
                    + zeitFixkreuz);
            ////////////////////////////////////////////////////////////////////////////////

            //öffnen der nächsten Aktivity Moduswahl und übergabe der Einstellugnswerte Probandennummer,zeitGraubild, zeitOriginalbild
            Intent i = new Intent(Einstellungen.this, Moduswahl.class);
            i.putExtra("probandennummer", probandennummer);
            i.putExtra("zeitGraubild", zeitGraubild);
            i.putExtra("zeitOriginalbild", zeitOriginalbild);
            i.putExtra("zeitFixkreuz", zeitFixkreuz);
            startActivity(i);

            ////////////////////////////////////////////////////////////////////////////////
            Log.v("MODUSWAHL", "WECHSEL in MODUSWAHL Aktivity.");
            ///////////////////////////////////////////////////////////////////////////////
        }
    }

    //Diese Methode wird beim schließen der App automatisch aufgerufen und die Daten werden in der Datei SpeicherDatei gespeichert
    public void onStop(){
        super.onStop();
        //shared preferences Datei öffnen
        SharedPreferences sp = getSharedPreferences("SpeicherDatei", 0);
        //Klasse initialisieren
        SharedPreferences.Editor editor = sp.edit();
        //Text aus den Textfeldern holen und in SpeicherDatei speichern
        editor.putString("key1", et_probandennummer.getText().toString());
        editor.putString("key2", et_originalbild.getText().toString());
        editor.putString("key3", et_graubild.getText().toString());
        editor.putString("key4", et_fixkreuz.getText().toString());
        //Speichern in SpeicherDatei
        editor.commit();
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        Log.v("SHAREDPREFERENCES", "Apo die Daten wurden in der Datei SpeicherDatei gespeichert.");
        //////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public void daten () {
        //shared preferences Datei öffnen
        SharedPreferences sp = getSharedPreferences("SpeicherDatei", 0);
        //Die werte welche in der SpeicherDatei stehen, werden hier übernommen, sollte in SpeicherDatei noch keine Werte stehen
        //dann als Standartwert, 0 500 500 500.
        et_probandennummer.setText(sp.getString("key1", "0"));
        et_originalbild.setText(sp.getString("key2", "500"));
        et_graubild.setText(sp.getString("key3", "500"));
        et_fixkreuz.setText(sp.getString("key4", "500"));

        Log.v("SHAREDPREFERENCES", "Apo die Daten wurden aus der Datei SpeicherDatei ausgelesen.");
    }


    //Bei onclick auf start wird diese Methode ausgeführt und es werden Daten in eine CSV geschrieben
    public void SaveInCsv (){
        //Erstellen der CSV Datei, für jeden Probanden wird eine eigene CSV erstellt.
        //Zunächst wird geprüft ob diese Datei für den Probanden schon besteht, wenn ja wird in diese geschrieben
        //sollte es diese Datei noch nicht geben, dann wird diese erstellt Dateiname ist hedenius_probandennummer
        String DateiNamen = "hedenius_" + et_probandennummer.getText().toString() + ".csv";
        //In der CSV steht als erster Eintrag die Probandennummer
        String probandennummer = "Probandennummer: "+ et_probandennummer.getText().toString();
        //Als nächster Eintrag eine Kopfzeile mit den Einstellungswerten darunter
        String zeile1 = "Originalbild Display Time ; Graubild Display Time ; Fixation Display Time" ;
        String EintragInDatei =  et_originalbild.getText().toString() + "ms ; " +
                                 et_graubild.getText().toString() + "ms ; " +
                                 et_fixkreuz.getText().toString() + "ms ; " ;
        String newline = "\n";
        //Mode Append -> fügt hinzu, wenn die Datei nicht existiert, wird sie erstellt
        try{
            FileOutputStream inCSV = openFileOutput( DateiNamen, Context.MODE_APPEND);
            inCSV.write( probandennummer.getBytes() );
            inCSV.write( newline.getBytes());
            inCSV.write( zeile1.getBytes() );
            inCSV.write( newline.getBytes());
            inCSV.write( EintragInDatei.getBytes() );
            inCSV.write( newline.getBytes());
            Log.v("CSV", "write content: " + EintragInDatei);
            inCSV.flush();
            inCSV.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        ///////////////////////////////////////////////////////////////////////////////////////
        Log.v("CSV", "Apo ich habe nun etwas in die File hedenius_01 geschrieben");
        ///////////////////////////////////////////////////////////////////////////////////////
    }
}
