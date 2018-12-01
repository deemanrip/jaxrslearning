package com.yukhlin.resource;

import com.yukhlin.model.Message;
import com.yukhlin.resource.bean.MessageFilterBean;
import com.yukhlin.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService messageService = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJSONMessages(@BeanParam MessageFilterBean filterBean) {
        return getMessages(filterBean);
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Message> getXmlMessages(@BeanParam MessageFilterBean filterBean) {
        return getMessages(filterBean);
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
        message.addLink(getProfileUri(uriInfo, message), "profile");
        message.addLink(getCommentsUri(uriInfo, message), "comments");

        return message;
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }

    private List<Message> getMessages(MessageFilterBean filterBean) {
        if (filterBean.getYear() > 0) {
            return messageService.getAllMessagesForYear(filterBean.getYear());
        }
        if (filterBean.getOffset() > 0 && filterBean.getPageSize() > 0) {
            return messageService.getAllMessagesPaginated(filterBean.getOffset(), filterBean.getPageSize());
        }

        return messageService.getAllMessages();
    }

    private UriBuilder getCommentsUri(UriInfo uriInfo, Message message) {
        return uriInfo
                .getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .resolveTemplate("messageId", message.getId());
    }

    private UriBuilder getProfileUri(UriInfo uriInfo, Message message) {
        return uriInfo
                .getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor());
    }

    private UriBuilder getSelfUri(UriInfo uriInfo, Message message) {
        return uriInfo
                .getBaseUriBuilder()
                .path(MessageResource.class)
                .path(String.valueOf(message.getId()));
    }
}