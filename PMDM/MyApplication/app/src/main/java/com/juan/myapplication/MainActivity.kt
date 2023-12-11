package com.juan.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var et_1 = findViewById<EditText>(R.id.et_1)
        var et_2 = findViewById<EditText>(R.id.et_2)
        var btn_calcular = findViewById<CardView>(R.id.cv_btnCalcular)

        btn_calcular.setOnClickListener {
            var rgSigno = findViewById<RadioGroup>(R.id.rg_operations)
            var rdSignoSelectedId = rgSigno.checkedRadioButtonId
            var rdSignoSelectedRadioButton: RadioButton = rgSigno.findViewById(rdSignoSelectedId)
            var tv_numero1 = findViewById<TextView>(R.id.tv_numero1)
            var tv_numero2 = findViewById<TextView>(R.id.tv_numero2)
            if (et_1.text.matches(Regex("[0-9]{1,}"))) {
                if (et_2.text.matches(Regex("[0-9]{1,}"))) {
                    navigateToResultado(
                        et_1.text.toString(),
                        rdSignoSelectedRadioButton.text.toString(),
                        et_2.text.toString()
                    )
                } else {
                    var cadena = tv_numero1.text
                    tv_numero1.setText("$cadena, ¡zoquete!")
                }
            } else if (et_2.text.matches(Regex("[0-9]{1,}"))) {
                et_2.text = null
                tv_numero2.setText("Y aquí otro, ¡cenutrio!")
            } else {
                var cadena = tv_numero1.text
                tv_numero1.setText("$cadena, ¡zoquete!")
                tv_numero2.setText("Y aquí otro, ¡cenutrio!")
            }
        }
    }

    private fun navigateToResultado(num1c: String, signoc: String, num2c: String) {
        var num1 = num1c
        var signo = signoc
        var num2 = num2c

        var intent = Intent(this, resultado::class.java)
        intent.putExtra("num1", num1)

        intent.putExtra("operacion", signo)

        intent.putExtra("num2", num2)
        startActivity(intent)
    }

}
