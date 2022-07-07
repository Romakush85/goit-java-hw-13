import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

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

    public static List<User> sendGetAllUsers(URI uri) throws IOException {
        HttpGet getRequest = new HttpGet(uri);
        CloseableHttpResponse response = HTTPCLIENT.execute(getRequest);
        HttpEntity entity = response.getEntity();
        String entityAsString = EntityUtils.toString(entity);
        System.out.println(response.getStatusLine());
        List<User> users = MAPPER.readValue(entityAsString, new TypeReference<List<User>>() {});
        return users;
    }

    public static void sendPostUser(URI uri, User user) throws IOException {
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

    public static User sendGetUserByID(int id) throws IOException {
        String URL = String.format("https://jsonplaceholder.typicode.com/users/%d", id);
        HttpGet getRequest = new HttpGet(URL);
        CloseableHttpResponse response = HTTPCLIENT.execute(getRequest);
        HttpEntity entity = response.getEntity();
        String entityAsString = EntityUtils.toString(entity);
        User user = MAPPER.readValue(entityAsString, User.class);
        System.out.println(response.getStatusLine());
        return user;

    }

    public static User sendGetUserByUsername(String username) throws IOException {
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

}
