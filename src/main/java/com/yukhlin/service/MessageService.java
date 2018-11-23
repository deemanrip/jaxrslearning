package com.yukhlin.service;

import com.yukhlin.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageService {

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1L, "First test message", "Deeman"));
        messages.add(new Message(2L, "Hello!", "Ksenia"));
        messages.add(new Message(3L, "How are you doing?", "test_user111"));

        return messages;
    }
}