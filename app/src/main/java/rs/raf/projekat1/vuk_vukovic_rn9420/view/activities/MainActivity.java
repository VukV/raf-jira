package rs.raf.projekat1.vuk_vukovic_rn9420.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.data.LoginData;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.JiraFragment;
import rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {
            startApp();
            return false;
        });

        setContentView(R.layout.activity_main);
    }

    private void startApp(){
        if(checkLogin()){
            startJiraFragment();
        }
        else {
            startLoginFragment();
        }
    }

    private boolean checkLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences(LoginData.PREF, MODE_PRIVATE);
        String username = sharedPreferences.getString(LoginData.PREF_USERNAME, "");

        return !username.equals("");
    }

    private void startJiraFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainerMain, new JiraFragment());
        transaction.commit();
    }

    private void startLoginFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainerMain, new LoginFragment());
        transaction.commit();
    }
}