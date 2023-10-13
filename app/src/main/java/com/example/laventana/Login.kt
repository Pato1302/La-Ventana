package com.example.laventana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.laventana.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater) // Inflate the layout
        setContentView(binding.root) // Use root as the main layout

        // Iniciar sesión
        binding.btnIniciar.setOnClickListener {
            if (binding.edtUsuario.text.toString() == "admin" && binding.edtContrasena.text.toString() == "admin") {
                // Llevar a la pantalla de inicio
                val intent = Intent(this, BottomNav::class.java)
                startActivity(intent)
            } else {
                // Mostrar mensaje de error
                Toast.makeText(this, "Usuario o contraseña incorrectos", android.widget.Toast.LENGTH_SHORT).show()
            }
        }

        // Ir a la pantalla de registro
        binding.txtCrearCuenta.setOnClickListener {
            onClick(it)
        }



    }

    fun onClick(view: View) {
        val intent = Intent(this@Login, Register::class.java)
        startActivity(intent)
    }
}
