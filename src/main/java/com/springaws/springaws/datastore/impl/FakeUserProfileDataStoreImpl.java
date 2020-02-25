package com.springaws.springaws.datastore.impl;


import com.springaws.springaws.datastore.FakeUserProfileDataStore;
import com.springaws.springaws.profile.UserProfile;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Repository
public class FakeUserProfileDataStoreImpl implements FakeUserProfileDataStore {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "janetjones", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "antoniojunior", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "miguelcz", null));
    }

    @Override
    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }

    @Override
    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        //Do nothing for now
        logger.info("Do nothing for now");
    }

    @Override
    public UserProfile getOneProfile(UUID userProfileId) {

        UserProfile userAux = new UserProfile(userProfileId, "No se encontro", "No se encontro");

        for (UserProfile userProfile : USER_PROFILES) {
            if (userProfile.getUserProfileID().equals(userProfileId)) {
                return userProfile;
            }
        }
        return userAux;
    }
}

