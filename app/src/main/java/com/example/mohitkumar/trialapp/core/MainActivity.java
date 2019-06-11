package com.example.mohitkumar.trialapp.core;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.MainApplication;
import com.example.mohitkumar.trialapp.Util.Constants;
import com.example.mohitkumar.trialapp.Util.PrefManager;
import com.example.mohitkumar.trialapp.Util.Utils;
import com.example.mohitkumar.trialapp.core.Feed.APIClient;
import com.example.mohitkumar.trialapp.core.Feed.GlobalfeedFragment;
import com.example.mohitkumar.trialapp.core.Login.ILoginPresenter;
import com.example.mohitkumar.trialapp.core.Login.LoginActivity;
import com.example.mohitkumar.trialapp.core.Login.LoginPresenter;
import com.example.mohitkumar.trialapp.core.SignUp.SignUpActivity;
import com.example.mohitkumar.trialapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ILoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new LoginPresenter();
        loadFragments();
        PrefManager.putString(Constants.ACCESS_TOKEN, null);
    }

    private void loadFragments() {
        MainFragmentAdapter leagueFragmentAdapter;
        leagueFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        if (Utils.isLoggedIn()) {
         //   binding.signIn.setVisibility(View.GONE);
         //   binding.signUp.setVisibility(View.GONE);

            leagueFragmentAdapter.addFragments(new GlobalfeedFragment(), this.getResources().getString(R.string.global_Feed));
            // leagueFragmentAdapter.addFragments(new StandingsFragment(), this.getResources().getString(R.string.standings_fragment));
            // add again the personal fragment

        } else {
            leagueFragmentAdapter.addFragments(new GlobalfeedFragment(), this.getResources().getString(R.string.global_Feed));
        }
        binding.viewPager.setAdapter(leagueFragmentAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    public void signUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
    public void login(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
