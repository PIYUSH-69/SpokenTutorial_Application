package com.example.searchapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.spinner1
import kotlinx.android.synthetic.main.activity_main.spinner2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var foss = HashMap<String, Int>()
        foss.put("Java",10)
        foss.put("Cpp",57)
        foss.put("Python",26)
        foss.put("RDBMS",92)

        var lang=HashMap<String,Int>()
        lang.put("English",22)
        lang.put("Hindi",6)
        lang.put("Gujarati",5)
        lang.put("Tamil",18)
        lang.put("Marathi",12)
        lang.put("Kannada",7)


        var fosslang= HashMap<String,ArrayList<String>>()
        var langav= ArrayList<String>()

        langav= arrayListOf("English","Gujarati","Hindi","Kannada")
        fosslang.put("Java",langav)

        langav = arrayListOf("English", "Gujarati", "Hindi","Kannada", "Marathi", "Tamil")
        fosslang.put("Cpp", langav)

        langav = arrayListOf("English", "Hindi")
        fosslang.put("Python", langav)

        langav = arrayListOf("English")
        fosslang.put("RDBMS", langav)

        var fossoptions =foss.keys.toList()
        var langoptions =lang.keys.toMutableList()

        var selectedfoss="NONE"
        var selectedlang="NONE"


        spinner1.adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,fossoptions)
        spinner2.adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,langoptions)

        spinner1.onItemSelectedListener=
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    langoptions.clear()
                    selectedfoss= spinner1.selectedItem.toString()
                    langoptions.addAll(ArrayList(fosslang.get(selectedfoss)))
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }


        spinner2.onItemSelectedListener=
            object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedlang=spinner2.selectedItem.toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        button.setOnClickListener{
            if(selectedfoss=="NONE" && selectedlang=="NONE")
            {
                Toast.makeText(this, "Please select FOSS and Language both", Toast.LENGTH_SHORT).show()
            }
            else
            {
                //Toast.makeText(this, "sheeeeeeeesh", Toast.LENGTH_SHORT).show()
                var fossid=foss.get(selectedfoss)
                var langid=lang.get(selectedlang)

                var intent=Intent(this,MainActivity2::class.java).apply {
                    putExtra("fossid","" +fossid)
                    putExtra("langid","" +langid)
                    putExtra("lang",selectedlang)

                }
                startActivity(intent)

            }
        }



    }
}
