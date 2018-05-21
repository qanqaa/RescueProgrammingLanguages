package com.brentaureli.game.profiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfileManager {

    private Profile currentProfile;
    private static ProfileManager instance = null;
    private List<Profile> profiles = new ArrayList<>();


    public static ProfileManager getInstance() {
        if (instance == null) {
            instance = new ProfileManager();
        }
        return instance;
    }

    public Profile getCurrentProfile() {
        if (currentProfile == null) {
            setCurrentProfile(createDefaultProfile());
        }
        return currentProfile;
    }

    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = getOrCreateProfile(currentProfile);
    }

    private Profile createDefaultProfile() {
        Profile defaultProfile = new Profile("Player");
        profiles.add(defaultProfile);
        return defaultProfile;
    }

    public Profile getOrCreateProfile(Profile profileToCheck) {
        Optional<Profile> profile = profiles.stream().filter(o -> o.getName().equals(profileToCheck.getName())).findFirst();
        if (!profile.isPresent()) {
            profiles.add(profileToCheck);
            return profileToCheck;
        } else {
            Profile existingProfile = profile.get();
            existingProfile.setTimeForQuestion(profileToCheck.getTimeForQuestion());
            //TODO: add photos
            return existingProfile;
        }
    }
}
