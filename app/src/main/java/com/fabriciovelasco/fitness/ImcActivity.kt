package com.fabriciovelasco.fitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ImcActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editheight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editWeight = findViewById(R.id.edit_imc_weight)
        editheight = findViewById(R.id.edit_imc_height)

        val btnSend: Button = findViewById(R.id.btn_imc_send)
        btnSend.setOnClickListener {
            if(!validate()){
                Toast.makeText(this, R.string.fields_message, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }

    private fun validate(): Boolean {
        //usuário não pode inserir valor nulo
        // usuário não pode inserir valor <= 0
        return (editWeight.text.toString().isNotEmpty() &&
                editheight.text.toString().isNotEmpty() &&
                !editWeight.text.toString().startsWith("0") &&
                !editheight.text.toString().startsWith("0")
                )
    }


}
