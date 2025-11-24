package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.app.pages.AppListScreen
import com.example.app.pages.PermissionInputScreen
import com.example.permissionchecker.AppInfo
import com.example.permissionchecker.PermissionChecker

class MainActivity : ComponentActivity() {

    private lateinit var checker: PermissionChecker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checker = PermissionChecker(this)

        setContent {
            var screen by remember { mutableStateOf("permissionInput") }
            var results by remember { mutableStateOf(emptyList<AppInfo>()) }

            when (screen) {
                "permissionInput" -> {
                    PermissionInputScreen { permList ->
                        results = checker.getAppsByPermissions(permList)
                        screen = "appList"
                    }
                }
                "appList" -> {
                    AppListScreen(
                        apps = results,
                        onAppClick = { pkg -> checker.openAppSettings(pkg) },
                        onBack = { screen = "permissionInput" }
                    )
                }
            }
        }
    }
}
