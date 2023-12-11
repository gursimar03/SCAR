package com.example.scar.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "guns")

class Gun(val name:String,  @PrimaryKey val gunID:Int){
}