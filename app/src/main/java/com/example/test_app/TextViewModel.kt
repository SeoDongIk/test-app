package com.example.test_app

import android.app.Application
import androidx.compose.material.Text
import androidx.lifecycle.*
import com.example.room.Database.TextDatabase
import com.example.room.Entity.TextEntity
import com.example.room.Repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TextViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData : LiveData<List<TextEntity>>
    val repository : Repository

    init {
        val textDao = TextDatabase.getDatabase(application)!!.textDao()
        repository = Repository(textDao)
        readAllData = repository.readAllData.asLiveData()
    }

    fun addText(text : TextEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addText(text)
        }
    }
}

