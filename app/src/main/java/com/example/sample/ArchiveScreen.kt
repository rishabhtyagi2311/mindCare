import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import coil.compose.rememberImagePainter

import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sample.ArchiveViewModel
import com.example.sample.R
import com.example.sample.UserArchiveEntry
import com.example.sample.unique


@Composable
fun Archive(navController: NavHostController)
{
    val archiveViewModel : ArchiveViewModel = viewModel()
    LaunchedEffect(Unit) {
        archiveViewModel.fetchUserEntries(unique)
    }

    val entriesState by archiveViewModel.entriesState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (entriesState.isNotEmpty()) {
            Column(modifier = Modifier.padding(2.dp)) {


                androidx.compose.material3.Text(
                    "      Down to your Memory Lane",

                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Cyan),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(entriesState) { entry ->
                        UserEntryItem(entry = entry)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }

}

@Composable
fun UserEntryItem(entry: UserArchiveEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, color = Color.Black),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Title: ${entry.title}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Date: ${entry.date}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Description: ${entry.description}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(entry.images) { imageUri ->
                    val image = Uri.parse(imageUri)

                    Image(
                        painter = rememberAsyncImagePainter(model = image),
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

