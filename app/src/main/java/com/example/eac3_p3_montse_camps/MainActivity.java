package com.example.eac3_p3_montse_camps;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable pampalluguesAnimacio;
    ImageView titolPampallugues;
    ImageView engVermell;
    ImageView engVerd;
    ImageView engBlau;
    ImageView ull;
    ImageView donut;
    MediaPlayer musica = null;
    Boolean visible = false;
    Boolean audible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //apartat A
        titolPampallugues = (ImageView) findViewById(R.id.simpsonsTitol);
        //l'animació està definida al recurs xml simpsonsfilm (imatges i duració)
        titolPampallugues.setBackgroundResource(R.drawable.simpsonsfilm);
        // Creem l'animació i la iniciem.
        pampalluguesAnimacio = (AnimationDrawable) titolPampallugues.getBackground();
        pampalluguesAnimacio.start();

        //apartat B
        // Localitzem els recursos de les imatges
        engVermell = (ImageView) findViewById(R.id.ivVermell);
        engVerd = (ImageView) findViewById(R.id.ivVerd);
        engBlau = (ImageView) findViewById(R.id.ivBlau);
        ull = (ImageView) findViewById(R.id.ivUll);
        donut = (ImageView) findViewById(R.id.ivDonut);

        // Afegim el listener a la imatge del títol.
        titolPampallugues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mètode que farà visibles i invisibles les imatges
                engegaImatges(v);
            }
        });

        //apartat F
        // Afegim el listener a la imatge del donut
        donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(audible == true) {
                    // Pause the music player
                    musica.pause();
                    audible=false;
                }else {
                    //Localitzem el recurs i el posem en marxa
                    musica = MediaPlayer.create(MainActivity.this,R.raw.the_simpsons);
                    musica.start();
                    audible=true;
                }
            }
        });
    }

    private void engegaImatges(View view){
        if (!visible) {
            engVermell.setVisibility(view.VISIBLE);
            engVerd.setVisibility(view.VISIBLE);
            engBlau.setVisibility(view.VISIBLE);
            ull.setVisibility(view.VISIBLE);
            donut.setVisibility(view.VISIBLE);

            //apartat C, D i E
            interpolaImatges(engVermell, "rodaDreta");
            interpolaImatges(engBlau, "rodaDreta");
            interpolaImatges(engVerd, "rodaEsq");
            interpolaImatges(donut, "botaIroda");
            interpolaImatges(ull, "rota");

            visible = true;
        } else {
            engVermell.setVisibility(view.INVISIBLE);
            engVerd.setVisibility(view.INVISIBLE);
            engBlau.setVisibility(view.INVISIBLE);
            ull.setVisibility(view.INVISIBLE);
            donut.setVisibility(view.INVISIBLE);

            //apartat C, D i E
            engVermell.clearAnimation();
            engBlau.clearAnimation();
            engVerd.clearAnimation();
            donut.clearAnimation();
            ull.clearAnimation();

            visible = false;
        }
    }

    private void interpolaImatges (ImageView iv, String accio){
        Animation animacio = null;

        switch (accio){
            case "rodaEsq":
                animacio = AnimationUtils.loadAnimation(this,R.anim.rodaesquerra);
                break;
            case "rodaDreta":
                animacio = AnimationUtils.loadAnimation(this,R.anim.rodadreta);
                break;
            case "botaIroda":
                animacio = AnimationUtils.loadAnimation(this,R.anim.botairoda);
                break;
            case "rota":
                animacio = AnimationUtils.loadAnimation(this,R.anim.rota);
                break;
        }

        iv.startAnimation(animacio);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(musica.isPlaying() == true) {
            // Pause the music player
            musica.pause();
            audible = true; //la música estava sonant
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(audible == true){
            musica.start();
        }

    }

}
