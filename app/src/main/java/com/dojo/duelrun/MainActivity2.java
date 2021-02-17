package com.dojo.duelrun;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends Activity {

    private Button credbutton,backbutton,exitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Comando usado para chamar o MainActivity quando � clicado em qualquer canto da tela.
        findViewById(R.id.backbegin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });

        //credbutton = (Button)findViewById(R.id.buttoncred);
        credbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                implementLayoutCredits();
            }
        });
        //exitbutton = (Button)findViewById(R.id.buttonexit);
        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //Fun��o criada para chamar layout de cr�ditos.
    private void implementLayoutCredits(){
        setContentView(R.layout.maincredits);

        //Bot�o usado para voltar ao primeiro layout.
        //backbutton=(Button)findViewById(R.id.buttonback);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreate(null);
            }
        });
    }
}
