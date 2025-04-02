package com.example.echovibe.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.echovibe.R
import com.example.echovibe.routes.Routes

@Composable
fun Login(modifier: Modifier = Modifier, navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    Column (
        modifier = modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Login",
            color = MaterialTheme.colorScheme.tertiary,
        )
        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            label = { Text(
                "Enter your email",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            ) }
        )
        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            label = { Text(
                "Enter your password",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            ) },
            visualTransformation = PasswordVisualTransformation(), //hides the password
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        ElevatedButton(
            modifier = Modifier.padding(top = 12.dp),

            onClick = {
                navController.navigate(Routes.Home.route)
            })
        {
            Text(
                text = "Login",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            )
        }
        //Log in with google or facebook
        Text(text = "OR")
            Image(
                modifier = Modifier.padding(12.dp),
                painter = painterResource(id = R.drawable.google_sign_in),
                contentDescription = "Google")


    }

}