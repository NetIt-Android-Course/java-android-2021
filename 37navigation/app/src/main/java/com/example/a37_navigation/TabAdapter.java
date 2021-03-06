package com.example.a37_navigation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabAdapter extends FragmentStateAdapter {

    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return DashboardFragment.newInstance();
            case 1: return ProfileFragment.newInstance();
            case 2: return DashboardFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
