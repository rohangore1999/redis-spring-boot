package com.api.redis.controllers;

import com.api.redis.dao.UserDao;
import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDao userDao;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        // creating random id using uuid
        user.setUserId(UUID.randomUUID().toString());

        return userDao.save(user);
    }

    @GetMapping("/get/{userId}")
    public User getUser(@PathVariable String userId){
        return userDao.get(userId);
    }

    @GetMapping("/get-all")
    public List<User> findAll(){
        Map<Object, Object> allUsers = userDao.findAll();

        // Collection<Object> values: This stores the collection of user objects (without their keys).
        Collection<Object> values = allUsers.values(); // allUsers.values(): Retrieves just the values (i.e., users) from the allUsers map.


        List<User> users = values.stream() // values.stream(): Converts the collection of values into a stream, which allows for functional operations (like mapping and collecting).
                .map(value -> (User) value) // map(value => (User) value): This maps each object in the stream to a User object by casting it to the User class.
                .collect(Collectors.toList()); //.collect(Collectors.toList()): This collects the stream of User objects and converts it into a List<User>.

        return users;
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String userId){ // delete?userId=<id>
        userDao.delete(userId);

        return userId + " Deleted";
    }

}
