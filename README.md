# MedApp

An Android app in Java for managing a stock of medicines. Scan a box's barcode, fill in its details, and browse, edit or delete medicines. Data is stored on the phone with Room and can be synced with a PHP web service.

## Features

- **Barcode scanning** with the camera (ZXing) to fill in a medicine's code
- **Add a medicine** with its name, price, laboratory, quantity, batch number, manufacturing and expiry dates (date picker), dosage form, therapeutic class, generic name, storage duration and whether it's reimbursable
- **Browse** all medicines in a list
- **View, edit and delete** a medicine
- **Local database** with Room (DAO, repository and ViewModel, MVVM)
- **Web service sync** with Volley: list, add and delete medicines on a PHP backend
- Splash screen

## Built with

- Java, Android SDK (min SDK 25, target SDK 30)
- Room
- Volley
- ZXing Android Embedded
- Gradle

## Run it

1. Clone the repo and open it in Android Studio:
   ```bash
   git clone https://github.com/hamzakhalem/MEDAPP.git
   ```
2. Let Gradle sync, then run the `app` configuration on a device or emulator (Android 7.1+).
3. Optional: the sync feature calls PHP scripts on a local server (`GetListFromMedApp.php`, `AjouterMedApp.php`, `DeleteMedApp.php`). Change the server address in `repository/WebServiceRepository.java` to point to your own backend.

## Project structure

```
app/src/main/java/com/example/medapp/
  entity/Medicament.java        Room entity
  DAO/MedcamentDao.java         Queries: insert, update, list, find and delete by barcode
  db/MedRoomDB.java             Room database
  repository/                   Local repository and web service repository
  viewmodel/MedViewModel.java   ViewModel for the screens
  Adapter/AdapterMed.java       RecyclerView adapter
  AddMedActivity, ConsulterActvity, Consulter2Activity, MedList   Screens
  CaptureAct.java               Barcode scanner screen
```
