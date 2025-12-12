package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.Storage
import com.example.myapplication.data.TrackDto
import com.example.myapplication.componentUI.PlaylistItem
import com.example.myapplication.componentUI.PlaylistViewModel

@OptIn(ExperimentalMaterial3Api::class)
class TrackDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val trackId = intent?.getIntExtra("TRACK_ID", -1) ?: -1
        val storage = Storage()
        val trackDto = storage.findTrackById(trackId)

        setContent {
            MaterialTheme {
                var showPlaylistBottomSheet by remember { mutableStateOf(false) }
                Surface(color = Color.White) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (trackDto != null) {
                            TrackDetailsHeader(
                                onBackClick = { finish() },
                            )
                            TrackDetailsScreen(
                                trackDto = trackDto,
                            )
                        } else {
                            Text("Трек не найден")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TrackDetailsHeader(onBackClick: () -> Unit) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackDetailsScreen(
    trackDto: TrackDto,
    viewModel: PlaylistViewModel = viewModel()
) {
    var showPlaylistBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Основной контент
        Spacer(modifier = Modifier.height(36.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Track Image",
            modifier = Modifier
                .size(312.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = trackDto.trackName,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 22.sp
            ),
            modifier = Modifier.width(312.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = trackDto.artistName,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
            modifier = Modifier.width(312.dp)
        )
        Spacer(modifier = Modifier.height(54.dp))
        Row(
            modifier = Modifier.width(312.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка добавления в плейлист
            FloatingActionButton(
                onClick = { showPlaylistBottomSheet = true },
                modifier = Modifier
                    .size(51.dp)
                    .background(color = Color(0x1A1B2240), shape = CircleShape)
                    .alpha(0.25f)
            ) {
                Image(
                    painter = painterResource(R.drawable.music),
                    contentDescription = "Добавить"
                )
            }

            if (showPlaylistBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showPlaylistBottomSheet = false }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(505.dp)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Добавить в плейлист",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = TextStyle(fontSize = 19.sp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyColumn {
                            items(viewModel.playlists) { playlist ->
                                PlaylistItem(playlist = playlist) {
                                    val added = viewModel.addTrackToPlaylist(playlist.name, trackDto)
                                    // Можно показывать уведомление, что добавлено
                                }
                            }
                        }
                    }
                }
            }

            // Другие кнопки
            FloatingActionButton(
                onClick = { /* действие */ },
                modifier = Modifier
                    .size(51.dp)
                    .background(color = Color(0x1A1B2240), shape = CircleShape)
                    .alpha(0.25f)
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Добавить"
                )
            }
        }
    }
}