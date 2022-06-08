package com.example.roombasicscodelab

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class AddCity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var editCapitalView: EditText
    private lateinit var editTesteView: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)

        editWordView = findViewById(R.id.edit_word)
        editCapitalView = findViewById(R.id.edit_capital)
        editTesteView = findViewById(R.id.edit_teste)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener{
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editWordView.text) || TextUtils.isEmpty(editCapitalView.text) || TextUtils.isEmpty(editTesteView.text)){
                setResult(Activity.RESULT_CANCELED,replyIntent)
            }else {
                val city = editWordView.text.toString()
                val capital = editCapitalView.text.toString()
                val teste = editTesteView.text.toString()
                replyIntent.putExtra(EXTRA_CITY, city)
                replyIntent.putExtra(EXTRA_CAPITAL, capital)
                replyIntent.putExtra(EXTRA_TESTE,teste)
                setResult(Activity.RESULT_OK,replyIntent)
            }
            finish()
        }
    }

    companion object {
        //const val EXTRA_REPLY = "com.example-android.wordlistsql.REPLY"
        const val EXTRA_CITY = "city"
        const val EXTRA_CAPITAL = "capital"
        const val EXTRA_TESTE = "teste"
    }
}