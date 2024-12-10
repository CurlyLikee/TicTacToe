package com.example.tictactoe

const val DIM = 3

data class Model(val cells: List<CellState>) {
    companion object {
        val empty = Model.of("___|___|___")
    }
}

val Model.gameState: GameState
    get() {
        val lines = listOf(
            listOf(this[0, 0], this[0, 1], this[0, 2]),
            listOf(this[1, 0], this[1, 1], this[1, 2]),
            listOf(this[2, 0], this[2, 1], this[2, 2]),
            listOf(this[0, 0], this[1, 0], this[2, 0]),
            listOf(this[0, 1], this[1, 1], this[2, 1]),
            listOf(this[0, 2], this[1, 2], this[2, 2]),
            listOf(this[0, 0], this[1, 1], this[2, 2]),
            listOf(this[0, 2], this[1, 1], this[2, 0])
        )

        if (lines.any { it.all { cell -> cell == CellState.CROSS } }) return GameState.CROSS_WIN
        if (lines.any { it.all { cell -> cell == CellState.NOUGHT } }) return GameState.NOUGHT_WIN
        if (cells.all { it != CellState.EMPTY }) return GameState.DRAW
        return GameState.IN_PROCESS
    }

fun Model.update(row: Int, col: Int): Model {
    if (this[row, col] != CellState.EMPTY) {
        throw IllegalArgumentException("Cell is already occupied")
    }

    val newCells = cells.toMutableList()
    newCells[ix(row, col)] = CellState.CROSS
    return Model(newCells)
}

enum class GameState {
    CROSS_WIN,
    NOUGHT_WIN,
    DRAW,
    IN_PROCESS
}

enum class CellState {
    CROSS,
    NOUGHT,
    EMPTY
}

fun ix(row: Int, col: Int): Int = row * DIM + col

operator fun Model.get(row: Int, col: Int): CellState = cells[ix(row, col)]

val CellState.str: String
    get() = when (this) {
        CellState.CROSS -> "X"
        CellState.NOUGHT -> "O"
        CellState.EMPTY -> "_"
    }

fun Model.print() {
    repeat(3) { row ->
        repeat(3) { col ->
            print(this[row, col].str)
        }
        println()
    }
}

fun Model.Companion.of(str: String): Model {
    val list = mutableListOf<CellState>()
    str.forEach { ch ->
        list.add(
            when (ch) {
                'X' -> CellState.CROSS
                'O', '0' -> CellState.NOUGHT
                '_', ' ' -> CellState.EMPTY
                else -> throw IllegalArgumentException("Invalid character in string")
            }
        )
    }
    return Model(list)
}