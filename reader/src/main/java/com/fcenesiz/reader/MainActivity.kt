package com.fcenesiz.reader

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fcenesiz.reader.databinding.ActivityMainBinding
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val packageNameOfWriter = "com.fcenesiz.interprocess"

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonReadFile.setOnClickListener {
            loadFile()
        }
    }

    private fun loadFile(){

        try {
            val info = packageManager.getApplicationInfo(packageNameOfWriter, PackageManager.GET_META_DATA)
            val filePath = info.dataDir + "/files/my_shared_file.txt"
            readFile(filePath)
        }catch (e : IOException){
            e.printStackTrace()
        }catch (e : PackageManager.NameNotFoundException){
            e.printStackTrace()
        }


    }

    private fun readFile(filePath: String) {
        val fileInputStream: FileInputStream?
        try {
            fileInputStream = FileInputStream(File(filePath))
            var readData = -1
            val buffer = StringBuffer()
            while (fileInputStream.read().also { readData = it } != -1) {
                buffer.append(readData.toChar())
            }
            binding.textViewRead.text = buffer
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}