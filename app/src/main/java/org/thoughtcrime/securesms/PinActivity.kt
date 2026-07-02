package org.thoughtcrime.securesms

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.thoughtcrime.securesms.home.HomeActivity


class PinActivity : AppCompatActivity() {

    private val correctPin = "5859471120741230"
    private var input = ""

    private lateinit var txtPin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        txtPin = findViewById(R.id.txtPin)
        updateUI()
    }

    fun onNumberClick(view: View) {
        val value = (view as Button).text.toString()
        if (input.length < correctPin.length) input += value
        updateUI()
        checkPin()
    }

    fun onDeleteClick(view: View) {
        if (input.isNotEmpty()) input = input.dropLast(1)
        updateUI()
    }

    fun onOkClick(view: View) {
        checkPin()
    }

    private fun updateUI() {
        txtPin.text = "• ".repeat(input.length).trim()
    }

    private fun checkPin() {
        if (input.length == correctPin.length) {
            if (input == correctPin) {
                openApp()
            } else {
                input = ""
                updateUI()
                Toast.makeText(this, "Wrong PIN", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openApp() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {}
}
