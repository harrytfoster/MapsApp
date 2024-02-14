package com.example.mapsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, getPreferences(MODE_PRIVATE))
        setContent {
            MapContent()
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
            {controller.setCenter(GeoPoint(51.05, -0.72))
                controller.setZoom(14.0)
                setTileSource(TileSourceFactory.MAPNIK)
                isClickable =true }
        }

    )

}

