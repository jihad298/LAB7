
# 🌟 Stars Gallery — Android Application

## Overview

Android Java application displaying a scrollable catalog of actors with images and ratings.
The project demonstrates RecyclerView architecture, service-based data management, real-time filtering, and basic UI interactions such as sharing and editing.

---

## Tech Stack

* Java (Android)
* RecyclerView
* Glide (image loading)
* CircleImageView
* AndroidX components
* Minimum SDK: 24

---

## Architecture

MVC-style separation:

* **Model** → `Star` (data entity)
* **Service Layer** → `StarService` (Singleton, in-memory storage)
* **DAO Layer** → `IDao<T>` (generic CRUD contract)
* **UI Layer** → Activities + Adapter

---

## Project Structure

```text id="p1"
ui/
  SplashActivity
  ListActivity

beans/
  Star

dao/
  IDao<T>

service/
  StarService (Singleton)

adapter/
  StarAdapter (RecyclerView + Filter)
```

---

## Core Features

### Splash Screen

* Animated entry screen
* Delayed navigation to main activity
* Uses view animations and timed transition

---

### Star List

* RecyclerView-based scrolling list
* Circular avatar images
* Name + rating display per item
* Optimized rendering via ViewHolder pattern

---

### Search

* Real-time filtering on actor name
* Case-insensitive search logic
* Updates list dynamically while typing

---

### Share

* System share intent integration
* Sends predefined text via Android share sheet

---

### Edit Rating (Popup)

* Custom AlertDialog per item
* RatingBar input
* Updates underlying data through service layer
* UI refresh limited to modified row

---

## Data Model

Each `Star` contains:

* ID (auto-generated)
* Name
* Image URL
* Rating

Encapsulation is enforced via private fields and getters/setters.

---

## Service Layer

`StarService`:

* Singleton pattern (single shared instance)
* In-memory ArrayList storage
* Implements CRUD operations via `IDao<T>`
* Preloaded dataset of actors

---

## Adapter Behavior

`StarAdapter`:

* Binds data to RecyclerView rows
* Uses ViewHolder pattern for performance
* Loads images asynchronously via Glide
* Implements `Filterable` for search functionality

---

## Key Technical Concepts

* Activity lifecycle management
* Intent-based navigation
* Singleton design pattern
* Generic DAO abstraction
* RecyclerView optimization
* ViewHolder caching
* Real-time filtering logic
* Dialog-based UI updates
* Asynchronous image loading

---

## Permissions

```xml id="p2"
<uses-permission android:name="android.permission.INTERNET" />
```

---

## Run Instructions

1. Open project in Android Studio
2. Sync Gradle dependencies
3. Run on emulator or physical device (API 24+)
4. App starts with Splash screen → List screen

---

## Expected Behavior

* Animated splash screen on launch
* Scrollable list of actors with images and ratings
* Live search filtering in toolbar
* Share action via system dialog
* Editable rating via popup dialog
* Immediate UI update after modifications

---

## Engineering Summary

This project demonstrates:

* Clean separation of layers (MVC-like structure)
* Efficient list rendering (RecyclerView + ViewHolder)
* Service abstraction over raw data
* Real-time UI filtering
* Basic Android navigation and interaction patterns

---
