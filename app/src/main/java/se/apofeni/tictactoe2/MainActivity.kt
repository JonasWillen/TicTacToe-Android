package se.apofeni.tictactoe2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import se.apofeni.tictactoe2.ui.theme.TicTacToeTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.compose.material3.Button
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.Locale

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener  {
    private val viewModel: TicTacToeVM by viewModels()
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize TextToSpeech object
        tts = TextToSpeech(this, this)

        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )

                        InfoText(viewModel = viewModel)
                        BoardView()

                    }
                    }

                }
            }
        }

    // Called when TTS is initialized
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // Set language for the TTS engine
            val result = tts.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported or missing data")
            } else {
                // If successful, start speaking
                speakOut("Hello, this is a voice-over example.")
            }
        } else {
            Log.e("TTS", "Initialization failed")
        }
    }

    // Function to speak text
    private fun speakOut(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    // Release TTS resources when done
    override fun onDestroy() {
        // Stop speaking if TTS is active and release resources
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }

}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Column{
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        NavHost(navController = navController, startDestination = "firstView") {
            composable("firstView") {
                FirstView { navController.navigate("secondView") } // Navigate to second view
            }
            composable("secondView") {
                SecondView { navController.popBackStack() } // Go back to the first view
            }
        }
    }

}

@Composable
fun FirstView(onNavigate: () -> Unit) {
    Column {
        Text("This is the First View")
        Button(onClick = onNavigate) {
            Text("Go to Second View")
        }
    }
}

@Composable
fun SecondView(onNavigateBack: () -> Unit) {
    Column {
        Text("This is the Second View")
        Button(onClick = onNavigateBack) {
            Text("Go Back to First View")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeTheme {
        Greeting("Android")
    }
}