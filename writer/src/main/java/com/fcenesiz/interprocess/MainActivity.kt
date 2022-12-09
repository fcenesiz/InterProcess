package com.fcenesiz.interprocess

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fcenesiz.interprocess.databinding.ActivityMainBinding
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            save()
        }
    }

    private fun save(){
        var file : File?
        val data : String = binding.editTextEnterData.text.toString()
        var fileOutputStream : FileOutputStream?

        try {
            file = filesDir
            fileOutputStream = openFileOutput("my_shared_file.txt", Context.MODE_PRIVATE)
            fileOutputStream.write(data.toByteArray())
            binding.textViewResult.text = "${data}\n saved to \n${file.absolutePath}/my_shared_file.txt"
        }catch (e : FileNotFoundException){
            e.printStackTrace()
        }
    }
}