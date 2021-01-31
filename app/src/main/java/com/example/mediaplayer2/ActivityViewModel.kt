package com.example.mediaplayer2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {

    private val _playlist = ArrayList<Int>()
    val playlist: ArrayList<Int>
        get() = _playlist

    private val _titles = ArrayList<String>()
    val titles: ArrayList<String>
        get() = _titles

    private var _index = MutableLiveData<Int>()
    var index: MutableLiveData<Int>
        get() = _index
        set(value) {
            _index.value = value.value
        }

    init {
        _playlist.add(R.raw.dancing_queen)
        _playlist.add(R.raw.chiquitita)
        _playlist.add(R.raw.honey_honey)
        _playlist.add(R.raw.money_money_money)
        _playlist.add(R.raw.take_a_chance_on_me)

        _titles.add("Dancing Queen")
        _titles.add("Chiquitita")
        _titles.add("Honey Honey")
        _titles.add("Money Money Money")
        _titles.add("Take A Chance On Me")

        _index.value = 0
    }


}