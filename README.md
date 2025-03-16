# Jetpack Compose Navigation Application

This project demonstrates how to implement navigation in a Jetpack Compose application. It includes features such as dynamic **TopAppBar**, **BottomNavigation**, and passing data between screens.

## Features

### Basic Navigation Setup
- Implemented Jetpack Navigation to navigate between two screens.
- Configured a **NavHost** with appropriate composable destinations.
- Added navigation buttons to move between screens.

### Dynamic TopAppBar Implementation
- Added a **TopAppBar** to the second screen with a custom title.
- Configured the navigation to dynamically change the title when navigating between screens.
- Ensured the title updates correctly during navigation.

### TopAppBar Navigation Configuration
- Removed the default back button from the **TopAppBar** of the first screen.
- Customized the **TopAppBar** options using appropriate Compose functions.
- Tested the navigation to ensure the back button appears only on the second and third screens.

### Bottom Navigation Bar Integration
- Created a **BottomNavigation** component for three different screens.
- Added appropriate icons and text labels for each **BottomNavigationItem**.
- Implemented navigation functionality between the screens using **NavController**.

### Saving and Displaying Information
- Created a **TextField** to add information on the first screen.
- Added a **Save** button in the **TopAppBar**.
- After clicking the **Save** button, the app navigates to the second screen, where the information entered on the first screen is displayed.

## How to Run the Project

### Clone the Repository
```bash
git clone https://github.com/AbdallahLearn/W5D1_Navigation_Assignment.git
```

### Open the Project
- Open the project in **Android Studio**.

### Build and Run
- Build the project and run it on an emulator or a physical device.

## Screenshots

### First Screen
- Displays a **TextField** for entering information and a "Save" button.

### Second Screen
- Displays the information entered on the first screen.

### Bottom Navigation
- Shows the **BottomNavigation** bar with icons and labels for three screens.

## Code Structure

- **MainActivity.kt**:
  - Contains the **NavHost** and **Scaffold** for the app.
  - Handles navigation between screens.

- **FirstScreen.kt**:
  - Contains the **TextField** and "Save" button.
  - Passes the entered data to the second screen.

- **SecondScreen.kt**:
  - Displays the data passed from the first screen.
  - Includes a **TopAppBar** with a dynamic title.

- **ThirdScreen.kt**:
  - A placeholder screen for demonstrating navigation.

- **Screen.kt**:
  - Defines the sealed class for navigation routes.

## Commits

- **Basic Navigation Setup**: Implemented navigation between two screens.
- **Dynamic TopAppBar Implementation**: Added a dynamic **TopAppBar** to the second screen.
- **TopAppBar Navigation Configuration**: Customized the **TopAppBar** and removed the default back button from the first screen.
- **Bottom Navigation Bar Integration**: Added a **BottomNavigation** bar with three screens.
- **Saving and Displaying Information**: Implemented data passing between the first and second screens.

## Dependencies

### Jetpack Compose Navigation
```gradle
implementation "androidx.navigation:navigation-compose:2.7.7"
```

### Material 3
```gradle
implementation "androidx.compose.material3:material3:1.2.0"
```

## Author
Abdullah Aljohani



