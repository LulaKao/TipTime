package com.quarterlife.tiptime2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quarterlife.tiptime2.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // add this for view binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // add this for view binding
        setContentView(binding.root) // add this for view binding

        // set OnClickListener on calculate button
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    // calculate tip
    private fun calculateTip() {
        // get cost of service in EditText, and convert it to double
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()

        // prevent null values
        if(cost == null || cost == 0.0){
            displayTip(0.0)
            return // stop here
        }

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

        // display the formatted tip value on screen
        displayTip(tip)
    }

    // display the formatted tip value on screen
    private fun displayTip(tip: Double) {
        // format the tip
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        // display tip on the TextView
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}