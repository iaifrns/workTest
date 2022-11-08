package com.example.work1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var AuthName: EditText
    private lateinit var Title: EditText
    private lateinit var Description: EditText
    private lateinit var Submit: Button
    private lateinit var ViewBooks: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AuthName= findViewById(R.id.authName)
        Title = findViewById(R.id.title)
        Description= findViewById(R.id.desc)
        Submit = findViewById(R.id.submit)
        ViewBooks= findViewById(R.id.viewBooks)

        dbRef= FirebaseDatabase.getInstance().getReference("books")

        Submit.setOnClickListener {
            SaveBooksData()
        }

        ViewBooks.setOnClickListener{ startActivity(Intent(this, listbooks::class.java)) }


    }

    private fun SaveBooksData() {
        val Aname= AuthName.text.toString()
        val BookTitle= Title.text.toString()
        val Desc= Description.text.toString()

        if (Aname.isEmpty()){
            AuthName.error = "please enter the author name"
        }
        if (BookTitle.isEmpty()){
            Title.error = "please enter the Title"
        }
        if (Desc.isEmpty()){
            Description.error = "please enter the description"
        }
        val BookId = dbRef.push().key!!

        val book = bookModel(BookId, Aname, BookTitle, Desc)

        dbRef.child(BookId).setValue(book)
            .addOnCompleteListener {
                Toast.makeText(this, "The Data have been Inserted", Toast.LENGTH_LONG).show()
                AuthName.text.clear()
                Title.text.clear()
                Description.text.clear()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "An error occured", Toast.LENGTH_LONG).show()
            }
    }
}