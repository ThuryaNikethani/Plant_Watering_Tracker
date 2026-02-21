package com.example.plantwateringtracker.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple in-memory data repository (singleton).
 * In a real app, this would use Room database or SharedPreferences.
 */
public class PlantRepository {

    private static PlantRepository instance;
    private final List<Plant> plants = new ArrayList<>();
    private int nextId = 1;

    private PlantRepository() {
        // Seed with some sample plants
        plants.add(new Plant(nextId++, "Sunny", "Aloe Vera", 14, "🌵", "Keep near window. Very drought tolerant."));
        plants.add(new Plant(nextId++, "Bella", "Peace Lily", 7, "🌸", "Loves humidity. Droops when thirsty."));
        plants.add(new Plant(nextId++, "Greeny", "Pothos", 10, "🌿", "Can tolerate low light. Easy beginner plant."));
    }

    public static PlantRepository getInstance() {
        if (instance == null) {
            instance = new PlantRepository();
        }
        return instance;
    }

    public List<Plant> getAllPlants() {
        return new ArrayList<>(plants);
    }

    public List<Plant> getPlantsNeedingWater() {
        List<Plant> result = new ArrayList<>();
        for (Plant p : plants) {
            if (p.needsWatering()) result.add(p);
        }
        return result;
    }

    public Plant getPlantById(int id) {
        for (Plant p : plants) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public void addPlant(String name, String species, int intervalDays, String emoji, String notes) {
        plants.add(new Plant(nextId++, name, species, intervalDays, emoji, notes));
    }

    public void removePlant(int id) {
        plants.removeIf(p -> p.getId() == id);
    }

    public void waterPlant(int id) {
        Plant p = getPlantById(id);
        if (p != null) p.waterNow();
    }
}
