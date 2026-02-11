package com.example.jugandoalosdados

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog ///
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.jugandoalosdados.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var miCorrutina: Job? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lateinit var txtSaldo : TextView
        lateinit var editApuesta : EditText
        lateinit var btLanzar : Button

        var tipoJuego = ""
        var saldo = 100



        //ASOCIAR

        txtSaldo = findViewById(R.id.txtSaldo)
        editApuesta = findViewById(R.id.editApuesta)
        btLanzar = findViewById(R.id.btLanzar)


        //Cargar el spinner según el btn segmentado
        binding.toggleButton.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                val opciones = if (checkedId == R.id.btParImpar) {
                    tipoJuego = "PAR_IMPAR"
                    listOf("PAR", "IMPAR")
                } else {
                    tipoJuego = "MAYOR_MENOR"
                    listOf("Mayor o igual que 7", "Menor o igual que 7")
                }
                val adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
                binding.spinner.adapter = adapter
            }
        }

        //Lanzar
        btLanzar.setOnClickListener {
            val apuestaTexto = editApuesta.text.toString()


            if (tipoJuego == "") {
                Toast.makeText(this, "Debe elegir un tipo de juego", Toast.LENGTH_SHORT).show()
                return@setOnClickListener //importante añadi esto pq si no se sale de la app..
            }

            if (apuestaTexto.isEmpty() || tipoJuego == "") {
                Toast.makeText(this, "Debe introducir una apuesta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (apuestaTexto.isEmpty() || tipoJuego == "") {
                Toast.makeText(this, "Debe elegir una opción de juego", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val importeApuesta = apuestaTexto.toInt()
            if (importeApuesta <= 0 || importeApuesta > saldo) {
                Toast.makeText(this, "La apuesta debe ser menor al saldo disponible", Toast.LENGTH_SHORT).show()
                return@setOnClickListener //importante porque si no se sigue ejecutando lo demás de la app...
            }

            //Corrutina para el GIF
            miCorrutina = lifecycleScope.launch {

                binding.imgResultado.visibility = View.VISIBLE
                binding.imgResultado.setImageResource(R.drawable.dado_imagen_animada_0092)

                delay(3000)

                val d1 = (1..6).random()
                val d2 = (1..6).random()
                val suma = d1 + d2

                binding.txtResultadoDados.text = "Dados: $d1 + $d2 = $suma"


                val eleccion = binding.spinner.selectedItem.toString()
                val gano = if (tipoJuego == "PAR_IMPAR") {
                    (eleccion == "PAR" && suma % 2 == 0) || (eleccion == "IMPAR" && suma % 2 != 0)
                } else {
                    (eleccion == "Mayor o igual que 7" && suma >= 7) || (eleccion == "Menor o igual que 7" && suma < 7)
                }

                if (gano) {
                    saldo += importeApuesta
                    binding.imgResultado.setImageResource(R.drawable.ganar_dados)
                } else {
                    saldo -= importeApuesta
                    binding.imgResultado.setImageResource(R.drawable.perder_dados)
                }

                txtSaldo.text = saldo.toString()


                delay(3000)
                if (saldo <= 0) {
                    binding.imgResultado.setImageResource(R.drawable.bancarrota)
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("LANZANDO LOS DADOS")
                        .setMessage("ESTAS ARRUINADO")

                        .setNegativeButton("Salir del juego") { _, _ -> finish() }
                        .show()
                } else {
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("LANZANDO LOS DADOS")
                        .setMessage("¿Deseas seguir jugando?")
                        .setPositiveButton("Seguir Jugando") { _, _ -> }
                        .setNegativeButton("Salir del juego") { _, _ -> finish() }
                    .show()
                }
            }
        }
    }
}