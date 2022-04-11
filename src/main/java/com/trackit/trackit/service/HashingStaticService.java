package com.trackit.trackit.service;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashingStaticService {
    static String hashString(String stringToHash) {
        // Returned sha256 hashed value of the string
        return Hashing.sha256()
                .hashString(stringToHash, StandardCharsets.UTF_8)
                .toString();
    }
}
