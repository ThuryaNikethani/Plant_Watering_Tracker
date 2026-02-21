package com.example.plantwateringtracker.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Plant implements Serializable {

    private int id;
    private String name;
    private String species;
    private int wateringIntervalDays;
    private long lastWateredTimestamp;
    private String emoji;
    private String notes;

    public Plant(int id, String name, String species, int wateringIntervalDays, String emoji, String notes) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.wateringIntervalDays = wateringIntervalDays;
        this.lastWateredTimestamp = System.currentTimeMillis();
        this.emoji = emoji;
        this.notes = notes;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getWateringIntervalDays() { return wateringIntervalDays; }
    public long getLastWateredTimestamp() { return lastWateredTimestamp; }
    public String getEmoji() { return emoji; }
    public String getNotes() { return notes; }

    public void setName(String name) { this.name = name; }
    public void setSpecies(String species) { this.species = species; }
    public void setWateringIntervalDays(int days) { this.wateringIntervalDays = days; }
    public void setLastWateredTimestamp(long ts) { this.lastWateredTimestamp = ts; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public void setNotes(String notes) { this.notes = notes; }

    public void waterNow() {
        this.lastWateredTimestamp = System.currentTimeMillis();
    }

    public long getDaysUntilNextWatering() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastWateredTimestamp;
        long elapsedDays = elapsed / (1000L * 60 * 60 * 24);
        return wateringIntervalDays - elapsedDays;
    }

    public boolean needsWatering() {
        return getDaysUntilNextWatering() <= 0;
    }

    public String getLastWateredFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return sdf.format(new Date(lastWateredTimestamp));
    }

    public String getNextWateringFormatted() {
        long nextTs = lastWateredTimestamp + ((long) wateringIntervalDays * 24 * 60 * 60 * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        return sdf.format(new Date(nextTs));
    }
}
