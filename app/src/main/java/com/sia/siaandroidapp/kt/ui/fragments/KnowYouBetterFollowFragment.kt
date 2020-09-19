package com.sia.siaandroidapp.java.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sia.siaandroidapp.R
import com.sia.siaandroidapp.java.model.Followee
import com.sia.siaandroidapp.java.ui.adapters.FollowedAdapter
import kotlinx.android.synthetic.main.fragment_know_you_better_follow.*

class KnowYouBetterFollowFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_know_you_better_follow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerFollowing.layoutManager = LinearLayoutManager(context)
        val followed = ArrayList<Followee>()

        followed.add(Followee("Hassan Abdou Elseoudy", "Grade 12", "somephoto"))
        followed.add(Followee("Mahmoud", "Grade 11", "somephoto"))
        followed.add(Followee("Menna", "Grade 10", "somephoto"))
        followed.add(Followee("Tarek", "Grade 9", "somephoto"))
        followed.add(Followee("Ahmed", "Grade 8", "somephoto"))
        followed.add(Followee("Mohamed", "Grade 7", "somephoto"))
        followed.add(Followee("Nour", "Grade 6", "somephoto"))
        followed.add(Followee("Adham", "Grade 5", "somephoto"))
        followed.add(Followee("Hashem", "Grade 4", "somephoto"))
        followed.add(Followee("Zeyad", "Grade 3", "somephoto"))

        recyclerFollowing.adapter = FollowedAdapter(followed)
    }

}