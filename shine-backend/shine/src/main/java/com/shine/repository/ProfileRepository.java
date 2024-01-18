package com.shine.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.shine.modal.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}
