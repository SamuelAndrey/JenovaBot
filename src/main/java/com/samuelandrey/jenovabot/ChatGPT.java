package com.samuelandrey.jenovabot;

import com.samuelandrey.jenovabot.token.TokenApi;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatGPT {

    public String getChatGPTResponse(String message) throws IOException, URISyntaxException {
    
        TokenApi token = new TokenApi();
        String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
        
        CloseableHttpClient httpClient = HttpClients.createDefault();

        URI uri = new URI(OPENAI_API_URL);
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader("Authorization", "Bearer " + token.getTokenChatGpt());
        httpPost.setHeader("Content-Type", "application/json");

        JsonObject payload = new JsonObject();

        JsonArray messages = new JsonArray();

        JsonObject systemMessage = new JsonObject();
        systemMessage.addProperty("role", "system");
        systemMessage.addProperty("content", "You are a helpful assistant.");
        messages.add(systemMessage);

        JsonObject userMessage = new JsonObject();
        userMessage.addProperty("role", "user");
        userMessage.addProperty("content", message);
        messages.add(userMessage);

        payload.add("messages", messages);
        payload.addProperty("model", "gpt-3.5-turbo");

        String requestBody = new Gson().toJson(payload);
        HttpEntity requestEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();

        String responseString = EntityUtils.toString(responseEntity);

        httpClient.close();
        response.close();

        return responseString;
    }
    
    public String extractMessageFromResponse(String response) {
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        JsonArray replies = jsonResponse.getAsJsonArray("choices");

        if (replies != null && !replies.isJsonNull() && replies.size() > 0) {
            JsonElement replyElement = replies.get(0);
            if (replyElement != null && !replyElement.isJsonNull()) {
                JsonObject reply = replyElement.getAsJsonObject();
                return reply.get("message").getAsJsonObject().get("content").getAsString();
            }
        }

        return null;
    
    }
    
    public String gpt(String userMessage) throws IOException{
        
        try {
            String response = getChatGPTResponse(userMessage);
            String output = extractMessageFromResponse(response);
            
            return output;
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(ChatGPT.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;    
    }
}
