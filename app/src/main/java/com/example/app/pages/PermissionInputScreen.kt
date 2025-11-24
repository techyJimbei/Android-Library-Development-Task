package com.example.app.pages

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionInputScreen(
    onSearch: (List<String>) -> Unit
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedPermissions by remember { mutableStateOf(listOf<String>()) }

    val permissionOptions = listOf(
        "camera", "location", "storage", "contacts", "phone",
        "sms", "microphone", "calendar", "bluetooth", "notifications"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Select permission") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                },
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                permissionOptions
                    .filter { it !in selectedPermissions }
                    .forEach { permission ->
                        DropdownMenuItem(
                            text = { Text(permission) },
                            onClick = {
                                selectedPermissions = selectedPermissions + permission
                                expanded = false
                            }
                        )
                    }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Selected: ${if (selectedPermissions.isEmpty()) "None" else selectedPermissions.joinToString()}",
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (selectedPermissions.isEmpty()) {
                        Toast.makeText(context, "Select permission to search", Toast.LENGTH_SHORT).show()
                    } else {
                        onSearch(selectedPermissions)
                    }
                },
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text("Search Apps")
            }
        }
    }
}
