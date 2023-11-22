package com.example.scar.interfaces

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.scar.R
import com.example.scar.ui.theme.cardBg
import com.example.scar.ui.theme.cardBg2
import com.example.scar.ui.theme.creme
import com.example.scar.ui.theme.textBg

//sources
///https://stackoverflow.com/questions/72794300/is-there-a-way-to-filter-the-exposed-dropdown-menu-options-depending-on-the-valu/72795638#72795638
@Composable
fun Startmatch() {
    val list = listOf("SelectionA", "SelectionB", "SelectionC")
    val subListA = listOf("SubA1", "SubA2", "SubA3")
    val subListB = listOf("SubB1", "SubB2", "SubB3")
    val subListC = listOf("SubC1", "SubC2", "SubC3")


    var mainSelection by remember { mutableIntStateOf(-1) }
    var subSelection by remember { mutableIntStateOf(-1) }

    val subList = remember(mainSelection) {
        when (mainSelection) {
            1 -> subListB
            2 -> subListC
            else -> subListA
        }
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "bg",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
//                .fillMaxSize()
                .padding(horizontal = 30.dp)

        ) {
            Card(
                modifier = Modifier
//                    .fillMaxSize()
                    .padding(16.dp)
                    .height(550.dp)
                    .shadow(
                        spotColor = Color.Green,
                        elevation = 40.dp,
                    ),
                colors = CardDefaults.cardColors(containerColor = cardBg2)
            ) {
                Text(
                    text = "Configuration",
                    style = TextStyle(color= Color.White, fontSize = 30.sp),
                    fontFamily= FontFamily(
                        Font(R.font.montserrat_bold,weight= FontWeight.Normal)
                    ),
                    modifier = Modifier.padding(10.dp)

                )
                Column(
                    modifier = Modifier
//                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                        ,
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .padding(0.dp)
                            .shadow(
                                shape = RoundedCornerShape(70),
                                spotColor = Color.Green,
                                elevation = 9.dp,
                            )) {
                        Row {
                            Card (colors = CardDefaults.cardColors(containerColor = creme),
                                modifier = Modifier.width(100.dp)){
                                Text(text = "Arena",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

                            Spacer(modifier = Modifier.width(80.dp))
                            SwitchMinimalExample()
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                        ,
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .shadow(
                                shape = RoundedCornerShape(70),
                                spotColor = Color.Black,
                                elevation = 30.dp,
                            )) {
                        Row {
                            Card (colors = CardDefaults.cardColors(containerColor = creme),
                                modifier = Modifier.width(100.dp)){
                                Text(text = "GPS",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

                            Spacer(modifier = Modifier.width(80.dp))
                            SwitchMinimalExample()
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                        ,
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .shadow(
                                shape = RoundedCornerShape(70),
                                spotColor = Color.Black,
                                elevation = 30.dp,
                            )) {
                        Row {
                            Card (colors = CardDefaults.cardColors(containerColor = creme),
                                modifier = Modifier.width(130.dp)){
                                Text(text = "Highlight",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

                            Spacer(modifier = Modifier.width(80.dp))
                            SwitchMinimalExample()
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    ElevatedCard(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .shadow(
                                shape = RoundedCornerShape(70),
                                spotColor = Color.Black,
                                elevation = 30.dp,
                            ),
                        ) {
                        Row {
                            Card (colors = CardDefaults.cardColors(containerColor = creme),
                                modifier = Modifier.width(100.dp)){
                                Text(text = "LED",
                                    style = TextStyle(color = Color.White),
                                    fontSize = 20.sp,
                                    fontFamily= FontFamily(
                                        Font(R.font.montserrat_regular,weight= FontWeight.Normal)
                                    ),
                                    modifier = Modifier.padding(15.dp))
                            }

                            Spacer(modifier = Modifier.width(80.dp))
                            SwitchMinimalExample()
                        }
                    }

                  Spacer(modifier = Modifier.height(35.dp))

                    Button(
                        modifier = Modifier.width(300.dp),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            textBg
                        )
                    ) {
                        Text(text = "Done", fontFamily= FontFamily(
                            Font(R.font.montserrat_bold,weight= FontWeight.Normal)
                        ), fontSize = 20.sp)
                    }

                }
            }

        }

    }
    Spacer(modifier = Modifier.height(40.dp))

    BottomNavigationBar(navController = rememberNavController())
}



@Composable
fun SwitchMinimalExample() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        modifier = Modifier
            .width(150.dp)
            .padding(15.dp)
    )
}