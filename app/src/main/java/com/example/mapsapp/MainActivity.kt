package com.example.mapsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, getPreferences(MODE_PRIVATE))
        setContent {
            BoxMapContent()
        }
    }
}

@Composable
fun MapContent() {
    val context =
        LocalContext.current
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
                context ->
            MapView(context).apply()
            {controller.setCenter(GeoPoint(50.9, -1.4))
                controller.setZoom(14.0)
                setTileSource(TileSourceFactory.MAPNIK)
                isClickable =true }
        }

    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxMapContent() {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()){
    var currentLatitude by remember { mutableStateOf(0.0) }
    var currentLongitude by remember { mutableStateOf(0.0) }
    var SearchLatitude = 0.0
    var SearchLongitude = 0.0
    TextField(value = "$currentLatitude", onValueChange = { currentLatitude=it.toDouble()}, modifier = Modifier.align(Alignment.BottomStart))
    TextField(value = "$currentLongitude", onValueChange = { currentLongitude=it.toDouble()}, modifier = Modifier.align(Alignment.BottomCenter))
    Button(onClick = {SearchLongitude = currentLongitude; SearchLatitude=currentLatitude}, modifier = Modifier.align(Alignment.BottomEnd)) {
        Text("Search")
    }
        AndroidView(modifier = Modifier.height(this.maxHeight-250.dp),factory = {
                context ->
            MapView(context).apply()
            {controller.setCenter(GeoPoint(SearchLatitude, SearchLongitude))
                controller.setZoom(14.0)
                setTileSource(TileSourceFactory.MAPNIK)
                isClickable =true
            }})
    }
}

@Composable
fun NewMap(Latitude: Double, Longitude: Double){
    BoxWithConstraints {
        AndroidView(modifier = Modifier.height(this.maxHeight-250.dp),factory = {
                context ->
            MapView(context).apply()
            {controller.setCenter(GeoPoint(Latitude, Longitude))
                controller.setZoom(14.0)
                setTileSource(TileSourceFactory.MAPNIK)
                isClickable =true
            }})
    }
}


