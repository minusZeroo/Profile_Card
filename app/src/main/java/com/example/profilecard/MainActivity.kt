package com.example.profilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.Coil
import com.example.profilecard.ui.theme.MyTheme
import com.example.profilecard.ui.theme.lightGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme() {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(playerProfiles: List<PlayerProfile> = playerProfileList) {
    Scaffold(topBar = { AppBar() }) {
        //add a surface so that we have a background
        //Fill the screen and make the color Light gray
        Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
            //Pass profile card to the main screen
            //Because the surface should contain a profile card

            //since we cannot have 2 composable put we want to user cards
            //so use rows or columns

            //using LazyCoumns for multiple cards
            LazyColumn {
                items(playerProfiles) { playerProfile ->
                    ProfileCard(playerProfile = playerProfile)
                }
            }
        }
    }
}

@Composable
fun playerProfileDetailsScreen(playerProfiles: PlayerProfile = playerProfileList[0]) {
    Scaffold(topBar = { AppBar() }) {

        Surface(modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) { //specify some alignments for the card //this is the row for the text
                ProfilePicture(playerProfiles.drawable, playerProfiles.status, 240.dp)
                ProfileContent(playerProfiles.name, playerProfiles.status, Alignment.CenterHorizontally)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun playerProfileDetailsPreview() {
    MyTheme() {
        playerProfileDetailsScreen()
    }
}

@Composable
fun AppBar() {
    TopAppBar(navigationIcon = {
        Icon(
            Icons.Default.Home,
            "content description",
            Modifier.padding(horizontal = 12.dp)
        )
    },
        title = { Text("Current Football players") }
    )
}

@Composable
fun ProfileCard(playerProfile: PlayerProfile) {
    //since we are making a profile card layout
    //this composable should be a card
    //make this card as wide as the screen and also the height
    //add some padding to see the elevation that was passed to it
    //we need to add the actual content of the card, such as:...
    // profile icon, profile picture...
    // and another composable that holds the name and the activity
    //but a Card can only accept one composable, therefore it will have an...
    //issue of rendering 2 composables ontop of the other
    //Therefore, we use a row and a column
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) { //specify some alignments for the card //this is the row for the text
            ProfilePicture(playerProfile.drawable, playerProfile.status, 72.dp)
            ProfileContent(playerProfile.name, playerProfile.status, alignment = Alignment.Start)
        }
    }
}

//this is the composable for the profile picture
@Composable
fun ProfilePicture(drawableId: Int, onlineStatus: Boolean, imageSize:Dp) {
    //lets wrap this picture in a card
    //because card has shape and shape can take alot of forms
    //Have the image in a circle shape card
    //specify a border to this circle shape
    //also add some padding
    //add some elevation for the picture
    Card(
        shape = CircleShape, border = BorderStroke(
            width = 2.dp,
            color = if (onlineStatus)
                MaterialTheme.colors.lightGreen
            else Color.Red
        ),
        modifier = Modifier.padding(16.dp), elevation = 4.dp
    ) {

        //we need an Image: add a description and size
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "describe the picture", modifier = Modifier.size(imageSize),
            contentScale = ContentScale.Crop
        )
        //if the image is too big, crop it

    }
}

//this is the composable for the profile content
@Composable
fun ProfileContent(playerName: String, onlineStatus: Boolean, alignment: Alignment.Horizontal) {
    //add sum content information
    //cannot have two composables without a row or column
    //name of the user should be on top of the activity text
    //side by side is row
    //top and bottom is column
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) { //column should fill the width

        CompositionLocalProvider(LocalContentAlpha provides (
                if (onlineStatus)
                    1f else ContentAlpha.medium)){
            Text(text = playerName, style = MaterialTheme.typography.h5
            )
        }

                //to make status text abit more transparent, we have to change its alpha
                CompositionLocalProvider (LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = if (onlineStatus)
                    "Active now" else "Offline",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerListPreview() {
    MyTheme() {
        MainScreen()
    }
}