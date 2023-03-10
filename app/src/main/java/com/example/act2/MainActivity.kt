package com.example.act2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.act2.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Write a message to the database
            val database = Firebase.database
            val myRef = database.getReference()

        binding.btn.setOnClickListener {

            var name = binding.name.text.toString()
            var id = binding.id.text.toString()
            var age = binding.age.text.toString()
            val person = hashMapOf(
                "name" to name ,
                "age" to age ,
                "id" to id
            )

            myRef.child("person").child("${count++}").setValue(person)
        }


        binding.get.setOnClickListener{

            // Read from the database
            myRef.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue()
                    binding.tv.text = value.toString()
//                    Log.e("aaaa" , value.toString())
                    Toast.makeText(applicationContext, "get data is success", Toast.LENGTH_SHORT).show()

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "get data is failed", Toast.LENGTH_SHORT).show()
                }

            })

        }

    }
}