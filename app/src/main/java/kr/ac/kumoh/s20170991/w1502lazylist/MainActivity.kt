package kr.ac.kumoh.s20170991.w1502lazylist

import android.app.Application
import android.app.ProgressDialog.show
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import kr.ac.kumoh.s20170991.w1502lazylist.ui.theme.W1502LazyListTheme

class MainActivity : ComponentActivity() {
    //data class Song(var title: String, var singer: Stsring)
    //private val songs = mutableStateListOf<Song>()
    private lateinit var model: SongViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this)[SongViewModel::class.java]
        //repeat(10){
            //songs.add(SongViewModel.SongViewModel.Song("demons", "ImagineDragons"))
            //songs.add(SongViewModel.SongViewModel.Song("Ghost Town", "benson boone"))
            //songs.add(SongViewModel.SongViewModel.Song("Dead man Walking", "cityWolf"))
        //}
        setContent {
             MyApp()
        }
    }

    @Composable
    fun MyApp(){
        W1502LazyListTheme {
            Column {
                Button(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp),
                    onClick = {
                        //songs.add(SongViewModel.SongViewModel.Song("spaceman", "tomRider"))
                        model.requestSong()
                    }
                ){
                    Text("추가")
                }
                SongList()
            }

        }
    }

    @Composable
    fun SongItem(index: Int, song: SongViewModel.SongViewModel.Song){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(255, 210, 210))
                .padding(8.dp)
                .clickable {
                    Toast
                        .makeText(application, song.title, Toast.LENGTH_LONG)
                        .show()
                }
        ) {
            AsyncImage(
                //model = "https://picsum.photos/300/300?random=$index",
                //contentDescription = "노래 앨범 이미지",
                model = "${SongViewModel.SEVER_URL}/image/${song.image}",
                contentDescription = "노래 앨범 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    //.clip(CircleShape)
                    .clip(RoundedCornerShape(percent = 10)),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(255, 210, 210))
                    .padding(8.dp)
            ) {
                Text(song.title, fontSize = 30.sp)
                Text(song.singer, fontSize = 20.sp)
            }
        }
    }

    @Composable
    fun SongList() {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(4.dp),
        ){
            itemsIndexed(model.songs) { index, song ->
                SongItem(index, song)
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    W1502LazyListTheme {
//        MyApp()
//    }
//}