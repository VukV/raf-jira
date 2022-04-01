package rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.data.LoginData;

public class LoginFragment extends Fragment {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initListeners();
    }

    private void initView(View view){
        usernameEditText = view.findViewById(R.id.usernameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEmailText);
        loginButton = view.findViewById(R.id.loginButton);
    }

    private void initListeners(){
        loginButton.setOnClickListener(click -> {
            if(checkLoginInfo()){
                loginToSharedPreferences();
                startJiraFragment();
            }
        });
    }

    private boolean checkLoginInfo(){
        return checkUsername() && checkEmail() && checkPassword();
    }

    private boolean checkUsername(){
        String username = usernameEditText.getText().toString();
        if(username.equals("")){
            Toast.makeText(getActivity(), getString(R.string.username_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkEmail(){
        String email = emailEditText.getText().toString();
        if(email.equals("")){
            Toast.makeText(getActivity(), getString(R.string.email_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkPassword(){
        String password = passwordEditText.getText().toString();
        if(password.length() < 5){
            Toast.makeText(getActivity(), getString(R.string.password_length), Toast.LENGTH_SHORT).show();
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

        Toast.makeText(getActivity(), getString(R.string.incorrect_password), Toast.LENGTH_SHORT).show();
        return false;
    }

    private void loginToSharedPreferences(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginData.PREF, Context.MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putString(LoginData.PREF_USERNAME, usernameEditText.getText().toString())
                .putString(LoginData.PREF_EMAIL, emailEditText.getText().toString())
                .apply();
    }

    private void startJiraFragment(){
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerMain, new JiraFragment());
        transaction.commit();
    }
}
