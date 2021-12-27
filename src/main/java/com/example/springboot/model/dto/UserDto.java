package com.example.springboot.model.dto;

import com.example.springboot.model.Phone;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "{registration_name_not_empty}")
    private String name;

    @NotBlank(message = "{username_not_empty}")
    private String username;

    @Column(unique = true)
    @Email(message = "{email_is_not_valid}")
    @NotBlank(message = "{email_not_empty}")
    private String email;

    @NotBlank(message = "{registration_password_not_empty}")
    private String password;

    @Min(value = 0)
    @Max(100)
    private int age;

    private List<Phone> phones;
}
