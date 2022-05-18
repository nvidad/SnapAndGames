package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.login.R
import com.example.login.databinding.ActivityBottomBinding
import com.example.login.ui.utis.Utility.transparentStatusBar
import com.example.login.ui.utis.Utility.viewBinding

class bottom: AppCompatActivity() {

    private val binding by viewBinding(ActivityBottomBinding::inflate)

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_LOGIN)

        super.onCreate(savedInstanceState)

        transparentStatusBar()

        setContentView(binding.root)

        initViews()

    }

    private fun initViews(){

        setUpBottomNavigation()

    }

    private fun setUpBottomNavigation(){

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHost.navController

        binding.bottomNavigation.setupWithNavController(navController)

        binding.bottomNavigation.setOnItemReselectedListener {
            //do something when selected twice
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(
                item,
                Navigation.findNavController(this, R.id.nav_host_fragment)
            )
        }

        binding.bottomNavigation.itemIconTintList = null

        //if we are viewing stories, hide the bottom navigation
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if(destination.id == R.id.viewStoriesFragment) {
//
//                binding.bottomNavigation.visibility = View.GONE
//            } else {
//                binding.bottomNavigation.visibility = View.VISIBLE
//            }
//        }


    }

}