import java.io.IOException;
import java.net.URI;

public class Main {
    private final static String USERS_URL =  "https://jsonplaceholder.typicode.com/users";
    public static void main(String[] args) throws IOException {
        User defaultUser = new User();
        Address defaultAddress = new Address();
        Company defaultCompany = new Company();
        defaultUser.setCompany(defaultCompany);
        defaultUser.setAddress(defaultAddress);


        HttpUtil.sendPostUser(URI.create(USERS_URL), defaultUser);
        User userByID = HttpUtil.sendGetUserByID(1);
        userByID.setUsername("SuperBrat");
        System.out.println(userByID.toString());
        HttpUtil.sendUpdateUser(userByID);

//        List<User> users = HttpUtil.sendGetAllUsers(URI.create(USERS_URL));
//        System.out.println(users.toString());

//
//        User userByUsername = HttpUtil.sendGetUserByUsername("Bret");
//        System.out.println(userByUsername.toString());
//
//        HttpUtil.sendDeleteUser(1);
//        User userByID1 = HttpUtil.sendGetUserByID(1);
//        System.out.println(userByID1.toString());
    }


}
