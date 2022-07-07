import java.io.IOException;
import java.net.URI;
import java.util.List;

public class Main {
    private final static String USERS_URL =  "https://jsonplaceholder.typicode.com/users";
    public static void main(String[] args) throws IOException {
        User defaultUser = new User();
        Address defaultAddress = new Address();
        Company defaultCompany = new Company();
        defaultUser.setCompany(defaultCompany);
        defaultUser.setAddress(defaultAddress);


        HttpUtil.sendPostUser(URI.create(USERS_URL), defaultUser);
        List<User> users = HttpUtil.sendGetAllUsers(URI.create(USERS_URL));
        System.out.println(users.toString());

        User userByID = HttpUtil.sendGetUserByID(2);
        System.out.println(userByID.toString());

        User userByUsername = HttpUtil.sendGetUserByUsername("Bret");
        System.out.println(userByUsername.toString());


    }
}
