package com.example.sample

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FitnessRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getCategories(): List<catgeoryExercises> {
        val snapshot = db.collection("Exercises").get().await()
        return snapshot.documents.map { doc ->
            doc.getString("Name")?.let {
                catgeoryExercises(

                    name = it,
                    description = doc.getString("Description")!!,
                    id = doc.getString("categoryID")!!


                    )
            }!!
        }
    }

    suspend fun getExercisesByCategory(categoryId: String): List<exercises> {
        val snapshot = db.collection("Choices").whereEqualTo("DocumentID", categoryId).get().await()
        return snapshot.documents.map { doc ->
            exercises(

                name = doc.id,
               documentId = doc.getString("DocumentID") ?: "",
                difficulty = doc.getString("Difficulty") ?: "",
                description = doc.getString("Description") ?: "",
                url = doc.getString("imageUrl") ?: ""
            )
        }
    }
}