package com.example.dogapi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogapi.databinding.ActivityMainBinding
import com.example.dogapi.model.Datos
import com.example.dogapi.model.DogRespuesta
import com.example.dogapi.recycler.MiAdaptador
import com.example.dogapi.viewModel.DogViewModel
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: DogViewModel by viewModels()


    private lateinit var adaptador: MiAdaptador


    private lateinit var misDatosLocal: Datos


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

        misDatosLocal = Datos("loading", 0, 0, mutableListOf())

        //Configurar RecyclerView
        val mLayout = LinearLayoutManager(this)
        binding.recycler.layoutManager = mLayout
        adaptador = MiAdaptador(DogRespuesta("loading", emptyList()))
        binding.recycler.adapter = adaptador

        //Logica de scroll
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val ultimoVisible = mLayout.findLastVisibleItemPosition()


                val esFinDePagina = ultimoVisible % 10 >= 9 &&
                        (ultimoVisible / 10) == (misDatosLocal.pagina - 1)

                if (esFinDePagina && misDatosLocal.pagina < (misDatosLocal.numPaginas ?: 0)) {
                    com.google.android.material.snackbar.Snackbar.make(
                        binding.root, "Hay más fotos...", com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                    ).setAction("CARGAR MÁS") {
                        viewModel.scrollFotos()
                    }.show()
                }
            }
        })

        //
        viewModel.datos.observe(this) { datosRecibidos ->
            misDatosLocal = datosRecibidos

            when (datosRecibidos.status) {
                "success" -> {

                    val respuestaParaAdapter = DogRespuesta(datosRecibidos.status, datosRecibidos.list)

                    //

                    if (datosRecibidos.pagina == 1) {

                        adaptador.actualizarDatos(respuestaParaAdapter)
                        adaptador.notifyDataSetChanged()
                        binding.recycler.scrollToPosition(0)
                    } else {
                        //Carga de scroll
                        val posicionInicio = (datosRecibidos.pagina - 1) * 10
                        adaptador.actualizarDatos(respuestaParaAdapter)
                        adaptador.notifyItemRangeInserted(posicionInicio, 10)
                    }



                }
                "error" -> {
                    Toast.makeText(this, "Error al cargar imágenes", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //Botón Cargar
        binding.btnCargar.setOnClickListener {
            val raza = binding.txtRaza.text.toString().trim()
            if (raza.isNotEmpty()) {
                viewModel.devuelveFotos(raza)
            }
        }
    }
}