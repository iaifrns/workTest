package com.example.work1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class listbooks : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var bookRecyclerView: RecyclerView
    private lateinit var bookArrayList: ArrayList<bookModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listbooks)

        bookRecyclerView= findViewById(R.id.listBooks)
        bookRecyclerView.layoutManager = LinearLayoutManager(this)
        bookRecyclerView.setHasFixedSize(true)

        bookArrayList = arrayListOf<bookModel>()
        getBookData()
    }

    private fun getBookData() {
        dbref = FirebaseDatabase.getInstance().getReference("books")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (bookSnapshot in snapshot.children){
                        val book= bookSnapshot.getValue(bookModel::class.java)
                        bookArrayList.add(book!!)
                    }
                    bookRecyclerView.adapter = MyAdapter(bookArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}