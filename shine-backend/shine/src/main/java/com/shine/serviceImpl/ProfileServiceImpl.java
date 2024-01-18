package com.shine.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shine.modal.Profile;
import com.shine.service.ProfileService;

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
