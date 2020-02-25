package com.springaws.springaws.datastore;

import com.springaws.springaws.profile.UserProfile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FakeUserProfileDataStore {

    List<UserProfile> getUserProfiles();

    void uploadUserProfileImage(UUID userProfileId, MultipartFile file);

    UserProfile getOneProfile(UUID userProfileId);
}
