package com.example.unitconvertorthemechange;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup themeRadioGroup;
    RadioButton lightTheme, darkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        themeRadioGroup = findViewById(R.id.themeRadioGroup);
        lightTheme = findViewById(R.id.lightTheme);
        darkTheme = findViewById(R.id.darkTheme);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean("isDarkMode", false);

        if (isDarkMode) {
            darkTheme.setChecked(true);
        } else {
            lightTheme.setChecked(true);
        }

        themeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            boolean useDark = (checkedId == R.id.darkTheme);
            AppCompatDelegate.setDefaultNightMode(
                    useDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isDarkMode", useDark);
            editor.apply();
        });
    }
}
