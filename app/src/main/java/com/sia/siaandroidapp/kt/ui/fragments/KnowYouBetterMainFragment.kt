package com.sia.siaandroidapp.java.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sia.siaandroidapp.R
import com.sia.siaandroidapp.java.ui.adapters.ViewPageAdapter
import kotlinx.android.synthetic.main.fragment_know_you_better_main.*


class KnowYouBetterMainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_know_you_better_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mImageViewPager = vpKYB4
        val tabLayout = tlKYB4
        tabLayout.setupWithViewPager(mImageViewPager, true)
        mImageViewPager.adapter = ViewPageAdapter(childFragmentManager, listOf(KnowYouBetterFirstLevelsFragment(), KnowYouBetterSecondLevelsFragment(), KnowYouBetterFollowFragment(), KnowYouBetterInterestsFragment()))

        btnNext.setOnClickListener {
            (++mImageViewPager.currentItem) % vpKYB4.childCount + 1
        }

        tvSkip.setOnClickListener {
            (++mImageViewPager.currentItem) % mImageViewPager.childCount + 1
        }
    }
}
