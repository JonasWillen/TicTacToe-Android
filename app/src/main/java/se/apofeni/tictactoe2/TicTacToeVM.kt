package se.apofeni.tictactoe2

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData

class TicTacToeVM : ViewModel() {
    // State to hold some data

    var theModel = TicTacToeModel()

    private val _someData = mutableStateOf("Hello, Jetpack Compose!")
    val someData: State<String> get() = _someData
    val markers = MutableLiveData<List<Marker>>(initMarkers())


    fun resetGame(){
       // markers.forEach { it.state = 0 }
        theModel.resetGame()
    }

    // Function to update data
    fun updateData(newData: String) {
        _someData.value = newData
    }


}

fun initMarkers(): List<Marker> {
    println("start")
    return listOf(
        Marker(id = 0, state = 0, x = 0, y = 0),
        Marker(id = 1, state = 0, x = 1, y = 0),
        Marker(id = 2, state = 0, x = 2, y = 0),
        Marker(id = 3, state = 0, x = 0, y = 1),
        Marker(id = 4, state = 0, x = 1, y = 1),
        Marker(id = 5, state = 0, x = 2, y = 1),
        Marker(id = 6, state = 0, x = 0, y = 2),
        Marker(id = 7, state = 0, x = 1, y = 2),
        Marker(id = 8, state = 0, x = 2, y = 2)
    )
}



data class Marker(
    val id: Int,
    val state: Int,
    val x: Int,
    val y: Int
)