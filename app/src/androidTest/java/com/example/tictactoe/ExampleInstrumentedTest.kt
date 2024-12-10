package com.example.tictactoe

import org.junit.Assert.assertEquals
import org.junit.Test

class ModelTest {

    @Test
    fun testUpdate() {
        val model = Model.of("___|___|___")
        val updated = model.update(0, 0)
        assertEquals(CellState.CROSS, updated[0, 0])
    }

    @Test
    fun testGameStateInProcess() {
        val model = Model.of("___|___|___")
        assertEquals(GameState.IN_PROCESS, model.gameState)
    }

    @Test
    fun testGameStateCrossWin() {
        val model = Model.of("XXX|___|___")
        assertEquals(GameState.CROSS_WIN, model.gameState)
    }

    @Test
    fun testGameStateNoughtWin() {
        val model = Model.of("_O_|_O_|_O_")
        assertEquals(GameState.NOUGHT_WIN, model.gameState)
    }

    @Test
    fun testGameStateDraw() {
        val model = Model.of("XOX|OXO|OXO")
        assertEquals(GameState.DRAW, model.gameState)
    }
}