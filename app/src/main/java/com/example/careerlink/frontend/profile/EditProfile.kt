package com.example.careerlink.frontend.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.careerlink.R
import com.example.careerlink.frontend.component.MainTopBar
import com.example.careerlink.viewmodels.PenggunaViewModel

@Composable
fun EditProfileScreen(
    viewModel: PenggunaViewModel = hiltViewModel(),
    navController: NavController,
    context: Context = LocalContext.current
) {
    val pengguna by viewModel.pengguna.collectAsState()

    var namaLengkap by remember { mutableStateOf("") }
    var namaPengguna by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }


    LaunchedEffect(pengguna) {
        namaLengkap = pengguna?.namaLengkap ?: ""
        namaPengguna = pengguna?.namaPengguna ?: ""
        email = pengguna?.email ?: ""
    }

    Scaffold(
        topBar = { MainTopBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Edit Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.button_blue),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            EditableField(
                label = "Full Name",
                value = namaLengkap,
                onValueChange = { namaLengkap = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            EditableField(
                label = "Username",
                value = namaPengguna,
                onValueChange = { namaPengguna = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            EditableField(
                label = "Email",
                value = email,
                onValueChange = { email = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {navController.popBackStack()},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Batal",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        viewModel.updatePengguna(
                            namaLengkap = namaLengkap,
                            namaPengguna = namaPengguna,
                            email = email,
                            onSucces = {
                                Toast.makeText(context, "Profile berhasil dikirim", Toast.LENGTH_SHORT).show()
                                navController.navigate("profile") {
                                    popUpTo("profile") {
                                        inclusive = true
                                    }
                                }
                            },
                            onError = { errorMessage ->
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button_blue)),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Simpan",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.cream),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.button_yellow), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp)
        )
    }
}

@Composable
@Preview
fun EditProfilePreview() {
//    EditProfileScreen()
}
