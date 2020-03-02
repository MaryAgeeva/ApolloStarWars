package com.mary.starwars.presentation.films

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmViewObject(
    val id: String,
    val episodeId: Int,
    val title: String
): Parcelable