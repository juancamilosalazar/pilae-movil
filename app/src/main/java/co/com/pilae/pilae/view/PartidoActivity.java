package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.pilae.R;
import co.com.pilae.pilae.adapter.PartidoAdapter;
import co.com.pilae.pilae.entidades.Partido;
import co.com.pilae.pilae.utilities.ActionBarUtil;

public class PartidoActivity extends AppCompatActivity {

    @BindView(R.id.listViewTablaPosiciones)
    public ListView listViewPartido;

    private PartidoAdapter partidoAdapter;
    private ActionBarUtil actionBarUtil;
    List<Partido> partidoList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido);
        ButterKnife.bind(this);
        initComponents();
        loadPartidos();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.partidos));
    }

    private void loadPartidos() {

        partidoAdapter = new PartidoAdapter(this, partidoList);
        listViewPartido.setAdapter(partidoAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
