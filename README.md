# QR Reader Android App

This is a simple Android app that allows users to scan QR codes using their phone's camera and save the scanned data to a backend server via Flask.

## Installation

1. Clone this repository to your local machine.
2. Install the required libraries by running `pip install -r requirements.txt` in the `backend/` directory.
3. Install the APK file to your Android device or emulator.

## Usage

1. Open the app on your Android device or emulator.
2. Point the camera at the QR code you want to scan.
3. The scanned data will be displayed on the screen.
4. Tap the "Save to Backend" button to save the data to the Flask backend server.

## Code Description

### `app/`

This folder contains the source code for the Android app. The app uses the `Zxing` library to scan QR codes and `Retrofit` library to make HTTP requests to the Flask backend.

### `backend/` => Refactoring upcoming

This folder contains the Flask backend server. The server receives POST requests from the Android app and saves the data to a database.

### `models/`

This folder contains the database models used by the Flask backend.

### `requirements.txt`

This file contains a list of required libraries for the Flask backend.

## License

This app is released under the MIT License. See the LICENSE file for more information.
