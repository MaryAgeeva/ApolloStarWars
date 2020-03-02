package com.mary.starwars.presentation.detail

import android.os.Parcelable
import androidx.annotation.ColorRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmDetailViewObject(
    val title: String,
    val episodeId: Int,
    val director: String,
    val characters: List<CharacterViewObject>
): Parcelable

@Parcelize
data class CharacterViewObject(
    val name: String,
    val species: String,
    @ColorRes val hairColor: Int
): Parcelable