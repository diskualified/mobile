package hu.ait.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.simplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Plus.setOnClickListener {
            if (binding.firstnum.text.toString() == "" || binding.secondnum.text.toString() == "") {
                binding.solution.text = "Missing input!"
            }
            else {
                binding.solution.text = "Result: ${
                    binding.firstnum.text.toString().toInt() + binding.secondnum.text.toString().toInt()
                }"
            }
        }

        binding.Minus.setOnClickListener {
            if (binding.firstnum.text.toString() == "" || binding.secondnum.text.toString() == "") {
                binding.solution.text = "Missing input!"
            }
            else {
                binding.solution.text = "Result: ${
                    binding.firstnum.text.toString().toInt() - binding.secondnum.text.toString().toInt()
                }"
            }
        }

    }
}