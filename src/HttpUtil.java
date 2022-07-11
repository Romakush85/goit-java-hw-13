import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

public class HttpUtil {
    static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(5000)
            .setConnectTimeout(5000)
            .setSocketTimeout(5000)
            .build();

    final static ObjectMapper MAPPER = new ObjectMapper();



    private static final CloseableHttpClient HTTPCLIENT = HttpClientBuilder.create()
            .setDefaultRequestConfig(requestConfig)
            .build();

    public static List<User> getAllUsers(URI uri) throws IOException {
        HttpGet getRequest = new HttpGet(uri);
        CloseableHttpResponse response = HTTPCLIENT.execute(getRequest);
        HttpEntity entity = response.getEntity();
        String entityAsString = EntityUtils.toString(entity);
        System.out.println(response.getStatusLine());
        List<User> users = MAPPER.readValue(entityAsString, new TypeReference<List<User>>() {});
        return users;
    }

    public static void postUser(URI uri, User user) throws IOException {
        HttpPost postRequest = new HttpPost(uri);
        StringEntity json = new StringEntity(MAPPER.writeValueAsString(user), ContentType.APPLICATION_JSON);
        postRequest.setEntity(json);
        CloseableHttpResponse response = HTTPCLIENT.execute(postRequest);
        HttpEntity entity = response.getEntity();
        String entityAsString = EntityUtils.toString(entity);
        User addedUser = MAPPER.readValue(entityAsString, User.class);
        System.out.println(response.getStatusLine());
        System.out.println(addedUser.toString());
    }

    public static User getUserByID(int id) throws IOException {
        String URL = String.format("https://jsonplaceholder.typicode.com/users/%d", id);
        HttpGet getRequest = new HttpGet(URL);
        CloseableHttpResponse response = HTTPCLIENT.execute(getRequest);
        HttpEntity entity = response.getEntity();
        String entityAsString = EntityUtils.toString(entity);
        User user = MAPPER.readValue(entityAsString, User.class);
        System.out.println(response.getStatusLine());
        return user;

    }

    public static User getUserByUsername(String username) throws IOException {
        String URL = String.format("https://jsonplaceholder.typicode.com/users?username=%s", username);
        HttpGet getRequest = new HttpGet(URL);
        CloseableHttpResponse response = HTTPCLIENT.execute(getRequest);
        HttpEntity entity = response.getEntity();
        String entityAsString = EntityUtils.toString(entity);
        List<User> users = MAPPER.readValue(entityAsString, new TypeReference<List<User>>() {});
        System.out.println(response.getStatusLine());
        User userByUsername = users.get(0);
        return userByUsername;
    }


    public static void updateUser(User user) throws IOException {
        String url = String.format("https://jsonplaceholder.typicode.com/users/%d", user.getId());
        HttpPut putRequest = new HttpPut(url);
        StringEntity json = new StringEntity(MAPPER.writeValueAsString(user), ContentType.APPLICATION_JSON);
        putRequest.setEntity(json);
        CloseableHttpResponse response = HTTPCLIENT.execute(putRequest);
        HttpEntity entity = response.getEntity();
        String entityAsString = EntityUtils.toString(entity);
        User updatedUser = MAPPER.readValue(entityAsString, User.class);
        System.out.println(response.getStatusLine());
        System.out.println(updatedUser.toString());
    }

    public static void deleteUser(int id) throws IOException {
        String URL = String.format("https://jsonplaceholder.typicode.com/users/%d", id);
        HttpDelete deleteRequest = new HttpDelete(URL);
        CloseableHttpResponse response = HTTPCLIENT.execute(deleteRequest);
        System.out.println(response.getStatusLine());
    }

    //  TASK 2

    public static void getLastPostComments(int id) throws IOException {
        String postsURL = String.format("https://jsonplaceholder.typicode.com/users/%d/posts", id);
        HttpGet getRequest = new HttpGet(postsURL);
        CloseableHttpResponse response = HTTPCLIENT.execute(getRequest);
        HttpEntity entity = response.getEntity();
        String entityAsString = EntityUtils.toString(entity);
        List<Post> posts = MAPPER.readValue(entityAsString, new TypeReference<List<Post>>() {});
        int lastPostId = posts
                .stream()
                .map(post -> post.getId())
                .max(Integer::compare)
                .get();
        String commentsURL = String.format("https://jsonplaceholder.typicode.com/posts/%d/comments", lastPostId);
        HttpGet getCommRequest = new HttpGet(commentsURL);
        CloseableHttpResponse ComResponse = HTTPCLIENT.execute(getCommRequest);
        HttpEntity CommEntity = ComResponse.getEntity();
        String commEntityAsString = EntityUtils.toString(CommEntity);
        List<Comment> comments = MAPPER.readValue(commEntityAsString, new TypeReference<List<Comment>>() {});
        String jsonName = String.format("./resources/user-%d-post-%d-comments.json", id, lastPostId);
        ObjectWriter writer = MAPPER.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(jsonName), comments);
    }

        // TASK 3

    public static List<Todo> getTasks(int id) throws IOException {
        String URL = String.format("https://jsonplaceholder.typicode.com/users/%d/todos", id);
        HttpGet getRequest = new HttpGet(URL);
        CloseableHttpResponse response = HTTPCLIENT.execute(getRequest);
        HttpEntity entity = response.getEntity();
        String entityAsString = EntityUtils.toString(entity);
        List<Todo> todos = MAPPER.readValue(entityAsString, new TypeReference<List<Todo>>() {});
        List<Todo> uncompletedTasks = todos.stream().filter(todo -> !todo.isCompleted()).toList();
        return uncompletedTasks;
    }
}
