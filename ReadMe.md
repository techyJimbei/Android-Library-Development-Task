Permission Checker Android Library and Sample App

Overview:
This project contains an Android AAR library named permissionchecker along with a sample host app. The library allows host apps to query installed applications based on permissions and provides functionality to open app settings.

Features:
- Query installed apps that have specific permissions (Camera, SMS, Location, etc.)
- Support multiple permission queries at once
- Maps user-friendly permission labels to actual Android permissions
- Open any app's settings screen
- Handle errors gracefully
- Minimum SDK 26, Target SDK 36
- Written in Kotlin

Sample App Features:
- Select permissions from a dropdown menu
- Display apps that match selected permissions
- Click an app to open its settings
- Clean UI using Jetpack Compose

Installation and Dependencies:
1. Clone the repository:
   git clone https://github.com/techyJimbei/Android-Library-Development-Task.git

2. Open the project in Android Studio.

3. The project uses Jetpack Compose, Material3, and Kotlin.

4. The permissionchecker library is included as a module. 
   To use it in another project, build the AAR:
   - Right-click the permissionchecker module in Android Studio
   - Select Build -> Build Bundle(s) / APK(s) -> Build AAR
   - The generated .aar file will be in permissionchecker/build/outputs/aar/

Running the Project:
1. Open the app module in Android Studio
2. Use a device or emulator with Android 8.0 (API 26) or higher
3. Click Run to launch the app
4. Select one or more permissions from the dropdown and click Search Apps
5. The app will display all installed apps matching the selected permissions
6. Tap an app to open its settings

Project Structure:
PermissionCheckerProject/
├─ app/                   Sample host app module
│  ├─ src/main/kotlin/com/example/app
│  │  ├─ MainActivity.kt           Launches Compose screens and connects to library
│  │  └─ pages/
│  │     ├─ PermissionInputScreen.kt   Screen to select permissions
│  │     └─ AppListScreen.kt           Screen displaying apps matching permissions
├─ permissionchecker/     Library module
│  ├─ src/main/kotlin/com/example/permissionchecker
│  │  ├─ PermissionChecker.kt      Main API class
│  │  ├─ PermissionMapper.kt       Maps user-friendly labels to Android permissions
│  │  ├─ PermissionQueryEngine.kt  Queries installed apps by permissions
│  │  └─ AppInfo.kt                Data class representing an app
├─ build.gradle            Project-level Gradle
├─ settings.gradle         Includes modules
└─ README.md               Project documentation

Notes:
- The library only queries declared permissions of installed apps and does not request runtime permissions.
- If no permissions are selected, a Toast message will prompt the user to select permissions before searching.