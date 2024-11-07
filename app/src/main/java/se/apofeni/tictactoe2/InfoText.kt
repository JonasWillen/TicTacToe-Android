package se.apofeni.tictactoe2
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InfoText (viewModel: TicTacToeVM = viewModel()) {
    val data = viewModel.someData.value

    // UI that observes ViewModel data
    Column {
        Text(text = data)
        Button(onClick = { viewModel.updateData("Updated Data!") }) {
            Text("Update Data")
        }
    }
}