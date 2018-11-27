package com.yukhlin.service;

import com.yukhlin.database.DatabaseClass;
import com.yukhlin.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId((long) profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);

        return profile;
    }

    public Profile updateProfile(Profile profile) {
        String profileName = profile.getProfileName();

        if (profileName.isEmpty()) return null;

        Profile currentProfile = profiles.get(profileName);
        profile.setId(currentProfile.getId());
        profiles.put(profileName, profile);

        return profile;
    }

    public void removeProfile(String profileName) {
        profiles.remove(profileName);
    }

}