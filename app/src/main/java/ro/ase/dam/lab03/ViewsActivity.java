package ro.ase.dam.lab03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ro.ase.dam.R;

public class ViewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab03_views);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etText = findViewById(R.id.etText);
        SwitchCompat swConfirmare = findViewById(R.id.swConfirmare);
        Button btnAplica = findViewById(R.id.btnAplica);
        TextView tvRezultat = findViewById(R.id.tvRezultat);

        btnAplica.setEnabled(false);

        swConfirmare.setOnCheckedChangeListener((buttonView, isChecked) ->
                btnAplica.setEnabled(isChecked));

        btnAplica.setOnClickListener(view -> {
            String text = etText.getText().toString().trim();
            if (text.isEmpty()) {
                etText.setError(getString(R.string.lab03_eroare_gol));
            } else {
                tvRezultat.setText(text);
                Toast.makeText(this, R.string.lab03_toast_succes, Toast.LENGTH_SHORT).show();
            }
        });
    }
}