package com.example.careerlink.frontend.component

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.careerlink.R

@Composable
fun ButtonMenu(
   text: String,
   backgroundColor: Color = colorResource(R.color.button_yellow),
   textColor: Color = colorResource(R.color.white),
   @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
   onClick: () -> Unit
) {
   Button(
      onClick = {},
      colors = ButtonDefaults.buttonColors(
         containerColor = backgroundColor,
         contentColor = textColor
      ),
      shape = RoundedCornerShape(10.dp),
   ) {
      Text(text = text, color = textColor)
   }
}

@Preview
@Composable
private fun ButtonMenuPrev() {
   ButtonMenu(
      text = "Magang",
      backgroundColor = colorResource(R.color.button_yellow),
      textColor = colorResource(R.color.white),
      onClick = {  },
   )
}