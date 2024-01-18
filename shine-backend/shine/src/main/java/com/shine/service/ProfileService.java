package com.shine.service;

import com.shine.modal.Profile;

import java.util.List;

public interface ProfileService {

    public Profile createProfile(Profile profile);

    public Profile getProfileById(String profile_id);

    public Profile getProfileByUserName(String user_name);

    public List<Profile> getAllProfiles();

    public Profile updateProfileById(String profile_id, Profile profile);

    public String deleteProfileById(String profile_id);
}
