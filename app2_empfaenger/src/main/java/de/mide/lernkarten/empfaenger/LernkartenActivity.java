package de.mide.lernkarten.empfaenger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Diese Activity wird angezeigt, wenn ein impliziter Intent
 *  mit einer Lernkarte empfangen wird.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class LernkartenActivity extends AppCompatActivity {

    /** TextView für Vorder- oder Hinterseite der Lernkarte. */
    protected TextView _textviewLernkarte = null;

    /**
     * Button-Objekt, mit dem die "Rückseite" der Lernkarte
     * mit der Antwort angezeigt werden kann.
     */
    private Button _buttonRueckseite = null;

    private ActionBar _actionBar = null;


    /**
     * Lifecycle-Methode: Setzt Titel der Activity und holt
     * Text für Vorderseite der Lernkarte (Frage), um diese
     * im TextView darzustellen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lernkarte);

        // Intent holen, mit dem diesen Activity aufgerufen wurde.
        Intent intent = getIntent();
        String textVorne = intent.getStringExtra("text_vorne");

        actionBarKonfigurieren();

        _textviewLernkarte = findViewById(R.id.textview_lernkarte);
        _textviewLernkarte.setText(textVorne);

        _buttonRueckseite = findViewById( R.id.button_rueckseite_zeigen );
    }


    /**
     * Konfiguriert die ActionBar
     */
    private void actionBarKonfigurieren() {

        _actionBar = getSupportActionBar();
        if (_actionBar == null) {

            Toast.makeText( this, "Keine ActionBar vorhanden", Toast.LENGTH_LONG ).show();
            return;
        }

        _actionBar.setTitle( R.string.title_lernkarte_vorne );
    }


    /**
     * Button-Event-Handler: zeigt "Rückseite" der Lernkarte an.
     *
     * @param view  Button, der Event ausgelöst hat; wird nicht ausgewertet.
     */
    public void onButtonRueckseiteZeigen(View view) {

        Intent intent     = getIntent();
        String textHinten = intent.getStringExtra("text_hinten");

        _textviewLernkarte.setText(textHinten);

        if ( _actionBar != null ) {

            _actionBar.setTitle(R.string.title_lernkarte_hinten);
        }
    }

}
