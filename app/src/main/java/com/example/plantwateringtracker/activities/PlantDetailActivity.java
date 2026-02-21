package com.example.plantwateringtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plantwateringtracker.R;
import com.example.plantwateringtracker.models.Plant;
import com.example.plantwateringtracker.models.PlantRepository;

public class PlantDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PLANT_ID = "extra_plant_id";

    private PlantRepository repository;
    private Plant plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        repository = PlantRepository.getInstance();
        int plantId = getIntent().getIntExtra(EXTRA_PLANT_ID, -1);
        plant = repository.getPlantById(plantId);

        if (plant == null) {
            finish();
            return;
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(plant.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        bindViews();
    }

    private void bindViews() {
        TextView tvEmoji = findViewById(R.id.tvDetailEmoji);
        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvSpecies = findViewById(R.id.tvDetailSpecies);
        TextView tvInterval = findViewById(R.id.tvDetailInterval);
        TextView tvLastWatered = findViewById(R.id.tvDetailLastWatered);
        TextView tvNextWatering = findViewById(R.id.tvDetailNextWatering);
        TextView tvNotes = findViewById(R.id.tvDetailNotes);
        Button btnWater = findViewById(R.id.btnDetailWater);
        Button btnShare = findViewById(R.id.btnDetailShare);
        Button btnDelete = findViewById(R.id.btnDetailDelete);

        tvEmoji.setText(plant.getEmoji());
        tvName.setText(plant.getName());
        tvSpecies.setText(plant.getSpecies());
        tvInterval.setText("Every " + plant.getWateringIntervalDays() + " days");
        tvLastWatered.setText("Last watered: " + plant.getLastWateredFormatted());
        tvNextWatering.setText("Next watering: " + plant.getNextWateringFormatted());
        tvNotes.setText(plant.getNotes().isEmpty() ? "No notes added." : plant.getNotes());

        btnWater.setOnClickListener(v -> {
            repository.waterPlant(plant.getId());
            plant = repository.getPlantById(plant.getId());
            bindViews();
            Toast.makeText(this, plant.getName() + " watered! 💧", Toast.LENGTH_SHORT).show();
        });

        // Share care tip using Intent
        btnShare.setOnClickListener(v -> {
            String shareText = "🌿 My plant \"" + plant.getName() + "\" (" + plant.getSpecies() + ") "
                    + "needs watering every " + plant.getWateringIntervalDays() + " days. "
                    + "Next watering: " + plant.getNextWateringFormatted()
                    + " — tracked with Plant Watering Tracker!";
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            startActivity(Intent.createChooser(shareIntent, "Share plant care tip"));
        });

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Plant")
                    .setMessage("Are you sure you want to remove " + plant.getName() + "?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        repository.removePlant(plant.getId());
                        Toast.makeText(this, plant.getName() + " removed.", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
