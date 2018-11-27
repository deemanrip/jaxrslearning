package com.yukhlin.resources;

import com.yukhlin.model.Comment;
import com.yukhlin.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") Long messageId) {
        return commentService.getAllComments(messageId);
    }

    @POST
    public Comment addComment(@PathParam("messageId") Long messageId, Comment comment) {
        return commentService.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") Long messageId,
                                 @PathParam("commentId") Long commentId,
                                 Comment comment) {
        comment.setId(commentId);
        return commentService.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public Comment deleteComment(@PathParam("messageId") Long messageId,
                                 @PathParam("commentId") Long commentId) {
        return commentService.removeComment(messageId, commentId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") Long messageId,
                                 @PathParam("commentId") Long commentId) {
        return commentService.getComment(messageId, commentId);
    }
}