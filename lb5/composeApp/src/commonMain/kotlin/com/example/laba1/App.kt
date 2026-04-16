package com.example.laba1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.laba1.ui.*
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

// 1. Тема додатка
val MyPrimary = Color(0xFF1976D2)
val MySecondary = Color(0xFF388E3C)
val MyTypography = Typography(
    headlineMedium = TextStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 24.sp)
)

@Composable
fun App() {
    LaunchedEffect(Unit) {
        Napier.base(DebugAntilog())
    }

    MaterialTheme(
        colorScheme = lightColorScheme(primary = MyPrimary, secondary = MySecondary),
        typography = MyTypography
    ) {
        var currentRoute by remember { mutableStateOf("main") }
        var showTimeZoneDialog by remember { mutableStateOf(false) }
        var showResultDialog by remember { mutableStateOf(false) }

        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == "timezone",
                        onClick = { currentRoute = "timezone" },
                        label = { Text("Зони") },
                        icon = { Text("⚙️") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == "meeting",
                        onClick = { currentRoute = "meeting" },
                        label = { Text("Зустріч") },
                        icon = { Text("📅") }
                    )
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (currentRoute) {
                    "main" -> MainScreen(onNavigate = { currentRoute = it })
                    "timezone" -> TimeZoneScreen(onShowDialog = { showTimeZoneDialog = true })
                    "meeting" -> MeetingScreen(onSearchClick = { showResultDialog = true })
                    "buttons" -> ButtonsScreen(onBack = { currentRoute = "main" })
                    "checkboxes" -> CheckboxesScreen(onBack = { currentRoute = "main" })
                    "chips" -> ChipsScreen(onBack = { currentRoute = "main" })
                    "datepicker" -> DatePickerScreen(onBack = { currentRoute = "main" })
                    "dialog" -> DialogScreen(onBack = { currentRoute = "main" })
                    "divider" -> DividerScreen(onBack = { currentRoute = "main" })
                    "progress" -> ProgressScreen(onBack = { currentRoute = "main" })
                    "radio" -> RadioButtonsScreen(onBack = { currentRoute = "main" })
                    "switch" -> SwitchScreen(onBack = { currentRoute = "main" })
                    "timepicker" -> TimePickerScreen(onBack = { currentRoute = "main" })
                }
            }

            if (showTimeZoneDialog) {
                TimeZoneSelectionDialog(
                    onDismiss = { showTimeZoneDialog = false },
                    allTimeZones = listOf("Europe/Kyiv", "London", "New York", "Tokyo")
                )
            }

            if (showResultDialog) {
                SearchResultDialog(
                    onDismiss = { showResultDialog = false },
                    searchResult = "Знайдено підходящий час: 16:00 - 18:00"
                )
            }
        }
    }
}

@Composable
fun MainScreen(onNavigate: (String) -> Unit) {
    val dateTimeHelper = remember { DateTimeHelper() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Час: ${dateTimeHelper.getCurrentDateTime()}")
        Spacer(Modifier.height(24.dp))

        Text("Лабораторна №4", style = MaterialTheme.typography.headlineMedium)
        Button(
            onClick = { onNavigate("timezone") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Відкрити TimeZone App")
        }

        Spacer(Modifier.height(16.dp))
        Text("Компоненти (ЛР3)", style = MaterialTheme.typography.titleMedium)

        val menu = listOf(
            "Buttons" to "buttons", "Checkboxes" to "checkboxes", "Chips" to "chips",
            "Datepicker" to "datepicker", "Dialog" to "dialog", "Divider" to "divider",
            "Progress" to "progress", "Radio" to "radio", "Switch" to "switch", "Timepicker" to "timepicker"
        )

        menu.forEach { (name, route) ->
            Button(
                onClick = { onNavigate(route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) { Text(name) }
        }
    }
}

@Composable
fun ScreenWrapper(title: String, onBack: () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("⬅ Назад") }
            Text(title, style = MaterialTheme.typography.headlineSmall)
        }
        Spacer(Modifier.height(16.dp))
        content()
    }
}

@Composable fun ButtonsScreen(onBack: () -> Unit) = ScreenWrapper("Buttons", onBack) {
    Button(onClick = {}) { Text("Filled") }
    Text("❤️")
}

@Composable fun CheckboxesScreen(onBack: () -> Unit) = ScreenWrapper("Checkboxes", onBack) {
    var s by remember { mutableStateOf(true) }
    Checkbox(checked = s, onCheckedChange = { s = it })
}

@Composable fun ChipsScreen(onBack: () -> Unit) = ScreenWrapper("Chips", onBack) { AssistChip(onClick = {}, label = { Text("Chip") }) }
@Composable fun DatePickerScreen(onBack: () -> Unit) = ScreenWrapper("Date Picker", onBack) { Text("Datepicker Dialog") }
@Composable fun DialogScreen(onBack: () -> Unit) = ScreenWrapper("Dialog", onBack) {
    var o by remember { mutableStateOf(false) }
    Button(onClick = { o = true }) { Text("Open") }
    if (o) AlertDialog(onDismissRequest = { o = false }, confirmButton = { TextButton(onClick = { o = false }) { Text("OK") } }, title = { Text("Title") }, text = { Text("Msg") })
}
@Composable fun DividerScreen(onBack: () -> Unit) = ScreenWrapper("Divider", onBack) { HorizontalDivider() }
@Composable fun ProgressScreen(onBack: () -> Unit) = ScreenWrapper("Progress", onBack) { CircularProgressIndicator() }
@Composable fun RadioButtonsScreen(onBack: () -> Unit) = ScreenWrapper("Radio", onBack) { RadioButton(selected = true, onClick = {}) }
@Composable fun SwitchScreen(onBack: () -> Unit) = ScreenWrapper("Switch", onBack) {
    var c by remember { mutableStateOf(false) }
    Switch(checked = c, onCheckedChange = { c = it })
}
@Composable fun TimePickerScreen(onBack: () -> Unit) = ScreenWrapper("Time Picker", onBack) { Text("Timepicker Dialog") }