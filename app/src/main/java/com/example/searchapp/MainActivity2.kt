package com.example.searchapp

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main2.recyle
import kotlinx.android.synthetic.main.activity_main2.textView3
import org.json.JSONArray
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {
    var finalResultsArrayList = ArrayList<VideoItem>()
    lateinit var  Myadapter: myadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var foss = intent.getStringExtra("fossid")
        var lang = intent.getStringExtra("langid")
        var langu = intent.getStringExtra("lang").toString()

        textView3.text = langu + "\nFossid:" + foss + "\nLANG:" + lang

        Myadapter= myadapter(applicationContext,finalResultsArrayList)
        recyle.layoutManager=LinearLayoutManager(this)
        recyle.setHasFixedSize(true)
        recyle.adapter=Myadapter


        val queue = Volley.newRequestQueue(this)
        val url = "https://spoken-tutorial.org/api/get_tutorials/" + foss + "/" + lang


        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            Toast.makeText(applicationContext, "Received server response!", Toast.LENGTH_SHORT)
                .show()

            extractJsonData(response)

        }, {
            Toast.makeText(
                applicationContext,
                "Unable to connect to the server",
                Toast.LENGTH_SHORT
            ).show()
        })
        Toast.makeText(applicationContext, "Contacting server", Toast.LENGTH_SHORT).show()
        queue.add(stringRequest)


    }

        private fun extractJsonData(jsonResponse: String){
            var videosDataArray =JSONArray(jsonResponse)
            var singleVideoJsonObject: JSONObject
            var singleVideoItem: VideoItem
            var i = 0
            var size = videosDataArray.length()

            while (i < size)
            {
                singleVideoJsonObject = videosDataArray.getJSONObject(i)

                singleVideoItem = VideoItem(singleVideoJsonObject.getString("video_id"),singleVideoJsonObject.getString("tutorial_name"),singleVideoJsonObject.getString("tutorial_level"))

                finalResultsArrayList.add(singleVideoItem)

                i++
            }

            println("The parsed videoItems are :")
            finalResultsArrayList.forEach { println(it) }
            Myadapter.notifyDataSetChanged()


        }

}






