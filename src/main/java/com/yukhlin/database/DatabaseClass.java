package com.yukhlin.database;

import com.yukhlin.model.Message;
import com.yukhlin.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    static {
        messages.put(1L, new Message(1, "Test message", "deeman"));
        messages.put(2L, new Message(2, "Hello!", "test_user"));

        profiles.put("deeman", new Profile(1L, "deeman", "Dmitry", "Yukhlin"));
    }

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}