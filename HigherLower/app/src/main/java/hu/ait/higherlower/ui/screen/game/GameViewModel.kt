package hu.ait.higherlower.ui.screen.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.util.*

//responsible for logic
class GameViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var generatedNumber by mutableStateOf(0)
    var upperBound by mutableStateOf(3)

    init {
        generateNewNumber()
        savedStateHandle.get<Int>("upperBound")?.let{
            upperBound = it
        }
    }

    fun generateNewNumber() {
        generatedNumber = Random(System.currentTimeMillis()).nextInt(upperBound)
    }

}