package hu.ait.higherlower.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.ait.higherlower.R
import hu.ait.higherlower.ui.screen.game.GameViewModel
import hu.ait.higherlower.ui.view.SimpleAlertDialog
import kotlin.random.Random

@Composable
fun GameScreen(
    gameModel: GameViewModel = viewModel()
) {
    val context = LocalContext.current

    var text by rememberSaveable { mutableStateOf("") }
    var textResult by rememberSaveable {
        mutableStateOf(context.getString(R.string.text_good_luck))
    }
    var inputErrorState by rememberSaveable { mutableStateOf(false) }
    var errorText by rememberSaveable { mutableStateOf("Error...") }

    var showWinDialog by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) {
        val allDigit = text.all { char -> char.isDigit() }
        errorText = context.getString(R.string.text_error)
        inputErrorState = !allDigit
    }

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        OutlinedTextField(
            label = { Text(text = "Enter number here") },
            value = text,
            isError = inputErrorState,
            onValueChange = {
                text = it
                validate(text)
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            trailingIcon = {
                if (inputErrorState)
                    Icon(Icons.Filled.Warning, "error", tint = MaterialTheme.colorScheme.error)
            }
        )
        Row {
            OutlinedButton(onClick = {
                try {
                    val myNum = text.toInt()
                    if (myNum == gameModel.generatedNumber) {
                        textResult = context.getString(R.string.text_win)
                        showWinDialog = true
                    } else if (myNum < gameModel.generatedNumber) {
                        textResult = "The number is higher"
                    } else if (myNum > gameModel.generatedNumber) {
                        textResult = "The number is lower"
                    }
                } catch (e: Exception) {
                    inputErrorState = true
                    errorText = e.localizedMessage
                }

            }) {
                Text(text = "Guess")
            }

            OutlinedButton(onClick = {
                //generatedNum = Random(System.currentTimeMillis()).nextInt(10)
                gameModel.generateNewNumber()
                textResult = context.getString(R.string.text_good_luck)
            }) {
                Text(text = "Restart")
            }
        }

        if (inputErrorState) {
            Text(
                text = "$errorText",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Text(
            text = "$textResult",
            color = Color.Blue,
            fontSize = 28.sp
        )

        SimpleAlertDialog(show = showWinDialog,
            onDismiss = { showWinDialog = false },
            onConfirm = { showWinDialog = false }
        )
    }
}
//@Composable
//fun GameScreen() {
//    var generatedNum by rememberSaveable {
//        mutableStateOf(
//            Random(System.currentTimeMillis()).nextInt(10)
//        )
//    }
//
//    var errorMessage by rememberSaveable { mutableStateOf("")}
//    var inputErrorState by rememberSaveable { mutableStateOf(false)}
//
//    var text by rememberSaveable { mutableStateOf("") }
//    var textResult by rememberSaveable {
//        mutableStateOf("")
//    }
//
//    fun validate(text: String) {
//        val allDigit = text.all( {char -> char.isDigit()})
//        errorMessage = "Input numbers only"
//        inputErrorState = !allDigit
//    }
//    Column(
//        modifier = Modifier.padding(10.dp)
//    ) {
//        OutlinedTextField(
//            value = text,
//            label = {Text("Enter your guess here")},
//            isError = inputErrorState,
//            onValueChange = {
//                text = it
//                validate(text)
//            },
//            modifier = Modifier.fillMaxWidth(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
//            trailingIcon = {
//                if (inputErrorState)
//                    Icon(Icons.Filled.Warning, "error", tint = MaterialTheme.colorScheme.error)
//            })
//
//        Row() {
//            OutlinedButton(onClick = {
//                try {
//                    val myNum = text.toInt()
//                    if (generatedNum == myNum) {
//                        textResult = "Congratulations, you won!"
//                    } else if (myNum > generatedNum) {
//                        textResult = "The number is lower"
//                    } else {
//                        textResult = "The number is higher"
//                    }
//                } catch (e: Exception) {
//                    inputErrorState = true
//                    errorMessage = e.localizedMessage
//                }
//            }) {
//                Text("Guess")
//            }
//
//            OutlinedButton(onClick = {
//                generatedNum = Random(System.currentTimeMillis()).nextInt(10)
//                text=""
//                textResult=""
//            }) {
//                Text("Reset")
//            }
//        }
//
//        if (inputErrorState) {
//            Text(
//                text = "$errorMessage",
//                color = MaterialTheme.colorScheme.error,
//                style = MaterialTheme.typography.labelSmall,
//                modifier = Modifier.padding(start = 16.dp)
//            )
//        }
//        Text(text = "$textResult",
//            color = Color.Blue,
//            fontSize = 30.sp
//        )
//    }
//}