package com.example.a37_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
//        setupToolbarAsView(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Georgy");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        setupBottomNav();
//        setupTabs();

//        viewPager = findViewById(R.id.viewPager);
//        viewPager.setAdapter(new TabAdapter(this));
//
//        tabLayout = findViewById(R.id.tabLayout);
//
//        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
//                viewPager.setCurrentItem(tab.getPosition())).attach();
    }

//    private void setupTabs() {
//        tabLayout = findViewById(R.id.tabLayout);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
//
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }

//    private void setupBottomNav() {
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.page_dashboard:
//                    openDashboard();
//                    break;
//                case R.id.page_profile:
//                    openProfile();
//                    break;
//            }
//            return true;
//        });
//        bottomNavigationView.setSelectedItemId(R.id.page_dashboard);
//    }

//    private void openProfile() {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.grpContainer, ProfileFragment.newInstance())
//                .commit();
//    }
//
//    private void openDashboard() {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.grpContainer, DashboardFragment.newInstance())
//                .commit();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_send) {
            Toast.makeText(this, "Sending..", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Up pressed", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "On back", Toast.LENGTH_SHORT).show();
    }

//    private void setupToolbarAsView(Toolbar toolbar) {
//        toolbar.setTitle("Ivan");
//        toolbar.setLogo(R.drawable.ic_snowflake);
//        toolbar.inflateMenu(R.menu.menu_main);
//
//        toolbar.setOnMenuItemClickListener(item -> {
//            if (item.getItemId() == R.id.action_send) {
//                Toast.makeText(this, "Sending..", Toast.LENGTH_SHORT).show();
//            }
//            return true;
//        });
//    }
}