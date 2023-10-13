package com.example.laventana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.laventana.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater) // Inflate the layout
        setContentView(binding.root) // Use root as the main layout

        // Ir a la pantalla de inicio de sesión
        binding.txtYaTengo.setOnClickListener {
            val intent = android.content.Intent(this, Login::class.java)
            startActivity(intent)
        }

        // Registrar usuario
        binding.btnConfirmar.setOnClickListener {
            // TODO: Registrar usuario
            // Llevar a la pantalla de inicio
            val intent = android.content.Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}