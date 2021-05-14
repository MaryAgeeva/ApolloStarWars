package com.mary.starwars.presentation.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

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

fun ViewGroup.inflate(resource: Int): View {
    return LayoutInflater.from(context).inflate(resource, this, false)
}