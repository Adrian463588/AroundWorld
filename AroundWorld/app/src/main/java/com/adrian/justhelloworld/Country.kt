package com.adrian.justhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: String,
    val description: String,
    val uniquephoto: Int,
    val photo: Int
) : Parcelable