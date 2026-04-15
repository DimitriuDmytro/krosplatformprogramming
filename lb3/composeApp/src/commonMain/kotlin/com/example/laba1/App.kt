package com.example.laba1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

// 1. Тема
val MyPrimary = Color(0xFF1976D2)
val MySecondary = Color(0xFF388E3C)
val MyTypography = Typography(
    headlineMedium = TextStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 24.sp)
)

@Composable
fun App() {
    LaunchedEffect(Unit) {
        Napier.base(DebugAntilog())
        Napier.d("Запуск ЛР3")
    }

    MaterialTheme(colorScheme = lightColorScheme(primary = MyPrimary, secondary = MySecondary), typography = MyTypography) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "main") {
            composable("main") { MainScreen(navController) }
            composable("buttons") { ButtonsScreen(navController) }
            composable("checkboxes") { CheckboxesScreen(navController) }
            composable("chips") { ChipsScreen(navController) }
            composable("datepicker") { DatePickerScreen(navController) }
            composable("dialog") { DialogScreen(navController) }
            composable("divider") { DividerScreen(navController) }
            composable("progress") { ProgressScreen(navController) }
            composable("radio") { RadioButtonsScreen(navController) }
            composable("switch") { SwitchScreen(navController) }
            composable("timepicker") { TimePickerScreen(navController) }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val dateTimeHelper = remember { DateTimeHelper() }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Час: ${dateTimeHelper.getCurrentDateTime()}")
        Spacer(Modifier.height(24.dp))
        Text("Компоненти", style = MaterialTheme.typography.headlineMedium)

        val menu = listOf(
            "Buttons" to "buttons", "Checkboxes" to "checkboxes", "Chips" to "chips",
            "Datepicker" to "datepicker", "Dialog" to "dialog", "Divider" to "divider",
            "Progress" to "progress", "Radio" to "radio", "Switch" to "switch", "Timepicker" to "timepicker"
        )

        menu.forEach { (name, route) ->
            Button(onClick = { navController.navigate(route) }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) { Text(name) }
        }
    }
}

@Composable
fun ScreenWrapper(title: String, navController: NavController, content: @Composable ColumnScope.() -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(title, style = MaterialTheme.typography.headlineSmall)
        }
        Spacer(Modifier.height(16.dp))
        content()
    }
}

// Екрани (Завдання 4)
@Composable fun ButtonsScreen(navController: NavController) = ScreenWrapper("Buttons", navController) {
    Button(onClick = {}) { Text("Filled") }
    IconButton(onClick = {}) { Icon(Icons.Default.Favorite, "Fav") }
}
@Composable fun CheckboxesScreen(navController: NavController) = ScreenWrapper("Checkboxes", navController) {
    var s by remember { mutableStateOf(true) }
    Checkbox(checked = s, onCheckedChange = { s = it })
}
@Composable fun ChipsScreen(navController: NavController) = ScreenWrapper("Chips", navController) { AssistChip(onClick = {}, label = { Text("Chip") }) }
@Composable fun DatePickerScreen(navController: NavController) = ScreenWrapper("Date Picker", navController) { Text("Datepicker Dialog") }
@Composable fun DialogScreen(navController: NavController) = ScreenWrapper("Dialog", navController) {
    var o by remember { mutableStateOf(false) }
    Button(onClick = { o = true }) { Text("Open") }
    if (o) AlertDialog(onDismissRequest = { o = false }, confirmButton = { TextButton(onClick = { o = false }) { Text("OK") } }, title = { Text("Title") }, text = { Text("Msg") })
}
@Composable fun DividerScreen(navController: NavController) = ScreenWrapper("Divider", navController) { HorizontalDivider() }
@Composable fun ProgressScreen(navController: NavController) = ScreenWrapper("Progress", navController) { CircularProgressIndicator() }
@Composable fun RadioButtonsScreen(navController: NavController) = ScreenWrapper("Radio", navController) { RadioButton(selected = true, onClick = {}) }
@Composable fun SwitchScreen(navController: NavController) = ScreenWrapper("Switch", navController) {
    var c by remember { mutableStateOf(false) }
    Switch(checked = c, onCheckedChange = { c = it })
}
@Composable fun TimePickerScreen(navController: NavController) = ScreenWrapper("Time Picker", navController) { Text("Timepicker Dialog") }