package de.mide.lernkarten.sender;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;


/**
 * App, die einen selbst-definierten <b>impliziten Intent</b>
 * verschickt. Diese Intents enthalten Lernkarten
 * (Text mit Frage/Vorderseite und Text mit
 * Antwort/Rückseite). Zum Empfang dieser Intents wird
 * die Schwester-App "Lernkarten-Empfänger" benötigt
 * (siehe anderen App-Modul in diesem Android-Studio-Projekt).
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends Activity {


    /**
     * Lifecycle-Methode, lädt Layout-Datei.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Event-Handler für Button 1, um eine bestimmte
     * Lernkarte (Flash Card) per impliziten Intent an
     * eine andere App zu schicken.
     * <br><br>
     *
     * Diese Methode wird in der Layout-Datei mit dem
     * Attribut <i>android:onClick</i> zugewiesen.
     *
     * @param view Button, der Event ausgelöst hat (wird nicht ausgewertet).
     */
    public void onSendenButton1(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setType("application/lernkarte"); // Eigener "MIME-Type"

        intent.putExtra("text_vorne",  "Wofür steht ADB?"     );
        intent.putExtra("text_hinten", "Android Debug Bridge" );

        if (wirdIntentUnterstuetzt(intent) == true) {
            startActivity(intent);
        }
    }


    /**
     * Event-Handler für Button 2; wie Button 1, nur
     * mit anderem Inhalt der Flash-Card.
     * <br><br>
     *
     * Diese Methode wird in der Layout-Datei mit dem
     * Attribut <i>android:onClick</i> zugewiesen.
     *
     * @param view Button, der Event ausgelöst hat (wird nicht ausgewertet).
     */
    public void onSendenButton2(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setType("application/lernkarte");  // Eigener "MIME-Type"

        intent.putExtra("text_vorne",  "Wofür steht DVM?"       );
        intent.putExtra("text_hinten", "Dalvik Virtual Machine" );

        if (wirdIntentUnterstuetzt(intent) == true) {
            startActivity(intent);
        }
    }


    /**
     * Die Methode überprüft, ob es für den als Parameter übergebenen Intent eine
     * passende Zielkomponente auf dem Android-Gerät gibt.
     * Wenn es keine Komponente gibt, dann wird eine Fehlermeldung als Dialog
     * ausgegeben und <i>false</i> als Ergebnis zurückgeliefert.
     * <br><br>
     *
     * Wenn diese Methode für einen Intent <i>false</i> liefert,
     * der Intent aber trotzdem mit <i>startActivity(intent)</i>
     * abgesetzt wird, dann wird die App vom System beendet
     * (sie stürzt ab).
     * <br><br>
     *
     * Die Verwendung der Methode {@link Intent#resolveActivity(PackageManager)}
     * wird auch auf
     * <a href="https://developer.android.com/guide/components/intents-common.html">dieser Seite</a>
     * im API-Guide beschrieben. Wenn es mehrere Apps gibt, die den Intent
     * verarbeiten/empfangen können, dann wird die mit der höchsten Priorität
     * zurückgeliefert. Für uns ist aber nur wichtig, ob es wenigstens eine
     * App gibt, die den Intent verarbeiten/empfangen kann.
     *
     * @return <i>true</i>, wenn es mindestens einen Receiver für
     *         den <i>intent</i> gibt, sonst <i>false</i>.
     */
    protected boolean wirdIntentUnterstuetzt(Intent intent) {

        PackageManager packageManager = this.getPackageManager();

        ComponentName componentName = intent.resolveActivity(packageManager);

        if (componentName == null) {

            zeigeDialog("Auf diesem Gerät ist keine App für die Anzeige von Lernkarten vorhanden.");
            return false;

        } else {

            return true;
        }
    }


    /**
     * Hilfs-Methode, um <i>nachricht</i> mit einem Dialog anzuzeigen.
     *
     * @param nachricht Im Dialog anzuzeigender Text.
     */
    protected void zeigeDialog(String nachricht) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage( nachricht );
        dialogBuilder.setPositiveButton("Zur Kenntnis genommen", null);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

}