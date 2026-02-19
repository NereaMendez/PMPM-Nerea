package com.example.calculadorav2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.calculadorav2.databinding.ActivityMainBinding
import com.example.calculadorav2.viewModel.CalculadoraViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CalculadoraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //ViewModel
        viewModel = ViewModelProvider(this)[CalculadoraViewModel::class.java]


        viewModel.pantallaLiveData.observe(this) { nuevoValor ->
            //Actualiza el TextView grande
            binding.textView2.text = nuevoValor
        }

        viewModel.recordatorioLiveData.observe(this) { nuevoRecordatorio ->
            //Actualiza el TextView pequeño
            binding.textView.text = nuevoRecordatorio
        }

        viewModel.avisoDobleOperador.observe(this) { mostrar ->
            if (mostrar) {
                Toast.makeText(this, "Primero elige un número", Toast.LENGTH_SHORT).show()
                //
                viewModel.avisoDobleOperadorConsumido()
            }
        }


        binding.bt0.setOnClickListener { viewModel.numeroPulsado("0") }

        binding.bt1.setOnClickListener { viewModel.numeroPulsado("1") }
        binding.bt2.setOnClickListener { viewModel.numeroPulsado("2") }
        binding.bt3.setOnClickListener { viewModel.numeroPulsado("3") }
        binding.bt4.setOnClickListener { viewModel.numeroPulsado("4") }
        binding.bt5.setOnClickListener { viewModel.numeroPulsado("5") }
        binding.bt6.setOnClickListener { viewModel.numeroPulsado("6") }
        binding.bt7.setOnClickListener { viewModel.numeroPulsado("7") }
        binding.bt8.setOnClickListener { viewModel.numeroPulsado("8") }
        binding.bt9.setOnClickListener { viewModel.numeroPulsado("9") }


        binding.btSumar.setOnClickListener { viewModel.operadorPulsado("+") }
        binding.btRestar.setOnClickListener { viewModel.operadorPulsado("-") }
        binding.btMultiplicar.setOnClickListener { viewModel.operadorPulsado("x") }
        binding.btDividir.setOnClickListener { viewModel.operadorPulsado("/") }



        binding.btIgual.setOnClickListener { viewModel.igualPulsado() }
        binding.btBorrar.setOnClickListener { viewModel.clearPulsado() }
    }
}