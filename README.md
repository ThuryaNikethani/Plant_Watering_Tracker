# 🌿 Plant Watering Tracker

A simple Android app to track your houseplants and never forget to water them again!

---


## ✨ Features

- 🌱 **Add plants** with a nickname, species, emoji, watering interval, and care notes
- 💧 **Mark plants as watered** with one tap
- ⏰ **Smart status indicators** — shows when each plant needs water (overdue, tomorrow, or on schedule)
- 🗂 **Two views** — All Plants and Needs Water tabs via Bottom Navigation
- 📤 **Share care tips** for any plant via Android's native share sheet
- 🗑 **Delete plants** with confirmation dialog

---

## 🏗 Architecture & Android Concepts Used

| Concept | Where Used |
|---|---|
| **Activities** | `MainActivity`, `PlantDetailActivity`, `AddPlantActivity` |
| **Fragments** | `PlantListFragment` (all plants), `NeedsWaterFragment` (needs water tab) |
| **Intents (Explicit)** | Navigating from `MainActivity` → `PlantDetailActivity` and `AddPlantActivity` |
| **Intents (Implicit)** | Sharing plant care tips via `ACTION_SEND` intent |
| **RecyclerView + Adapter** | `PlantAdapter` rendering plant cards |
| **BottomNavigationView** | Switching between fragments |
| **FloatingActionButton** | Launching Add Plant screen |
| **AlertDialog** | Confirming plant deletion |

---

## 📂 Project Structure

```
app/src/main/
├── java/com/example/plantwateringtracker/
│   ├── activities/
│   │   ├── MainActivity.java          # Entry point, hosts fragments
│   │   ├── PlantDetailActivity.java   # Plant detail view + actions
│   │   └── AddPlantActivity.java      # Form to add a new plant
│   ├── adapters/
│   │   └── PlantAdapter.java          # RecyclerView adapter
│   ├── fragments/
│   │   ├── PlantListFragment.java     # Reusable list fragment
│   │   └── NeedsWaterFragment.java    # Filtered "needs water" view
│   └── models/
│       ├── Plant.java                 # Plant data model
│       └── PlantRepository.java       # In-memory data store (singleton)
└── res/
    ├── layout/                        # XML layouts
    ├── menu/                          # Navigation menus
    └── values/                        # Strings, colors, themes
```

---

## 🛠 Tech Stack

- **Language:** Java
- **Min SDK:** 24 (Android 7.0 Nougat)
- **Target SDK:** 34 (Android 14)
- **Libraries:**
  - AndroidX AppCompat
  - Material Components
  - RecyclerView
  - CardView
  - ConstraintLayout

---

## 🌱 Future Improvements

- [ ] Persist data using **Room Database**
- [ ] Add **notification reminders** using WorkManager
- [ ] Add **plant photos** via camera or gallery
- [ ] Dark mode support
- [ ] Export plant list to CSV

---

## 👨‍💻 Author

Made with ❤️ and Java

---
