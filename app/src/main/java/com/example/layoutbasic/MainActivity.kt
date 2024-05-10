@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.layoutbasic

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.callbackFlow

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
            composable("register"){
                RegisterScreen(navController = navController)
            }
            composable("home") {
                HomeScreen(navController = navController)
            }
        }
    }
}
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun LoginForm(navController: NavController) {
        var username by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var text by remember {
            mutableStateOf("")
        }
        var passwordVisibility by remember {
            mutableStateOf(false)
        }
        var dialogShow by remember {
            mutableStateOf(false)
        }
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    containerColor = contentColorFor(backgroundColor = Color(0xFF1976D2)),
                ) {
                    Text(text = "©️copyright reserved - 2024", color = Color.White,modifier = Modifier.padding(start = 10.dp))
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
            OutlinedTextField(value = username, onValueChange = {
                username = it
            }, placeholder = { Text(text = "Username") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 50.dp, bottom = 10.dp)
            )
            OutlinedTextField(value = password, onValueChange = {
                password = it
            }, placeholder = {
                Text(text = "Password")
            },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 10.dp),
                visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {passwordVisibility = !passwordVisibility}) {
                        Icon(modifier = Modifier.size(20.dp), painter = painterResource(id = R.drawable.invisible), contentDescription = "Show Password")
                    }
                }
            )
            Button(
                onClick = {
                    if (username == "admin" && password == "admin") {
                        navController.navigate("home")
                    }else if(text.isBlank()){
                        dialogShow = true
                    }
                }, modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            {
                Text(text = "Login")

            }

            Button(onClick = {
                navController.navigate("register")
            }, modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(text = "Register")
            }
            if (dialogShow) {
                AlertDialog(
                    onDismissRequest = { dialogShow = false },
                    title = { Text("Peringatan") },
                    text = { Text("Tidak dapat mengirimkan formulir karena TextField kosong.") },
                    confirmButton = {
                        Button(
                            onClick = { dialogShow = false }
                        ) {
                            Text("OK")
                        }
                    }
                )
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun HomeScreen(navController: NavController) {
    var searchBar by remember {
        mutableStateOf("")
    }
    var addButton by remember {
        mutableStateOf(0)
    }
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Menu FoodXYZ", fontWeight = FontWeight.Bold,fontSize = 20.sp)
                    },
                    modifier = Modifier
                        .shadow(1.dp)
                        .fillMaxWidth()
                )
            },
            content = {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 125.dp, start = 10.dp)
                    ) {
                        OutlinedTextField(value = searchBar, onValueChange = {
                            searchBar = it
                        }, placeholder = { Text(text = "Search", fontSize = 14.sp, )},
                            textStyle = TextStyle(fontSize = 13.sp),
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(50.dp),
                            trailingIcon = {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = "Search Icon")
                            }
                        )
                        Text(text = "Semua", fontSize = 18.sp ,fontWeight = FontWeight.Bold,modifier = Modifier.padding(top = 20.dp, bottom = 10.dp))
                        Row(
                            modifier = Modifier
                                .requiredSize(width = 400.dp, height = 100.dp)
                                .background(color = Color.White)
                                .padding(top = 10.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .requiredSize(100.dp)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 10.dp,
                                            topEnd = 10.dp,
                                            bottomEnd = 10.dp,
                                            bottomStart = 10.dp
                                        )
                                    )
                                    .background(color = Color(233, 236, 239))
                            ) {
                                Image(
                                    alignment = Alignment.Center,
                                    modifier = Modifier.requiredSize(90.dp),
                                    painter = painterResource(id = R.drawable.coke),
                                    contentDescription = "Image Produk")

                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(start = 10.dp)
                            ) {
                                Row (
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Column {
                                        Text(text = "Coca - Cola", fontWeight = FontWeight.SemiBold)
                                    }
                                    Column (
                                        horizontalAlignment = Alignment.End,
                                        modifier = Modifier.fillMaxWidth(0.8f)
                                    ){
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Image(
                                                modifier = Modifier.requiredSize(15.dp),
                                                painter = painterResource(id = R.drawable.star),
                                                contentDescription = "Icon")
                                            Text(text = "4.5", Modifier.padding(start = 5.dp))
                                        }
                                    }
                                }
                                Text(text = "Rp. 7000")
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp)
                                ){
                                    IconButton(
                                        onClick = {addButton++ }) {
                                        Icon(
                                            modifier = Modifier.requiredSize(20.dp),
                                            painter = painterResource(id = R.drawable.add),
                                            contentDescription = "Add")
                                    }
                                    Text(text = "$addButton", fontSize = 20.sp)
                                    IconButton(onClick = { addButton-- }) {
                                       Icon(modifier = Modifier.requiredSize(20.dp),painter =  painterResource(id = R.drawable.min), contentDescription = "Min")
                                    }
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .requiredSize(width = 400.dp, height = 100.dp)
                                .background(color = Color.White)
                                .padding(top = 10.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .requiredSize(100.dp)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 10.dp,
                                            topEnd = 10.dp,
                                            bottomEnd = 10.dp,
                                            bottomStart = 10.dp
                                        )
                                    )
                                    .background(color = Color(233, 236, 239))
                            ) {
                                Image(
                                    alignment = Alignment.Center,
                                    modifier = Modifier.requiredSize(90.dp),
                                    painter = painterResource(id = R.drawable.coke),
                                    contentDescription = "Image Produk")

                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(start = 10.dp)
                            ) {
                                Row (
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Column {
                                        Text(text = "Coca - Cola", fontWeight = FontWeight.SemiBold)
                                    }
                                    Column (
                                        horizontalAlignment = Alignment.End,
                                        modifier = Modifier.fillMaxWidth(0.8f)
                                    ){
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Image(
                                                modifier = Modifier.requiredSize(15.dp),
                                                painter = painterResource(id = R.drawable.star),
                                                contentDescription = "Icon")
                                            Text(text = "4.5", Modifier.padding(start = 5.dp))
                                        }
                                    }
                                }
                                Text(text = "Rp. 7000")
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp)
                                ){
                                    IconButton(
                                        onClick = {addButton++ }) {
                                        Icon(
                                            modifier = Modifier.requiredSize(20.dp),
                                            painter = painterResource(id = R.drawable.add),
                                            contentDescription = "Add")
                                    }
                                    Text(text = "$addButton", fontSize = 20.sp)
                                    IconButton(onClick = { addButton-- }) {
                                        Icon(modifier = Modifier.requiredSize(20.dp),painter =  painterResource(id = R.drawable.min), contentDescription = "Min")
                                    }
                                }
                            }
                        }
                    }
                }
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(bottom = 1000.dp)
//                ) {
//                    Column (
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ){
//                        Button(onClick = {
//                            navController.popBackStack("login", inclusive = false)
//                        }, modifier = Modifier
//                            .fillMaxWidth(0.5f)
//                            .padding(top = 15.dp)
//                            .align(Alignment.End)
//                        ) {
//                            Text(text = "Log Out")
//                        }
//                    }
//                }
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = colorResource(id = R.color.Green100)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(start = 50.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(30.dp),
                                painter = painterResource(id = R.drawable.cart),
                                tint = colorResource(id = R.color.white),
                                contentDescription = "Cart")
                            Text(text = "Cart", color = Color.White)
                        }
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 50.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(35.dp),
                                tint = colorResource(id = R.color.white),
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = "Profile"
                            )
                            Text(text = "Profile", color = Color.White)
                        }
                    }
                }
            }
        )

    }
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavController){
    var username by remember{
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var nama by remember {
        mutableStateOf("")
    }
    var alamat by remember{
        mutableStateOf("")
    }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Register Form") })
        },
//        containerColor = contentColorFor(backgroundColor = Color(52, 78, 65)),

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Silahkan Isi data Pribadi",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 28.sp),
                modifier = Modifier.padding(bottom = 20.dp))
            TextField(value = username, onValueChange = {
                username = it
            }, placeholder = { Text(text = "Username") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 10.dp)
            )
            TextField(value = nama , onValueChange = {
                nama = it
            }, placeholder = { Text(text = "Nama")},
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 10.dp)
            )
            TextField(value = alamat, onValueChange = {
                alamat = it
            }, placeholder = { Text(text = "Alamat")},
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 10.dp)
            )
            TextField(value = password, onValueChange = {
                password = it
            }, placeholder = { Text(text = "Password") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 10.dp),
                visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {passwordVisibility = !passwordVisibility}) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.invisible),
                            contentDescription = "Show Password"
                        )
                    }
                }
            )
            TextField(value = confirmPassword, onValueChange = {
                confirmPassword = it
            }, placeholder = {
                Text(text = "Confirm Password")
            }, modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom = 10.dp),
                visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {passwordVisibility = !passwordVisibility}) {
                        Icon(modifier = Modifier.size(20.dp), painter = painterResource(id = R.drawable.invisible), contentDescription = "Show Password")
                    }
                }
            )
            Button(modifier = Modifier
                .fillMaxWidth(0.8f),
                onClick = {
                navController.popBackStack("login", inclusive = false)
            }) {
                Text(text = "Daftar")
            }
            Button(modifier = Modifier
                .fillMaxWidth(0.8f),
                content = {
                    contentColorFor(backgroundColor = Color.Blue)
                    Text(text = "Sudah Punya Akun")
                },
                onClick = {
                    navController.popBackStack("login", inclusive = false)
                }) 
        }
    }
}




