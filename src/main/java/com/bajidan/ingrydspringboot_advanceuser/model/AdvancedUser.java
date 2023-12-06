package com.bajidan.ingrydspringboot_advanceuser.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "advanced_user")
public class AdvancedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_name")
    @Length(min = 6, message = "Enter your first and last name")
    private String fullName;

    @Email(message = "Enter a valid email")
    private String email;

    @Length(min = 4)
    private String gender;

    @Pattern(regexp = "[0-9]{11}")
    @Column(name = "phone_number")
    private String phoneNumber;

    public AdvancedUser( String fullName, String email, String gender, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("{%n \"id\": %s%n" +
                        "\"fullName\": \"%s\"%n" +
                        "\"email\": \"%s\"%n" +
                        "\"gender\": \"%s\"%n" +
                        "\"phoneNumber\": \"%s\"%n" +
                        "}", id, fullName,
                email, gender, phoneNumber);
    }
}


