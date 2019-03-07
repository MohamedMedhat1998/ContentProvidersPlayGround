package com.andalus.contentprovidersplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.contentprovidersplayground.Adapters.ContactsAdapter
import com.andalus.contentprovidersplayground.ArchtectureComponents.MainActivityModel
import com.andalus.contentprovidersplayground.ArchtectureComponents.MainActivityModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvContacts = rvContacts

        val adapter = ContactsAdapter()

        rvContacts.layoutManager = LinearLayoutManager(this)

        rvContacts.adapter = adapter

        val viewModel =
            ViewModelProviders.of(this, MainActivityModelFactory(application)).get(MainActivityModel::class.java)

        viewModel.liveData.observe(this, Observer {
            adapter.data = it
            adapter.notifyDataSetChanged()
            Log.d("Observer","works")
        })
    }

}
