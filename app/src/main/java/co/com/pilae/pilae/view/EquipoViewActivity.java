package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;
import co.com.pilae.pilae.R;
import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.Torneo;
import co.com.pilae.pilae.persistencia.room.DataBaseHelper;
import co.com.pilae.pilae.utilities.ActionBarUtil;
import co.com.pilae.pilae.utilities.ParseUtil;

public class EquipoViewActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.nombreEquipoView)
    public TextView nombreEquipo;

    @BindView(R.id.torneoEquipoView)
    public TextView torneoEquipo;
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_view);
        ButterKnife.bind(this);
        initComponents();
        loadEquipo();

    }

    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.equipos));
    }

    private void loadEquipo() {
        String idEquipo = (String) getIntent().getExtras().getSerializable("id");
        Equipo equipo = db.getEquipoDAO().findByIdEquipo(idEquipo);
        Torneo torneo = db.getTorneoDAO().findByIdTorneo(ParseUtil.intToString(equipo.getTorneo()));
        nombreEquipo.setText(equipo.getNombre());
        torneoEquipo.setText(torneo.getNombre());

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void goToEditar(View view) {
        String idEquipo = (String) getIntent().getExtras().getSerializable("id");
        Intent intent = new Intent(this,EdicionEquipoActivity.class);
        intent.putExtra("id",idEquipo);
        startActivity(intent);
    }
}
