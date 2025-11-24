package com.example.permissionchecker

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

internal class PermissionQueryEngine(private val context: Context) {

    private val packageManager: PackageManager = context.packageManager

    fun queryAppsByPermissions(permissions: List<String>): List<AppInfo> {
        if (permissions.isEmpty()) {
            return emptyList()
        }

        val apps = mutableListOf<AppInfo>()

        try {
            val installedPackages = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getInstalledPackages(
                    PackageManager.PackageInfoFlags.of(PackageManager.GET_PERMISSIONS.toLong())
                )
            } else {
                @Suppress("DEPRECATION")
                packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS)
            }

            for (packageInfo in installedPackages) {
                val requestedPermissions = packageInfo.requestedPermissions

                if (requestedPermissions != null) {
                    val matchingPermissions = requestedPermissions.filter { it in permissions }

                    if (matchingPermissions.isNotEmpty()) {
                        val appInfo = AppInfo(
                            appName = packageInfo.applicationInfo?.loadLabel(packageManager).toString(),
                            packageName = packageInfo.packageName,
                            appIcon = try {
                                packageInfo.applicationInfo?.loadIcon(packageManager)
                            } catch (e: Exception) {
                                null
                            },
                            permissions = matchingPermissions
                        )
                        apps.add(appInfo)
                    }
                }
            }

            apps.sortBy { it.appName.lowercase() }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return apps
    }
}