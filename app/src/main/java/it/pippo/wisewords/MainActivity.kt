package it.pippo.wisewords

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import it.pippo.wisewords.ui.theme.RoomDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import it.pippo.wisewords.db.DbProverb
import it.pippo.wisewords.db.Repository
import it.pippo.wisewords.ui.theme.romaRed

class MainActivity : ComponentActivity() {
    @SuppressLint("SwitchIntDef")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { Content() }
    }
}
@SuppressLint("SwitchIntDef")
@Composable
fun Content() {
    var proverb by rememberSaveable { mutableStateOf("") }
    var filter by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val db = DbProverb.getInstance(context)
    val repository = Repository(db.proverbDao())
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            RoomDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = romaRed
                ) {
                    ShowProtraitView(proverb, filter, onChangeName = {filter=it}) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val p = repository.readFilteredNext("%$it%", 0)
                            proverb = p?.text ?: "I can't find any proverb with this word‼️"
                        }
                    }
                }
            }
        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            RoomDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = romaRed
                ) {
                    ShowLandscapeView(proverb, filter, onChangeName = {filter=it}) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val p = repository.readFilteredNext("%$it%", 0)
                            proverb = p?.text ?: "I can't find any proverb with this word‼️"
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun fontFamily(): FontFamily {
    val assets = LocalContext.current.assets
    return FontFamily(
        Font("pirate.ttf", assets)
    )
}

@Composable
fun titleFont(): FontFamily{
    val assets = LocalContext.current.assets
    return FontFamily(
        Font("Chomsky.otf", assets)
    )
}
