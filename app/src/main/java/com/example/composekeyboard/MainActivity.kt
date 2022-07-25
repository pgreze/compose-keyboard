package com.example.composekeyboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composekeyboard.ui.theme.ComposeKeyboardTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeKeyboardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(
                        modifier = Modifier
                            // .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        (1..10).forEach { Section("Header $it") }

                        Section("About this bug")

                        var text by remember {
                            mutableStateOf("Hello\nBonjour\nこんにちは\n".repeat(3))
                        }
                        val bringIntoViewRequester = BringIntoViewRequester()
                        val coroutineScope = rememberCoroutineScope()
                        BasicTextField(
                            value = text,
                            onValueChange = {
                                text = it
                                coroutineScope.launch {
                                    bringIntoViewRequester.bringIntoView()
                                }
                            },
                            modifier = Modifier
                                .bringIntoViewRequester(bringIntoViewRequester)
                                .fillMaxSize()
                                .padding(horizontal = 12.dp)
                                .border(1.dp, Color.Black),
                        )

                        Section("Below section")

                        Section("Footer")
                    }
                }
            }
        }
    }
}

@Composable
fun Section(name: String) {
    Text(
        text = name,
        modifier = Modifier.padding(12.dp)
    )
}
