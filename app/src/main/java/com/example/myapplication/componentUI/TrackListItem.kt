package com.example.myapplication.componentUI

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.PlaylistActivity
import com.example.myapplication.R
import com.example.myapplication.TrackDetailsActivity
import com.example.myapplication.domain.Track
@Composable
fun TrackListItem(track: Track) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(context, TrackDetailsActivity::class.java)
            intent.putExtra("TRACK_ID", track.trackId)
            context.startActivity(intent)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.music),
                contentDescription = "Трек ${track.trackName}",
                modifier = Modifier.size(40.dp)
            )
            Column(
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(track.trackName, fontWeight = FontWeight.Bold,color = Color.Black)
                Text(track.artistName, color = Color.Black)
            }
            Text(track.trackTime, color = Color.Black)
        }
    }
}