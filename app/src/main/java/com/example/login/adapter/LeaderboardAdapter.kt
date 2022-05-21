package com.example.login.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.R
import com.example.login.ChatActivity
import com.example.login.ProfileActivity
import com.example.login.model.User
import com.google.android.material.internal.ContextUtils.getActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_chat1.*
import kotlinx.android.synthetic.main.item_leaderboard_user.view.*

class LeaderboardAdapter(private val context: Context, private val userList: ArrayList<User>) :
    RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.txtUserName.text = user.userName
        Glide.with(context).load(user.profileImage).placeholder(R.drawable.profile_image).into(holder.imgUser)
        holder.txtUserPoints.text = user.userPoints.toString()

        holder.layoutUser.userImage.setOnClickListener {
            val intent = Intent(
                ,
                ProfileActivity::class.java
            )
            startActivity(intent)
        }

        holder.layoutUser.userName.setOnClickListener {
            val intent = Intent(context,ChatActivity::class.java)
            intent.putExtra("userId",user.userId)
            intent.putExtra("userName",user.userName)
            context.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtUserName:TextView = view.findViewById(R.id.userName)
        val txtUserPoints:TextView = view.findViewById(R.id.userPoints)
        val imgUser:CircleImageView = view.findViewById(R.id.userImage)
        val layoutUser:LinearLayout = view.findViewById(R.id.layoutUser)
    }

}