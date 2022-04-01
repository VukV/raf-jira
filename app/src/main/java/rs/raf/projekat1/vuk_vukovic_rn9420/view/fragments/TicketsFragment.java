package rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.viewpager.TabsPagerAdapter;

public class TicketsFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public TicketsFragment() {
        super(R.layout.fragment_tickets);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initTabs();
    }

    private void initView(View view){
        viewPager = view.findViewById(R.id.tabsViewPager);
        tabLayout = view.findViewById(R.id.ticketsTabLayout);
    }

    private void initTabs(){
        viewPager.setAdapter(new TabsPagerAdapter(getChildFragmentManager(), getActivity()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
