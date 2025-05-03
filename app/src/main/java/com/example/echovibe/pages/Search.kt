package com.example.echovibe.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Search",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.headlineSmall)
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        Emoji()
    }
}
//Search Bar
@Composable
fun SearchBar() {
    TextField(
        value ="" ,
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedIndicatorColor = Color.Transparent, // Remove underline when not focused
            focusedIndicatorColor = Color.Transparent  // Remove underline when focused
        ),
        placeholder = {
            Text(text = "How are you feeling today?")
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Mic Icon"
            )

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            ),
        singleLine = true,
        maxLines = 1
    )
}

//Emoji Section
@Preview
@Composable
fun Emoji () {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.onBackground)
            .padding(8.dp)
    ){
        //The outer box for adding padding since compose has no margin
        Box (modifier = Modifier .padding(8.dp)){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(4.dp)
            ) {
                Text(
                    modifier = Modifier
                    .align(Alignment.Center),
                    text = "❤️",
                    fontSize = 40.sp,
                )
            }
        }
        Spacer (modifier = Modifier.weight(1f))
        Box (modifier = Modifier .padding(8.dp)){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "😂",
                    fontSize = 40.sp,
                )
            }
        }
        Spacer (modifier = Modifier.weight(1f))
        Box (modifier = Modifier .padding(8.dp)){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "😢",
                    fontSize = 40.sp,
                )
            }
        }
    }
}
