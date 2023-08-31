package com.example.ind17mod5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.ind17mod5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val divisas = listOf<String>("USD", "Peso Chileno", "Euro")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tipoMoneda.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)
        binding.tipoMoneda2.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)

        initListener()
    }

    private fun initListener() {
        binding.btnConvertir.setOnClickListener {
            val cantidad = binding.moneda.text.toString().toDouble()
            val divisaActual = binding.tipoMoneda.selectedItem.toString()
            val divisaCambio = binding.tipoMoneda2.selectedItem.toString()
            Log.d("funciona", "$cantidad $divisaActual $divisaCambio")
            val resultado = conversorDivisas(cantidad, divisaActual, divisaCambio)

            binding.resultado.text = resultado.toString()

        }
        binding.btnResetear.setOnClickListener{
            limpiar()
        }
    }

    fun conversorDivisas(cantidad: Double, divisaActual: String, divisaCambio: String): Double {
        var resultado = cantidad
        when (divisaActual) {
            "USD" -> {
                if (divisaCambio == "Peso Chileno") {
                    resultado = cantidad * 817

                } else if (divisaCambio == "USD") {
                    resultado = cantidad * 1
                } else if (divisaCambio == "Euro") {
                    resultado = cantidad * 0.90
                }
            }
            "Peso Chileno"-> if(divisaCambio == "Peso Chileno"){
                resultado =  cantidad * 1
            }else if (divisaCambio == "USD"){
                resultado = cantidad * 0.001
            } else if (divisaCambio == "Euro"){
                resultado =  cantidad * 0.001
            }
            "Euro" -> if(divisaCambio == "Peso Chileno"){
                resultado =  cantidad * 910
            }else if (divisaCambio == "USD"){
                resultado = cantidad * 1.11
            } else if (divisaCambio == "Euro"){
                resultado = cantidad * 1
            }

            else -> {
                resultado =   cantidad
            }
        }

        return resultado
    }
    fun limpiar(){
        binding.resultado.text = " "
        binding.moneda.setText("")
    }
}