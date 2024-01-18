package com.shine.controller;

import com.shine.modal.Profile;
import com.shine.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping()
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile){
        return new ResponseEntity<Profile>(
                profileService.createProfile(profile),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(value = "/{profile_id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("profile_id") String profile_id){
        return new ResponseEntity<Profile>(
                profileService.getProfileById(profile_id),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(value = "/user_name/{user_name}")
    public ResponseEntity<Profile> getProfileByUserName(@PathVariable("user_name") String user_name){
        return new ResponseEntity<Profile>(
                profileService.getProfileByUserName(user_name),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping()
    public ResponseEntity<List<Profile>> getAllProfiles(){
        return new ResponseEntity<List<Profile>>(
                profileService.getAllProfiles(),
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Profile> updateProfileById(
            @PathVariable("profile_id") String profile_id,
            @RequestBody Profile profile){
        return new ResponseEntity<Profile>(
                profileService.updateProfileById(profile_id, profile),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(value = "/{profile_id}")
    public ResponseEntity<String> deleteProfileById(@PathVariable("profile_id") String profile_id){
        return new ResponseEntity<String>(
                profileService.deleteProfileById(profile_id),
                HttpStatus.ACCEPTED
        );
    }

}
