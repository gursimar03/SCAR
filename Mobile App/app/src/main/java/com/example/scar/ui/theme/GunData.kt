package com.example.scar.ui.theme

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GunData(@SerialName(value = "data")
                           var guns: List<GunList>,
                           val success: Boolean)
@Serializable
data class GunList(
    @SerialName(value = "user_id")  val type: Int,
    @SerialName(value = "weapons")  val weapons: List<Gun>,
)


@Serializable
data class Gun(
    @SerialName(value = "weapon_id")  val type: Int,
    @SerialName(value = "weapon_name")  val weaponName: String,
    @SerialName(value = "weapon_type")  val weaponType: String,
    @SerialName(value = "image")  val image: String,

    )
