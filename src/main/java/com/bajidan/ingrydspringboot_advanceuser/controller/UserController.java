package com.bajidan.ingrydspringboot_advanceuser.controller;

import com.bajidan.ingrydspringboot_advanceuser.model.AdvancedUser;
import com.bajidan.ingrydspringboot_advanceuser.model.AdvancedUserResource;
import com.bajidan.ingrydspringboot_advanceuser.repository.CriteriaUserRepository;
import com.bajidan.ingrydspringboot_advanceuser.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CriteriaUserRepository criteriaUserRepository;

    @PostMapping("/users")
    public ResponseEntity<AdvancedUser> saveUser(@RequestBody @Valid AdvancedUser advancedUser){
        return new ResponseEntity<>(userService.saveUser(advancedUser), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdvancedUser>> getAllUsers(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<AdvancedUser> getUserById( @PathVariable long id ){
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @Valid @RequestBody AdvancedUser advancedUser ){
        return ResponseEntity.ok(userService.updateUser(id, advancedUser));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<AdvancedUser> deleteUser(@PathVariable long id ){
        return ResponseEntity.ok(userService.deleteUser(id));

    }

    @GetMapping("user/{id}")
    public ResponseEntity<AdvancedUserResource> getUserResource(@PathVariable long id ){
        AdvancedUserResource advancedUserResource = new AdvancedUserResource();
        AdvancedUser user = getUserById(id).getBody();
        advancedUserResource.setAdvancedUser(user);
        Link idLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(UserController.class).getUserById(id)).withRel("idLink");
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(UserController.class).updateUser(id, user)).withRel("updateLink");
        Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).deleteUser(id)).withRel("deleteLink");
        advancedUserResource.add(idLink, updateLink, deleteLink);
        return new ResponseEntity<>(advancedUserResource, HttpStatus.OK);

    }

    /*
        *************************************************** Criteria End point ************************************************************
     */

    @GetMapping("/criteria/users")
    public ResponseEntity<List<AdvancedUser>> getAllUser(){
        return new ResponseEntity<>(criteriaUserRepository.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/criteria/users/{id}")
    public ResponseEntity<AdvancedUser> getUserByItId(@PathVariable long id ){
        return new ResponseEntity<>(criteriaUserRepository.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/criteria/users/{name}/{gender}")
    public ResponseEntity<List<AdvancedUser>> search(@PathVariable String name, @PathVariable String gender){
        return new ResponseEntity<>(criteriaUserRepository.search(name, gender), HttpStatus.OK);
    }

    /*
     *************************************************** Exercise End point ************************************************************
     */

    @GetMapping("sortAscending")
    public List<AdvancedUser> sortAscending() {
        return userService.getAll().stream()
                .sorted((x, y) -> x.getFullName()
                        .compareTo(y.getFullName())).toList();
    }
    @GetMapping("sortDescending")
    public List<AdvancedUser> sortDescending() {
        return userService.getAll().stream()
                .sorted((y, x) -> x.getFullName()
                        .compareTo(y.getFullName())).toList();
    }
    @GetMapping("getSortedMale")
    public List<AdvancedUser> sortedMaleUser() {
        return userService.getAll().stream()
                .filter(x -> x.getGender().equalsIgnoreCase("male"))
                .sorted((x, y) -> x.getFullName()
                        .compareTo(y.getFullName())).toList();
    }

    @GetMapping("/changeNames")
    public List<AdvancedUser> changeMale() {
        Function<AdvancedUser, AdvancedUser> result = x ->
                new AdvancedUser(x.getId(), x.getFullName().toUpperCase(),
                        x.getEmail(), x.getGender(), x.getPhoneNumber());

        return userService.getAll().stream()
                .map(result)
                .sorted((x, y) -> x.getFullName()
                        .compareTo(y.getFullName())).toList();
    }







}
