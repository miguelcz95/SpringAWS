package com.springaws.springaws.controller;

import com.springaws.springaws.profile.UserProfile;
import com.springaws.springaws.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {

    UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/get")
    public List<UserProfile> getAllProfiles() {
        return userProfileService.getAllProfiles();
    }

    @GetMapping("/get/{userProfileId}")
    public UserProfile getOneProfile(@PathVariable("userProfileId") UUID userProfileId) {
        return userProfileService.getOneProfile(userProfileId);
    }

    @PostMapping(
            path = "{userProfileId}/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId, @RequestParam("file") MultipartFile file) {
        userProfileService.uploadUserProfileImage(userProfileId, file);
    }
}
