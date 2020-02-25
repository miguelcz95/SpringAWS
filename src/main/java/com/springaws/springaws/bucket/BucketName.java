package com.springaws.springaws.bucket;

public enum BucketName {

    PROFILE_IMAGE("springaws-images-upload");

    private final String name;

    BucketName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
