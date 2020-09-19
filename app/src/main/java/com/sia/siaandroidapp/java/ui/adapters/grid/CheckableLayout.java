package com.sia.siaandroidapp.java.ui.adapters.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sia.siaandroidapp.R;

public class CheckableLayout extends FrameLayout implements Checkable {

    private boolean mChecked;
    public TextView tvSubjectTitle;
    public ImageView ivSubjectImage;


    public CheckableLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.grid_item_fav_subject,
                this);
        tvSubjectTitle = getRootView().findViewById(R.id.tvSubjectTitle);
        ivSubjectImage = getRootView().findViewById(R.id.ivSubjectImage);

    }

    public boolean isChecked() {
        return mChecked;
    }

    @SuppressWarnings("deprecation")
    public void setChecked(boolean checked) {
        mChecked = checked;
        setBackgroundDrawable(checked ? getResources().getDrawable(
                R.drawable.sia_logo_holo_dark) : null);
    }

    public void toggle() {
        setChecked(!mChecked);
    }

}
