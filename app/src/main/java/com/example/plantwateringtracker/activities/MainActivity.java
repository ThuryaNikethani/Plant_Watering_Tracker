package com.example.plantwateringtracker.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.plantwateringtracker.R;
import com.example.plantwateringtracker.fragments.NeedsWaterFragment;
import com.example.plantwateringtracker.fragments.PlantListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(PlantListFragment.newInstance(true));
        }

        // Bottom navigation to switch between fragments
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_all) {
                loadFragment(PlantListFragment.newInstance(true));
                return true;
            } else if (id == R.id.nav_needs_water) {
                loadFragment(NeedsWaterFragment.newInstance());
                return true;
            }
            return false;
        });

        // FAB launches AddPlantActivity via Intent
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddPlantActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh current fragment when returning from other activities
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (current instanceof PlantListFragment) {
            ((PlantListFragment) current).refreshList();
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
