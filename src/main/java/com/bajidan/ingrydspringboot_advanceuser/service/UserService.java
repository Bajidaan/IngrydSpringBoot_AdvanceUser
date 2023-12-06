package com.bajidan.ingrydspringboot_advanceuser.service;


import com.bajidan.ingrydspringboot_advanceuser.repository.UserRepository;
import com.bajidan.ingrydspringboot_advanceuser.model.AdvancedUser;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService { //model -> Repository -> Service -> Controller

    private UserRepository userRepository;


    @CacheEvict(value = {"allUser", "singleUser"}, allEntries = true)
    public AdvancedUser saveUser(AdvancedUser advancedUser){
        return userRepository.save(advancedUser);
    }

    @Cacheable("allUsers")
    public List<AdvancedUser> getAll(){
        return  userRepository.findAll();
    }

    @Cacheable(value = "singleUser", key = "#id")
    public AdvancedUser findUserById(long id){
        return userRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = {"allUser", "singleUser"}, key = "#id")
    public String updateUser(long id, AdvancedUser advancedUser){
        AdvancedUser toUpdate = findUserById(id);

        toUpdate.setFullName(advancedUser.getFullName());
        toUpdate.setEmail(advancedUser.getEmail());
        toUpdate.setGender(advancedUser.getGender());
        toUpdate.setPhoneNumber(advancedUser.getPhoneNumber());
        userRepository.save(toUpdate);

        return "Advanced User successfully updated";
    }

    @CacheEvict(value = {"allUser", "singleUser"}, key = "#id")
    public AdvancedUser deleteUser(long id){
        AdvancedUser user = findUserById(id);
        userRepository.delete(user);
        return  user;
    }


}
