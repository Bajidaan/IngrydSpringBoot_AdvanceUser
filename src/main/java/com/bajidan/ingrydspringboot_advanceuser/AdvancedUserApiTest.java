package com.bajidan.ingrydspringboot_advanceuser;

import com.bajidan.ingrydspringboot_advanceuser.model.AdvancedUser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AdvancedUserApiTest {
    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();

        String urlGetAllUsers = "http://localhost:8080/api/v1/users";
        String urlGetById = "http://localhost:8080/api/v1/users/1";
        String urlResource = "http://localhost:8080/api/v1/student/resource/3";
        String urlUpdate = "http://localhost:8080/api/v1/users/2";
        String urlAddUser = "http://localhost:8080/api/v1/users";
        String urlDelete = "http://localhost:8080/api/v1/users/53";

//        AdvancedUser advancedUser = new AdvancedUser("Ayomide Oke",
//                "ayos@gmail.com", "female", "09067348975");
//
//        AdvancedUser user = template.postForEntity(urlAddUser, advancedUser, AdvancedUser.class).getBody();
//        System.out.println(user);

//        List<AdvancedUser> advancedUserList = template.exchange(urlGetAllUsers, HttpMethod.GET,
//                null, new ParameterizedTypeReference<List<AdvancedUser>>() {
//                }).getBody();
//        assert advancedUserList != null;
//        advancedUserList.forEach(System.out::println);

        // Get Student with id 1
//        AdvancedUser aUser = template.getForObject(urlGetById, AdvancedUser.class);
//        System.out.println(aUser);

        //update
        AdvancedUser updateUser = new AdvancedUser("Akinpelu David",
                "akins@gmail.com", "Male", "09067345625");
        template.put(urlUpdate, updateUser);

        //delete
        template.delete(urlDelete);


    }
}
