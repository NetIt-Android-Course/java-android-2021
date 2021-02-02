package com.example.a36_fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentCallback {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        findViewById(R.id.btnLogin).setOnClickListener(view -> startLogin());
        findViewById(R.id.btnRegister).setOnClickListener(view -> startRegister());
//        findViewById(R.id.btnMain).setOnClickListener(view -> startMain(email, pass));

        startLogin();
    }

    private void startLogin() {
        fragmentManager
                .beginTransaction()
                .addToBackStack("1")
                .replace(R.id.grpContainer, new LoginFragment(), "login1")
                .commit();
    }

    private void startRegister() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.grpContainer, new RegisterFragment(), "register1")
                .commit();
    }

    private void startMain(String email, String pass) {

        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userEmail", email);
        bundle.putString("userPass", pass);
        bundle.putParcelable("asdasd", new Dog());
        mainFragment.setArguments(bundle);

        fragmentManager
                .beginTransaction()
                .addToBackStack("3")
                .replace(R.id.grpContainer, mainFragment, "main1")
                .commit();
    }

    @Override
    public void notifyLoginBtnClicked(String email, String pass) {
        startMain(email, pass);
    }

    @Override
    public void notifyForgotPasswordClicked() {
        //TODO
    }
}