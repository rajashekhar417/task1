package com.reprototyperstech.transactionapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.reprototyperstech.transactionapp.fregment.FragmentTab1;
import com.reprototyperstech.transactionapp.fregment.FragmentTab2;
import com.reprototyperstech.transactionapp.fregment.FragmentTab3;

public class pagerAdapter extends FragmentStateAdapter {
    int pageCount;
    public pagerAdapter(FragmentActivity fa, int pageCount) {
        super(fa);
        this.pageCount = pageCount;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return switch (position) {
            case 1 -> new FragmentTab2();
            case 2 -> new FragmentTab3();
            default -> new FragmentTab1();
        };
    }

    @Override
    public int getItemCount() {
        return pageCount;
    }
}
