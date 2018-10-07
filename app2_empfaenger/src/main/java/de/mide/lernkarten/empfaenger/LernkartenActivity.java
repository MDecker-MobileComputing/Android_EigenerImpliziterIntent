package de.mide.lernkarten.empfaenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Diese Activity wird angezeigt, wenn ein impliziter
 * Intent mit einer Lernkarte empfangen wird.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class LernkartenActivity extends Activity {

    /** TextView für Vorder- oder Hinterseite der Lernkarte. */
    protected TextView _textviewLernkarte = null;

    /**
     *  Button-Objekt, mit dem die "Rückseite" der Lernkarte
     *  mit der Antwort angezeigt werden kann.
     */
    protected Button _buttonRueckseite = null;


    /**
     * Lifecycle-Methode: Setzt Titel der Activity und holt
     * Text für Vorderseite der Lernkarte (Frage), um diese
     * im TextView darzustellen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lernkarte);

        setTitle(R.string.title_lernkarte_vorne);

        // Intent holen, mit dem diesen Activity aufgerufen wurde.
        Intent intent = getIntent();
        String textVorne = intent.getStringExtra("text_vorne");

        _textviewLernkarte = findViewById(R.id.textview_lernkarte);
        _textviewLernkarte.setText(textVorne);

        _buttonRueckseite = findViewById( R.id.button_rueckseite_zeigen );
    }


    /**
     * Button-Event-Handler: zeigt "Rückseite" der Lernkarte an.
     *
     * @param view Button, der Event ausgelöst hat; wird nicht ausgewertet.
     */
    public void onButtonRueckseiteZeigen(View view) {

        Intent intent     = getIntent();
        String textHinten = intent.getStringExtra("text_hinten");

        _textviewLernkarte.setText(textHinten);

        setTitle(R.string.title_lernkarte_hinten);

        // Button zum "Umdrehen" der Karte unsichtbar machen
        _buttonRueckseite.setVisibility( View.INVISIBLE );
    }

}
