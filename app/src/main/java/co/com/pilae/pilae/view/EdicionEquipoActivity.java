package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.pilae.R;
import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.persistencia.room.DataBaseHelper;
import co.com.pilae.pilae.utilities.ActionBarUtil;

public class EdicionEquipoActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.txtNombreEquipo)
    public EditText nombreEquipo;
    @BindView(R.id.txtDeporteEdicionEquipo)
    public EditText deporteEquipo;
    Equipo equipo = new Equipo();
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_equipo);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.editar));
        equipo = getEquipo();
    }

    private Equipo getEquipo() {
        String idEquipo = (String) getIntent().getExtras().getSerializable("id");
        return db.getEquipoDAO().findByIdEquipo(idEquipo);
    }


    public void editarEquipo(View view) {

        String nombre = nombreEquipo.getText().toString();
        String deporte = deporteEquipo.getText().toString();
        if (validarInformacion(nombre,deporte)) {
            equipo.setDeporte(deporte);
            equipo.setNombre(nombre);
            db.getEquipoDAO().update(equipo);
            finish();
        }
    }

    private boolean validarInformacion(String nombre, String deporte) {
        boolean esValido = true;
        if ("".equals(nombre)) {
            nombreEquipo.setError(getString(R.string.requerido));
            esValido = false;
        }
        if ("".equals(deporte)) {
            deporteEquipo.setError(getString(R.string.requerido));
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
