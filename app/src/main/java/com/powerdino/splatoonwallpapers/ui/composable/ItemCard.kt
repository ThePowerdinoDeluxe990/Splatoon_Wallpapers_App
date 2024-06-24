package com.powerdino.splatoonwallpapers.ui.composable

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.SplatoonWallpapersTheme
import com.powerdino.splatoonwallpapers.R

@Composable
fun ItemCard(
    onClickButton: () -> Unit,
    @StringRes wallpaperName:Int,
    @DrawableRes wallpaperImage:Int
){
   Card(
       onClick = onClickButton,
       colors = CardDefaults.cardColors(
           containerColor = MaterialTheme.colorScheme.secondaryContainer,
       ),


   ) {
       Column(
           modifier = Modifier.padding(5.dp).fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ) {
           Text(
               text = stringResource(wallpaperName),
               style = MaterialTheme.typography.titleLarge,
               color = MaterialTheme.colorScheme.onSurface,
               fontWeight = FontWeight.Bold,
               textAlign = TextAlign.Center
           )

           Spacer(modifier = Modifier.padding(2.dp))

           Image(
               painter = painterResource(wallpaperImage) ,
               contentDescription = stringResource(wallpaperName),
               contentScale = ContentScale.Crop,
               modifier = Modifier
                   .clip(RoundedCornerShape(16.dp)),
               alignment = Alignment.Center
           )

       }
   }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light",
    backgroundColor = 0xFFFCFCFF
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark",
    backgroundColor = 0xFF1A1C1E
)
@Composable
private fun Preview(){
    SplatoonWallpapersTheme {
        ItemCard (
            {},
            R.string.splatoon1,
            R.drawable.splatoon1
        )

    }
}