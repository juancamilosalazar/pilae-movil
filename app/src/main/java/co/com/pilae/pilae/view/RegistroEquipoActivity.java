package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import co.com.pilae.pilae.R;

public class RegistroEquipoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_equipo);
    }

    public void guardar(View view) {
        finish();
    }
}
