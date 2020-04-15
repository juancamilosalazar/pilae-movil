package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import co.com.pilae.pilae.R;
import co.com.pilae.pilae.utilities.ActionBarUtil;

public class RegistroTorneoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_torneo);
        initComponents();
    }

    private void initComponents() {

    }

    public void guardar(View view) {
        finish();
    }
}
