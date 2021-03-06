package com.andalus.contentprovidersplayground.Providers

import android.content.Context
import android.database.Cursor
import android.os.AsyncTask
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andalus.contentprovidersplayground.Objects.Contact

class ContactsProvider {

    companion object {
        private val globalContactsList = MutableLiveData<MutableList<Contact>>()
        fun getContactsList(context: Context): LiveData<MutableList<Contact>> {
            ContactsAsyncTask().execute(context)
            return globalContactsList
        }
    }

    private class ContactsAsyncTask : AsyncTask<Context, Unit, Cursor?>() {
        override fun doInBackground(vararg p0: Context?): Cursor? {
            val projection = arrayOf(
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.TIMES_CONTACTED
            )
            return p0[0]?.contentResolver?.query(
                ContactsContract.Contacts.CONTENT_URI,
                projection,
                null,
                null,
                ContactsContract.Contacts.TIMES_CONTACTED + " DESC"
            )
        }

        override fun onPostExecute(result: Cursor?) {
            super.onPostExecute(result)
            globalContactsList.value = result?.toContactsList()
        }
    }
}

fun Cursor.toContactsList(): MutableList<Contact> {
    val contactsList = mutableListOf<Contact>()
    while (this.moveToNext()) {
        contactsList.add(
            Contact(
                this.getString(0),
                this.getString(1)
            )
        )
    }
    return contactsList
}

