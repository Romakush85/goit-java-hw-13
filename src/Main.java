import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class Main {
    private final static String USERS_URL =  "https://jsonplaceholder.typicode.com/users";
    public static void main(String[] args) throws IOException {
        User defaultUser = new User();
        Address defaultAddress = new Address();
        Company defaultCompany = new Company();
        defaultUser.setCompany(defaultCompany);
        defaultUser.setAddress(defaultAddress);

        // TASK 1
        HttpUtil.postUser(URI.create(USERS_URL), defaultUser);
        User userByID = HttpUtil.getUserByID(1);
        userByID.setUsername("SuperBrat");
        System.out.println(userByID.toString());
        HttpUtil.updateUser(userByID);

        List<User> users = HttpUtil.getAllUsers(URI.create(USERS_URL));
        System.out.println(users.toString());


        User userByUsername = HttpUtil.getUserByUsername("Bret");
        System.out.println(userByUsername.toString());

        HttpUtil.deleteUser(1);
        User userByID1 = HttpUtil.getUserByID(1);
        System.out.println(userByID1.toString());

        // TASK 2
        HttpUtil.getLastPostComments(5);

        // TASK 3
        List<Todo> todos = HttpUtil.getTasks(1);
        System.out.println(Arrays.toString(todos.toArray()));

    }


}
