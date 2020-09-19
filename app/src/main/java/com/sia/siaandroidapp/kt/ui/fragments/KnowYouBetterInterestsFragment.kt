package com.sia.siaandroidapp.java.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.flexbox.FlexboxLayoutManager
import com.sia.siaandroidapp.R
import com.sia.siaandroidapp.java.model.Interest
import com.sia.siaandroidapp.java.ui.adapters.InterestAdapter
import kotlinx.android.synthetic.main.fragment_know_you_better_interests.*

class KnowYouBetterInterestsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_know_you_better_interests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerInterests.layoutManager = FlexboxLayoutManager(context)
        val interest = ArrayList<Interest>()

        interest.add(Interest("Football"))
        interest.add(Interest("Singing"))
        interest.add(Interest("Video games"))
        interest.add(Interest("Ping Pong"))
        interest.add(Interest("Shopping"))
        interest.add(Interest("Playing cards"))

        recyclerInterests.adapter = InterestAdapter(interest)
    }
}