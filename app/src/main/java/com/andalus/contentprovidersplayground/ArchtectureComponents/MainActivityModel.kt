package com.andalus.contentprovidersplayground.ArchtectureComponents

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.andalus.contentprovidersplayground.Objects.Contact
import com.andalus.contentprovidersplayground.Providers.ContactsProvider


class MainActivityModel(application: Application) : AndroidViewModel(application) {

    private val privateLiveData: MutableLiveData<MutableList<Contact>> = MutableLiveData()

    val liveData: LiveData<MutableList<Contact>> = Transformations.switchMap(privateLiveData) {
        Log.d("LiveData","works")
        ContactsProvider.getContactsList(application.applicationContext)
    }

    init {
        privateLiveData.value = mutableListOf()
    }


}