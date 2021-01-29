package com.example.controles

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var venta: Boolean = true
    private var acepta: Boolean = false
    private var generoPelicula: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener {group, checkedId ->
            venta = when(checkedId) {
                R.id.comprarRadioButton -> true
                else -> {
                    false
                }
            }
        })

        val adapter = ArrayAdapter.createFromResource(this, R.array.generos, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            acepta = isChecked
        }

       // spinner.onItemSelectedListener {}

        imageButton.setOnClickListener {
            Log.d("[Venta]", venta.toString())
            Log.d("[Acepta]", acepta.toString())
            Log.d("[Genero]", generoPelicula)
            val nombrePelicula: String = textInputLayout.editText?.text.toString()
            if (nombrePelicula.isNotEmpty()) {
                textInputLayout.error = ""
                Log.d("[Pelicula]", nombrePelicula)
            } else {
                textInputLayout.error = "No debe ser vacío"
            }
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        generoPelicula = parent?.getItemAtPosition(position).toString()
    }
}