package com.sia.siaandroidapp.java.ui.fragments

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.sia.siaandroidapp.R
import kotlinx.android.synthetic.main.fragment_landing_page.*


class LandingPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var searchToBar: AnimatedVectorDrawable? = null
    private var barToSearch: AnimatedVectorDrawable? = null
    private val offset = 0f
    private var interp: Interpolator? = null
    private var duration = 0
    private var expanded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_landing_page, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initSearchBar()

        var isShow = true
        var scrollRange = -1
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                // collapseToolBar_home_page_top_progress.title = "Title Collapse"
                tv_tool_bar_title.text = getString(R.string.Home)

                /*    val param = tv_home_page_subjects.layoutParams as ViewGroup.MarginLayoutParams
                    param.setMargins(resources.getDimensionPixelSize(R.dimen._15sdp)
                            ,resources.getDimensionPixelSize(R.dimen._15sdp)
                            ,resources.getDimensionPixelSize(R.dimen._204sdp)
                            ,0
                    )


                    tv_home_page_subjects.layoutParams = param
                    */
                isShow = true
            } else if (isShow) {
                //  collapseToolBar_home_page_top_progress.title = " " //careful there should a space between double quote otherwise it wont work
                isShow = false
                tv_tool_bar_title.text = " "


            }
        })


    }

    fun initSearchBar() {

        searchToBar = context?.getDrawable(R.drawable.anim_search_to_bar) as AnimatedVectorDrawable
        barToSearch = context?.getDrawable(R.drawable.anim_bar_to_search) as AnimatedVectorDrawable
        interp = AnimationUtils.loadInterpolator(activity, android.R.interpolator.linear_out_slow_in)
        duration = resources.getInteger(R.integer.duration_bar)
        // iv is sized to hold the search+bar so when only showing the search icon, translate the
        // whole view left by half the difference to keep it centered
        // iv is sized to hold the search+bar so when only showing the search icon, translate the
        // whole view left by half the difference to keep it centered
        val offset = -71f * resources.displayMetrics.scaledDensity.toInt()
        iv_home_page_search.translationX = offset
    }
/*
    fun animate(view: View?) {
        if (!expanded) {
            iv_home_page_search.setImageDrawable(searchToBar)
            searchToBar!!.start()
            iv_home_page_search.animate().translationX(0f).setDuration(duration.toLong()).setInterpolator(interp)
            text.animate().alpha(1f).setStartDelay((duration - 100).toLong()).setDuration(100).interpolator = interp
        } else {
            iv_home_page_search.setImageDrawable(barToSearch)
            barToSearch!!.start()
            iv_home_page_search.animate().translationX(offset).setDuration(duration.toLong()).setInterpolator(interp)
            text.alpha = 0f
        }
        expanded = !expanded
    }

 */

}