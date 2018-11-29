package com.yukhlin.service;

import com.yukhlin.database.DatabaseClass;
import com.yukhlin.model.Comment;
import com.yukhlin.model.ErrorMessage;
import com.yukhlin.model.Message;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommentService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllComments(Long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<>(comments.values());
    }

    public Comment getComment(Long messageId, Long commentId) {
        ErrorMessage errorMessage =
                new ErrorMessage("Not Found", Response.Status.NOT_FOUND.getStatusCode(), "http://test.com");
        Response response = Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();

        return Optional
                .ofNullable(messages.get(messageId))
                .map(message -> message.getComments().get(commentId))
                .orElseThrow(() -> new NotFoundException(response));
    }

    public Comment addComment(Long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId((long) comments.size() + 1);
        comments.put(comment.getId(), comment);

        return comment;
    }

    public Comment updateComment(Long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <= 0) return null;
        comments.put(comment.getId(), comment);

        return comment;
    }

    public Comment removeComment(Long messageId, Long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }
}
