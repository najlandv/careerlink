package com.example.careerlink.frontend.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.careerlink.R

@Composable
fun BottomBar(modifier: Modifier = Modifier, navController: NavController ) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(colorResource(R.color.cream)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem(
            iconRes = R.drawable.baseline_home_24,
            label = "Home",
            onClick = { navController.navigate("home") {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            } }
        )
        BottomBarItem(
            iconRes = R.drawable.baseline_post_add_24,
            label = "My Post",
            onClick = {navController.navigate("list-magang-my-post")}
        )
        BottomBarItem(
            iconRes = R.drawable.baseline_account_circle_24,
            label = "Profile",
            onClick = {navController.navigate("profile")}
        )
    }
}

@Composable
fun BottomBarItem(iconRes: Int, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .background(Color.Transparent, CircleShape)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun BottomBarPrev() {
//    BottomBar()
}
