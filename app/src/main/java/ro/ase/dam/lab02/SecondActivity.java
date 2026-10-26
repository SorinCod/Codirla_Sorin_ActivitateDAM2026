package ro.ase.dam.lab02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ro.ase.dam.R;

public class SecondActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvMesaj = findViewById(R.id.tvMesaj);
        String mesaj = getIntent().getStringExtra("mesaj");
        tvMesaj.setText(mesaj);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String raspuns = result.getData().getStringExtra("raspuns");
                        Toast.makeText(this,
                                getString(R.string.lab02_toast_raspuns, raspuns),
                                Toast.LENGTH_LONG).show();
                    }
                });

        Button btnDeschide = findViewById(R.id.btnDeschideA3);
        btnDeschide.setOnClickListener(view ->
                launcher.launch(new Intent(this, ThirdActivity.class)));
    }
}