package com.example.tutorial5.response;

import com.example.tutorial5.request.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class getResponse {
    boolean success;
    User user;
}
