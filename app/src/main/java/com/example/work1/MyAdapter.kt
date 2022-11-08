package com.example.work1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.time.Instant

class MyAdapter(private val listbooks: ArrayList<bookModel>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var Select: Button
    //private lateinit var bookId: ArrayList<String>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        //bookId= arrayListOf<String>()
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = listbooks[position]

        holder.Aname.text= currentitem.Aname
        holder.BookTitle.text = currentitem.BookTitle
        holder.Desc.text = currentitem.Desc
        holder.bookId.text = currentitem.bookId
    }

    override fun getItemCount(): Int {
        return listbooks.size
    }
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val Aname: TextView
        val BookTitle: TextView
        val Desc: TextView
        val bookId: TextView

        init {
            Aname = itemView.findViewById(R.id.Aname)
            BookTitle = itemView.findViewById(R.id.Ltitle)
            Desc= itemView.findViewById(R.id.Ldesc)
            bookId= itemView.findViewById(R.id.bookId)
            itemView.setOnClickListener{
                val position: Int = adapterPosition
                val currentitem = listbooks[position]
                Toast.makeText(itemView.context, "you clicked on ${currentitem.bookId}", Toast.LENGTH_LONG).show()

            }
        }

    }

}