package ru.education.services.stellarburgers.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserModel {

    private String email;
    private String password;
    private String name;
}
