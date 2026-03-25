package com.example.tabs_fragmentos_menu

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.tabs_fragmentos_menu.databinding.ActivityMainBinding
import com.example.tabs_fragmentos_menu.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val miViewModel: MainViewModel by viewModels()
    private var fragmentoActual: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //configurar toolbar:
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            finish()
        }

        //tabs:
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                cargarFragmentoSeleccionado()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })

        binding.btnGeneraNum.setOnClickListener {
            miViewModel.generarNum()
        }

        miViewModel.datos.observe(this, Observer { datos ->
            binding.txtNumGenerado.text = datos.numGenerado.toString()
            if (datos.numGenerado != 0) {
                cargarFragmentoSeleccionado()
            }
        })
    }

    private fun cargarFragmentoSeleccionado() {
        val num = miViewModel.datos.value?.numGenerado ?: 0
        if (num == 0) {
            Toast.makeText(this, "Genera un número antes", Toast.LENGTH_LONG).show()
            return
        }

        fragmentoActual = when (binding.tabLayout.selectedTabPosition) {
            0 -> FragmentBisiesto()
            1 -> Fragment_Divisible()
            else -> null
        }

        fragmentoActual?.let {
            supportFragmentManager.beginTransaction()
                .replace(binding.fml.id, it)
                .commit()
        }

        binding.materialToolbar.title = when (binding.tabLayout.selectedTabPosition) {
            0 -> "Bisiesto"
            1 -> "Divisible"
            else -> "Fragmentos"
        }
    }
}