package com.kata.testbed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.kata.testbed.framework.ui.SectionTabScreen
import com.kata.testbed.sections.registerAllSections
import com.kata.testbed.theme.KataTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerAllSections()
        enableEdgeToEdge()
        setContent {
            KataTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Kata Testbed") })
                    }
                ) { padding ->
                    SectionTabScreen(modifier = Modifier.fillMaxSize().padding(padding))
                }
            }
        }
    }
}
