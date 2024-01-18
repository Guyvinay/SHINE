package com.shine.service;

import java.util.List;

import com.shine.modal.Profile;

public interface ProfileService {

    public Profile createProfile(Profile profile);

    public Profile getProfileById(String profile_id);

    public Profile getProfileByUserName(String user_name);

    public List<Profile> getAllProfiles();

    public Profile updateProfileById(String profile_id, Profile profile);

    public String deleteProfileById(String profile_id);
}
