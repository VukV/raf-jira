package rs.raf.projekat1.vuk_vukovic_rn9420.view.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.NewFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.ProfileFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.StatisticsFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.TicketsFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    public static final int FRAGMENT_COUNT = 4;
    public static final int FRAGMENT_STATISTICS = 0;
    public static final int FRAGMENT_NEW = 1;
    public static final int FRAGMENT_TICKETS = 2;

    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case FRAGMENT_STATISTICS: return new StatisticsFragment();
            case FRAGMENT_NEW: return new NewFragment();
            case FRAGMENT_TICKETS: return new TicketsFragment();
            default: return new ProfileFragment();
        }
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
}
