package co.com.pilae.pilae.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.pilae.R;
import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.TablaPosicion;
import co.com.pilae.pilae.entidades.Torneo;
import co.com.pilae.pilae.persistencia.room.DataBaseHelper;
import co.com.pilae.pilae.utilities.ActionBarUtil;
import co.com.pilae.pilae.utilities.ParseUtil;
@RequiresApi(api = Build.VERSION_CODES.N)
public class RegistroEquipoActivity extends AppCompatActivity {

    @BindView(R.id.registroDeporteEquipo)
    public EditText txtRegistroDeporte;
    @BindView(R.id.registroNombreEquipo)
    public EditText txtRegistroNombre;
    @BindView(R.id.spinner)
    public Spinner spinner;
    private ActionBarUtil actionBarUtil;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_equipo);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.crear_equipo));
        List<String> nombreEquipos = convertListNombreEquipos(db.getTorneoDAO().listar());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,nombreEquipos);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private List<String> convertListNombreEquipos(List<Torneo> listar) {
        List<String> strings = new ArrayList<>();
        listar.stream().map(x-> strings.add(x.getNombre())).collect(Collectors.toList());
        return strings;
    }

    public void guardarEquipo(View view) {
        String nombreTorneo = spinner.getSelectedItem().toString();

        String nombreEquipo = txtRegistroNombre.getText().toString();
        String nombreDeporte = txtRegistroDeporte.getText().toString();
        Integer idTorneo = (findIdTorneoByName(nombreTorneo));
        if (validarInformacion(nombreEquipo, idTorneo,nombreDeporte)) {
            Equipo equipo = getEquipo(nombreEquipo, idTorneo,nombreDeporte);
            new InsercionEquipo().execute(equipo);
            finish();
            initTablaPosiciones(equipo.getNombre(),idTorneo);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    private class InsercionEquipo extends AsyncTask<Equipo, Void, Void> {

        @Override
        protected Void doInBackground(Equipo... equipos) {
            DataBaseHelper.getSimpleDB(getApplicationContext()).getEquipoDAO().insert(equipos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), getString(R.string.successfully), Toast.LENGTH_SHORT).show();

            super.onPostExecute(aVoid);
        }
    }

    private void initTablaPosiciones(String nombreEquipo, Integer idTorneo) {
        Equipo equipo = db.getEquipoDAO().findByNombreEquipo(nombreEquipo);
        TablaPosicion tablaPosicion = new TablaPosicion();
        tablaPosicion.setEquipo(equipo.getIdEquipo());
        tablaPosicion.setTorneo(idTorneo);
        tablaPosicion.setPuntos(0);
        tablaPosicion.setPartidosEmpatados(0);
        tablaPosicion.setPartidosPerdidos(0);
        tablaPosicion.setPartidosGanados(0);
        tablaPosicion.setPartidosJugados(0);
        tablaPosicion.setGolesDiferencia(0);
        tablaPosicion.setGolesFavor(0);
        tablaPosicion.setGolesContra(0);
        new InsercionTablaPosiciones().execute(tablaPosicion);
    }
    private class InsercionTablaPosiciones extends AsyncTask<TablaPosicion, Void, Void> {

        @Override
        protected Void doInBackground(TablaPosicion... tablaPosicions) {
            DataBaseHelper.getSimpleDB(getApplicationContext()).getTablaPosicionDAO().insert(tablaPosicions[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), getString(R.string.successfully), Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }

    private Integer findIdTorneoByName(String nombreTorneo) {
        Torneo torneo = db.getTorneoDAO().findByNombreTorneo(nombreTorneo);
        return torneo.getIdTorneo();
    }

    private Equipo getEquipo(String nombreEquipo, Integer idTorneo,String deporteEquipo) {
        Equipo equipo = new Equipo();
        equipo.setNombre(nombreEquipo);
        equipo.setTorneo(idTorneo);
        equipo.setDeporte(deporteEquipo);
        return equipo;
    }

    private boolean validarInformacion(String nombreEquipo, Integer idTorneo,String deporteEquipo) {
        boolean esValido = true;
        if ("".equals(nombreEquipo)) {
            txtRegistroNombre.setError(getString(R.string.requerido));
            esValido = false;
        }

        if ("".equals(deporteEquipo)) {
            txtRegistroDeporte.setError(getString(R.string.requerido));
            esValido = false;
        }
        return esValido;
    }


    private boolean torneoIsNull(Integer idTorneo) {
        return Objects.isNull(db.getTorneoDAO().findByIdTorneo(idTorneo.toString()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
