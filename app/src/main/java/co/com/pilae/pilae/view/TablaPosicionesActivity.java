package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.pilae.R;
import co.com.pilae.pilae.adapter.PartidoAdapter;
import co.com.pilae.pilae.adapter.TablaPosicionAdapter;
import co.com.pilae.pilae.entidades.TablaPosicion;
import co.com.pilae.pilae.utilities.ActionBarUtil;

public class TablaPosicionesActivity extends AppCompatActivity {

    @BindView(R.id.listViewTablaPosiciones)
    public ListView listViewTablaPosiciones;

    private TablaPosicionAdapter tablaPosicionAdapter;
    private ActionBarUtil actionBarUtil;
    List<TablaPosicion> tablaPosicions = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_posiciones);
        ButterKnife.bind(this);
        initComponents();
        loadTablaPosicion();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.tabla_posiciones));
    }

    private void loadTablaPosicion() {

        tablaPosicions.add(new TablaPosicion(1L,0,0,0,0,0,0,0,0,"Medellin"));
        tablaPosicions.add(new TablaPosicion(2L,0,0,0,0,0,0,0,0,"Cali"));
        tablaPosicionAdapter = new TablaPosicionAdapter(this, tablaPosicions);
        listViewTablaPosiciones.setAdapter(tablaPosicionAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
