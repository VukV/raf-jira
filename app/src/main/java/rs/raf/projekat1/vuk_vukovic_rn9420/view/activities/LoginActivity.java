package rs.raf.projekat1.vuk_vukovic_rn9420.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.data.LoginData;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {
            start();
            return false;
        });
    }

    private void initView(){
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEmailText);
        loginButton = findViewById(R.id.loginButton);
    }

    private void initListeners(){
        loginButton.setOnClickListener(click -> {
            if(checkLoginInfo()){
                loginToSharedPreferences();
                startJiraActivity();
            }
        });
    }

    private boolean checkLoginInfo(){
        return checkUsername() && checkEmail() && checkPassword();
    }

    private boolean checkUsername(){
        String username = usernameEditText.getText().toString();
        if(username.equals("")){
            Toast.makeText(this, "Username must not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkEmail(){
        String email = emailEditText.getText().toString();
        if(email.equals("")){
            Toast.makeText(this, "Email must not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkPassword(){
        String password = passwordEditText.getText().toString();
        if(password.length() < 5){
            Toast.makeText(this, "Password must be at least 5 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        String username = usernameEditText.getText().toString();
        if(username.startsWith("admin")){
            if(password.equals(LoginData.ADMIN_PASSWORD)){
                return true;
            }
        }
        else {
            if(password.equals(LoginData.USER_PASSWORD)){
                return true;
            }
        }

        Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void loginToSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putString(LoginData.PREF_USERNAME, usernameEditText.getText().toString())
                .putString(LoginData.PREF_EMAIL, emailEditText.getText().toString())
                .apply();
    }

    private void startJiraActivity(){
        finish();
        Intent jiraIntent = new Intent(this, JiraActivity.class);
        startActivity(jiraIntent);
    }

    private void start(){
        if (checkLogin()){
            finish();

            Intent jiraActivity = new Intent(this, JiraActivity.class);
            startActivity(jiraActivity);
        }
        else {
            setContentView(R.layout.activity_login);

            initView();
            initListeners();
        }
    }

    private boolean checkLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        String username = sharedPreferences.getString(LoginData.PREF_USERNAME, "");

        return !username.equals("");
    }
}