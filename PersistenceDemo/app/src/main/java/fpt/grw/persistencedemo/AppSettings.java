package fpt.grw.persistencedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AppSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);
        //Load the fragment into an activity
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.idFrameLayout, new SettingsFragment())
                .commit();

        Button btn = findViewById(R.id.btnShow);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = PreferenceManager.getDefaultSharedPreferences(AppSettings.this)
                        .getString("signature", null);
                Toast.makeText(AppSettings.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}