package com.mary.starwars.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mary.starwars.core.presentation.BaseViewModel
import com.mary.starwars.domain.use_case.GetFilmUseCase
import com.mary.starwars.presentation.mappers.toDetailFilmView
import com.mary.starwars.presentation.utils.plusAssign

class DetailViewModel(
    private val id: String,
    private val getFilmAction: GetFilmUseCase
) : BaseViewModel() {

    private val item = MutableLiveData<FilmDetailViewObject>()

    val film: LiveData<FilmDetailViewObject> = item.also {
        disposable += getFilmAction(id)
            .subscribe(
                { film ->
                    item.value = film.toDetailFilmView()
                },
                {
                    Log.v("DetailViewModel", "couldn't find film info")
                }
            )
    }
}
