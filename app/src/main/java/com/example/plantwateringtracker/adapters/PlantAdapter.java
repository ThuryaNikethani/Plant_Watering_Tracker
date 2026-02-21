package com.example.plantwateringtracker.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantwateringtracker.R;
import com.example.plantwateringtracker.models.Plant;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    public interface OnPlantClickListener {
        void onPlantClick(Plant plant);
        void onWaterClick(Plant plant);
    }

    private List<Plant> plants;
    private final OnPlantClickListener listener;

    public PlantAdapter(List<Plant> plants, OnPlantClickListener listener) {
        this.plants = plants;
        this.listener = listener;
    }

    public void updatePlants(List<Plant> newPlants) {
        this.plants = newPlants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plant, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        Plant plant = plants.get(position);
        holder.bind(plant, listener);
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    static class PlantViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvEmoji, tvName, tvSpecies, tvStatus, tvBtnWater;

        PlantViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tvEmoji = itemView.findViewById(R.id.tvEmoji);
            tvName = itemView.findViewById(R.id.tvPlantName);
            tvSpecies = itemView.findViewById(R.id.tvSpecies);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvBtnWater = itemView.findViewById(R.id.btnWater);
        }

        void bind(Plant plant, OnPlantClickListener listener) {
            tvEmoji.setText(plant.getEmoji());
            tvName.setText(plant.getName());
            tvSpecies.setText(plant.getSpecies());

            long daysLeft = plant.getDaysUntilNextWatering();
            if (plant.needsWatering()) {
                tvStatus.setText("💧 Needs water now!");
                tvStatus.setTextColor(Color.parseColor("#E53935"));
                cardView.setCardBackgroundColor(Color.parseColor("#FFF3F3"));
            } else if (daysLeft == 1) {
                tvStatus.setText("⏰ Water tomorrow");
                tvStatus.setTextColor(Color.parseColor("#FB8C00"));
                cardView.setCardBackgroundColor(Color.parseColor("#FFFDE7"));
            } else {
                tvStatus.setText("✅ Next watering in " + daysLeft + " days");
                tvStatus.setTextColor(Color.parseColor("#43A047"));
                cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            }

            itemView.setOnClickListener(v -> listener.onPlantClick(plant));
            tvBtnWater.setOnClickListener(v -> listener.onWaterClick(plant));
        }
    }
}
