package hu.bme.aut.tictactoedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hu.bme.aut.tictactoedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReset.setOnClickListener {
            binding.ticTacToeView.reset()
        }
    }

    fun showMessage(msg:String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun showText(msg:String) {
        binding.tvMessage.text = msg
    }
}