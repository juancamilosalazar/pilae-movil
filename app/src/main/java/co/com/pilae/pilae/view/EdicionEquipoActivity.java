package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import co.com.pilae.pilae.R;

public class EdicionEquipoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_equipo);
    }



    public void editar(View view) {
        finish();
    }
}
