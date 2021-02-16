package com.example.a39storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsRepository {

    public static final String FILE_NAME = "UserSettings";
    public static final String SETTING_ENABLE_SPLASH = "enableSplash";
    private final SharedPreferences sharedPreferences;

    private static SettingsRepository instance;

    public static SettingsRepository getInstance(Context context) {
        if(instance == null) instance = new SettingsRepository(context);
        return instance;
    }

    private SettingsRepository(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setSplashEnabled(boolean isEnabled) {
        sharedPreferences
                .edit()
                .putBoolean(SETTING_ENABLE_SPLASH, isEnabled)
                .apply();
    }

    public boolean getSplashEnabled() {
        return sharedPreferences.getBoolean(SETTING_ENABLE_SPLASH, true);
    }
}
