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

public class EquipoViewActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;
    @BindView(R.id.nombreEquipoView)
    public TextView nombreEquipo;

    @BindView(R.id.torneoEquipoView)
    public TextView torneoEquipo;
    @BindView(R.id.escudoEquipoView)
    ImageView escudoEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_view);
        ButterKnife.bind(this);
        initComponents();
        loadEquipo();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.equipos));
    }

    private void loadEquipo() {
        String idEquipo = (String) getIntent().getExtras().getSerializable("id");
        if("1".equals(idEquipo)){
            nombreEquipo.setText("Deportivo cali");
            torneoEquipo.setText("Liga postobon");
            escudoEquipo.setImageResource(R.drawable.cali);
        }else{
            nombreEquipo.setText("Medellin");
            torneoEquipo.setText("Liga postobon");
            escudoEquipo.setImageResource(R.drawable.descarga);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void goToEditar(View view) {
        Intent intent = new Intent(this,EdicionEquipoActivity.class);
        startActivity(intent);
    }
}
