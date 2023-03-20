package hu.ait.higherlower.ui.screen.mainmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigateToGame: () -> Unit,
    navController: NavController
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            modifier = Modifier.requiredWidth(200.dp),
            onClick = {
                onNavigateToGame()
            }
        ) {
            Text("Start")
        }
        OutlinedButton(
            modifier = Modifier.requiredWidth(200.dp),
            onClick = {
                navController.navigate("game?upperBound=50")
            }
        ) {
            Text("About")
        }
        OutlinedButton(
            modifier = Modifier.requiredWidth(200.dp),
            onClick = {
                val helpText = "Use the buttons to navigate"
                navController.navigate("helpscreen/$helpText")
            }
        ) {
            Text("Help")
        }
    }
}