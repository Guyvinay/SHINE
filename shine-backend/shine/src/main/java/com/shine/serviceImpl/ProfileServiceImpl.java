package com.shine.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shine.exception.ProfileNotFoundException;
import com.shine.modal.Profile;
import com.shine.repository.ProfileRepository;
import com.shine.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	
	@Autowired
	private ProfileRepository profileRepository;
	
    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfileById(String profile_id) {
        return profileRepository.findById(profile_id).orElseThrow(
        		()->new ProfileNotFoundException("Profile with id: "+ profile_id+", not found!!!"));
    }

    @Override
    public Profile getProfileByUserName(String user_name) {
        return null;
    }

    @Override
    public List<Profile> getAllProfiles() {
    	List<Profile> profiles = profileRepository.findAll();
    	if(profiles.isEmpty())
    		throw new ProfileNotFoundException("No Profiles found!!!");
        return profiles; 
    }

    @Override
    public Profile updateProfileById(String profile_id, Profile profile) {
        return null;
    }

    @Override
    public String deleteProfileById(String profile_id) {
    	Profile profile = profileRepository.findById(profile_id).orElseThrow(
        		()->new ProfileNotFoundException("Profile with id: "+ profile_id+", not found!!!"));
    	profileRepository.delete(profile);
        return "Profile with id: "+ profile_id+", deleted";
    }
}
