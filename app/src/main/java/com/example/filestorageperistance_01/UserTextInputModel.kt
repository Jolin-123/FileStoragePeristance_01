package com.example.filestorageperistance_01

import android.content.Context
import java.io.File

private val TEXT_FILE_NAME = "userInput.txt"

class UserTextInputModel(_context: Context) {
    private var context = _context
    private fun makeFile(): File {
        return File(this.context.filesDir, TEXT_FILE_NAME)
    }
    fun saveTextInFile (s: String){
        var file = makeFile()
        file.delete()
        file.writeText(s)
    }
    fun loadTextFromFile(): String {
        val file = makeFile()
        return if (file.exists()){
            file.readText()
        }
        else{
            ""
        }
    }
}