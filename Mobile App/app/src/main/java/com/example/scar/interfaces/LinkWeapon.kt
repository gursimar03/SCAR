package com.example.scar.interfaces

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

//import com.example.scar.AppViewModelProvider
import com.example.scar.R
import com.example.scar.screens.GunUiState
import com.example.scar.screens.GunViewModel
//import com.example.scar.gun.GunEntryViewModel

import com.example.scar.screens.PlayerUiState
import com.example.scar.screens.PlayerViewModel
import com.example.scar.ui.theme.Gun

import com.example.scar.ui.theme.GunData
import com.example.scar.ui.theme.GunList

import com.example.scar.ui.theme.Screen
import com.example.scar.ui.theme.cardBg2
import com.example.scar.ui.theme.cardBgDark
import com.example.scar.ui.theme.creme
import com.example.scar.ui.theme.textBg


//sources
///https://stackoverflow.com/questions/72794300/is-there-a-way-to-filter-the-exposed-dropdown-menu-options-depending-on-the-valu/72795638#72795638
@Composable
fun LinkWeapon(navController: NavController) {

    val gunViewModel: GunViewModel = viewModel()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
//                .fillMaxSize()
                .padding(horizontal = 16.dp)

        ) {
            Card(
                modifier = Modifier
//                    .fillMaxSize()
                    .padding(16.dp)
                    .height(600.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
//            weaponList()
                GunUI(guns = gunViewModel.gunUiState)
            }

            Column {


                val playerViewModel: PlayerViewModel = viewModel()
                Button(
                    modifier = Modifier
                        .width(350.dp)
                        .height(80.dp)
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(70),
                            clip = true
                        ),
                    onClick = {

                        navController.navigate(Screen.StartMatch.route)

                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        textBg
                    ),
//                border = BorderStroke(5.dp, Color.Black)
                ) {
                    Text(
                        text = "Continue", fontFamily = FontFamily(
                            Font(R.font.montserrat_bold, weight = FontWeight.Normal)
                        ), fontSize = 20.sp
                    )
                }
            }
        }


    }
    Spacer(modifier = Modifier.height(40.dp))

    BottomNavigationBar(navController = navController)
}

data class weaponData(val name:String)
@Composable
fun weaponList(list: List<Gun>)
{

    LazyColumn {
        itemsIndexed(list) { index, entry ->
            weapon(name = entry.weaponName, image = entry.image)
        }
    }
}

@Composable
fun weapon(name:String, image:String){
    var clicked by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
//    val GunViewModel:GunEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)

    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(400.dp)
            .shadow(
                elevation = 8.dp,
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),

    ) {
        Text(
            text = name,
            style = TextStyle(color= Color.Black, fontSize = 30.sp),
            fontFamily= FontFamily(
                Font(R.font.montserrat_bold,weight= FontWeight.Normal)
            ),
            modifier = Modifier.padding(10.dp)

        )
        Box(modifier=Modifier
            .size(250.dp).align(Alignment.CenterHorizontally)
            ,
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,

            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ElevatedCard(
                colors = CardDefaults.cardColors(containerColor = cardBgDark),
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .shadow(
                        shape = RoundedCornerShape(70),
                        spotColor = Color.White,
                        elevation = 30.dp,
                    ),
            ) {
                Row {

                    Button(onClick = {
//                        val TestGun = Gun(name = "Gun A", gunID = 1)
//                        coroutineScope.launch {
//                            GunViewModel.saveItem(TestGun);
//                        }
                        clicked = !clicked
                    },

                        modifier = Modifier.fillMaxSize()
                            .background(if (clicked) Color.Green else Color.Gray),
                        colors = if (clicked) ButtonDefaults.buttonColors(Color.Green) else ButtonDefaults.buttonColors(
                            cardBg2)
                    ) {
                        Text(text = if (clicked) "Gun Linked" else "Link Gun", color = Color.White,
                            fontFamily = FontFamily(
                                Font(R.font.montserrat_bold, weight = FontWeight.Normal)),
                            fontSize = 16.sp)
                    }
                }
            }

        }
    }
}

@Composable
fun GunUI(guns: GunUiState) {

    when (guns) {
        is GunUiState.Loading -> LoadingGuns()
        is GunUiState.Success ->
            SuccessLoadingGuns(guns.data)
//

        is GunUiState.Error -> LoadingGuns()
        else -> {
            LoadingGuns()}
    }
}

@Composable
fun LoadingGuns()
{
//    val matchDataExample = remember {
//        listOf(
//            weaponData("weapon1"),
//            weaponData("weapon1"),
//            weaponData("weapon1"),
//
//            )
//    }
//
//    LazyColumn {
//        itemsIndexed(matchDataExample) { index, entry ->
//            (entry.kills,entry.spotted,entry.travelled)
//        }
//    }
}

@Composable
fun SuccessLoadingGuns(gunInfoList: GunData)
{
    Log.d("userInfoList",gunInfoList.guns.toString())
    for (gun in gunInfoList.guns) {
        // Replace this with your custom layout for displaying each gun
        weaponList(list = gun.weapons)
    }
}








