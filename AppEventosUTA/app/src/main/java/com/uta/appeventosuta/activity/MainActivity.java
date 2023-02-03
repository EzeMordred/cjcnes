package com.uta.appeventosuta.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appeventosuta.R;
import com.google.android.material.navigation.NavigationView;
import com.uta.appeventosuta.fragment.CategoryFragment;
import com.uta.appeventosuta.fragment.HomeFragment;
import com.uta.appeventosuta.fragment.ProfileFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        centerToolbarTitle(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getColor(R.color.colorPrimary));

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout nav_footer = findViewById(R.id.footerLogOut);

        nav_footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        displaySelectedScreen(R.id.navHome);
    }

    static void centerToolbarTitle(@NonNull final Toolbar toolbar) {
        final CharSequence title = toolbar.getTitle();
        final ArrayList<View> outViews = new ArrayList<>(1);
        toolbar.findViewsWithText(outViews, title, View.FIND_VIEWS_WITH_TEXT);
        if (!outViews.isEmpty()) {
            final TextView titleView = (TextView) outViews.get(0);
            titleView.setGravity(Gravity.CENTER);
            titleView.setTextColor(Color.parseColor("#B41E26"));
            final Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) titleView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            toolbar.requestLayout();
            titleView.setTypeface(null, Typeface.BOLD);
        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    private void displaySelectedScreen(int itemId) {
        Fragment fragment = null;
        //Inicializar el fragmento seleccionado
        switch (itemId) {
            case R.id.navHome:
                fragment = new HomeFragment(this);
                break;
            case R.id.navProfile:
                fragment = new ProfileFragment("1805155569", this);
                break;
            case R.id.navCertificates:
                //fragment = new CategoryFragment();
                break;
            case R.id.navUpcomingEvents:
                //fragment = new PopularProductFragment();
                break;
            case R.id.navCategories:
                fragment = new CategoryFragment(this);
                break;
            /*case R.id.navTest:
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;*/
        }
        //Reemplazar el fragmento
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
            ft.replace(R.id.contentFrame, fragment);
            ft.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);

        toolbar.setTitle(this.getTitle());
    }

}