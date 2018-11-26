package com.yukhlin.service;

import com.yukhlin.database.DatabaseClass;
import com.yukhlin.model.Message;

import java.util.*;
import java.util.stream.Collectors;

public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year) {
        Calendar calendar = Calendar.getInstance();

        return messages
                .values()
                .stream()
                .filter(message -> {
            calendar.setTime(message.getDate());
            return calendar.get(Calendar.YEAR) == year;
        })
                .collect(Collectors.toList());
    }

    public List<Message> getAllMessagesPaginated(int offset, int pageSize) {
        List<Message> list = new ArrayList<>(messages.values());
        if (offset + pageSize > list.size()) return Collections.emptyList();
        return list.subList(offset, offset + pageSize);
    }

    public Message getMessage(Long id) {
        return messages.get(id);
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);

        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) return null;
        messages.put(message.getId(), message);

        return message;
    }

    public void removeMessage(Long id) {
        messages.remove(id);
    }
}