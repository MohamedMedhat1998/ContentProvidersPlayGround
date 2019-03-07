package com.andalus.contentprovidersplayground

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.contentprovidersplayground.Adapters.ContactsAdapter
import com.andalus.contentprovidersplayground.ArchtectureComponents.MainActivityModel
import com.andalus.contentprovidersplayground.ArchtectureComponents.MainActivityModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val CONTACTS_PERMISSION_REQUEST_CODE = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_CONTACTS), CONTACTS_PERMISSION_REQUEST_CODE
                )
            } else {
                runTheApp()
            }
        } else {
            runTheApp()
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CONTACTS_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                runTheApp()
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.permission_error),
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
    }

    private fun runTheApp() {
        val rvContacts = rvContacts

        val adapter = ContactsAdapter()

        rvContacts.layoutManager = LinearLayoutManager(this)

        rvContacts.adapter = adapter

        val viewModel =
            ViewModelProviders.of(this, MainActivityModelFactory(application)).get(MainActivityModel::class.java)

        viewModel.liveData.observe(this, Observer {
            adapter.data = it
            adapter.notifyDataSetChanged()
            Log.d("Observer", "works")
        })
    }
}
