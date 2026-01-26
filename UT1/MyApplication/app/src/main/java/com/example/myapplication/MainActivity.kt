package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //inicializarlo mas tarde

    lateinit var texto1 : EditText

    lateinit var texto2 : EditText

    lateinit var btSumar : Button

    lateinit var btRestar : Button

    lateinit var btMultiplicar : Button

    lateinit var btDividir : Button

    lateinit var btResultado : Button

    lateinit var textoResultado : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    // Asociar
        texto1 = this.findViewById(R.id.edt1)
        texto2 = this.findViewById(R.id.edt2)

        textoResultado = this.findViewById(R.id.textView4Resultado)

            // botones asociar
        btSumar = this.findViewById(R.id.btSumar)
        btRestar = this.findViewById(R.id.btSumar)
        btMultiplicar = this.findViewById(R.id.btMultiplicar)
        btDividir = this.findViewById(R.id.btDividir)
        btResultado = this.findViewById(R.id.btResultado)

        //ahi ya tenemos el valor1
        var valor1 = texto1.text.toString().toDouble()
        //  y el valor 2 también
        var valor2 = texto2.text.toString().toDouble()


        var resultado : String

        btSumar.setOnClickListener {
            valor1
            textoResultado.text = valor1.toString()

        }


    }
}