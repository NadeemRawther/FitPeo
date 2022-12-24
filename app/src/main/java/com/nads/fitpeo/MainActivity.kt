package com.nads.fitpeo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nads.fitpeo.ui.theme.FitPeoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitPeoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var text by remember{
                        mutableStateOf("nadeem")
                    }
                    Greeting(text, { text = "changed" })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, function: () -> Unit) {
    Column() {
        Text(text = "Hello $name!")
        Button(onClick = {function.invoke()}) {

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FitPeoTheme {
        //Greeting("Android") { text = "changed" }
    }
}