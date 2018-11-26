package com.yukhlin.database;

import com.yukhlin.model.Message;
import com.yukhlin.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<Long, Profile> profiles = new HashMap<>();

    static {
        messages.put(1L, new Message(1, "Test message", "deeman"));
        messages.put(2L, new Message(2, "Hello!", "test_user"));
    }

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<Long, Profile> getProfiles() {
        return profiles;
    }

}