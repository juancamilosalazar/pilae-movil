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
import co.com.pilae.pilae.adapter.TorneoAdapter;
import co.com.pilae.pilae.entidades.Equipo;
import co.com.pilae.pilae.entidades.Torneo;
import co.com.pilae.pilae.utilities.ActionBarUtil;

public class TorneoActivity extends AppCompatActivity {

    @BindView(R.id.listViewTorneo)
    public ListView listViewTorneo;

    private TorneoAdapter torneoAdapter;
    private ActionBarUtil actionBarUtil;
    List<Torneo> torneoList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneo);
        ButterKnife.bind(this);
        initComponents();
        loadTorneos();
        onItemClickListener();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.torneos));
    }

    private void loadTorneos() {

        torneoList.add(new Torneo(1,"Liga postobon","Fútbol"));
        torneoAdapter = new TorneoAdapter(this, torneoList);
        listViewTorneo.setAdapter(torneoAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void goToAgregarTorneo(View view) {
    }
    private void onItemClickListener() {
        listViewTorneo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TorneoViewActivity.class);
                intent.putExtra("id", getRateId(position));
                startActivity(intent);
            }

            private String getRateId(int position) {
                return String.valueOf(torneoList.get(position).getIdTorneo());
            }
        });
    }

    public void goToRegistroTorneo(View view) {
        Intent intent = new Intent(this, RegistroTorneoActivity.class);
        startActivity(intent);
    }
}
