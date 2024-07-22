package com.example.searchapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.my_customlayout.view.imageView4
import kotlinx.android.synthetic.main.my_customlayout.view.itemlinearlayout
import kotlinx.android.synthetic.main.my_customlayout.view.textView5
import kotlinx.android.synthetic.main.my_customlayout.view.video_title

class myadapter (
    val mContext: Context,
    val videolist : ArrayList<VideoItem>):
    RecyclerView.Adapter<myadapter.Myviewholder>()
{


    class Myviewholder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val videoItem = layoutInflator.inflate(R.layout.my_customlayout, parent, false)

        return Myviewholder(videoItem)
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        var singleVideoItem = videolist.get(position)

        holder.itemView.video_title.text = singleVideoItem.videoTitle
        holder.itemView.textView5.text = "Level : " + singleVideoItem.videoLevel

        Picasso.get()
            .load("https://i.ytimg.com/vi/"+singleVideoItem.videoId+"/sddefault.jpg")
            .resize(640,360)//640x360   *initially 320x180
            .centerCrop()
            .into(holder.itemView.imageView4)

        holder.itemView.itemlinearlayout.setOnClickListener{

            var playVideoIntent = Intent(mContext, MainActivity3::class.java).apply {
                putExtra("VIDEO_ID",""+singleVideoItem.videoId)
                putExtra("VIDEO_TITLE",""+singleVideoItem.videoTitle)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            mContext.startActivity(playVideoIntent)
        }
    }

    override fun getItemCount(): Int {
       return videolist.size
    }


}
