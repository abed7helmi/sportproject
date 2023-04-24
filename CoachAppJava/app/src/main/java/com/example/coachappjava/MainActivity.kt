package com.example.coachappjava

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.coachappjava.databinding.ActivityMainBinding
import com.example.coachappjava.service.MqttService

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var name:EditText
    private lateinit var button: Button

    private val mqttClient by lazy {
        MqttService(this.applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        name = findViewById(R.id.nameText)
        val submitButton = findViewById<Button>(R.id.button)

        submitButton.setOnClickListener {
            val name = name.text.toString()
            println("my name is $name")
        }

    }
}