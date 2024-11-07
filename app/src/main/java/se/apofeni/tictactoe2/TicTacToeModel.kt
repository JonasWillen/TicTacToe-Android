package se.apofeni.tictactoe2

class TicTacToeModel {
    var currentPlayer = 1 // cross (1 for player 1, 2 for player 2)
    var gameState = MutableList(9) { 0 } // Representing the board state
    var gameIsActive = true
    var count = 1
    private val winningCombinations = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
        listOf(0, 4, 8), listOf(2, 4, 6)
    )

    // Reset the game to initial state
    fun resetGame() {
        gameState = MutableList(9) { 0 }
        gameIsActive = true
        currentPlayer = 1
        count = 1
    }

    // Perform a move at the specified position
    fun makeMove(position: Int): Int {
        if (gameState[position] == 0 && gameIsActive) {
            gameState[position] = currentPlayer
            count++

            return if (currentPlayer == 1) {
                currentPlayer = 2
                1
            } else {
                currentPlayer = 1
                2
            }
        }
        return 0
    }

    // Get the symbol at a specific tile
    fun getTile(position: Int): Int {
        return gameState[position]
    }

    // Check if there is a winner or a draw
    fun checkWinner(): Int {
        for (combination in winningCombinations) {
            if (gameState[combination[0]] != 0 &&
                gameState[combination[0]] == gameState[combination[1]] &&
                gameState[combination[1]] == gameState[combination[2]]
            ) {
                gameIsActive = false
                return if (gameState[combination[0]] == 1) 1 else 2
            }
        }

        // Check for a draw
        if (gameIsActive && count > 9) {
            gameIsActive = false
            return 3 // Draw
        }
        return 0 // No winner yet
    }
}