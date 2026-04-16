package com.example.laba1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
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
import com.example.laba1.ui.* // Твої нові екрани: TimeZoneScreen, MeetingScreen, діалоги
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
        Napier.d("Запуск ЛР4")
    }

    MaterialTheme(
        colorScheme = lightColorScheme(primary = MyPrimary, secondary = MySecondary),
        typography = MyTypography
    ) {
        val navController = rememberNavController()

        // Стани для діалогів ЛР4 (п. 2 та п. 3 лаби)
        var showTimeZoneDialog by remember { mutableStateOf(false) }
        var showResultDialog by remember { mutableStateOf(false) }

        Scaffold(
            bottomBar = {
                // Нижня навігація для швидкого доступу (п. 1 лаби)
                NavigationBar {
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("timezone") },
                        label = { Text("Зони") },
                        icon = { Icon(Icons.Default.Settings, null) }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("meeting") },
                        label = { Text("Зустріч") },
                        icon = { Icon(Icons.Default.DateRange, null) }
                    )
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(navController = navController, startDestination = "main") {
                    // Головний екран
                    composable("main") { MainScreen(navController) }

                    // Екрани ЛР4
                    composable("timezone") {
                        TimeZoneScreen(onShowDialog = { showTimeZoneDialog = true })
                    }
                    composable("meeting") {
                        MeetingScreen(onSearchClick = { showResultDialog = true })
                    }

                    // Екрани ЛР3 (Компоненти)
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

            // Логіка відображення діалогів ЛР4
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
fun MainScreen(navController: NavController) {
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
            onClick = { navController.navigate("timezone") },
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
                onClick = { navController.navigate(route) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) { Text(name) }
        }
    }
}

// --- ДОПОМІЖНІ ЕКРАНИ ТА WRAPPER (ЛР3) ---

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