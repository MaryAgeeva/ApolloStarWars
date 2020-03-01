package com.mary.starwars.presentation.films

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mary.starwars.core.presentation.BaseViewModel
import com.mary.starwars.domain.use_case.GetFilmsUseCase
import com.mary.starwars.presentation.mappers.toFilmViews
import com.mary.starwars.presentation.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers

class FilmsViewModel(private val getFilmsAction: GetFilmsUseCase) : BaseViewModel() {

    private var items = MutableLiveData<FilmsState>()

    val films: LiveData<FilmsState> = items.also {
        getFilms()
    }

    fun getFilms() {
        disposable += getFilmsAction()
            .doOnSubscribe {
                items.value = FilmsState.Loading
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { films ->
                    items.value = FilmsState.Success(films.toFilmViews())
                },
                {
                    items.value = FilmsState.Error
                }
            )
    }
}
