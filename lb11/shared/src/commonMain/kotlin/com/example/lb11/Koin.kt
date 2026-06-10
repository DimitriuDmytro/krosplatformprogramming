import network.*
import org.koin.dsl.module
import org.koin.core.context.startKoin

val appModule = module {
    single { createHttpClient() }
    single<ApiService> { ApiServiceImpl(get()) }
    single { Repository(get()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}