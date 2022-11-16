package org.example;

import com.google.gson.reflect.TypeToken;
import org.example.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Type;

public class JSONTest {
    private static String body = "{\"id\":2663,\"name\":\"John\",\"email\":\"john@mail.com\",\"gender\":\"female\",\"status\":\"inactive\"}";
    private static User user = new User();

    @BeforeAll
    public static void init(){
        System.out.println("BeforeAll init() method called");
        user.setName("John");
        user.setId(2663);
        user.setEmail("john@mail.com");
        user.setGender("female");
        user.setStatus("inactive");
    }

    @Test
    void deserializeTest() {
        Type returnType = new TypeToken<User>(){}.getType();
        User u = JSON.deserialize(body, returnType);
        Assertions.assertEquals(u.getId(), 2663);
        Assertions.assertEquals(u.getName(), "John");
        Assertions.assertEquals(u.getEmail(), "john@mail.com");
        Assertions.assertEquals(u.getGender(), "female");
        Assertions.assertEquals(u.getStatus(), "inactive");
    }

    @Test
    void deserializeEmptyTest() {
        Type returnType = new TypeToken<User>(){}.getType();
        User u = JSON.deserialize("", returnType);
        Assertions.assertNull(u);
    }

    @Test
    void deserializeNullTest() {
        Type returnType = new TypeToken<User>(){}.getType();
        User u = JSON.deserialize(null, returnType);
        Assertions.assertNull(u);
    }

    @Test
    void deserializeWrongTypeTest() {
        Type returnType = new TypeToken<WrongBody>(){}.getType();
        WrongBody wb = JSON.deserialize(body, returnType);

        Assertions.assertNull(wb.address);
        Assertions.assertNull(wb.phone);
    }

    @Test
    void serializeTest() {
        String json = JSON.serialize(user);
        Assertions.assertTrue(json.contains("2663"));
        Assertions.assertTrue(json.contains("John"));
        Assertions.assertTrue(json.contains("john@mail.com"));
        Assertions.assertTrue(json.contains("female"));
        Assertions.assertTrue(json.contains("inactive"));
    }

    @Test
    void serializeEmptyTest() {
        String json = JSON.serialize("");
        Assertions.assertFalse(json.contains("2663"));
        Assertions.assertFalse(json.contains("John"));
    }
}


class WrongBody {
    String phone;
    String address;
}