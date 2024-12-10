package com.example.tictactoe
import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun `isWin is false for empty board`() {
        val model = Model.empty
        assertEquals(GameState.IN_PROCESS, model.gameState)
    }

    @Test
    fun `update empty board should add cross`() {
        val model = Model.empty
        val expected = Model.of("X__|___|___")

        val actual = model.update(0, 0)

        assertEquals(expected, actual)
    }

    @Test
    fun `update board with a single cross should add nought`() {
        val model = Model.of("X__|___|___")
        val expected = Model.of("X__|_O_|___")

        val actual = model.update(1, 1)

        assertEquals(expected, actual)
    }

    @Test
    fun `check win condition for a horizontal line of crosses`() {
        val model = Model.of("XXX|___|___")
        assertEquals(GameState.CROSS_WIN, model.gameState)
    }

    @Test
    fun `check win condition for a vertical line of crosses`() {
        val model = Model.of("X__|X__|X__")
        assertEquals(GameState.CROSS_WIN, model.gameState)
    }

    @Test
    fun `check diagonal win for crosses`() {
        val model = Model.of("X__|_X_|__X")
        assertEquals(GameState.CROSS_WIN, model.gameState)
    }

    @Test
    fun `check draw condition`() {
        val model = Model.of("XOX|OXO|XOX")
        assertEquals(GameState.DRAW, model.gameState)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `update already occupied cell should throw exception`() {
        val model = Model.of("X__|___|___")
        model.update(0, 0)
    }
}