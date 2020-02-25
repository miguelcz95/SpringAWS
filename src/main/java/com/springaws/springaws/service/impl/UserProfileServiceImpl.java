package com.springaws.springaws.service.impl;

import com.springaws.springaws.datastore.FakeUserProfileDataStore;
import com.springaws.springaws.datastore.impl.FakeUserProfileDataStoreImpl;
import com.springaws.springaws.profile.UserProfile;
import com.springaws.springaws.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    FakeUserProfileDataStore fakeUserProfileDataStore;

    @Autowired
    public UserProfileServiceImpl(FakeUserProfileDataStoreImpl fakeUserProfileDataStoreImpl) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStoreImpl;
    }

    @Override
    public List<UserProfile> getAllProfiles() {
        return fakeUserProfileDataStore.getUserProfiles();
    }

    @Override
    public UserProfile getOneProfile(UUID userProfileId) {
        return fakeUserProfileDataStore.getOneProfile(userProfileId);
    }

    @Override
    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        /**
         * 1. Check if image is not empty
         * 2. If file is an image
         * 3. The user exists in our database
         * 4. Grab some metadata from file if any
         * 5. Store the image in s3 and update database with s3 image link
         * **/
        fakeUserProfileDataStore.uploadUserProfileImage(userProfileId, file);
    }
}
