package com.sia.siaandroidapp.java.ui.adapters.grid;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.appcompat.view.ActionMode;

public class MultiChoiceModeListener implements GridView.MultiChoiceModeListener {

    private static final String TAG = "MultiChoiceModeListener";
    private GridView gridView;

    public MultiChoiceModeListener(GridView gridView) {
        this.gridView = gridView;
    }

    @Override
    public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
        Log.e(TAG, "onItemCheckedStateChanged: " );
    }

    @Override
    public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
        mode.setTitle("Select Items");
        mode.setSubtitle("One item selected");
        Log.e(TAG, "onCreateActionMode: ");
        return true;
    }

    @Override
    public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
        Log.e(TAG, "onPrepareActionMode: " );
        return true;
    }

    @Override
    public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
        Log.e(TAG, "onActionItemClicked: " );
        return true;
    }

    @Override
    public void onDestroyActionMode(android.view.ActionMode mode) {
        int selectCount = gridView.getCheckedItemCount();
        Log.e(TAG, "onDestroyActionMode: " );
        switch (selectCount) {
            case 1:
                mode.setSubtitle("One item selected");
                break;
            default:
                mode.setSubtitle("" + selectCount + " items selected");
                break;
        }

    }
}