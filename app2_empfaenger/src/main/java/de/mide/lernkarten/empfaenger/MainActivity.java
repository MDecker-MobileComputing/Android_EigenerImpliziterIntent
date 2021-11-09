package de.mide.lernkarten.empfaenger;

import android.app.Activity;
import android.os.Bundle;


/**
 * App empfängt Lernkarten-Intents, die von der Schwester-App
 * "Lernkarten-Sender" abgeschickt wurden (siehe anderes
 * App-Modul in diesem Android-Studio-Projekt).
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends Activity {
    
    /**
     * Lifecycle-Methode zur Initialisierung des Activity-Objekts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
