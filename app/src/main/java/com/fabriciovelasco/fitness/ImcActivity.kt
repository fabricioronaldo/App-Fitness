package com.fabriciovelasco.fitness

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

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
            } else {
                val weight = editWeight.text.toString().toInt()
                val height = editheight.text.toString().toInt()

                val result = calculateImc(weight,height)

                val imcResponseId = imcResponse(result)


                AlertDialog.Builder(this)
                .setTitle(getString(R.string.imc_response, result))
                .setMessage(imcResponseId)
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    TODO("Not yet implemented")
                }
                .create()
                .show()

//                código mais verboso
//                val dialog = AlertDialog.Builder(this)
//
//                dialog.setTitle(getString(R.string.imc_response, result))
//                dialog.setMessage(imcResponseId)
//                dialog.setPositiveButton(android.R.string.ok) { dialog, which ->
//                        TODO("Not yet implemented")
//                }
//
////                  opção mais verbosa
////                dialog.setPositiveButton(android.R.string.ok, object  : DialogInterface.OnClickListener{
////                    override fun onClick(dialog: DialogInterface?, which: Int) {
////                        TODO("Not yet implemented")
////                    }
////                })
//
//                val d = dialog.create()
//                d.show()

            }
        }
    }

    @StringRes
    private fun imcResponse(imc: Double): Int{

        return when {
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
    }
    private fun calculateImc(weight: Int, height: Int): Double {
        return weight / ((height /100.0) * (height / 100.0))
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
