package com.example.login.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

class FirestoreService {

    private val TAG:String = "FirestoreService"

    val user = FirebaseAuth.getInstance().currentUser
    private var uid: String? = user?.uid
    val db = FirebaseFirestore.getInstance()
    private val docRef = db.collection("users").document(uid!!)


//    fun getPoints(uid:String): Long {
//        val docRef =  db.collection("users").document(uid)
//        var points:Long = 0
//        docRef.get()
//            .addOnSuccessListener { document ->
//                if (document != null && document.exists()) {
//                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
//                    points = document.get("points") as Long
//                    Log.d(TAG, "points value: $points")
//                } else {
//                    Log.d(TAG, "No such document")
//                    docRef.set(hashMapOf("points" to 0))
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "get failed with ", exception)
//            }
//
//        return points
//    }

    fun addPoints(newPoints:Int) {
        if (uid != null) {
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                        val oldPoints = document.get("points") as Long
                        val newScore:Long = oldPoints + newPoints
                        docRef.set(hashMapOf("points" to newScore))
                    } else {
                        Log.d(TAG, "No such document")
                        docRef.set(hashMapOf("points" to newPoints))
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
        }
    }

}