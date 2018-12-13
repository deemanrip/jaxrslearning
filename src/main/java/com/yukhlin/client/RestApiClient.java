package com.yukhlin.client;

import com.yukhlin.model.Message;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class RestApiClient {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget baseTarget = client.target("http://localhost:8080/JAX_RS_Learning_war/webapi/");
        WebTarget messagesTarget = baseTarget.path("messages");
        WebTarget singleMessageTarget = messagesTarget.path("{messageId}");

        Message message1 = singleMessageTarget
                .resolveTemplate("messageId", "1")
                .request()
                .get(Message.class);

        Message message2 = singleMessageTarget
                .resolveTemplate("messageId", "2")
                .request()
                .get(Message.class);

        Message newMessage = new Message(4L, "Message from rest client", "restClient");

        Response postResponse = messagesTarget
                .request()
                .post(Entity.json(newMessage));
        Message createdMessage = postResponse.readEntity(Message.class);

        System.out.println(createdMessage.getMessage());
    }
}