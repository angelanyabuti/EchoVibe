package com.example.echovibe.pages

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.echovibe.R
import com.example.echovibe.ui.theme.LocalAppGradients


private val RecentlyPlayedData = listOf(
    R.drawable.sad to R.string.sad,
    R.drawable.happy to R.string.happy,
    R.drawable.party to R.string.party,
    R.drawable.adventure to R.string.adventure,
    R.drawable.study to R.string.study,
).map {
    DrawableStringPair(it.first, it.second)
}
data class MoodItem(
    @DrawableRes val drawable: Int,
    @StringRes val songTitle: Int,
    @StringRes val artist: Int
)

private val TodaysMood = listOf(
    MoodItem(R.drawable.happysong, R.string.happy, R.string.Pharell),
    MoodItem(R.drawable.happy, R.string.HappyNow, R.string.Zedd),
    MoodItem(R.drawable.party, R.string.PartyRock, R.string.LMFAO),
    MoodItem(R.drawable.adventure, R.string.OnTopOfTheWorld, R.string.ImagineDragons),
    MoodItem(R.drawable.study, R.string.LoFiBeats, R.string.Unknown)
)


private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)
@Composable
fun Home(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(40.dp)
                            .background(
                                MaterialTheme.colorScheme.onBackground,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        imageVector = Icons.Default.Person,
                        contentDescription = " ",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Hello Jane",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    // Push Notification Icon to the Right
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(30.dp),
                        imageVector = Icons.Default.Notifications,
                        contentDescription = " ",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "How are you feeling today?",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextField(
                    value ="" ,
                    onValueChange = {},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = " "
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedIndicatorColor = Color.Transparent, // Remove underline when not focused
                        focusedIndicatorColor = Color.Transparent  // Remove underline when focused
                    ),
                    placeholder = {
                        Text("Search")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        RecentlyPlayed()
        Spacer(modifier = Modifier.height(12.dp))
        TodayMood()
        Spacer(modifier = Modifier.height(12.dp))
        CuratedPlaylist()

    }
}
//Recently Played
@Composable
fun RecentlyPlayed(modifier: Modifier = Modifier) {
    Text(
        text = "Recently Played",
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(horizontal = 5.dp),
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(12.dp))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier
    ){
        items(RecentlyPlayedData){
            item->
            MoodBodyElement(item.drawable, item.text)
        }
    }
}
//How the mood body looks like
@Composable
fun MoodBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.onBackground)
            .padding(5.dp)
            .border(
                1.dp,
                MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(8.dp)
            ),
            horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            ),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }

}

//Todays mood
@Preview(showBackground = true)
@Composable
fun TodayMood(modifier: Modifier = Modifier){
    Text(
        text = "Most people are feeling happy today",
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(horizontal = 5.dp),
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(12.dp))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier
    ){
        items(TodaysMood) { mood ->
            TodayMoodElement(
                drawable = mood.drawable,
                songTitle = mood.songTitle,
                artist = mood.artist
            )
        }
    }
}

//Todays mood element
@Composable
fun TodayMoodElement(
    @DrawableRes drawable: Int,
    @StringRes songTitle: Int,
    @StringRes artist: Int,
    modifier: Modifier = Modifier){
    val gradients = LocalAppGradients.current
    Column (
        modifier = modifier
            .width(260.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(gradients.primaryGradient)
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .padding(vertical = 10.dp)
                .clip(CircleShape)
        )
        //play button
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 5.dp, vertical = 2.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary //text/icon color
            ),
            //remove elevation
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            ),
        ) {
            Row {
                Column {
                    Text(
                        text = stringResource(artist),
                        modifier = Modifier
                            .paddingFromBaseline(
                                top = 24.dp, bottom = 8.dp
                            )
                            .padding(end = 5.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(songTitle),
                        modifier = Modifier
                            .paddingFromBaseline(
                                top = 24.dp, bottom = 8.dp
                            )
                            .padding(end = 5.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    modifier = Modifier
                        .size(50.dp) // Make it larger if needed
                        .padding(start = 5.dp)
                        .clip(CircleShape) //Clip it to a circle
                        .background(MaterialTheme.colorScheme.onBackground) // Background color
                        .shadow(4.dp, shape = CircleShape), // Add shadow
                    tint = Color.White // You can set a custom tint
                )
            }



        }

    }
}

//Curated Playlist
@Composable
fun CuratedPlaylist(){
    Text(
        text = "Curated Playlist for You",
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.padding(horizontal = 5.dp),
        style = MaterialTheme.typography.titleMedium)
}

