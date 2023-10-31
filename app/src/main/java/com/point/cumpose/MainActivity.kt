package com.point.cumpose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MagicButton()
        }
    }
}

@Composable
fun MagicButton() {
    val columns =
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 3
    var clickCount by rememberSaveable { mutableStateOf(0)}
        FilledIconButton(onClick = { clickCount++ },modifier = Modifier.padding(start= if (columns == 3) 0.dp else 20.dp).height(50.dp).width(if (columns == 3) 180.dp else 240.dp)) {
            Text(
                "Do magic"
            )
        }
    Box(
        modifier = Modifier
            .padding(start= if (columns == 3) 0.dp else 20.dp, top=51.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            val rows = clickCount / columns + if (clickCount % columns == 0) 0 else 1
            repeat(rows) { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    repeat(columns) { column ->
                        val curNumber = row * columns + column
                        if (curNumber < clickCount) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(60.dp)
                                    .background(if (curNumber % 2 == 0) Color.Red else Color.Blue)
                                    .border(1.dp, Color.White),
                            ) {
                                Text(
                                    text = curNumber.toString(),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
