package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.login.bottom
import com.google.firebase.auth.FirebaseAuth

class SnapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snap)
    }

    val mAuth = FirebaseAuth.getInstance()//gives us instance of authentication.

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflattor = menuInflater
        inflattor.inflate(R.menu.snaps,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         if (item?.itemId== R.id.logout){
            mAuth.signOut()//signout
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mAuth.signOut()
    }


//calling memory game
    fun memoryGameClicked(view : View){
    memoryGame()
    }
    fun memoryGame(){
        val intent = Intent(this, UsersActivity::class.java)
        startActivity(intent)
    }

    //calling brain game
    fun brainGameClicked(view : View){
        brainGame()
    }
    fun brainGame(){
          println("hello world")
    //val intent = Intent(this, brainGameActivity::class.java)
        //startActivity(intent)
    }
    fun SNAPCHATClicked(view: View){
        snapchat()
    }
    fun snapchat(){
        val intent = Intent(this, bottom::class.java)
        startActivity(intent)
    }


}