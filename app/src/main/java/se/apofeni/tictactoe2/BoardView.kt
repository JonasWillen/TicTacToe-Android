package se.apofeni.tictactoe2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Enum to define cell state
enum class Symbol {
    Cross, Circle, Empty
}

// Function to create a 3x3 matrix and display it
@Composable
fun TicTacToeBoard() {
    // Initialize a 3x3 matrix with all cells set to Empty
    var board by remember { mutableStateOf(List(3) { MutableList(3) { Symbol.Empty } }) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Loop through each row in the board
        board.forEachIndexed { rowIndex, row ->
            Row {
                // Loop through each item in the row
                row.forEachIndexed { colIndex, symbol ->
                    CrossOrCircle(
                        symbol = symbol,
                        modifier = Modifier
                            .size(80.dp)
                            .clickable {
                                // Update cell on click (for example, setting it to Cross)
                                board = board.toMutableList().apply {
                                    this[rowIndex][colIndex] = when (symbol) {
                                        Symbol.Empty -> Symbol.Cross // Toggle Cross on empty cell
                                        Symbol.Cross -> Symbol.Circle // Toggle Circle on Cross
                                        Symbol.Circle -> Symbol.Empty // Toggle Empty on Circle
                                    }
                                }
                            }
                            .background(Color.LightGray)
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}

// Composable to display Cross or Circle based on the input
@Composable
fun CrossOrCircle(symbol: Symbol, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (symbol) {
            Symbol.Cross -> Text(text = "X", color = Color.Red, fontSize = 32.sp)
            Symbol.Circle -> Text(text = "O", color = Color.Blue, fontSize = 32.sp)
            Symbol.Empty -> Text("") // Empty cell
        }
    }
}

// Usage in a top-level Composable
@Composable
fun BoardView() {
    Surface(color = MaterialTheme.colorScheme.background) {
        TicTacToeBoard()
    }
}