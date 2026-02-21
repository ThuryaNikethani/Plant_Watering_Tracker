🌿 Plant Watering Tracker
A simple Android app to help you track your houseplants and never forget to water them again!

📱 About the App
Many people forget to water their plants and they die. This app solves that by letting you:

Track all your houseplants in one place
Set how often each plant needs water
See which plants are overdue for watering
Mark plants as watered with one tap
Share plant care tips with friends via any app


✨ Features

🌱 Add plants with a nickname, species, emoji, watering interval, and care notes
💧 Mark plants as watered with one tap
⏰ Color-coded status cards — red (overdue), orange (tomorrow), green (on schedule)
🗂 Two tabs — All Plants and Needs Water via Bottom Navigation
📤 Share care tips using Android's native share sheet
🗑 Delete plants with a confirmation dialog
🌿 3 sample plants pre-loaded so the app is ready to explore immediately


🏗 Android Concepts Used
Activities (3)
ActivityPurposeMainActivityHome screen — hosts fragments and bottom navigationPlantDetailActivityFull plant info with water, share, and delete actionsAddPlantActivityForm to add a new plant
Fragments (2)
FragmentPurposePlantListFragmentDisplays all plants as scrollable cardsNeedsWaterFragmentFiltered view showing only overdue plants
Intents (3)
IntentTypePurposeMainActivity → PlantDetailActivityExplicitOpens plant detail, passes plant ID via putExtra()MainActivity → AddPlantActivityExplicitOpens add plant form via FAB buttonShare Care TipImplicitOpens Android share sheet to send plant info
App Flow
MainActivity
├── Fragment: PlantListFragment  (tab 1 - all plants)
│       └── tap card → Intent → PlantDetailActivity
│                                   └── Share → Implicit Intent
├── Fragment: NeedsWaterFragment (tab 2 - overdue only)
│       └── tap card → Intent → PlantDetailActivity
└── FAB (+) → Intent → AddPlantActivity


## 📂 Project Structure
```
app/src/main/
├── java/com/example/plantwateringtracker/
│   ├── activities/
│   │   ├── MainActivity.java
│   │   ├── PlantDetailActivity.java
│   │   └── AddPlantActivity.java
│   ├── adapters/
│   │   └── PlantAdapter.java
│   ├── fragments/
│   │   ├── PlantListFragment.java
│   │   └── NeedsWaterFragment.java
│   └── models/
│       ├── Plant.java
│       └── PlantRepository.java
└── res/
    ├── layout/
    ├── menu/
    └── values/
```

---

## 🛠 Tech Stack

- **Language:** Java (100%)
- **Min SDK:** 24 (Android 7.0 Nougat)
- **Target SDK:** 34 (Android 14)
- **Libraries:**
  - AndroidX AppCompat
  - Material Components
  - RecyclerView & CardView
  - ConstraintLayout

---

## 🌱 Future Improvements

- [ ] Persist data using Room Database
- [ ] Add notification reminders using WorkManager
- [ ] Add plant photos via camera or gallery
- [ ] Dark mode support
- [ ] Widget for home screen

---

## 👨‍💻 Author

Made with ❤️ and Java
