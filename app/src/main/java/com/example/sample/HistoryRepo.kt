package com.example.sample

import com.google.firebase.database.FirebaseDatabase

class HistoryRepo {
    private val database = FirebaseDatabase.getInstance()
    private val usersRef = database.getReference("users")

    fun addUserBmiEntry(userPassword: String, bmiEntry: BmiElement, onComplete: (Boolean) -> Unit) {
        val userEntriesRef = usersRef.child(userPassword).child("bmi_records")
        val entryId = userEntriesRef.push().key ?: return
        userEntriesRef.child(entryId).setValue(bmiEntry)
            .addOnCompleteListener { task ->
                onComplete(task.isSuccessful)
            }
    }

    fun getUserBmiEntries(userPassword: String, onComplete: (List<BmiElement>?) -> Unit) {
        val userEntriesRef = usersRef.child(userPassword).child("bmi_records")
        userEntriesRef.get().addOnSuccessListener { snapshot ->
            val entriesList = snapshot.children.mapNotNull { it.getValue(BmiElement::class.java) }
            onComplete(entriesList)
        }.addOnFailureListener {
            onComplete(null)
        }
    }
}