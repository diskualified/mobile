package hu.ait.aithello

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewAnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import hu.ait.aithello.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    // will init later
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val btnTime = findViewById<Button>(R.id.btnShow)

        binding.btnShow.setOnClickListener {
            val currentTime =
                "Hello ${binding.etName.text.toString()}, the current time is: ${Date(System.currentTimeMillis()).toString()}"

            binding.tvDate.text = currentTime
            //Toast.makeText(this, currentTime, Toast.LENGTH_LONG).show()
            Snackbar.make(binding.root, currentTime, Snackbar.LENGTH_LONG).show()
            Snackbar.make(binding.root, currentTime, Snackbar.LENGTH_LONG).setAction("undo",
                {
                    Toast.makeText(this@MainActivity, "undo done", Toast.LENGTH_LONG).show()
                }).show()

            revealCard()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun revealCard() {
        val x = binding.cardView.getRight()
        val y = binding.cardView.getBottom()

        val startRadius = 0
        val endRadius = Math.hypot(binding.cardView.getWidth().toDouble(),
            binding.cardView.getHeight().toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
            binding.cardView,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )

        binding.cardView.setVisibility(View.VISIBLE)
        anim.start()
    }
}