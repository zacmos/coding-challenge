package org.example.api;

import com.google.gson.reflect.TypeToken;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.example.JSON;
import org.example.model.User;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class UsersApi extends ApiCommon {
    public List<User> getAllUsers() throws Exception {
        return getAllUsers("/users");
    }

    public List<User> getAllUsers(int page, int pageSize) throws Exception {
        return getAllUsers(String.format("/users?page=%s&per_page=%s", page, pageSize));
    }

    private List<User> getAllUsers(String url) throws Exception {
        Type returnType = new TypeToken<Collection<User>>() {
        }.getType();

        Request request = new Request.Builder()
                .url(BASE_URL + url)
                .build();
        return client.execute(request, returnType);
    }

    public User getUserById(long userId) throws Exception {
        Type returnType = new TypeToken<User>() {
        }.getType();

        Request request = new Request.Builder()
                .url(BASE_URL + "/users/" + userId)
                .build();
        return client.execute(request, returnType);
    }

    public User updateUser(User user) throws Exception {
        Type returnType = new TypeToken<User>() {
        }.getType();
        String content = JSON.serialize(user);
        RequestBody reqBody = RequestBody.create(content, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(BASE_URL + "/users/" + user.getId())
                .addHeader("Authorization", getAccessTokenHeader())
                .put(reqBody)
                .build();
        return client.execute(request, returnType);
    }

    public void deleteUser(User user) throws Exception {
        Type returnType = new TypeToken<User>() {
        }.getType();

        Request request = new Request.Builder()
                .url(BASE_URL + "/users/" + user.getId())
                .addHeader("Authorization", getAccessTokenHeader())
                .delete()
                .build();
        client.execute(request, returnType);
    }
}
