package com.shine.serviceImpl;

import com.shine.modal.Profile;
import org.springframework.stereotype.Service;

import com.shine.service.ProfileService;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Override
    public Profile createProfile(Profile profile) {
        return null;
    }

    @Override
    public Profile getProfileById(String profile_id) {
        return null;
    }

    @Override
    public Profile getProfileByUserName(String user_name) {
        return null;
    }

    @Override
    public List<Profile> getAllProfiles() {
        return null;
    }

    @Override
    public Profile updateProfileById(String profile_id, Profile profile) {
        return null;
    }

    @Override
    public String deleteProfileById(String profile_id) {
        return null;
    }
}
