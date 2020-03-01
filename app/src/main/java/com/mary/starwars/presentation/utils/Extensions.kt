package com.mary.starwars.presentation.utils

import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

fun View.show() {
    if(visibility != View.VISIBLE)
        visibility = View.VISIBLE
}

fun View.hide() {
    if(visibility != View.GONE)
        visibility = View.GONE
}