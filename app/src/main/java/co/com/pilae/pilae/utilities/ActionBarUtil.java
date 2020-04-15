package co.com.pilae.pilae.utilities;

import androidx.appcompat.app.AppCompatActivity;

public class ActionBarUtil {
    private final AppCompatActivity appCompatActivity;


    public ActionBarUtil(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }




    public void setToolBar(String mensaje) {
        if (appCompatActivity.getSupportActionBar() != null) {
            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            appCompatActivity.getSupportActionBar().setTitle(mensaje);
        }
    }

}
