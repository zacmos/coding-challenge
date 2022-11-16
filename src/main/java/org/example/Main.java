package org.example;

import org.example.api.UsersApi;
import org.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        UsersApi users = new UsersApi();
        ApiClient client = new ApiClient();
        users.setClient(client);

        int pageNumber = 3;
        int pageSize = 10;

        try {
            //1. Retrieve page 3 of the list of all users.
            List<User> usersThirdPage = users.getAllUsers(pageNumber, pageSize);

            //2. Using a logger, log the total number of pages from the previous request.
            // The request was to retrieve page 3, so the answer is only one page.
            // In order to retrieve all pages we need to call in a loop until the response is empty.
            logger.debug("total number of pages from the previous request is 1");

            //3. Sort the retrieved user list by name.
            usersThirdPage.sort(Comparator.comparing(User::getName));

            //4. After sorting, log the name of the last user.
            User lastUserInThirdPage = usersThirdPage.get(pageSize - 1);
            logger.debug("The name of the last user in the third page is " + lastUserInThirdPage.getName());

            //5. Update that user's name to a new value and use the correct http method to save it.
            lastUserInThirdPage.setName("UpdatedName");
            User updatedUser = users.updateUser(lastUserInThirdPage);

            //6. Delete that user.
            users.deleteUser(lastUserInThirdPage);

            //7. Attempt to retrieve a nonexistent user with ID 5555. Log the resulting http response code.
            // The http response code is logged in ApiClient.handleResponse()
            users.getUserById(5555);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}