package com.example.scar

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scar.ui.theme.SCARTheme
import com.example.scar.ui.theme.SCARTheme
import com.example.scar.ui.theme.cardBg
import com.example.scar.ui.theme.mainBg


class MainActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SCARTheme {
                // A surface container using the 'background' color from the theme
                Navigation()
//
//                val url = "http://192.168.1.5:5000//match/1"  // Replace with your actual URL
//
//                // Create a request queue
//                val requestQueue = Volley.newRequestQueue(this)
//
//                // Create a GET request
//                val jsonObjectRequest = JsonObjectRequest(
//                    Request.Method.GET, url, null,
//                    Response.Listener { response ->
//                        // Handle the JSON response
//                        val message = response.getString("message")
//                        Log.d("JSON","$message")
//                    },
//                    Response.ErrorListener { error ->
//                        // Handle errors here
//                        Log.d("ada:", "Error: ${error.message}")
//                    }
//                )
//
//                // Add the request to the request queue
//                requestQueue.add(jsonObjectRequest)

            }
        }
    }
}
