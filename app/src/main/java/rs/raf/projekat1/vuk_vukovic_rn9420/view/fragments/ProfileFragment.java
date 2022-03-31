package rs.raf.projekat1.vuk_vukovic_rn9420.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import rs.raf.projekat1.vuk_vukovic_rn9420.R;
import rs.raf.projekat1.vuk_vukovic_rn9420.data.LoginData;

public class ProfileFragment extends Fragment {

    private TextView usernameTextView;
    private TextView emailTextView;
    private Button logoutButton;

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        readPreferenceInfo();
        initListeners();
    }

    private void initView(View view){
        usernameTextView = view.findViewById(R.id.usernameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        logoutButton = view.findViewById(R.id.logoutButton);
    }

    private void readPreferenceInfo(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginData.PREF, Context.MODE_PRIVATE);
        usernameTextView.setText(sharedPreferences.getString(LoginData.PREF_USERNAME, getString(R.string.unavailable)));
        emailTextView.setText(sharedPreferences.getString(LoginData.PREF_EMAIL, getString(R.string.unavailable)));
    }

    private void initListeners(){
        logoutButton.setOnClickListener(click -> {
            logoutFromPreferences();
            returnToLogin();
        });
    }

    private void logoutFromPreferences(){
        getActivity().getSharedPreferences(LoginData.PREF, Context.MODE_PRIVATE).edit().clear().apply();
    }

    private void returnToLogin(){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerMain, new LoginFragment());
        transaction.commit();
    }
}