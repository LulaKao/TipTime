package com.quarterlife.tiptime2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.quarterlife.tiptime2.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding // add this for view binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // add this for view binding
        setContentView(binding.root) // add this for view binding

        // set OnClickListener on calculate button
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    // calculate tip
    private fun calculateTip() {
        if(binding.costOfService.text.isEmpty()){
            Toast.makeText(this, getString(R.string.toast_empty), Toast.LENGTH_SHORT).show()
        } else {
            // get cost of service in EditText, and convert it to double
            val cost = binding.costOfService.text.toString().toDouble()

            // get tip percentage
            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }

            // calculate the tip
            var tip = tipPercentage * cost

            // round up the tip if necessary
            if(binding.roundUpSwitch.isChecked) tip = ceil(tip) // ceil : unconditional carry to integer

            // format the tip
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

            // display tip on the TextView
            binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        }
    }
}