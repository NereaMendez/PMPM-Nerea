package com.example.quizmatematico

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

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



        lateinit var edtNumero : EditText
        lateinit var txtResultado : TextView

        lateinit var rbSi : RadioButton
        lateinit var rbNo : RadioButton

        lateinit var btGenerar : Button
        lateinit var btComprobar : Button

        lateinit var swFondo : Switch

        lateinit var layoutPrincipal : ConstraintLayout

        //Asociar


        edtNumero = findViewById(R.id.edtNumero)
        txtResultado = findViewById(R.id.txtResultado)

        rbSi = findViewById(R.id.rbSi)
        rbNo = findViewById(R.id.rbNo)

        btGenerar = findViewById(R.id.btGenerar)
        btComprobar = findViewById(R.id.btComprobar)

        swFondo = findViewById(R.id.swFondo)
        layoutPrincipal = findViewById(R.id.main)

        //num aleatorio
        btGenerar.setOnClickListener {
            val numero = Random.nextInt(1900, 2501)
            edtNumero.setText(numero.toString())
            txtResultado.text = ""
            rbSi.isChecked = false
            rbNo.isChecked = false
        }

        //comprobar resultados
        btComprobar.setOnClickListener {

            if (!rbSi.isChecked && !rbNo.isChecked) {
                txtResultado.text = "Debe escoger una de las opciones"
                txtResultado.setTextColor(Color.BLUE)
            } else {

                val anio = edtNumero.text.toString().toInt()
                val esBisiesto = (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0)

                if (esBisiesto && rbSi.isChecked || !esBisiesto && rbNo.isChecked) {
                    txtResultado.text = "Correcto"
                    txtResultado.setTextColor(Color.GREEN)
                } else {
                    txtResultado.text = "Error"
                    txtResultado.setTextColor(Color.RED)
                }
            }
        }

        //cambiar fondo:
        swFondo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                layoutPrincipal.setBackgroundColor(Color.YELLOW)
            } else {
                layoutPrincipal.setBackgroundColor(Color.WHITE)
            }
        }
    }
}
