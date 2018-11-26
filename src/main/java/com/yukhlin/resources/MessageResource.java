package com.yukhlin.resources;

import com.yukhlin.model.Message;
import com.yukhlin.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@QueryParam("year") int year,
                                     @QueryParam("offset") int offset,
                                     @QueryParam("pageSize") int pageSize) {
        if (year > 0) {
            return messageService.getAllMessagesForYear(year);
        }
        if (offset > 0 && pageSize > 0) {
            return messageService.getAllMessagesPaginated(offset, pageSize);
        }

        return messageService.getAllMessages();
    }

    @POST
    public Message addMessage(Message message) {
        return messageService.addMessage(message);
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
    public Message getMessage(@PathParam("messageId") Long messageId) {
        return messageService.getMessage(messageId);
    }
}