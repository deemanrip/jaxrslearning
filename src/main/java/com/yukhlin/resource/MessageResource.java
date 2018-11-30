package com.yukhlin.resource;

import com.yukhlin.model.Message;
import com.yukhlin.resource.bean.MessageFilterBean;
import com.yukhlin.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
        if (filterBean.getYear() > 0) {
            return messageService.getAllMessagesForYear(filterBean.getYear());
        }
        if (filterBean.getOffset() > 0 && filterBean.getPageSize() > 0) {
            return messageService.getAllMessagesPaginated(filterBean.getOffset(), filterBean.getPageSize());
        }

        return messageService.getAllMessages();
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI location = uriInfo.getAbsolutePathBuilder().path(newId).build();

        return Response.created(location).entity(newMessage).build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") Long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void removeMessage(@PathParam("messageId") Long messageId) {
        messageService.removeMessage(messageId);
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") Long messageId, @Context UriInfo uriInfo) {
        Message message = messageService.getMessage(messageId);
        message.addLink(getSelfUri(uriInfo, message), "self");

        return message;
    }

    private String getSelfUri(UriInfo uriInfo, Message message) {
        return uriInfo
                    .getBaseUriBuilder()
                    .path(MessageResource.class)
                    .path(String.valueOf(message.getId()))
                    .build()
                    .toString();
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }
}