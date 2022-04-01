package rs.raf.projekat1.vuk_vukovic_rn9420.view.viewpager;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.NewFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.ProfileFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.StatisticsFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.TicketsFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.tickets.DoneFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.tickets.InProgressFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.tickets.ToDoFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_COUNT = 3;
    private static final int FRAGMENT_TODO = 0;
    private static final int FRAGMENT_IN_PROGRESS = 1;
    private static final int FRAGMENT_DONE = 2;
    private Context context;

    public TabsPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case FRAGMENT_TODO: return new ToDoFragment();
            case FRAGMENT_IN_PROGRESS: return new InProgressFragment();
            default: return new DoneFragment();
        }
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case FRAGMENT_TODO: return context.getString(R.string.todo);
            case FRAGMENT_IN_PROGRESS: return context.getString(R.string.in_progress);
            default: return context.getString(R.string.done);
        }
    }


}
