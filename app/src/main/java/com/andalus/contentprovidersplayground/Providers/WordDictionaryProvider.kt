package com.andalus.contentprovidersplayground.Providers

import android.content.Context
import android.database.Cursor
import android.os.AsyncTask
import android.provider.UserDictionary
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andalus.contentprovidersplayground.Objects.Word

class WordDictionaryProvider {

    companion object {
        private val globalWordList = MutableLiveData<MutableList<Word>>()
        fun getWordDictionaryList(context: Context): LiveData<MutableList<Word>> {
            WordDictionaryAsyncTask().execute(context)
            return globalWordList
        }
    }

    private class WordDictionaryAsyncTask : AsyncTask<Context, Unit, Cursor?>() {
        override fun doInBackground(vararg p0: Context?): Cursor? {
            val projection = listOf(
                UserDictionary.Words.WORD,
                UserDictionary.Words.LOCALE,
                UserDictionary.Words.FREQUENCY,
                UserDictionary.Words.APP_ID
            )
            return p0[0]?.contentResolver?.query(
                UserDictionary.Words.CONTENT_URI,
                projection.toTypedArray(),
                null,
                null,
                null
            )
        }

        override fun onPostExecute(result: Cursor?) {
            super.onPostExecute(result)
            val wordList = mutableListOf<Word>()
            while (result?.moveToNext() == true) {
                wordList.add(
                    Word(
                        result.getString(0),
                        result.getString(1),
                        result.getString(2),
                        result.getString(3)
                    )
                )
                globalWordList.value = wordList
            }
        }
    }
}