package com.example.doancoso.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController,authService : AuthService ,) {
//    val viewModel: AuthViewModel = viewModel()
//    val authState by viewModel.authState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var coroutineScope = rememberCoroutineScope()
    // LaunchedEffect để xử lý logic điều hướng một lần
//    LaunchedEffect(authState) {
//        when (authState) {
//            is AuthState.UserLoggedIn -> {
//                val user = (authState as AuthState.UserLoggedIn).user
//                Toast.makeText(navController.context, "Welcome, ${user?.name}!", Toast.LENGTH_SHORT).show()
//                navController.navigate("home") {
//                    popUpTo("login") { inclusive = true } // Xóa màn hình login khỏi back stack
//                }
//                viewModel.resetAuthState() // Reset trạng thái sau khi điều hướng
//            }
//            else -> {}
//        }
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E2A44))
            .padding(bottom = 50.dp), // Chừa khoảng cách 64.dp từ đáy
        contentAlignment = Alignment.BottomCenter
    ) {
//        when (authState) {
//            is AuthState.Loading -> CircularProgressIndicator()
//            is AuthState.Error -> {
//                val error = (authState as AuthState.Error).message
//                Text(text = error, color = MaterialTheme.colorScheme.error)
//            }
//            else -> {

        // Thêm hình ảnh biểu đồ tài chính
        Image(
            painter = painterResource(id = R.drawable.financial_chart),
            contentDescription = "Financial Chart",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.TopCenter) // Đặt hình ảnh ở trên cùng
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Person Icon"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Black  // Màu viền khi không focus
                    )

                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Person Icon"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
//                    Button(
//                        onClick = { viewModel.loginUser(email, password) },
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
                Button(
                    onClick = {
                        // Xử lý tạm thời khi nhấn nút, ví dụ in ra log
                        coroutineScope.launch {
                            val success = authService.login(email, password)
//                            isLoading = false
                            if (success) {
                                navController.navigate("home")
                            } else {
                              println("error")
                            }
                        }
                        println("Login clicked: Email=$email, Password=$password")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)) // Màu xanh
                ) {
                    Text("Login")
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { navController.navigate("signup") }) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Don't have an account? Sign up", color = Color.Gray)
                        Text("Google Sign In", color = Color.Gray)
                    }
                }
                Button(
                    onClick = {},
                    modifier = Modifier.size(76.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gg),
                        contentDescription = "Google Sign In",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
    }
}

//    }
//}

// thêm vào để giả định
//@Preview (showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    // Tạo một NavHostController giả (mock) hoặc bỏ qua điều hướng trong Preview
//    val navController = rememberNavController() // Sử dụng rememberNavController() cho Preview
//    LoginScreen( navController,)
//}