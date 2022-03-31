package rs.raf.projekat1.vuk_vukovic_rn9420.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.data.LoginData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {
            start();
            return false;
        });

        setContentView(R.layout.activity_main);
    }

    private void start(){
        finish();

        if (checkLogin()){
            Intent jiraActivity = new Intent(this, JiraActivity.class);
            startActivity(jiraActivity);
        }
        else {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }

    private boolean checkLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        String username = sharedPreferences.getString(LoginData.PREF_USERNAME, "");

        return !username.equals("");
    }
}