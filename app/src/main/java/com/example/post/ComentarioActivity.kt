package com.example.post

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_comentario.*

class ComentarioActivity : AppCompatActivity() {


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentario)

        val userId = intent.getIntExtra("userId",0)

        val button = findViewById<Button>(R.id.btnComentar)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(txtEmail.text) || TextUtils.isEmpty((txtEmail.text)) || TextUtils.isEmpty(textBody.text)) {
                Toast.makeText(this, "Ingrese en todos los campos",Toast.LENGTH_LONG).show()
            } else {
                val array = ArrayList<String>()
                array.add(txtEmail.text.toString())
                array.add(txtNombre.text.toString())
                array.add(textBody.text.toString())
                replyIntent.putExtra(EXTRA_REPLY, array)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }

        }
    }

    companion object {
        const val EXTRA_REPLY = "REPLY"
    }
}
