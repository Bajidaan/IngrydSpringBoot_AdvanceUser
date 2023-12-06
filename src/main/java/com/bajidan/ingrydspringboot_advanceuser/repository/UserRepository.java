package com.bajidan.ingrydspringboot_advanceuser.repository;

import com.bajidan.ingrydspringboot_advanceuser.model.AdvancedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<AdvancedUser, Long> {
    List<AdvancedUser> findByFullName(String fullName);
    List<AdvancedUser> findByGender(String gender);
    AdvancedUser findByEmail(String email);
}
