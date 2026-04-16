import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import com.example.laba1.App // Переконайся, що шлях до App правильний

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TimeZone App - Lab 5"
    ) {
        // Додавання меню та гарячих клавіш (п. 2.с лаби)
        MenuBar {
            Menu("Файл") {
                Item(
                    "Новий пояс",
                    shortcut = KeyShortcut(Key.N, ctrl = true),
                    onClick = {
                        // Логіка буде викликатися при натисканні Ctrl+N
                        println("Гаряча клавіша Ctrl+N спрацювала")
                    }
                )
                Item("Вихід", onClick = ::exitApplication)
            }
        }

        // Запуск основного інтерфейсу додатка
        App()
    }
}