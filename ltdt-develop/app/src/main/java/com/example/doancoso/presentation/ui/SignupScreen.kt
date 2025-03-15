package com.example.doancoso.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.doancoso.R
import com.example.doancoso.data.repository.AuthService
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(navController: NavHostController, authService: AuthService) {
//    val viewModel: AuthViewModel = viewModel()
//    val authState by viewModel.authState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var messageError by remember { mutableStateOf(false) }

    // LaunchedEffect để xử lý điều hướng một lần
//    LaunchedEffect(authState) {
//        when (authState) {
//            is AuthState.Success -> {
//                Toast.makeText(navController.context, "Registration successful!", Toast.LENGTH_SHORT).show()
//                navController.navigate("login") {
//                    popUpTo("signup") { inclusive = true } // Xóa màn hình signup khỏi back stack
//                }
//                viewModel.resetAuthState() // Reset trạng thái sau khi điều hướng
//            }
//            else -> {}
//        }
//    }

    //    sử dụng box để chồng các layer ( nen và form )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E2A44))
            .padding(bottom = 50.dp), // Chừa khoảng cách 64.dp từ đáy
        contentAlignment = Alignment.BottomCenter
    ) {
        // Thêm hình ảnh biểu đồ tài chính
        Image(
            painter = painterResource(id = R.drawable.financial_chart),
            contentDescription = "Financial Chart",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.TopCenter) // Đặt hình ảnh ở trên cùng
        )
    //    form ky nằm giữa
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp)
        ){
//        when (authState) {
//            is AuthState.Loading -> CircularProgressIndicator()
//            is AuthState.Error -> {
//                val error = (authState as AuthState.Error).message
//                Text(text = error, color = MaterialTheme.colorScheme.error)
//            }
//            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // trươờng nhập ten với icon
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Person Icon"
                            )
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)

                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // nhap mail
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email Icon"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // pass
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Lock Icon"
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
//                    Button(
//                        onClick = { viewModel.registerUser(email, password, name) },
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
                    // nút đk
                    if(messageError){
                        Text("Please enter vorrect amail syntax ! ")
                    }
                    Button(
                        onClick = {
                            // Xử lý tạm thời khi nhấn nút, ví dụ in ra log

                            coroutineScope.launch {
                                val success = authService.register(name,email, password)
//                            isLoading = false
                                if (success) {
                                    navController.navigate("login")
                                } else {
                                    messageError=true

                                }
                            }
                            println("Sign Up clicked: Name=$name, Email=$email, Password=$password")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)) // Màu xanh
                    ) {
                        Text("Sign Up", color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextButton(onClick = { navController.navigate("login") }) {
                        Text("Already have an account? Login", color = Color.Gray)
                    }
                }
              }
           }
        }
//    }
//}

