package com.example.permissionchecker
import android.Manifest

object PermissionMapper {

    private val permissionMap = mapOf(
        "camera" to Manifest.permission.CAMERA,
        "location" to Manifest.permission.ACCESS_FINE_LOCATION,
        "storage" to Manifest.permission.READ_EXTERNAL_STORAGE,
        "contacts" to Manifest.permission.READ_CONTACTS,
        "phone" to Manifest.permission.READ_PHONE_STATE,
        "sms" to Manifest.permission.SEND_SMS,
        "microphone" to Manifest.permission.RECORD_AUDIO,
        "calendar" to Manifest.permission.READ_CALENDAR,
        "bluetooth" to Manifest.permission.BLUETOOTH,
        "notifications" to Manifest.permission.POST_NOTIFICATIONS,

    )

    fun toAndroidPermission(userFriendlyName: String): String? {
        val normalized = userFriendlyName.trim().lowercase()
        return permissionMap[normalized]
    }
    fun toAndroidPermissions(userFriendlyNames: List<String>): List<String> {
        return userFriendlyNames.mapNotNull { toAndroidPermission(it) }
    }
    fun isValidPermission(userFriendlyName: String): Boolean {
        val normalized = userFriendlyName.trim().lowercase()
        return permissionMap.containsKey(normalized)
    }
}