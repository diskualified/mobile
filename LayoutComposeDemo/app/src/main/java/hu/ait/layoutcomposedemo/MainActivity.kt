package hu.ait.layoutcomposedemo

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.ait.layoutcomposedemo.ui.theme.LayoutComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieScreen()
                }
            }
        }
    }
}

@Composable
fun MovieScreen() {
    Column {
        MovieHead(name = "puss in boots", year = 2022, imageId = R.drawable.pussinboots)
        MovieHead(name = "puss in boots", year = 2022, imageId = R.drawable.pussinboots)
        MovieHead(name = "puss in boots", year = 2022, imageId = R.drawable.pussinboots)
    }
}

@Composable
fun MovieHead(name: String, year: Int, imageId: Int) {
    Row (modifier=Modifier.size(200.dp)){
        Image(painter = painterResource(id = imageId),
            contentDescription = "Movie",
            modifier = Modifier.clip(CircleShape).fillMaxHeight().background(androidx.compose.ui.graphics.Color.Red).width(100.dp)
        )
        Column(modifier = Modifier
            .border(
                width = 1.dp,
                color = androidx.compose.ui.graphics.Color.Blue
            )
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            Text(text = "$name")
            Text(text = "$year")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LayoutComposeDemoTheme {
        MovieScreen()
    }
}