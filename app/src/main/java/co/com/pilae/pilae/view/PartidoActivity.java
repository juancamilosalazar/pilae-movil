package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.pilae.R;
import co.com.pilae.pilae.adapter.PartidoAdapter;
import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.Partido;
import co.com.pilae.pilae.operaciones.GenerarFixture;
import co.com.pilae.pilae.persistencia.room.DataBaseHelper;
import co.com.pilae.pilae.utilities.ActionBarUtil;
import co.com.pilae.pilae.utilities.ParseUtil;

public class PartidoActivity extends AppCompatActivity {

    @BindView(R.id.listViewTablaPosiciones)
    public ListView listViewPartido;
    @BindView(R.id.generarFixture)
    public Button generarFixture;
    private PartidoAdapter partidoAdapter;
    private ActionBarUtil actionBarUtil;
    List<Partido> partidoList = new ArrayList<>();
    DataBaseHelper db;
    String idTorneo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido);
        ButterKnife.bind(this);
        initComponents();
        hideComponents();
        loadPartidos();
    }

    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.partidos));
        idTorneo = (String) getIntent().getExtras().getSerializable("id");
    }

    private void hideComponents() {
        if(!hayMasDeUnEquipo()){
            generarFixture.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), getString(R.string.minimo_equipos_fixture), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean hayMasDeUnEquipo() {
        return db.getEquipoDAO().findByIdTorneo(idTorneo).size()>1;
    }

    private void loadPartidos() {
        if(!noHayPartidos()) {
            partidoList=db.getPartidoDAO().findByIdTorneo(idTorneo);
            partidoAdapter = new PartidoAdapter(this, partidoList,db);
            listViewPartido.setAdapter(partidoAdapter);
            generarFixture.setVisibility(View.GONE);
        }
    }

    private boolean noHayPartidos() {
        return db.getPartidoDAO().findByIdTorneo(idTorneo).isEmpty();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private class InsercionPartido extends AsyncTask<Partido, Void, Void> {

        @Override
        protected Void doInBackground(Partido... equipos) {
            DataBaseHelper.getSimpleDB(getApplicationContext()).getPartidoDAO().insert(equipos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


    public void generarFixture(View view) {
        List<Equipo> equipos = db.getEquipoDAO().findByIdTorneo(idTorneo);

        HashMap<Integer, Equipo> generateFixture = new HashMap<>();
        int idEquipo = 0;
        for (Equipo equipo : equipos) {
            //para cada equipo le agrega un id que ayudara a generar el fixture
            idEquipo++;
            //inserta el equipo con el id generado para el fixture y el id
            generateFixture.put(idEquipo, equipo);
        }
        //calcula el fixture con el hashmap generado
        mostrarPartidos(GenerarFixture.calcularLiga(generateFixture), generateFixture, ParseUtil.stringToInteger(idTorneo));

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), getString(R.string.fixture_generado), Toast.LENGTH_SHORT).show();
    }

    public List<Partido> mostrarPartidos(GenerarFixture.Partido[][] rondas, HashMap<Integer, Equipo> equipos, Integer id) {
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        List<Partido> partidoEntities = new ArrayList<>();
        for (int i = 0; i < rondas.length; i++) {
            for (int j = 0; j < rondas[i].length; j++) {
                Partido partido = new Partido();
                partido.setFecha(calendar.getTime().toString());
                partido.setTorneo(id);
                partido.setEquipoLocal(equipos.get(1 + rondas[i][j].local).getIdEquipo());
                partido.setEquipoVisitante(equipos.get(1 + rondas[i][j].visitante).getIdEquipo());
                partido.setIdaVuelta("ida");
                new InsercionPartido().execute(partido);
                partidoEntities.add(partido);
            }


        }

        for (int i = 0; i < rondas.length; i++) {

            for (int j = 0; j < rondas[i].length; j++) {
                Partido partido = new Partido();
                partido.setFecha(calendar.getTime().toString());
                partido.setTorneo(id);
                partido.setEquipoLocal(equipos.get(1 + rondas[i][j].visitante).getIdEquipo());
                partido.setEquipoVisitante(equipos.get(1 + rondas[i][j].local).getIdEquipo());
                partido.setIdaVuelta("vuelta");
                new InsercionPartido().execute(partido);
                partidoEntities.add(partido);
            }


        }
        return partidoEntities;

    }



}
