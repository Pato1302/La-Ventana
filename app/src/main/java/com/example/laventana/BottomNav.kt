package com.example.laventana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.laventana.databinding.ActivityBottomNavBinding

class BottomNav : AppCompatActivity() {
    lateinit var binding: ActivityBottomNavBinding
    lateinit var navController: NavController // Se encarga de manejar los fragmentos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Referencia al NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.contenedor) as NavHostFragment

        // Optener el nav Controller del NavHostFragment
        navController = navHostFragment.navController

        // Ligar la bottomNav con el controller
        setupWithNavController(binding.bNavBarra, navController)
    }

    // Función para pasar al fragmento de mapa
    fun irAMapa() {
        // Cambiar el fragmento

    }

    fun ChangeItem(item: String, lat: Double, long: Double) {
        when (item) {
            "mapa" -> {
                // Pasar la latitud y longitud al fragmento de mapa por shared preferences
                val sharedPreferences = getSharedPreferences("location", MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putString("latitud", lat.toString())
                    putString("longitud", long.toString())
                    commit()
                }
                binding.bNavBarra.selectedItemId = R.id.map2
            }
        }
    }
}