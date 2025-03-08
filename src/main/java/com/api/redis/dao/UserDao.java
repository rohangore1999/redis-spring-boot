package com.api.redis.dao;

import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository // so that we can @Autowired(import) it.
public class UserDao {
    // importing RedisTemplate so that we can use their methods.
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // making String var immutable
    private final String KEY = "USER";

    // save user
    public User save(User user){
        redisTemplate.opsForHash().put(KEY, user.getUserId(), user);

        return user;
    }

    // get user
    public User get(String userId){
        // (User) -> Typecasting it to User model.
        // by default .get() return an Object.
        return (User) redisTemplate.opsForHash().get(KEY, userId);
    }

    // find all
    public Map<Object, Object> findAll(){
        // entries(KEY): This retrieves all field-value pairs in the Redis hash associated with the KEY.
        return redisTemplate.opsForHash().entries(KEY);
    }

    // delete
    public void delete(String userId){
        redisTemplate.opsForHash().delete(KEY, userId);
    }
}
