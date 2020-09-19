package com.sia.siaandroidapp.java.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sia.siaandroidapp.R
import com.sia.siaandroidapp.java.model.Followee
import de.hdodenhof.circleimageview.CircleImageView

class FollowedAdapter(private val followed: List<Followee>) : RecyclerView.Adapter<FollowedAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var followedName: TextView = itemView.findViewById(R.id.tvFolloweeName)
        var followedGrade: TextView = itemView.findViewById(R.id.tvFolloweeGrade)
        var profileImage: CircleImageView = itemView.findViewById(R.id.profile_image)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.following_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return followed.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val followed: Followee = followed[position]
        holder.followedGrade.text = followed.grade
        holder.followedName.text = followed.name
        holder.profileImage.setImageResource(R.drawable.a_2)
    }
}