package com.example.sample

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class firebaseRepository {

        private val database = FirebaseDatabase.getInstance()
        private val usersRef = database.getReference("users")

        fun addUserEntry(userPassword: String, entry: UserArchiveEntry, onComplete: (Boolean) -> Unit) {
            val userEntriesRef = usersRef.child(userPassword)
            val entryId = userEntriesRef.push().key ?: return
            userEntriesRef.child(entryId).setValue(entry)
                .addOnCompleteListener { task ->
                    onComplete(task.isSuccessful)
                }
        }

    fun getUserEntries(userPassword: String, onComplete: (List<UserArchiveEntry>?) -> Unit) {
        val userEntriesRef = usersRef.child(userPassword)
        userEntriesRef.get().addOnSuccessListener { snapshot ->
            val entriesList = snapshot.children.mapNotNull { it.getValue(UserArchiveEntry::class.java) }
            onComplete(entriesList)
        }.addOnFailureListener {
            onComplete(null)
        }
    }

    }


