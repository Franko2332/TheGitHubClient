package ru.gb.thegithubclient.ui.users

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.rxjava3.subjects.ReplaySubject


class RxFloatingActionButton: FloatingActionButton {
    constructor(context: Context): super(context)
    constructor(context: Context, attributes: AttributeSet): super(context, attributes)
    val clickEventObservable: ReplaySubject<Boolean> =  ReplaySubject.create()

    init {
        setOnClickListener { clickEventObservable.onNext(true) }
    }
}