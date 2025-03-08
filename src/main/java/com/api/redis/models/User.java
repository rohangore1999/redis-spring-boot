package com.api.redis.models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor // to have constructor with no args (eg: User user = new User())
//public User() {
//}

@AllArgsConstructor // to have constructor with all args (eg: User user = new User(userId, name, email, phone))
// public User(String userId, String name, String phone, String email) {
//    this.userId = userId;
//    this.name = name;
//    this.phone = phone;
//    this.email = email;
//}

@ToString
// To store data in redis should be Serialized
public class User implements Serializable {
    private String userId;
    private String name;
    private String phone;
    private String email;
}
