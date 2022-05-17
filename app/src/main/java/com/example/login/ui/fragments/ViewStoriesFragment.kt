package com.example.login.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.view.View.OnTouchListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.login.R

import com.example.login.databinding.FragmentViewStoriesBinding
import com.example.login.ui.utis.viewBinding
import jp.shts.android.storiesprogressview.StoriesProgressView



class ViewStoriesFragment : Fragment(), StoriesProgressView.StoriesListener {


    private val binding by viewBinding(FragmentViewStoriesBinding::bind)
    lateinit var navController: NavController

    private var counter = 0
    private val resources = intArrayOf(
            R.drawable.pichu,
            R.drawable.pikachu,
            R.drawable.raichu,
            R.drawable.leichu,
            R.drawable.yazi
    )

    private var pressTime = 0L
    private var limit = 500L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_stories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        navController = Navigation.findNavController(view)
        initView()
    }

    private fun initView(){

        showStories()

        handleClicks()
    }


    private fun handleClicks(){

        //on click previous
        binding.reverse.setOnClickListener { binding.stories.reverse() }
        binding.reverse.setOnTouchListener(onTouchListener)

        //on click skip
        binding.skip.setOnClickListener { binding.stories.skip() }
        binding.skip.setOnTouchListener(onTouchListener)

    }

    private fun showStories(){

        binding.stories.setStoriesCount(PROGRESS_COUNT)
        binding.stories.setStoryDuration(3000L)
        binding.stories.setStoriesListener(this)
        counter = 0
        binding.stories.startStories(counter)
        binding.image.setImageResource(resources[counter])

    }

    override fun onNext() {
        binding.image.setImageResource(resources[++counter])
    }

    override fun onPrev() {
        if (counter - 1 < 0) return
        binding.image.setImageResource(resources[--counter])
    }

    override fun onComplete() {
        navController.popBackStack()
    }

    @SuppressLint("ClickableViewAccessibility")
    private val onTouchListener = OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pressTime = System.currentTimeMillis()
                binding.stories.pause()
                return@OnTouchListener false
            }
            MotionEvent.ACTION_UP -> {
                val now = System.currentTimeMillis()
                binding.stories.resume()
                return@OnTouchListener limit < now - pressTime
            }
        }
        false
    }


    companion object {
        private const val PROGRESS_COUNT = 5
    }

}