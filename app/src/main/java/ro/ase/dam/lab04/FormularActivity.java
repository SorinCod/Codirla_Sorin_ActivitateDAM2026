package ro.ase.dam.lab04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ro.ase.dam.R;

public class FormularActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab04_formular);
        int pad = (int) (16 * getResources().getDisplayMetrics().density);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + pad, systemBars.top + pad, systemBars.right + pad, systemBars.bottom + pad);
            return insets;
        });

        EditText etMarca = findViewById(R.id.etMarca);
        EditText etModel = findViewById(R.id.etModel);
        EditText etRezolutie = findViewById(R.id.etRezolutie);
        CheckBox cbStabilizare = findViewById(R.id.cbStabilizare);
        RadioGroup rgTip = findViewById(R.id.rgTip);
        Spinner spSenzor = findViewById(R.id.spSenzor);
        RatingBar rbRating = findViewById(R.id.rbRating);
        SwitchCompat swWifi = findViewById(R.id.swWifi);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        String[] senzori = {"Full Frame", "APS-C", "Micro 4/3", "1 inch"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, senzori);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSenzor.setAdapter(adapter);

        btnSalveaza.setOnClickListener(view -> {
            String marca = etMarca.getText().toString().trim();
            String model = etModel.getText().toString().trim();
            String rezolutieText = etRezolutie.getText().toString().trim();

            if (marca.isEmpty()) {
                etMarca.setError("Câmp obligatoriu");
                return;
            }
            if (model.isEmpty()) {
                etModel.setError("Câmp obligatoriu");
                return;
            }

            int rezolutie;
            try {
                rezolutie = Integer.parseInt(rezolutieText);
            } catch (NumberFormatException e) {
                etRezolutie.setError("Introdu un număr valid");
                return;
            }

            TipAparat tip;
            int idSelectat = rgTip.getCheckedRadioButtonId();
            if (idSelectat == R.id.rbMirrorless) {
                tip = TipAparat.MIRRORLESS;
            } else if (idSelectat == R.id.rbCompact) {
                tip = TipAparat.COMPACT;
            } else {
                tip = TipAparat.DSLR;
            }

            AparatFoto aparat = new AparatFoto(
                    marca,
                    model,
                    rezolutie,
                    cbStabilizare.isChecked(),
                    tip,
                    spSenzor.getSelectedItem().toString(),
                    rbRating.getRating(),
                    swWifi.isChecked()
            );

            Intent rezultat = new Intent();
            rezultat.putExtra("aparat", aparat);
            setResult(RESULT_OK, rezultat);
            finish();
        });
    }
}