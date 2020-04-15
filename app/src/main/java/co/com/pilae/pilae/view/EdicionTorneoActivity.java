package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import co.com.pilae.pilae.R;

public class EdicionTorneoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_torneo);
    }

    public void actualizar(View view) {
        finish();
    }
}
