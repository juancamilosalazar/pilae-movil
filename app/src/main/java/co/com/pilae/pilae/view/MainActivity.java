package co.com.pilae.pilae.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import co.com.pilae.pilae.R;
import co.com.pilae.pilae.utilities.ActionBarUtil;

public class MainActivity extends AppCompatActivity {

    private ActionBarUtil actionBarUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        actionBarUtil = new ActionBarUtil(this);
        actionBarUtil.setToolBar(getString(R.string.menu_principal));
    }

    public void goToTorneoActivity(View view) {
        Intent intent = new Intent(this,TorneoActivity.class);
        startActivity(intent);
    }

    public void goToEquipoActivity(View view) {
        Intent intent = new Intent(this,EquipoActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
