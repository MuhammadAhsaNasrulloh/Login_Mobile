@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.layoutbasic

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.MediaStore.Audio.Artists
import android.util.Log
import android.widget.ImageView
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.layoutbasic.ui.theme.LayoutBasicTheme
import java.net.ContentHandler
import java.net.PasswordAuthentication
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginForm(navController = navController)
            }
            composable("home") {
                HomeScreen(navController = navController)
            }
        }
    }
}
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun LoginForm(navController: NavController) {
        var username by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Login Form") })
            },
            bottomBar = {
                BottomAppBar(
                    contentColor = contentColorFor(backgroundColor = Color(0xFF1976D2)),
                ) {
                    Text(text = "©️copyright reserved - 2024", modifier = Modifier.padding(start = 10.dp))
                }
            }

        ) {

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.pp), contentDescription = "PP",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 10.dp))
            TextField(value = username, onValueChange = {
                username = it
            }, placeholder = { Text(text = "Username") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 50.dp, bottom = 10.dp)
            )
            TextField(value = password, onValueChange = {
                password = it
            }, placeholder = { Text(text = "Password") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 10.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Button(
                onClick = {
                    if (username == "admin" && password == "admin") {
                        navController.navigate("home")
                    }
                }, modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            {
                Text(text = "Login")
            }
        }
    }

    @Composable
    fun HomeScreen(navController: NavController) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Text(text = "Welcome to App", style = MaterialTheme.typography.headlineMedium)
                Button(onClick = {
                    navController.popBackStack("login", inclusive = false)
                }, modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(top = 15.dp)
                ) {
                    Text(text = "Log Out")
                }

            }

        }
    }




