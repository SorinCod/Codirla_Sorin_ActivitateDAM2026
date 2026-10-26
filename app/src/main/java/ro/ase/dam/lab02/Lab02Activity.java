package ro.ase.dam.lab02;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ro.ase.dam.R;

public class Lab02Activity extends AppCompatActivity {

    private static final String TAG = "CicluViata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab02);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.d(TAG, "onCreate - log de tip debug");

        Button btnDeschide = findViewById(R.id.btnDeschideA2);
        btnDeschide.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("mesaj", getString(R.string.lab02_mesaj_trimis));
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart - log de tip error");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "onResume - log de tip warning");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause - log de tip debug");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop - log de tip info");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart - log de tip info");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy - log de tip verbose");
    }
}