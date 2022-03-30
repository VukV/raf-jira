package rs.raf.projekat1.vuk_vukovic_rn9420.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_LOGIN_INFO = "loginInfo";

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
            //TODO
        }
        else {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }

    private boolean checkLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        String username = sharedPreferences.getString(PREF_LOGIN_INFO, "");

        return !username.equals("");
    }
}