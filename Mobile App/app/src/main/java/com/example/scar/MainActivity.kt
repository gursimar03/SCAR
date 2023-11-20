package com.example.scar
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
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
import com.example.scar.ui.theme.createJson
import com.example.scar.ui.theme.mainBg
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException


class MainActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SCARTheme {

                Log.d("JSON", createJson().toString())
                // A surface container using the 'background' color from the theme
                Navigation()

//                val client = OkHttpClient()
//
//                val url = "https://demo5970075.mockable.io/user" // Replace with your HTTP URL
//
//                val request = Request.Builder()
//                    .url(url)
//                    .build()
//
//                client.newCall(request).enqueue(object : Callback {
//                    override fun onResponse(call: Call, response: Response) {
//                        if (response.isSuccessful) {
//                            val responseBody = response.body()?.string()
//                            println(responseBody)
//                            Log.d("success",responseBody.toString())
//
//                        } else {
//                            println("Request failed with code: ${response.code()}")
//                            Log.d("fail",response.code().toString())
//                        }
//                    }
//
//                    override fun onFailure(call: Call, e: IOException) {
//                        e.printStackTrace()
//                        Log.d("fail",e.printStackTrace().toString())
//
//                    }
//                })


            }
        }
    }

}
