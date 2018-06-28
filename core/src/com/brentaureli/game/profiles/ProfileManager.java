package com.brentaureli.game.profiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ProfileManager {

    Preferences prefs = Gdx.app.getPreferences("profile");
    private Profile currentProfile;
    private static ProfileManager instance = null;

    public static ProfileManager getInstance() {
        if (instance == null) {
            instance = new ProfileManager();
        }
        return instance;
    }

    public ProfileManager() {
        String profileName = prefs.getString("profileName");
        float profileTime = prefs.getFloat("profileTime");
        if (profileName.isEmpty()) {
            this.currentProfile = createDefaultProfile();
        }
        else {
            this.currentProfile = new Profile(profileName, profileTime);
        }
    }

    public Profile getCurrentProfile() {
        return currentProfile;
    }

    public void updateCurrentProfile(String name, float time) {
        Profile profile = new Profile(name, time);
        prefs.putString("profileName", name);
        prefs.putFloat("profileTime", time);
        prefs.flush();
        this.currentProfile = profile;
    }


    public void updateCurrentProfile(String name) {
        Profile profile = new Profile(name);
        prefs.putString("profileName", name);
        prefs.flush();
        this.currentProfile = profile;
    }

    private Profile createDefaultProfile() {
        prefs.putString("profileName", "Player");
        prefs.putFloat("profileTime", 5.0f);
        prefs.flush();
        Profile profile = new Profile("Player");
        return profile;
    }
}
