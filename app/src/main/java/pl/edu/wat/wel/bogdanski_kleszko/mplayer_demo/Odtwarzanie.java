package pl.edu.wat.wel.bogdanski_kleszko.mplayer_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;


public class Odtwarzanie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odtwarzanie);
    }
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
