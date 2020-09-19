package com.sia.siaandroidapp.java.ui.fragments.know_u_better;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.pixelcan.inkpageindicator.InkPageIndicator;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.ui.adapters.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KnowUMainFragment extends Fragment {

    private static final String TAG = "KnowUMainFragment";
    @BindView(R.id.view_pager)
    ViewPager viewPagerScreens;
//    @BindView(R.id.pager_indicator)
//    ViewPagerIndicator viewPagerIndicator;

    @BindView(R.id.pager_indicator)
    InkPageIndicator inkPageIndicator;

    private MyPagerAdapter adapterViewPager;

    public static KnowUMainFragment newInstance() {
        Bundle args = new Bundle();
        KnowUMainFragment fragment = new KnowUMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_know_u_better_main,
                container, false);
        ButterKnife.bind(KnowUMainFragment.this, rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");


    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new KnowUBetter1Fragment());
        fragments.add(new KnowUBetter2Fragment());
        fragments.add(new KnowUBetter3Fragment());
        fragments.add(new KnowUBetter4Fragment());


        adapterViewPager = new MyPagerAdapter(fragments,
                getChildFragmentManager());
        viewPagerScreens.setAdapter(adapterViewPager);
        // Attach the page change listener inside the activity
        viewPagerScreens.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getActivity(),
                        "Selected page position: " +
                                position, Toast.LENGTH_SHORT)
                        .show();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here

            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here


            }
        });


//        viewPagerIndicator.setPager(viewPagerScreens);  //pager - the target ViewPager
        inkPageIndicator.setViewPager(viewPagerScreens);  //pager - the target ViewPager


//        viewPagerScreens.setPageTransformer(true, new RotateUpTransformer());
//        viewPagerScreens.setPageTransformer(true, new ZoomOutTranformer());
//        viewPagerScreens.setPageTransformer(true, new FlipHorizontalTransformer());
//        viewPagerScreens.setPageTransformer(true, new FlipVerticalTransformer());
        viewPagerScreens.setPageTransformer(true, new AccordionTransformer());


    }


    @OnClick(R.id.tvSkip)
    public void tvSkip(View v) {
        viewPagerScreens.setCurrentItem(viewPagerScreens.getCurrentItem() + 1,
                true);
    }


    @OnClick(R.id.btnNext)
    public void btnNext(View v) {
        viewPagerScreens.setCurrentItem(viewPagerScreens.getCurrentItem() + 1,
                true);


    }


}
