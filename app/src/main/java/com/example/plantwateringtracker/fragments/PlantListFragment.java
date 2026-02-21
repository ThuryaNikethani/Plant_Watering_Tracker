package com.example.plantwateringtracker.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantwateringtracker.R;
import com.example.plantwateringtracker.activities.PlantDetailActivity;
import com.example.plantwateringtracker.adapters.PlantAdapter;
import com.example.plantwateringtracker.models.Plant;
import com.example.plantwateringtracker.models.PlantRepository;

import java.util.List;

public class PlantListFragment extends Fragment implements PlantAdapter.OnPlantClickListener {

    public static final String TAG = "PlantListFragment";
    private static final String ARG_SHOW_ALL = "show_all";

    private PlantAdapter adapter;
    private PlantRepository repository;
    private boolean showAll;

    public static PlantListFragment newInstance(boolean showAll) {
        PlantListFragment fragment = new PlantListFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_SHOW_ALL, showAll);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = PlantRepository.getInstance();
        if (getArguments() != null) {
            showAll = getArguments().getBoolean(ARG_SHOW_ALL, true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plant_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        TextView tvEmpty = view.findViewById(R.id.tvEmpty);

        List<Plant> plants = showAll ? repository.getAllPlants() : repository.getPlantsNeedingWater();

        if (plants.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
            tvEmpty.setText(showAll ? "No plants yet. Add one!" : "🎉 All plants are watered!");
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
            adapter = new PlantAdapter(plants, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    public void refreshList() {
        if (getView() == null) return;
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        TextView tvEmpty = getView().findViewById(R.id.tvEmpty);

        List<Plant> plants = showAll ? repository.getAllPlants() : repository.getPlantsNeedingWater();

        if (plants.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
            tvEmpty.setText(showAll ? "No plants yet.\nTap '+' to add one!" : "🎉 All plants are watered!");
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
            if (adapter == null) {
                adapter = new PlantAdapter(plants, this);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            } else {
                adapter.updatePlants(plants);
            }
        }
    }

    @Override
    public void onPlantClick(Plant plant) {
        // Navigate to PlantDetailActivity using an Intent
        Intent intent = new Intent(getActivity(), PlantDetailActivity.class);
        intent.putExtra(PlantDetailActivity.EXTRA_PLANT_ID, plant.getId());
        startActivity(intent);
    }

    @Override
    public void onWaterClick(Plant plant) {
        repository.waterPlant(plant.getId());
        refreshList();
    }
}
