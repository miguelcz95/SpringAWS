package com.springaws.springaws.service;

import com.springaws.springaws.profile.UserProfile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UserProfileService {

    List<UserProfile> getAllProfiles();

    void uploadUserProfileImage(UUID userProfileId, MultipartFile file);

    UserProfile getOneProfile(UUID userProfileId);
}
