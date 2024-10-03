package vn.edu.usth.clothesapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SettingActivity extends AppCompatActivity {
    Button backButton, languageButton, themeButton, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);


        backButton = findViewById(R.id.back_button);
        languageButton = findViewById(R.id.language_button);
        themeButton = findViewById(R.id.theme_button);
        profileButton = findViewById(R.id.profile_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadLanguageFragment();
            }
        });

        themeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadThemeFragment();
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaProfileFragment();
            }
        });
    }

    public void loadLanguageFragment() {

        backButton.setVisibility(View.GONE);
        themeButton.setVisibility(View.GONE);
        languageButton.setVisibility(View.GONE);
        profileButton.setVisibility(View.GONE);
        Fragment languageFragment = new LanguageFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, languageFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loadThemeFragment() {

        backButton.setVisibility(View.GONE);
        themeButton.setVisibility(View.GONE);
        languageButton.setVisibility(View.GONE);
        profileButton.setVisibility(View.GONE);
        Fragment themeFragment = new ThemeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, themeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loaProfileFragment() {

        backButton.setVisibility(View.GONE);
        themeButton.setVisibility(View.GONE);
        languageButton.setVisibility(View.GONE);
        profileButton.setVisibility(View.GONE);
        Fragment profileFragment = new ProfileFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, profileFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showSettingsButtons() {
        backButton.setVisibility(View.VISIBLE);
        languageButton.setVisibility(View.VISIBLE);
        themeButton.setVisibility(View.VISIBLE);
        profileButton.setVisibility(View.VISIBLE);
    }

}