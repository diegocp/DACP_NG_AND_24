
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ngtv.di.initKoinDesktop
import ngtv.composeapp.generated.resources.Res
import ngtv.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
    ) {
        initKoinDesktop()
        App()
    }
}