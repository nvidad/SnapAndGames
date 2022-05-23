package  com.example.login.ui.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.*
import com.example.login.R
import com.example.login.adapter.LeaderboardAdapter
import com.example.login.adapter.UserAdapter
import com.example.login.firebase.FirebaseService
import com.example.login.firebase.FirestoreService
import com.example.login.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_chat1.imgProfile
import kotlinx.android.synthetic.main.activity_users.*
import java.util.*
import kotlin.collections.ArrayList

class LeaderboardFragment : Fragment() {

    val TAG = "LeaderboardFragment"

    var userList = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_leaderboard, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseService.sharedPref = activity?.getSharedPreferences("sharedPref",Context.MODE_PRIVATE)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            FirebaseService.token = it.token
        }

        userRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        getUsersList()
    }



    private fun getUsersList() {
        val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

        val userid = firebase.uid
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/$userid")


        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Users")


        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                val db = FirebaseFirestore.getInstance()

                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val user = dataSnapShot.getValue(User::class.java)

                    if (user != null) {
                        val docRef = db.collection("users").document(user.userId)
                        docRef.get()
                            .addOnSuccessListener { document ->
                                if (document != null && document.exists()) {
                                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                                    user.userPoints = document.get("points") as Long
                                } else {
                                    Log.d(TAG, "No such document")
                                    user.userPoints = 0
                                }
                            }
                            .addOnFailureListener { exception ->
                                Log.d(TAG, "get failed with ", exception)
                            }
                        userList.add(user)
                    }
                }

                val sortedList = userList.sortedWith(compareBy { it.userPoints }).reversed()

                val newList = ArrayList<User>(sortedList)
                val leaderboardAdapter = LeaderboardAdapter(requireContext(),
                    newList
                )
                userRecyclerView.adapter = leaderboardAdapter
            }
        })
    }





























}