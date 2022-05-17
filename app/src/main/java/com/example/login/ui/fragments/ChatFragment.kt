package  com.example.login.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.login.R
import com.example.login.UsersActivity
import com.example.login.bottom
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.*
import java.util.ArrayList


class ChatFragment : Fragment() {


    var btn_open: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
        btn_open = view?.findViewById(R.id.btn_open)

        btn_open?.setOnClickListener() {
            //val intent = Intent(activity, UsersActivity::class.java)
            //startActivity(intent)
            println("hi how are you ")

        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



}