package com.example.asus.dconfo_app.presentation.view.activity.estudiante;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.example.asus.dconfo_app.MainActivity;
import com.example.asus.dconfo_app.R;


import com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.CasaHomeEstudianteFragment;
import com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.InicioEjercicioFragment;
import com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.Tipo1EstudianteFragment;
import com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.Tipo2EstudianteFragment;
import com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.fonico.Tipo1FonicoFragment;
import com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.fonico.Tipo2FonicoFragment;
import com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.silabico.Tipo1silabicoEstudianteFragment;
import com.example.asus.dconfo_app.presentation.view.fragment.Estudiante.silabico.Tipo2silabicoEstudianteFragment;
import com.roughike.bottombar.BottomBar;

public class HomeEstudianteActivity extends AppCompatActivity implements
        CasaHomeEstudianteFragment.OnFragmentInteractionListener,
        Tipo1EstudianteFragment.OnFragmentInteractionListener,
        Tipo2EstudianteFragment.OnFragmentInteractionListener,
        InicioEjercicioFragment.OnFragmentInteractionListener,
        Tipo1FonicoFragment.OnFragmentInteractionListener,
        Tipo2FonicoFragment.OnFragmentInteractionListener,
        Tipo1silabicoEstudianteFragment.OnFragmentInteractionListener,
        Tipo2silabicoEstudianteFragment.OnFragmentInteractionListener {


    private BottomBar bottomBar;
    CasaHomeEstudianteFragment homeEstudianteFragment;
    Tipo1EstudianteFragment tipo1EstudianteFragment;
    Tipo2EstudianteFragment tipo2EstudianteFragment;
    String nameestudiante;
    int idestudiante;
    FloatingActionButton fbtn_volver;
    FrameLayout fl_ejercicios;
    boolean flag = false;
    VideoView vv_video;
    FrameLayout fmcontainer;
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_estudiante);

        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();

        //fl_homeRecycler = (FrameLayout) findViewById(R.id.container_HomeEstudiante_1);
        fl_ejercicios = (FrameLayout) findViewById(R.id.container_HomeEstudiante);
        fbtn_volver = (FloatingActionButton) findViewById(R.id.fbtn_estudiante_home_deberes);
        fbtn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearTranstition();
            }
        });


        nameestudiante = extra.getString("nameestudiante");
        idestudiante = extra.getInt("idestudiante");
        showToolbar("Est: " + nameestudiante + " ,id: " + idestudiante, true);
        vv_video = findViewById(R.id.vv_home_est_presentacion);
        fmcontainer = findViewById(R.id.container_HomeEstudiante);
        fmcontainer.setVisibility(View.GONE);
        if (cont == 0) {
            verVideo();
        }
        crearTranstition();

    }

    public void crearTranstition() {
        Bundle bundle = new Bundle();
        bundle.putInt("idEstudiante", idestudiante);
        System.out.println("//////////////////////////////////////////idEstudiante: " + idestudiante);
        bundle.putString("nameEstudiante", nameestudiante);

        homeEstudianteFragment = new CasaHomeEstudianteFragment();
        homeEstudianteFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_HomeEstudiante, homeEstudianteFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

    private void verVideo() {
        cont++;
        vv_video.setVisibility(View.VISIBLE);
        Uri uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.intro1);
        vv_video.setVideoURI(uri);
        vv_video.start();

        CountDownTimer cTimer = null;
        cTimer = new CountDownTimer(19000, 1000) {
            public void onTick(long millisUntilFinished) {
                // cont++;
                //System.out.println("el cont: " + cont);
            }

            @Override
            public void onFinish() {
                vv_video.setVisibility(View.GONE);
                fmcontainer.setVisibility(View.VISIBLE);

                //ll_ocultar_video.setVisibility(View.GONE);
                //ll_ejercicio.setVisibility(View.VISIBLE);
            }
        }.start();

        /*if (cont == 2) {
            ll_ocultar_video.setVisibility(View.GONE);
            cont = 0;
        }*/


        //System.out.println("vv_video.getDrawingTime()" + vv_video.getDrawingTime());
        //System.out.println("startTime"+startTime);
       /* if (vv_video.isPlaying()) {
            ll_ocultar_video.setVisibility(View.VISIBLE);
        }else {
            ll_ocultar_video.setVisibility(View.GONE);
            vv_video.getDuration();
        }*/
    }

    //método que permite volver al padre conservando las variables
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    //método que permite volver al padre conservando las variables
   /* @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_estudiante, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actionShare) {
            finish();
            return true;
        } else if (id == R.id.actionSalir) {
            finish();
            Intent intent = new Intent(HomeEstudianteActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void showToolbar(String tittle, boolean upButton) {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_ejercicio);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        //getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
