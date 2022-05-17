package com.example.login.ui.fragments
import android.os.*
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.login.R
import com.example.login.databinding.FragmentMemoryGameBinding
import com.example.login.R.drawable.*
import kotlinx.android.synthetic.main.fragment_memory_game.*

class MemoryGameFragment : Fragment() {

    private lateinit var binding: FragmentMemoryGameBinding
    private lateinit var timer: CountDownTimer
    lateinit var time : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemoryGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        time = view.findViewById(R.id.timer)

        val images: MutableList<Int> = mutableListOf(resizedandroid, resizedcplus, resizedjava,resizedjavascript,
            resizedpython, resizedruby, resizedandroid, resizedcplus, resizedjava,resizedjavascript,
            resizedpython, resizedruby)
        val buttons = arrayOf(button1, button2, button3, button4, button5, button6, button7,
            button8, button9, button10, button11, button12)
        val states = BooleanArray(12)
        val cardBack = resizedprog
        var clicked=0
        var turnOver = false
        var flipped = false
        var lastClicked =-1

        images.shuffle()

        timer = object : CountDownTimer(60000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                time.text = "Seconds left:"+ millisUntilFinished / 1000
                if (flipped) { time.text = "You Won" }
            }

            override fun onFinish() {
                if (flipped) { time.text = "You Won" }
                    else { time.text = "Game Over!!!" }

                for (i in 0..11) { buttons[i].isClickable = false }
            }

        }.start()

//        for (i in 0..11){
////            flipped = buttons[i].text !in "cardBack"
//        }
        flipped = states.all { it }

        for (i in 0..11){
            buttons[i].text="cardBack"
            buttons[i].textSize=0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "cardBack" && !turnOver) {
                    buttons[i].setImageDraw(images[i])
                    buttons[i].sour
                    buttons[i].setText(images[i])
                    if (clicked == 0) { lastClicked = i }
                    clicked++
                }
                else if (buttons[i].text !in "cardBack") {
                    buttons[i].setBackgroundResource(cardBack)
                    buttons[i].text = "cardBack"
                    clicked--
                }

                if (clicked == 2) {
                    turnOver = true
                    if (buttons[i].text == buttons[lastClicked].text) {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        turnOver = false
                        clicked = 0
                    }
                } else if (clicked == 0) {
                    turnOver = false
                }
            }
        }
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//
//        return navController.navigateUp(appBarConfiguration)
//               || super.onSupportNavigateUp()
//   }
}

