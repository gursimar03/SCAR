package com.example.scar.ui.theme

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.json.JSONArray

@Serializable
data class Data(@SerialName(value = "data")
                 var players: Player? = null)

//                fun getPost(): userPost? {
//                return mPost
//                }
//
//                fun setPost(post: userPost) {
//                mPost = post
//                    }
