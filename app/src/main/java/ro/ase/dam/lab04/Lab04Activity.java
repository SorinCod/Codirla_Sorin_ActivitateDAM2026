package ro.ase.dam.lab04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ro.ase.dam.R;

public class Lab04Activity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;

    private TextView tvInfo;
    private TextView tvMarca;
    private TextView tvModel;
    private TextView tvRezolutie;
    private TextView tvStabilizare;
    private TextView tvTip;
    private TextView tvSenzor;
    private TextView tvRating;
    private TextView tvWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab04);
        int pad = (int) (16 * getResources().getDisplayMetrics().density);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + pad, systemBars.top + pad, systemBars.right + pad, systemBars.bottom + pad);
            return insets;
        });

        tvInfo = findViewById(R.id.tvInfo);
        tvMarca = findViewById(R.id.tvMarca);
        tvModel = findViewById(R.id.tvModel);
        tvRezolutie = findViewById(R.id.tvRezolutie);
        tvStabilizare = findViewById(R.id.tvStabilizare);
        tvTip = findViewById(R.id.tvTip);
        tvSenzor = findViewById(R.id.tvSenzor);
        tvRating = findViewById(R.id.tvRating);
        tvWifi = findViewById(R.id.tvWifi);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        AparatFoto aparat = IntentCompat.getParcelableExtra(
                                result.getData(), "aparat", AparatFoto.class);
                        if (aparat != null) {
                            afiseazaAparat(aparat);
                        }
                    }
                });

        Button btnAdauga = findViewById(R.id.btnAdauga);
        btnAdauga.setOnClickListener(view ->
                launcher.launch(new Intent(this, FormularActivity.class)));
    }

    private void afiseazaAparat(AparatFoto aparat) {
        tvInfo.setText("Aparat foto primit:");
        tvMarca.setText("Marcă: " + aparat.getMarca());
        tvModel.setText("Model: " + aparat.getModel());
        tvRezolutie.setText("Rezoluție: " + aparat.getRezolutieMP() + " MP");
        tvStabilizare.setText("Stabilizare: " + (aparat.isStabilizare() ? "Da" : "Nu"));
        tvTip.setText("Tip: " + aparat.getTip().name());
        tvSenzor.setText("Senzor: " + aparat.getSenzor());
        tvRating.setText("Rating: " + aparat.getRating() + " / 5");
        tvWifi.setText("Wi-Fi: " + (aparat.isWifi() ? "Da" : "Nu"));
    }
}