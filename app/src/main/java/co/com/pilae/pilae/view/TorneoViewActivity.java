package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.pilae.R;
import co.com.pilae.pilae.entidades.Torneo;
import co.com.pilae.pilae.persistencia.room.DataBaseHelper;
import co.com.pilae.pilae.utilities.ActionBarUtil;

public class TorneoViewActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.nombreTorneoView)
    public TextView nombreTorneo;

    @BindView(R.id.deporteTorneoView)
    public TextView deporteTorneo;
    DataBaseHelper db;
    String idTorneo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneo_view);
        ButterKnife.bind(this);
        initComponents();
        loadTorneo();
    }

    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.torneos));
        idTorneo = (String) getIntent().getExtras().getSerializable("id");
    }

    private void loadTorneo() {
        Torneo torneo = db.getTorneoDAO().findByIdTorneo(idTorneo);
        nombreTorneo.setText(torneo.getNombre());
        deporteTorneo.setText(torneo.getDeporte());
    }

    public void goToEditarTorneo(View view) {
        Intent intent = new Intent(this, EdicionTorneoActivity.class);
        intent.putExtra("id",idTorneo);
        startActivity(intent);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void goToPartidos(View view) {
        Intent intent = new Intent(this, PartidoActivity.class);
        intent.putExtra("id",idTorneo);
        startActivity(intent);
    }

    public void goToTablaPosiciones(View view) {
        Intent intent = new Intent(this, TablaPosicionesActivity.class);
        intent.putExtra("id",idTorneo);
        startActivity(intent);
    }
}
