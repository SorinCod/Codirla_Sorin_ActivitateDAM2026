package ro.ase.dam.lab02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ro.ase.dam.R;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etMesaj = findViewById(R.id.etMesaj);
        Button btnTrimite = findViewById(R.id.btnTrimite);

        btnTrimite.setOnClickListener(view -> {
            String text = etMesaj.getText().toString().trim();
            if (text.isEmpty()) {
                etMesaj.setError(getString(R.string.lab02_eroare_gol));
                return;
            }
            Intent raspuns = new Intent();
            raspuns.putExtra("raspuns", text);
            setResult(RESULT_OK, raspuns);
            finish();
        });
    }
}