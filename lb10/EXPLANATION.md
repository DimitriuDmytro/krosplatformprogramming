# Лабораторна робота №8 — Пояснення

## 📋 Що потрібно було зробити
* Перевести керування станом MVVM на ручний підхід без використання DI-контейнерів (видалити бібліотеку Koin).
* Спростити конфігурацію Gradle, прибравши сторонні DI бібліотеки.
* Переписати ініціалізацію `AboutViewModel` у точці входу інтерфейсу `App` на ручне створення об'єкта.
* Зберегти реактивне оновлення інтерфейсу за допомогою `collectAsState()` для демонстрації того, що логіка MVVM працює незалежно від обраного способу вирішення залежностей.

## 📁 Структура проєкту
Тут наведено ключові файли та папки проєкту `lb8`:
* `shared/build.gradle.kts` — оновлена конфігурація без залежностей Koin.
* `shared/src/commonMain/kotlin/com/example/lb6/`
  * [App.kt](file:///d:/AndroidStudioLaba/lb8/shared/src/commonMain/kotlin/com/example/lb6/App.kt) — інтерфейс користувача з ручним створення ViewModel.
  * [AboutViewModel.kt](file:///d:/AndroidStudioLaba/lb8/shared/src/commonMain/kotlin/com/example/lb6/AboutViewModel.kt) — опис ViewModel (ідентичний до ЛР7).
  * [Platform.kt](file:///d:/AndroidStudioLaba/lb8/shared/src/commonMain/kotlin/com/example/lb6/Platform.kt) — `expect`-оголошення платформи.

## ✅ Виконані завдання

### Пункт 1 — Видалення бібліотеки Koin
**Що зроблено:** З файлу конфігурації Gradle спільного модуля повністю видалено залежності Koin (`koin-core`, `koin-compose` тощо).  
**Де знаходиться:** [build.gradle.kts](file:///d:/AndroidStudioLaba/lb8/shared/build.gradle.kts)  
**Пояснення:** Щоб порівняти роботу DI-контейнера та ручного створення об'єктів, я очистив Gradle-файл від бібліотек `io.insert-koin`. Це зробило конфігурацію проєкту простішою та прискорило час збірки (build time), оскільки компілятору не потрібно опрацьовувати додаткові сторонні плагіни та бібліотеки.

---

### Пункт 2 — Ручне створення ViewModel
**Що зроблено:** Змінено сигнатуру функції `App` для створення екземпляра `AboutViewModel` безпосередньо в коді як значення за замовчуванням.  
**Де знаходиться:** [App.kt](file:///d:/AndroidStudioLaba/lb8/shared/src/commonMain/kotlin/com/example/lb6/App.kt#L14)  
**Як це виглядає в коді:**
```kotlin
@Composable
fun App(viewModel: AboutViewModel = AboutViewModel()) {
    // Підписуємось на стан з ViewModel
    val platformName by viewModel.platformName.collectAsState()
    
    // ... UI відмальовка
}
```
**Пояснення:** Замість виклику `koinViewModel()`, який шукає зареєстрований тип у контейнері Koin, тепер ми ініціалізуємо об'єкт напряму за допомогою конструктора `AboutViewModel()`. Оскільки клас не має складних вхідних параметрів, це дозволяє легко обійтися без DI-контейнерів.

---

### Пункт 3 — Збереження реактивності
**Що зроблено:** Незважаючи на видалення Koin, збережено підписку на `StateFlow` за допомогою `collectAsState()`, що забезпечує ідентичну поведінку інтерфейсу.  
**Де знаходиться:** [App.kt](file:///d:/AndroidStudioLaba/lb8/shared/src/commonMain/kotlin/com/example/lb6/App.kt#L16)  
**Пояснення:** Я залишив механізм `collectAsState()`, який підписує UI на зміни поля `platformName` у ViewModel. Це доводить, що сама концепція MVVM (відокремлення логіки від UI та реактивний зв'язок) є незалежною від DI-фреймворків — фреймворки лише автоматизують передачу об'єктів, але архітектурний потік даних залишається незмінним.

## 🔗 Залежності (з build.gradle.kts)
У файлі залежностей залишилися тільки базові бібліотеки:
* `libs.androidx.lifecycle.viewmodelCompose` — життєвий цикл ViewModel.
* `libs.androidx.lifecycle.runtimeCompose` — робота з життєвим циклом Compose та збиранням станів.

## 🧩 Ключові архітектурні рішення
* **Default Constructor Parameter**: Передача `viewModel` як параметра за замовчуванням `App(viewModel: AboutViewModel = AboutViewModel())` дозволяє легко підміняти її у прев'ю (`AppPreview`) чи під час тестування інтерфейсу (UI-тести), передаючи мок-об'єкти (mocks).

## ⚠️ Особливості та нюанси
* Ручна ініціалізація є чудовим та простим рішенням для невеликих проєктів. Проте, якщо ваш проєкт почне рости, і ViewModel почне вимагати передачі додаткових репозиторіїв, сервісів логування та клієнтів баз даних, ручний опис передачі цих залежностей через ланцюжок конструкторів стане занадто складним (так зване "Constructor Hell"). Саме для запобігання цьому у великих проєктах повертають Koin чи Dagger/Hilt.
