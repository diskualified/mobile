package hu.ait.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hu.ait.minesweeper.databinding.ActivityMainBinding
import android.content.Context
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toggleButton.isChecked = false
        binding.toggleButton.setOnClickListener {
            binding.minesweeperView.switchMode()
        }
        binding.btnReset.setOnClickListener {
            binding.minesweeperView.reset()
        }
    }

    fun showMessage(msg:String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }

    fun resetButton() {
        binding.toggleButton.isChecked = false
    }
}