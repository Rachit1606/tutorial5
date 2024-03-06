package com.example.tutorial5.response;

import com.example.tutorial5.request.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class getAllResponse {
    String message;
    boolean success;
    List<User> Users;
}
