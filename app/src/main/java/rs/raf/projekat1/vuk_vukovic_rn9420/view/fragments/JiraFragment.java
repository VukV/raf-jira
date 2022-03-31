package rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.viewpager.MainPagerAdapter;

public class JiraFragment extends Fragment {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    public JiraFragment() {
        super(R.layout.fragment_jira);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initNavigation();
    }

    private void initView(View view){
        viewPager = view.findViewById(R.id.mainViewPager);
        viewPager.setAdapter(new MainPagerAdapter(getChildFragmentManager()));

        bottomNavigationView = view.findViewById(R.id.mainNavigation);
    }

    private void initNavigation(){
        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.menu_statistics: viewPager.setCurrentItem(MainPagerAdapter.FRAGMENT_STATISTICS, false);
                    break;
                case R.id.menu_new: viewPager.setCurrentItem(MainPagerAdapter.FRAGMENT_NEW, false);
                    break;
                case R.id.menu_tickets: viewPager.setCurrentItem(MainPagerAdapter.FRAGMENT_TICKETS, false);
                    break;
                case R.id.menu_profile: viewPager.setCurrentItem(MainPagerAdapter.FRAGMENT_PROFILE, false);
                    break;
            }
            return true;
        });
    }
}
