package com.example.profilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
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
fun MainScreen() {
    Scaffold(topBar = { AppBar() }) {
        //add a surface so that we have a background
        //Fill the screen and make the color Light gray
        Surface(modifier = Modifier.fillMaxSize(), color = Color.LightGray) {
            //Pass profile card to the main screen
            //Because the surface should contain a profile card
            ProfileCard()
        }
    }
}

@Composable
fun AppBar() {
    TopAppBar(navigationIcon = { Icon(Icons.Default.Home, "content description", Modifier.padding(horizontal = 12.dp))},
        title = { Text("Current Football players") }
        )
}

@Composable
fun ProfileCard(){
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
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentHeight(align = Alignment.Top), elevation = 8.dp, backgroundColor = Color.White) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) { //specify some alignments for the card //this is the row for the text
            ProfilePicture()
            ProfileContent()
        }
    }
}

//this is the composable for the profile picture
@Composable
fun ProfilePicture() {
    //lets wrap this picture in a card
    //because card has shape and shape can take alot of forms
    //Have the image in a circle shape card
    //specify a border to this circle shape
    //also add some padding
    //add some elevation for the picture
    Card(shape = CircleShape, border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.lightGreen), modifier = Modifier.padding(16.dp), elevation = 4.dp) {
        //we need an Image: add a description and size
        Image(painter = painterResource(id = R.drawable.pic1),
            contentDescription = "describe the picture", modifier = Modifier.size(72.dp),
            contentScale = ContentScale.Crop)
        //if the image is too big, crop it

    }
}

//this is the composable for the profile content
@Composable
fun ProfileContent() {
    //add sum content information
    //cannot have two composables without a row or column
    //name of the user should be on top of the activity text
    //side by side is row
    //top and bottom is column
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) { //column should fill the width
        Text(text = "MinusZero", style = MaterialTheme.typography.h5)

        //to make status text abit more transparent, we have to change its alpha
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(text = "Active now", style = MaterialTheme.typography.body2)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme(){
        MainScreen()
    }
}