package com.sia.siaandroidapp.java.ui.activities;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.ui.adapters.NavPanelListAdapter;
import com.sia.siaandroidapp.java.ui.fragments.StartFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DrawerActivity extends AppCompatActivity {

    @BindView(R.id.navListView)
    ListView navlist;
    @BindView(R.id.profile_image)
    ImageView profile_image;
    @BindView(R.id.tvProfileName)
    TextView tvProfileName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String type;
    private Unbinder unbinder = null;

    private void fireBoardItemDetails(String id) {

//        getSupportFragmentManager()
//                .beginTransaction()
//                .addToBackStack(BoardDetailsFragment.class.getSimpleName())
//                .replace(R.id.nav_host_fragment, boardDetailsFragment).commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        unbinder = ButterKnife.bind(this);
//        unbinder = ButterKnife.bind(HomeActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.setFocusableInTouchMode(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navlist = findViewById(R.id.navListView);
        NavPanelListAdapter adapter = initNavigationPanel();
        adapter.setOnItemClickListener(position -> {
            if (drawer.isDrawerOpen(GravityCompat.START))
                drawer.closeDrawer(GravityCompat.START);
            switch (position) {
                case 0:
                    getSupportActionBar().setTitle(getString(R.string.boards));
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                            new StartFragment()).commit();
//                    fab.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    getSupportActionBar().setTitle(getString(R.string.menu_inbox));
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                            new StartFragment()).commit();
//                    fab.setVisibility(View.GONE);
                    break;
                case 2:
                    getSupportActionBar().setTitle(getString(R.string.menu_notifications));
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                            new StartFragment()).commit();
//                    fab.setVisibility(View.GONE);

//                    Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
//                    Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
//                    Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                    break;

            }
        });
        String id = getIntent().getStringExtra("id");
        if (savedInstanceState == null && id == null) {
            getSupportActionBar().setTitle(getString(R.string.boards));
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    new StartFragment()).commit();
        }
        if (id != null) {
            fireBoardItemDetails(id);
        }
//        setLocale(PreferenceProcessor.getInstance(this).getStr(MyConfig.MyPrefs.LOCAL_LANG, "en"));


    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

    }

    private NavPanelListAdapter initNavigationPanel() {
        NavPanelListAdapter adapter = new
                NavPanelListAdapter(this);
        navlist.setAdapter(adapter);
        return adapter;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        int count = getSupportFragmentManager().getBackStackEntryCount();
//        if (count > 0) {
//            getSupportFragmentManager().popBackStack(BoardDetailsFragment.class.getSimpleName(),
//                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        }
    }

}
