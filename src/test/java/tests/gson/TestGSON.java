package tests.gson;

import com.google.gson.Gson;
import test_data.models.LoginCred;

public class TestGSON {

    public static void main(String[] args) {
        LoginCred loginCred = new LoginCred("reobert@sth.com", "123456789");

        Gson gson = new Gson();
        System.out.println(gson.toJson(loginCred));

        String loginCredJSONData = "{\"email\":\"reobert@sth.com\",\"password\":\"123456789\"}";
        LoginCred convertedFromJSON = gson.fromJson(loginCredJSONData, LoginCred.class);
        System.out.println(convertedFromJSON);
    }
}
