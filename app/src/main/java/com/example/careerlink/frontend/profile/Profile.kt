package com.example.careerlink.frontend.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.careerlink.R
import com.example.careerlink.frontend.component.BottomBar
import com.example.careerlink.frontend.component.MainTopBar

@Composable
fun ProfileScreen(onEditProfile: () -> Unit, onEditPassword: () -> Unit) {
    Scaffold(
        topBar = { MainTopBar() },
        bottomBar = { BottomBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Profile Icon
            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "Profile Icon",
                tint = Color.Black,
                modifier = Modifier
                    .size(100.dp)
                    .background(colorResource(id = R.color.cream), shape = RoundedCornerShape(50.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Akun Saya",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.button_blue)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Profile Fields
            ProfileField(label = "Full Name", value = "Nadiva Najla")
            Spacer(modifier = Modifier.height(8.dp))
            ProfileField(label = "Username", value = "nadiva123")
            Spacer(modifier = Modifier.height(8.dp))
            ProfileField(label = "Email", value = "nadiva123@gmail.com")

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onEditProfile,
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button_blue)),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Edit Profile",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onEditPassword,
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button_blue)),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Edit Password",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Logout Action */ },
                colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Logout",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
fun ProfileField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.button_yellow), shape = RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Text(text = value, fontSize = 16.sp, color = Color.Black)
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onEditProfile = {}, onEditPassword = {})
}
