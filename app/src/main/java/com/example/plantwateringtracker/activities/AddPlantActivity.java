package com.example.plantwateringtracker.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plantwateringtracker.R;
import com.example.plantwateringtracker.models.PlantRepository;

public class AddPlantActivity extends AppCompatActivity {

    private EditText etName, etSpecies, etInterval, etNotes;
    private Spinner spinnerEmoji;

    private final String[] EMOJIS = {"🌿", "🌵", "🌸", "🌺", "🪴", "🌻", "🌹", "🍀", "🌴", "🎋"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add New Plant");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etName = findViewById(R.id.etPlantName);
        etSpecies = findViewById(R.id.etSpecies);
        etInterval = findViewById(R.id.etInterval);
        etNotes = findViewById(R.id.etNotes);
        spinnerEmoji = findViewById(R.id.spinnerEmoji);

        ArrayAdapter<String> emojiAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, EMOJIS);
        emojiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmoji.setAdapter(emojiAdapter);

        Button btnSave = findViewById(R.id.btnSavePlant);
        btnSave.setOnClickListener(v -> savePlant());
    }

    private void savePlant() {
        String name = etName.getText().toString().trim();
        String species = etSpecies.getText().toString().trim();
        String intervalStr = etInterval.getText().toString().trim();
        String notes = etNotes.getText().toString().trim();
        String emoji = (String) spinnerEmoji.getSelectedItem();

        if (TextUtils.isEmpty(name)) {
            etName.setError("Please enter a plant name");
            return;
        }
        if (TextUtils.isEmpty(species)) {
            etSpecies.setError("Please enter the species");
            return;
        }
        if (TextUtils.isEmpty(intervalStr)) {
            etInterval.setError("Please enter watering interval");
            return;
        }

        int interval;
        try {
            interval = Integer.parseInt(intervalStr);
            if (interval <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            etInterval.setError("Enter a valid number of days");
            return;
        }

        PlantRepository.getInstance().addPlant(name, species, interval, emoji, notes);
        Toast.makeText(this, name + " added! 🌱", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
