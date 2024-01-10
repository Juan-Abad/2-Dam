package com.juan.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView

class resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        var cv_btnNuevaOperacion = findViewById<CardView>(R.id.cv_btnNuevaOperacion)

        var et_1_texto = intent.extras?.getString("num1").orEmpty()

        var operacion = intent.extras?.getString("operacion").orEmpty()

        var et_2_texto = intent.extras?.getString("num2").orEmpty()

        var tv_numero1 = findViewById<TextView>(R.id.tv_numero1)
        var tv_numero1_2 = findViewById<TextView>(R.id.tv_numero1_2)
        var tv_cv_numero2 = findViewById<TextView>(R.id.tv_cv_numero2)

        var resultado: Int

        when (operacion) {
            "+" -> {
                tv_numero1.setText("Suma")
                resultado = Integer.parseInt(et_1_texto) + Integer.parseInt(et_2_texto)
                tv_cv_numero2.setText("$resultado")
            }

            "-" -> {
                tv_numero1.setText("Resta")
                resultado = Integer.parseInt(et_1_texto) - Integer.parseInt(et_2_texto)
                tv_cv_numero2.setText("$resultado")
            }

            "x" -> {
                tv_numero1.setText("Multiplicación")
                resultado = Integer.parseInt(et_1_texto) * Integer.parseInt(et_2_texto)
                tv_cv_numero2.setText("$resultado")
            }

            "/" -> {
                tv_numero1.setText("División")
                resultado = Integer.parseInt(et_1_texto) / Integer.parseInt(et_2_texto)
                tv_cv_numero2.setText("$resultado")
            }

            "%" -> {
                tv_numero1.setText("Módulo")
                resultado = Integer.parseInt(et_1_texto) % Integer.parseInt(et_2_texto)
                tv_cv_numero2.setText("$resultado")
            }
        }

        tv_numero1_2.setText("$et_1_texto $operacion $et_2_texto")

        cv_btnNuevaOperacion.setOnClickListener{
            var intent = Intent(this, com.juan.myapplication.MainActivity::class.java)
            startActivity(intent)
        }
    }

}