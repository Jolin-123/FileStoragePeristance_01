package com.example.filestorageperistance_01

import android.Manifest
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    // creating something
    private lateinit var textField: EditText
    private lateinit var saveButton: Button
    private lateinit var imageView: ImageView

    private var userTextInputModel = UserTextInputModel(this)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textField = findViewById(R.id.edit_text)
        saveButton = findViewById(R.id.save_text_button)
        imageView = findViewById(R.id.image_view)

        saveButton.setOnClickListener {
            val t = textField.text
            if (t != null) {
                userTextInputModel.saveTextInFile(t.toString())
            }
        }
        textField.setText(userTextInputModel.loadTextFromFile())

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            0
        )
        loadImage()
    }
        private fun loadImage(){
            val storageRoot = Environment.getExternalStorageDirectory()
            val downloadDirectory = File(storageRoot,  "Download")
            val fileList = downloadDirectory.listFiles()
            val myFile = fileList?.get(3)
            if (myFile != null && myFile.isFile){
                val bytes = myFile.readBytes()
                val myBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                this.imageView.setImageBitmap(myBitmap)
            }

        }
    }
