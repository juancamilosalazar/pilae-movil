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
import co.com.pilae.pilae.adapter.EquipoAdapter;
import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.utilities.ActionBarUtil;

public class EquipoActivity extends AppCompatActivity {
    @BindView(R.id.listViewEquipo)
    public ListView listViewEquipo;

    private EquipoAdapter equipoAdapter;
    private ActionBarUtil actionBarUtil;
    List<Equipo> equipoList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);
        ButterKnife.bind(this);
        initComponents();
        loadEquipos();
        onItemClickListener();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.equipos));
    }

    private void loadEquipos() {

        equipoList.add(new Equipo(1,"Deportivo cali","Fútbol"));
        equipoList.add(new Equipo(2,"Medellín","Fútbol"));
        equipoAdapter = new EquipoAdapter(this,equipoList);
        listViewEquipo.setAdapter(equipoAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void goToAgregarEquipo(View view) {
        Intent intent = new Intent(this, RegistroEquipoActivity.class);
        startActivity(intent);
    }
    private void onItemClickListener() {
        listViewEquipo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EquipoViewActivity.class);
                intent.putExtra("id", getRateId(position));
                startActivity(intent);
            }

            private String getRateId(int position) {
                return String.valueOf(equipoList.get(position).getIdEquipo());
            }
        });
    }


}
