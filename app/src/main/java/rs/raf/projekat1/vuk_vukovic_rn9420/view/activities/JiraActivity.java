package rs.raf.projekat1.vuk_vukovic_rn9420.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.viewpager.MainPagerAdapter;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.viewpager.MainViewPager;

public class JiraActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jira);

        initView();
        initNavigation();
    }

    private void initView(){
        viewPager = findViewById(R.id.mainViewPager);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

        bottomNavigationView = findViewById(R.id.mainNavigation);
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