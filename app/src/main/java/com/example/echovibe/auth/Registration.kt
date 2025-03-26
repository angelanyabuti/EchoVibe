package com.example.echovibe.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.echovibe.R
import com.example.echovibe.auth.Login
import com.example.echovibe.routes.Routes
import androidx.navigation.NavHostController



@Composable
fun Registration(modifier: Modifier = Modifier, navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    Column (
        modifier = modifier
            .fillMaxSize() //Ensures the column takes the entire available space
            .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.headphones),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .size(60.dp)
        )
        Text(
            text = "Create Account",
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            label = { Text(
                "Enter your email",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            ) }
        )
        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            label = { Text(
                "Enter your password",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            ) },
            visualTransformation = PasswordVisualTransformation(), //hides the password
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        ElevatedButton(
            modifier = Modifier.padding(top = 12.dp),

            onClick = {
                navController.navigate(Routes.Login.route)
            })
        {
            Text(
                text = "Register",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            )


        }

    }
}


