package com.example.permissionchecker

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

class PermissionChecker(private val context: Context) {

    private val queryEngine = PermissionQueryEngine(context)

    fun getAppsByPermissions(userFriendlyPermissions: List<String>): List<AppInfo> {
        if (userFriendlyPermissions.isEmpty()) {
            throw IllegalArgumentException("Permission list cannot be empty")
        }

        val androidPermissions = PermissionMapper.toAndroidPermissions(userFriendlyPermissions)

        if (androidPermissions.isEmpty()) {
            throw IllegalArgumentException(
                "No valid permissions found. Please check your input. "
            )
        }
        return queryEngine.queryAppsByPermissions(androidPermissions)
    }

    fun openAppSettings(packageName: String) {
        if (packageName.isBlank()) {
            throw IllegalArgumentException("Package name cannot be empty")
        }

        try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalStateException("Failed to open app settings for package: $packageName", e)
        }
    }

    fun isValidPermission(userFriendlyPermission: String): Boolean {
        return PermissionMapper.isValidPermission(userFriendlyPermission)
    }
}