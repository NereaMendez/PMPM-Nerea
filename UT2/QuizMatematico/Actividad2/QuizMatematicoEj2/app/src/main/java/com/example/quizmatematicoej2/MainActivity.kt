package com.example.quizmatematicoej2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lateinit var btNumeroAleatorio: Button
        lateinit var tvNumero: TextView
        lateinit var cb2: CheckBox
        lateinit var cb3: CheckBox
        lateinit var cb5: CheckBox
        lateinit var cb10: CheckBox
        lateinit var cbNinguno: CheckBox
        lateinit var btComprobar: Button
        lateinit var tvResultado: TextView
        lateinit var ivIcono: ImageView

        var numeroActual = 0


        btNumeroAleatorio = findViewById(R.id.btNumeroAleatorio)
        tvNumero = findViewById(R.id.tvNumero)
        cb2 = findViewById(R.id.cb2)
        cb3 = findViewById(R.id.cb3)
        cb5 = findViewById(R.id.cb5)
        cb10 = findViewById(R.id.cb10)
        cbNinguno = findViewById(R.id.cbNinguno)
        btComprobar = findViewById(R.id.btComprobar)
        tvResultado = findViewById(R.id.tvResultado)
        ivIcono = findViewById(R.id.ivIcono)

        //pulsar bt num aleatorio
        btNumeroAleatorio.setOnClickListener {
            numeroActual = (1000..2000).random()
            tvNumero.text = numeroActual.toString()


            //Limpiar...?
            cb2.isChecked = false
            cb3.isChecked = false
            cb5.isChecked = false
            cb10.isChecked = false
            cbNinguno.isChecked = false

            tvResultado.text = ""
            ivIcono.visibility = ImageView.GONE
        }

        //
        cbNinguno.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                cb2.isChecked = false
                cb3.isChecked = false
                cb5.isChecked = false
                cb10.isChecked = false
            }
        }
        val otros = listOf(cb2, cb3, cb5, cb10)
        for (cb in otros) {
            cb.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    cbNinguno.isChecked = false
                }
            }
        }

        //pulsar bt Comprobar
        btComprobar.setOnClickListener {

            if (numeroActual == 0) {
                tvResultado.text = "Genera un número primero"
                ivIcono.visibility = ImageView.GONE
                return@setOnClickListener
            }

            //Comprobar si marco alguna opción
            if (!cb2.isChecked && !cb3.isChecked && !cb5.isChecked && !cb10.isChecked && !cbNinguno.isChecked) {
                tvResultado.text = "Debe escoger al menos una de las opciones"
                ivIcono.visibility = ImageView.GONE
                return@setOnClickListener
            }

            //Comprobar divisibilidad

            val div2 = numeroActual % 2 == 0

            val div3 = numeroActual % 3 == 0
            val div5 = numeroActual % 5 == 0
            val div10 = numeroActual % 10 == 0

            val ninguno = !(div2 || div3 || div5 || div10)

            //ver si la rspta es correcta
            val correcto = (cb2.isChecked == div2) &&
                    (cb3.isChecked == div3) &&
                    (cb5.isChecked == div5) &&
                    (cb10.isChecked == div10) &&
                    (cbNinguno.isChecked == ninguno)

            if (correcto) {
                tvResultado.text = "Correcto"
                ivIcono.setImageResource(R.drawable.icon_ok) // Pon aquí el icono OK
                ivIcono.visibility = ImageView.VISIBLE
            } else {
                tvResultado.text = "Error"
                ivIcono.setImageResource(R.drawable.icon_ko) // Pon aquí el icono KO
                ivIcono.visibility = ImageView.VISIBLE
            }
        }
    }
}