package com.example.searchapp

import android.os.Bundle
import android.webkit.WebChromeClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main3.web


class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        var singleVideoId =intent.getStringExtra("VIDEO_ID")
        var singleVideoTitle =intent.getStringExtra("VIDEO_TITLE")

        //Toast.makeText(applicationContext,""+ singleVideoTitle, Toast.LENGTH_SHORT).show()
        //Toast.makeText(applicationContext,""+ singleVideoId, Toast.LENGTH_SHORT).show()

        val video ="<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <body>\n" +
                "    <!-- 1. The <iframe> (and video player) will replace this <div> tag. -->\n" +
                "    <div id=\"player\"></div>\n" +
                "\n" +
                "    <script>\n" +
                "      // 2. This code loads the IFrame Player API code asynchronously.\n" +
                "      var tag = document.createElement('script');\n" +
                "\n" +
                "      tag.src = \"https://www.youtube.com/iframe_api\";\n" +
                "      var firstScriptTag = document.getElementsByTagName('script')[0];\n" +
                "      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
                "\n" +
                "      // 3. This function creates an <iframe> (and YouTube player)\n" +
                "      //    after the API code downloads.\n" +
                "      var player;\n" +
                "      function onYouTubeIframeAPIReady() {\n" +
                "        player = new YT.Player('player', {\n" +
                "          height: '223dp',\n" +
                "          width: '100%',\n" +
                "          videoId: '"+singleVideoId+"',\n" +
                "          playerVars: {\n" +
                "            'playsinline': 1\n" +
                "          },\n" +
                "          events: {\n" +
                "            'onReady': onPlayerReady,\n" +
                "            'onStateChange': onPlayerStateChange\n" +
                "          }\n" +
                "        });\n" +
                "      }\n" +
                "\n" +
                "      // 4. The API will call this function when the video player is ready.\n" +
                "      function onPlayerReady(event) {\n" +
                "        event.target.playVideo();\n" +
                "      }\n" +
                "\n" +
                "      // 5. The API calls this function when the player's state changes.\n" +
                "      //    The function indicates that when playing a video (state=1),\n" +
                "      //    the player should play for six seconds and then stop.\n" +
                "      var done = false;\n" +
                "      function onPlayerStateChange(event) {\n" +
                "        if (event.data == YT.PlayerState.PLAYING && !done) {\n" +
                "          done = true;\n" +
                "        }\n" +
                "      }\n" +
                "      function stopVideo() {\n" +
                "        player.stopVideo();\n" +
                "      }\n" +

                "    </script>\n" +
                "  </body>\n" +
                "</html>"

        val video2 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/tUesv5u5bvA\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"

        web.loadData(video, "text/html", "utf-8")
        web.getSettings().setJavaScriptEnabled(true)
        web.setWebChromeClient(WebChromeClient())


    }
}