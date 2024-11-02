package vn.edu.usth.clothesapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import vn.edu.usth.clothesapp.R;
import vn.edu.usth.clothesapp.fragment.LanguageFragment;
import vn.edu.usth.clothesapp.fragment.ProfileFragment;
import vn.edu.usth.clothesapp.fragment.ThemeFragment;

public class SettingActivity extends AppCompatActivity {
    Button languageButton, themeButton, profileButton;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        languageButton = findViewById(R.id.language_button);
        themeButton = findViewById(R.id.theme_button);
        profileButton = findViewById(R.id.profile_button);

        backButton = findViewById(R.id.close_setting_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Directly return to MainActivity
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new LanguageFragment());
            }
        });

        themeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new ThemeFragment());
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new ProfileFragment());
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // Add to back stack so back button works correctly
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        // Check if there are any fragments on the back stack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack(); // Go back to SettingActivity if fragments are in stack
        } else {
            super.onBackPressed(); // Default back behavior if no fragments are in stack
        }
    }
}
