package com.example.test_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.room.Entity.TextEntity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "start"
            ){
                composable("start"){
                    StartScreen(navController)
                }
                composable("input"){
                    InputScreen()
                }
            }
        }
    }
}

@Composable
fun StartScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        IconButton(onClick = {
            navController.navigate("input")
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }
}

@Composable
fun InputScreen() {
    val viewModel = viewModel<TextViewModel>()
    val inputText = remember { mutableStateOf("") }
    var textList = viewModel.readAllData.observeAsState("")
    var dialogText by remember {
        mutableStateOf("")
    }
    var dialogState by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ){
        TextField(
            value = inputText.value,
            onValueChange = {
                inputText.value = it
                viewModel.addText(TextEntity(0,it))
            },
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ){
            TextButton(onClick = {
                val text : List<TextEntity> = textList.value as List<TextEntity>
                dialogText = text.last().text.toString()
                dialogState = true
            }) {
                Text("TextButton")
            }
        }
        ShowDialog(dialogState, dialogText){
            dialogState = !it
        }
    }
}

@Composable
fun ShowDialog(
    dialogState : Boolean,
    textList : String,
    onDismiss: (dialogState: Boolean) -> Unit
) {
    val viewModel = viewModel<TextViewModel>()
    if(dialogState){
        AlertDialog(
            onDismissRequest = {
                onDismiss(dialogState)
            },
            title = {
                Text(
                    text = "test"
                )
            },
            text = {
                Text(
                    text = textList
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDismiss(dialogState)
                    }
                ) {
                    Text(
                        "OK"
                    )
                }
            }
        )
    }
}