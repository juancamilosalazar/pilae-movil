package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.pilae.R;
import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.Torneo;
import co.com.pilae.pilae.persistencia.room.DataBaseHelper;
import co.com.pilae.pilae.utilities.ActionBarUtil;
import co.com.pilae.pilae.utilities.ParseUtil;

public class RegistroTorneoActivity extends AppCompatActivity {

    @BindView(R.id.deporteRegistroTorneo)
    public EditText txtRegistroDeporte;
    @BindView(R.id.nombreRegistroTorneo)
    public EditText txtRegistroNombre;
    private ActionBarUtil actionBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_torneo);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.crear_torneo));
    }

    private class InsercionTorneo extends AsyncTask<Torneo, Void, Void> {

        @Override
        protected Void doInBackground(Torneo... torneos) {
            DataBaseHelper.getSimpleDB(getApplicationContext()).getTorneoDAO().insert(torneos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), getString(R.string.successfully), Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }

    public void guardarTorneo(View view) {
        String nombreTorneo = txtRegistroNombre.getText().toString();
        String nombreDeporte = txtRegistroDeporte.getText().toString();
        if (validarInformacion(nombreTorneo,nombreDeporte)) {
            Torneo torneo = getTorneo(nombreTorneo,nombreDeporte);
            new InsercionTorneo().execute(torneo);
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
    private Torneo getTorneo(String nombreTorneo,String deporteTorneo) {
        Torneo torneo = new Torneo();
        torneo.setNombre(nombreTorneo);
        torneo.setDeporte(deporteTorneo);
        return torneo;
    }

    private boolean validarInformacion(String nombreTorneo,String deporteTorneo) {
        boolean esValido = true;
        if ("".equals(nombreTorneo)) {
            txtRegistroNombre.setError(getString(R.string.requerido));
            esValido = false;
        }

        if ("".equals(deporteTorneo)) {
            txtRegistroDeporte.setError(getString(R.string.requerido));
            esValido = false;
        }
        return esValido;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
