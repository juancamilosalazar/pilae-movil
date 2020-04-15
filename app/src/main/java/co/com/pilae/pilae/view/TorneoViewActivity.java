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
import co.com.pilae.pilae.utilities.ActionBarUtil;

public class TorneoViewActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.nombreTorneoView)
    public TextView nombreTorneo;

    @BindView(R.id.deporteTorneoView)
    public TextView deporteTorneo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneo_view);
        ButterKnife.bind(this);
        initComponents();
        loadTorneo();
    }

    private void loadTorneo() {
        nombreTorneo.setText("Liga postobón");
        deporteTorneo.setText("Fútbol");
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.torneos));
    }

    public void goToEditarTorneo(View view) {
        Intent intent = new Intent(this, EdicionTorneoActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void goToPartidos(View view) {
        Intent intent = new Intent(this, PartidoActivity.class);
        startActivity(intent);
    }

    public void goToTablaPosiciones(View view) {
        Intent intent = new Intent(this, TablaPosicionesActivity.class);
        startActivity(intent);
    }
}
