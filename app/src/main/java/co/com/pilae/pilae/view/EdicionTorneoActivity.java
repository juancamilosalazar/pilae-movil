package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.pilae.R;
import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.Torneo;
import co.com.pilae.pilae.persistencia.room.DataBaseHelper;
import co.com.pilae.pilae.utilities.ActionBarUtil;

public class EdicionTorneoActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.nombreTorneoEditar)
    public EditText nombreTorneo;
    @BindView(R.id.deporteTorneoEditar)
    public EditText deporteTorneo;
    Torneo torneo = new Torneo();
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_torneo);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.editar));
        torneo = getTorneo();
    }

    private Torneo getTorneo() {
        String idTorneo = (String) getIntent().getExtras().getSerializable("id");
        return db.getTorneoDAO().findByIdTorneo(idTorneo);
    }


    public void editarTorneo(View view) {

        String nombre = nombreTorneo.getText().toString();
        String deporte = deporteTorneo.getText().toString();
        if (validarInformacion(nombre,deporte)) {
            torneo.setDeporte(deporte);
            torneo.setNombre(nombre);
            db.getTorneoDAO().update(torneo);
            finish();
        }
    }

    private boolean validarInformacion(String nombre, String deporte) {
        boolean esValido = true;
        if ("".equals(nombre)) {
            nombreTorneo.setError(getString(R.string.requerido));
            esValido = false;
        }
        if ("".equals(deporte)) {
            deporteTorneo.setError(getString(R.string.requerido));
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
