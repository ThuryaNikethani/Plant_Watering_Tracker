package com.example.plantwateringtracker.fragments;

/**
 * Shows only plants that need watering.
 * Reuses PlantListFragment with showAll = false.
 */
public class NeedsWaterFragment extends PlantListFragment {

    public static final String TAG = "NeedsWaterFragment";

    public static NeedsWaterFragment newInstance() {
        NeedsWaterFragment fragment = new NeedsWaterFragment();
        android.os.Bundle args = new android.os.Bundle();
        args.putBoolean("show_all", false);
        fragment.setArguments(args);
        return fragment;
    }
}
