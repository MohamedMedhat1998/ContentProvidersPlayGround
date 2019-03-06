package com.andalus.contentprovidersplayground.ArchtectureComponents

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.andalus.contentprovidersplayground.Objects.Word
import com.andalus.contentprovidersplayground.Providers.WordDictionaryProvider


class MainActivityModel(application: Application) : AndroidViewModel(application) {

    private val privateLiveData: MutableLiveData<MutableList<Word>> = MutableLiveData()

    val liveData: LiveData<MutableList<Word>> = Transformations.switchMap(privateLiveData) {
        WordDictionaryProvider.getWordDictionaryList(application.applicationContext)
    }

    init {
        privateLiveData.value = mutableListOf()
    }


}