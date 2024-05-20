package com.example.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseError
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArchiveViewModel : ViewModel() {
    private val userRepository = firebaseRepository()
    private val _entriesState = MutableStateFlow<List<UserArchiveEntry>>(emptyList())
    val entriesState: StateFlow<List<UserArchiveEntry>> = _entriesState

    fun addUserEntry(userPassword: String, entry: UserArchiveEntry, onComplete: (Boolean) -> Unit) {
        userRepository.addUserEntry(userPassword, entry) { success ->
            onComplete(success)
        }
    }

    fun fetchUserEntries(userPassword: String) {
        viewModelScope.launch {
            userRepository.getUserEntries(userPassword) { entries ->
                if (entries != null) {
                    _entriesState.value = entries
                }
            }
        }
    }


}